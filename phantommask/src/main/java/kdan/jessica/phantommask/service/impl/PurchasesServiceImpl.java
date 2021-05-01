package kdan.jessica.phantommask.service.impl;

import kdan.jessica.phantommask.repository.entity.Customer;
import kdan.jessica.phantommask.repository.entity.MaskPriceRecord;
import kdan.jessica.phantommask.repository.entity.Pharmacy;
import kdan.jessica.phantommask.repository.entity.PurchaseRecord;
import kdan.jessica.phantommask.repository.service.CustomerDbService;
import kdan.jessica.phantommask.repository.service.MaskPriceRecordDbService;
import kdan.jessica.phantommask.repository.service.PharmacyDbService;
import kdan.jessica.phantommask.repository.service.PurchaseRecordDbService;
import kdan.jessica.phantommask.service.PurchasesService;
import kdan.jessica.phantommask.service.ex.BalanceNotEnough;
import kdan.jessica.phantommask.service.ex.DataNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Slf4j
public class PurchasesServiceImpl implements PurchasesService {

    @Autowired
    private PharmacyDbService pharmacyDbService;

    @Autowired
    private CustomerDbService customerDbService;

    @Autowired
    private MaskPriceRecordDbService priceRecordsDbService;

    @Autowired
    private PurchaseRecordDbService purchaseRecordDbService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String purchasesTransaction(Long pharmacySeqNo, Long customerId, Long itemNo) {
        log.info("PurchasesTransaction Start");

//        1. check the price records is exist
        MaskPriceRecord priceRecords = priceRecordsDbService.findByItemNoAndPharmacy(itemNo, pharmacySeqNo)
                .orElseThrow(() -> new DataNotFoundException("Pharmacy may not have the item, please check the data."));
        BigDecimal price = priceRecords.getPrice();
        log.debug("item price: {}",price);

//        2. check the customer id is correct
        Customer customer = customerDbService.findById(customerId)
                .orElseThrow(() -> new DataNotFoundException("Can't find customer, please check the data."));

//        3. check the customer balance can be charge
        if (customer.getBalance().compareTo(price) == -1) {
            throw new BalanceNotEnough("Customer,"+ customer.getName()+" ,balance is not enough to be charge, please add value. ");
        } else {
//            Charge customer
            log.debug("Customer balance before charge: {}",customer.getBalance());
            BigDecimal balanceAfterCharge = customer.getBalance().subtract(price);
            log.debug("Customer balance after charge: {}",balanceAfterCharge);
            customer.setBalance(balanceAfterCharge);
        }
//        4. Increase the balance of Pharmacy
        Pharmacy pharmacy = pharmacyDbService.findById(pharmacySeqNo)
                .orElseThrow(() -> new DataNotFoundException("The pharmacy is not exist, please check input data."));
        log.debug("Pharmacy balance before charge: {}",customer.getBalance());
        BigDecimal balanceAfterTransaction = pharmacy.getBalance().add(price);
        log.debug("Pharmacy balance after charge: {}",customer.getBalance());
        pharmacy.setBalance(balanceAfterTransaction);

//        6. Add Transaction Records
        PurchaseRecord purchaseRecord=purchaseRecordDbService.insert(priceRecords.getSeqNo(),customerId);
        log.info("PurchasesTransaction End");
        return purchaseRecord.getUuid();
    }
}
