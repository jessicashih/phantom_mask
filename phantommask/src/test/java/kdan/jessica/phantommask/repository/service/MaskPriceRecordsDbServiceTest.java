package kdan.jessica.phantommask.repository.service;

import kdan.jessica.phantommask.repository.entity.MaskPriceRecords;
import kdan.jessica.phantommask.repository.relation.PharmacyPriceMaskRelation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MaskPriceRecordsDbServiceTest {

    @Autowired
    private MaskPriceRecordsDbService dbService;

    @Test
    public void testFindByPharmacySeqno(){
        List<MaskPriceRecords> records =dbService.findByPharmacySeqno(List.of(1L,2L));
        assertEquals(1,records.size(),"record size not match");
        assertEquals(1,records.get(0).getSeqNo(),"item_seqNo not match");
    }

    @Test
    public void testRelationQuery(){
        List<PharmacyPriceMaskRelation> result =dbService.pharmacyRelationQuery
                (new BigDecimal("9"),new BigDecimal("11"));
        assertEquals(1, result.size(),"query fail");
        assertEquals("Neighbors",result.get(0).getPharmacyName(),"Pharmacy name not match");
    }

}
