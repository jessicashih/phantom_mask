package kdan.jessica.phantommask.repository.service;

import kdan.jessica.phantommask.mockdata.MockMaskPriceRecord;
import kdan.jessica.phantommask.repository.dao.MaskPriceRecordDao;
import kdan.jessica.phantommask.repository.entity.MaskPriceRecord;
import kdan.jessica.phantommask.repository.ex.DataException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Mask_Price_Record DbService Test
 */
@SpringBootTest
@ActiveProfiles("test")
public class MaskPriceRecordDbServiceTest {

    @Autowired
    private MaskPriceRecordDbService dbService;
    @Autowired
    private MaskPriceRecordDao dao;
    private MaskPriceRecord pricePast;
    private MaskPriceRecord priceNow;


    @BeforeEach
    public void initData() {
        priceNow = MockMaskPriceRecord.mock();
        pricePast = MockMaskPriceRecord.mockPriceHistory();
        priceNow = dao.save(priceNow);
        pricePast = dao.save(pricePast);
    }

    @Test
    public void findById() {
        Long priceSeqno = priceNow.getSeqNo();
        Optional<MaskPriceRecord> result = dbService.findById(priceSeqno);
        assertTrue(result.isPresent());
        assertNull(result.get().getIsDelete());
    }

    /**
     * GIVEN: The update mask data itemNo is null
     * WHEN : call dbService
     * THEN : throw DataException
     */
    @Test
    public void updateAndItemIsNull() {
        MaskPriceRecord mockData = MockMaskPriceRecord.mock();
        assertThrows(DataException.class, () -> dbService.update(mockData), "The test need throw exception.");
    }

    @Test
    public void update() {
        MaskPriceRecord updateData = new MaskPriceRecord();
        updateData.setSeqNo(priceNow.getSeqNo());
        updateData.setIsDelete(true);
        MaskPriceRecord maskAfter = dbService.update(updateData);
        assertTrue(maskAfter.getIsDelete(), "Update test fail.");
    }

    @Test
    public void insertAndItemIsNotNull() {
        MaskPriceRecord mockData = priceNow;
        assertThrows(DataException.class, () -> dbService.insert(mockData), "The test need throw exception.");
    }

    @Test
    public void insert() {
        MaskPriceRecord insertData = MockMaskPriceRecord.mock();
        MaskPriceRecord afterInsert = dbService.insert(insertData);
        assertNotNull(afterInsert.getSeqNo(), "After insert seqno can't be null.");
    }

    @Test
    public void testFindByPharmacySeqno() {
        List<MaskPriceRecord> records = dbService.findByPharmacySeqno(List.of(1L));
        for (MaskPriceRecord record : records) {
            assertEquals(1, record.getPharmacySeqno(), "mask_price_record seqno not match");
            assertNull(record.getIsDelete(), "Query price now,so isDelete can't be null");
        }
    }

    @Test
    public void findByItemNoAndPharmacy() {
        Long itemNo = 1L;
        Long pharmacy = 1L;
        Optional<MaskPriceRecord> result = dbService.findByItemNoAndPharmacy(itemNo, pharmacy);
        assertNotNull(result.get());
        assertEquals(pharmacy, result.get().getPharmacySeqno(), "Pharmacy seqno not match.");
        assertEquals(itemNo, result.get().getItemNo(), "itemno not match");
        assertNull(result.get().getIsDelete(), "Query price now,so isDelete can't be null");
    }

    @AfterEach
    public void deleteData() {
        dao.deleteAll();
    }
}
