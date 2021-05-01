package kdan.jessica.phantommask.service;

import kdan.jessica.phantommask.model.TransactionRepostRs;
import kdan.jessica.phantommask.repository.entity.Customer;
import kdan.jessica.phantommask.repository.entity.MaskPriceRecord;
import kdan.jessica.phantommask.repository.entity.Pharmacy;
import kdan.jessica.phantommask.repository.entity.PurchaseRecord;
import kdan.jessica.phantommask.repository.service.CustomerDbService;
import kdan.jessica.phantommask.repository.service.MaskPriceRecordDbService;
import kdan.jessica.phantommask.repository.service.PharmacyDbService;
import kdan.jessica.phantommask.repository.service.PurchaseRecordDbService;
import kdan.jessica.phantommask.service.ex.DataNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TransactionServiceTest {

    @Autowired
    private TransactionService service;

    @Autowired
    private PharmacyDbService pharmacyDbService;

    @Autowired
    private CustomerDbService customerDbService;

    @Autowired
    private MaskPriceRecordDbService priceRecordsDbService;

    @Autowired
    private PurchaseRecordDbService purchaseRecordDbService;

    @Test
    public void purchasesTransaction() {
        Long pharmacySeqno = 1L;
        Long customerId = 1L;
        Long itemNo = 1L;
        Customer customerB = customerDbService.findById(customerId).orElseThrow(() -> new DataNotFoundException());
        BigDecimal customerBalanceBefore = customerB.getBalance();
        Pharmacy pharmacyB = pharmacyDbService.findById(pharmacySeqno).orElseThrow(() -> new DataNotFoundException());
        BigDecimal pharmacyBalanceBefore = pharmacyB.getBalance();
        MaskPriceRecord priceRecords = priceRecordsDbService.findByItemNoAndPharmacy(itemNo, pharmacySeqno).orElseThrow(()->new DataNotFoundException());
        BigDecimal price = priceRecords.getPrice();
        List<PurchaseRecord> purchaseRecord = purchaseRecordDbService.findAll();
        int purchaseRecordSize = purchaseRecord.size();

        service.purchasesTransaction(pharmacySeqno, customerId, itemNo);

        Customer customerA = customerDbService.findById(customerId).orElseThrow(() -> new DataNotFoundException());
        BigDecimal customerBalanceAfter = customerA.getBalance();
        Pharmacy pharmacy = pharmacyDbService.findById(pharmacySeqno).orElseThrow(() -> new DataNotFoundException());
        BigDecimal pharmacyBalanceAfter = pharmacy.getBalance();
        List<PurchaseRecord> purchaseRecordAfter = purchaseRecordDbService.findAll();

        BigDecimal exceptCustomerBalance = customerBalanceBefore.subtract(price);
        BigDecimal exceptPharmacyBalance = pharmacyBalanceBefore.add(price);

        assertEquals(exceptCustomerBalance,customerBalanceAfter);
        assertEquals(exceptPharmacyBalance,pharmacyBalanceAfter);
        assertEquals(purchaseRecordSize+1,purchaseRecordAfter.size());
    }

    @Test
    public void findTotalTransaction(){
        String startDate = "2021-01-01";
        String endDate = "2021-04-30";
        TransactionRepostRs response = service.findTotalTransaction(startDate, endDate);
        assertEquals(9,response.getTotalAmountOfMask());
        assertEquals(new BigDecimal("51.22"),response.getTotalAmountOfDollarValue());
        assertEquals(2,response.getDetail().size());

    }

}
