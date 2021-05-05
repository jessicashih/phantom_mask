package kdan.jessica.phantommask.service.impl;

import kdan.jessica.phantommask.model.MaskAmountDetail;
import kdan.jessica.phantommask.model.TransactionRepostRs;
import kdan.jessica.phantommask.repository.entity.Customer;
import kdan.jessica.phantommask.repository.entity.MaskPriceRecord;
import kdan.jessica.phantommask.repository.entity.Pharmacy;
import kdan.jessica.phantommask.repository.entity.PurchaseRecord;
import kdan.jessica.phantommask.repository.entity.relation.TransactionReport;
import kdan.jessica.phantommask.repository.service.*;
import kdan.jessica.phantommask.service.TransactionService;
import kdan.jessica.phantommask.service.ex.BalanceNotEnough;
import kdan.jessica.phantommask.service.ex.DataNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private PharmacyDbService pharmacyDbService;

    @Autowired
    private CustomerDbService customerDbService;

    @Autowired
    private MaskPriceRecordDbService priceRecordsDbService;

    @Autowired
    private PurchaseRecordDbService purchaseRecordDbService;

    @Autowired
    private RelationalQueryService relationalQueryService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String purchasesTransaction(Long pharmacySeqNo, Long customerId, Long itemNo) {
        log.info("PurchasesTransaction Start");

//        1. check the price records is exist
        MaskPriceRecord priceRecords = priceRecordsDbService.findByItemNoAndPharmacy(itemNo, pharmacySeqNo)
                .orElseThrow(() -> new DataNotFoundException("Pharmacy may not have the item, please check the data."));
        BigDecimal price = priceRecords.getPrice();
        log.debug("item price: {}", price);

//        2. check the customer id is correct
        Customer customer = customerDbService.findById(customerId)
                .orElseThrow(() -> new DataNotFoundException("Can't find customer, please check the data."));

//        3. check the customer balance can be charge
        if (customer.getBalance().compareTo(price) == -1) {
            throw new BalanceNotEnough("Customer," + customer.getName() + " ,balance is not enough to be charge, please add value. ");
        } else {
//            Charge customer
            log.debug("Customer balance before charge: {}", customer.getBalance());
            BigDecimal balanceAfterCharge = customer.getBalance().subtract(price);
            log.debug("Customer balance after charge: {}", balanceAfterCharge);
            customer.setBalance(balanceAfterCharge);
        }
//        4. Increase the balance of Pharmacy
        Pharmacy pharmacy = pharmacyDbService.findById(pharmacySeqNo)
                .orElseThrow(() -> new DataNotFoundException("The pharmacy is not exist, please check input data."));
        log.debug("Pharmacy balance before charge: {}", customer.getBalance());
        BigDecimal balanceAfterTransaction = pharmacy.getBalance().add(price);
        log.debug("Pharmacy balance after charge: {}", customer.getBalance());
        pharmacy.setBalance(balanceAfterTransaction);

//        6. Add Transaction Records
        PurchaseRecord purchaseRecord = purchaseRecordDbService.insert(priceRecords.getSeqNo(), customerId);
        log.info("PurchasesTransaction End");
        return purchaseRecord.getUuid();
    }

    @Override
    public TransactionRepostRs findTotalTransaction(String startDateStr, String endDateStr) {
        log.info("findTotalTransaction Start");
        LocalDate startDate = null;
        LocalDate endDate = null;
        if (StringUtils.isBlank(startDateStr)) {
            startDate = LocalDate.now();
        } else {
            startDate = LocalDate.parse(startDateStr, DateTimeFormatter.ISO_DATE);
        }

        if (StringUtils.isBlank(endDateStr)) {
            endDate = LocalDate.now();
        } else {
            endDate = LocalDate.parse(endDateStr, DateTimeFormatter.ISO_DATE);
        }

//        1. Query data
        List<TransactionReport> dbResult = relationalQueryService.findTotalTransaction(startDate, endDate);

//        2. count total amount of masks and dollars value
        List<MaskAmountDetail> maskAmountDetails = new ArrayList<>();
        int totalAmountOfMask = 0;
        BigDecimal totalAmountOfDollarValue = BigDecimal.ZERO;
        for (TransactionReport report : dbResult) {
            MaskAmountDetail detail = new MaskAmountDetail();
            int amountOfMask = report.getAmountOfItem();
            int perPack = report.getItemNumOfPack();
            detail.setItemNo(report.getItemNo());
            detail.setName(report.getItemName());
            detail.setColor(report.getItemColor());
            detail.setItemNumOfPack(perPack);
            detail.setAmountOfItem(amountOfMask);
            detail.setAmountOfItem(report.getAmountOfItem());
            totalAmountOfMask += (amountOfMask * perPack);
            totalAmountOfDollarValue = totalAmountOfDollarValue.add(report.getAmountOfDollar());
            maskAmountDetails.add(detail);
        }

//        3.Convert to response
        TransactionRepostRs response = new TransactionRepostRs();
        response.setTotalAmountOfMask(totalAmountOfMask);
        response.setTotalAmountOfDollarValue(totalAmountOfDollarValue);
        response.setDetail(maskAmountDetails);
        log.info("findTotalTransaction End");
        return response;
    }

}
