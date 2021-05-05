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
import kdan.jessica.phantommask.service.ex.BalanceNotEnough;
import kdan.jessica.phantommask.service.ex.DataNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class TransactionServiceTest extends BaseServiceTest {

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

    /**
     * GIVEN: Original json data
     * WHEN : Pharmacy = "Better You"(balance=777.61) ,
     *          Item = pharmacy sell item[0](AniMask,33.65),
     *          Customer = "Eric Underwood"(balance=952.69)
     * THEN : Pharmacy balance = 811.26(777.61+33.65)
     *          Customer balance = 919.04(952.69-33.65)
     */
    @Test
    public void purchasesTransaction() {
        //Before update data
        //Pharmacy
        String pharmacyName = "Better You";
        Pharmacy pharmacy = pharmacyDao.findAll().stream()
                .filter(p -> StringUtils.equals(pharmacyName, p.getName()))
                .findFirst()
                .orElseThrow();
        Long pharmacySeqno = pharmacy.getSeqNo();
        BigDecimal pharmacyBalanceBefore = pharmacy.getBalance();

        //Item price
        MaskPriceRecord priceRecord = priceRecordsDbService.findByPharmacySeqno(List.of(pharmacySeqno)).stream()
                .findFirst()
                .orElseThrow();
        BigDecimal price = priceRecord.getPrice();
        Long itemNo = priceRecord.getItemNo();

        //Customer
        String customerName = "Eric Underwood";
        Customer customer = customerDao.findAll().stream()
                .filter(c -> StringUtils.equals(customerName, c.getName()))
                .findFirst()
                .orElseThrow();
        BigDecimal customerBalanceBefore = customer.getBalance();
        Long customerId = customer.getCustomerId();

        List<PurchaseRecord> purchaseRecord = purchaseRecordDbService.findAll();
        int purchaseRecordBeforeSize = purchaseRecord.size();

        service.purchasesTransaction(pharmacySeqno, customerId, itemNo);

        BigDecimal customerBalanceAfter = customerDao.findById(customerId)
                .orElseThrow()
                .getBalance();
        BigDecimal pharmacyBalanceAfter = pharmacyDbService.findById(pharmacySeqno)
                .orElseThrow()
                .getBalance();
        List<PurchaseRecord> purchaseRecordAfter = purchaseRecordDbService.findAll();

        BigDecimal exceptCustomerBalance = customerBalanceBefore.subtract(price);
        BigDecimal exceptPharmacyBalance = pharmacyBalanceBefore.add(price);

        assertEquals(exceptCustomerBalance, customerBalanceAfter);
        assertEquals(exceptPharmacyBalance, pharmacyBalanceAfter);
        assertEquals(purchaseRecordBeforeSize + 1, purchaseRecordAfter.size());
    }

    /**
     * GIVEN: Insert customer whose balance is lower than mask price
     *          Jessica(balance=30.0)
     * WHEN : Pharmacy = "Better You"(balance=777.61) ,
     *          Item = pharmacy sell item[0](AniMask,33.65),
     *          Customer = "Jessica"
     * Then : Throw exception BalanceNotEnough
     *          and pharmacy balance won't increase
     *          and customer balance won't decrease
     */
    @Test
    public void purchasesTransactionError() {
        //Before update data
        //Pharmacy
        String pharmacyName = "Better You";
        Pharmacy pharmacy = pharmacyDao.findAll().stream()
                .filter(p -> StringUtils.equals(pharmacyName, p.getName()))
                .findFirst()
                .orElseThrow();
        Long pharmacySeqno = pharmacy.getSeqNo();
        BigDecimal pharmacyBalanceBefore = pharmacy.getBalance();

        //Item price
        MaskPriceRecord priceRecord = priceRecordsDbService.findByPharmacySeqno(List.of(pharmacySeqno)).stream()
                .findFirst()
                .orElseThrow();
        BigDecimal price = priceRecord.getPrice();
        Long itemNo = priceRecord.getItemNo();

        //Customer
        String customerName = "Jessica";
        BigDecimal customerBalanceBefore = new BigDecimal("30.0");
        Customer customer = new Customer();
        customer.setName(customerName);
        customer.setBalance(customerBalanceBefore);
        customer = customerDao.save(customer);
        Long customerId = customer.getCustomerId();

        List<PurchaseRecord> purchaseRecord = purchaseRecordDbService.findAll();
        int purchaseRecordBeforeSize = purchaseRecord.size();

        Exception e = assertThrows(BalanceNotEnough.class,()->service.purchasesTransaction(pharmacySeqno, customerId, itemNo));
        assertEquals("Customer," +customerName + " ,balance is not enough to be charge, please add value. ",
                e.getMessage(),
                "Exception message not match.");
        BigDecimal customerBalanceAfter = customerDao.findById(customerId)
                .orElseThrow()
                .getBalance();
        BigDecimal pharmacyBalanceAfter = pharmacyDbService.findById(pharmacySeqno)
                .orElseThrow()
                .getBalance();
        List<PurchaseRecord> purchaseRecordAfter = purchaseRecordDbService.findAll();

        BigDecimal exceptCustomerBalance = customerBalanceBefore;
        BigDecimal exceptPharmacyBalance = pharmacyBalanceBefore;

        assertTrue(exceptCustomerBalance.compareTo(customerBalanceAfter)==0,
                "Customer balance not match,excepted:"+exceptCustomerBalance+"actual:"+customerBalanceAfter);
        assertTrue(exceptPharmacyBalance.compareTo(pharmacyBalanceAfter)==0
                ,"Pharmacy balance not match,excepted:"+exceptCustomerBalance+"actual:"+customerBalanceAfter);
        assertEquals(purchaseRecordBeforeSize, purchaseRecordAfter.size());
    }

    /**
     * GIVEN: Original json data
     * WHEN : startDate = "2021-01-01",endDate = "2021-01-05"
     * THEN : total balance = 297.73, item = 91,
     */
    @Test
    public void findTotalTransaction() {
        String startDate = "2021-01-01";
        String endDate = "2021-01-05";
        TransactionRepostRs response = service.findTotalTransaction(startDate, endDate);
        assertEquals(new BigDecimal("297.73").toString() + " (元)", response.getTotalAmountOfDollarValue(), "Total amount of dollar not match");
        assertEquals(91 + " (片)", response.getTotalAmountOfMask(), "Total amount of mask not match.");
    }

}
