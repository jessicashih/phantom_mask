package kdan.jessica.phantommask.repository.service;

import kdan.jessica.phantommask.repository.entity.MaskPriceRecord;
import kdan.jessica.phantommask.repository.entity.relation.PharmacyPriceMaskRelation;
import kdan.jessica.phantommask.repository.entity.relation.TransactionReport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Mask_Price_Record DbService Test
 */
@SpringBootTest
public class MaskPriceRecordDbServiceTest {

    @Autowired
    private MaskPriceRecordDbService dbService;

    /**
     * 依藥局編號查詢價格
     */
    @Test
    public void testFindByPharmacySeqno(){
        List<MaskPriceRecord> records =dbService.findByPharmacySeqno(List.of(1L));
        assertEquals(1,records.get(0).getSeqNo(),"mask_price_record seqno not match");
    }
    /**
     * 依照口罩編號以及藥局編號查詢價格紀錄
     */
    @Test
    public void testFindByItemNoAndPharmacy(){
        Long itemNo=1L;
        Long pharmacy=1L;
        Optional<MaskPriceRecord> result = dbService.findByItemNoAndPharmacy(itemNo, pharmacy);
        assertEquals(1,result.get().getSeqNo(),"mask_price_record seqno not match");
    }
}
