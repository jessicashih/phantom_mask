package kdan.jessica.phantommask.service;

import kdan.jessica.phantommask.repository.entity.Customer;
import kdan.jessica.phantommask.repository.entity.MaskPriceRecords;
import kdan.jessica.phantommask.repository.entity.Pharmacy;
import kdan.jessica.phantommask.repository.entity.PurchaseRecord;
import kdan.jessica.phantommask.repository.service.CustomerDbService;
import kdan.jessica.phantommask.repository.service.MaskPriceRecordsDbService;
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
public class PurchasesServiceTest {

    @Autowired
    private PurchasesService service;

    @Autowired
    private PharmacyDbService pharmacyDbService;

    @Autowired
    private CustomerDbService customerDbService;

    @Autowired
    private MaskPriceRecordsDbService priceRecordsDbService;

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
        MaskPriceRecords priceRecords = priceRecordsDbService.findByItemNoAndPharmacy(itemNo, pharmacySeqno).orElseThrow(()->new DataNotFoundException());
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
}
