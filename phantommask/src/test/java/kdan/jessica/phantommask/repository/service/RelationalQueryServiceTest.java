package kdan.jessica.phantommask.repository.service;

import kdan.jessica.phantommask.json.TransformJsonData;
import kdan.jessica.phantommask.repository.dao.*;
import kdan.jessica.phantommask.repository.entity.relation.PharmacyPriceMaskRelation;
import kdan.jessica.phantommask.repository.entity.relation.TopUser;
import kdan.jessica.phantommask.repository.entity.relation.TransactionReport;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 關聯查詢 Test
 */
@SpringBootTest
@ActiveProfiles("test")
public class RelationalQueryServiceTest {

    @Autowired
    private RelationalQueryService relationalQueryService;
    @Autowired
    private PharmacyDao pharmacyDao;
    @Autowired
    private MaskDao maskDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private MaskPriceRecordDao maskPriceRecordDao;
    @Autowired
    private PurchaseRecordDao purchaseRecordDao;

    @BeforeEach
    public void initData() throws IOException {
        TransformJsonData jsonConvertor = new TransformJsonData(pharmacyDao, maskDao, customerDao, maskPriceRecordDao, purchaseRecordDao);
        jsonConvertor.transform();
    }

    @AfterEach
    public void deleteAll() {
        pharmacyDao.deleteAll();
        maskDao.deleteAll();
        customerDao.deleteAll();
        maskPriceRecordDao.deleteAll();
        purchaseRecordDao.deleteAll();
    }

    /**
     * 查詢在某個時段間消費排行前幾名的顧客
     */
    @Test
    public void testFindTopUser() {
        List<TopUser> topUsersResult = relationalQueryService.findTopUser
                (LocalDate.of(2021, 01, 01),
                        LocalDate.of(2021, 04, 30), 1);
        assertEquals(1, topUsersResult.size(), "Result size not match");
    }

    /**
     * 查詢在價格區間內的口罩，並且有販售的藥局
     */
    @Test
    public void testFindPharmacyWithMaskByPriceBetween() {
        List<PharmacyPriceMaskRelation> result = relationalQueryService.findPharmacyWithMaskByPrice
                (new BigDecimal("9"), new BigDecimal("11"));
        assertEquals(8, result.size(), "Result size not match");
    }

    /**
     * 查詢在價格區間內的口罩，並且有販售的藥局
     */
    @Test
    public void testFindPharmacyWithMaskByPriceMoreThan() {
        List<PharmacyPriceMaskRelation> result = relationalQueryService.findPharmacyWithMaskByPrice
                (new BigDecimal("40"), null);
        assertEquals(6, result.size(), "Result size not match");
    }

    /**
     * 查詢在價格區間內的口罩，並且有販售的藥局
     */
    @Test
    public void testFindPharmacyWithMaskByPriceLessThan() {
        List<PharmacyPriceMaskRelation> result = relationalQueryService.findPharmacyWithMaskByPrice
                (null, new BigDecimal("10"));
        assertEquals(22, result.size(), "Result size not match");
    }

    /**
     * 查詢時間區間內的交易總額
     */
    @Test
    public void findTotalTransaction() {
        LocalDate startDate = LocalDate.of(2021, 01, 01);
        LocalDate endDate = LocalDate.of(2021, 04, 30);
        List<TransactionReport> result = relationalQueryService.findTotalTransaction(startDate, endDate);
        assertEquals(27, result.size(), "Result size not match");
    }
}
