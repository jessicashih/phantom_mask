package kdan.jessica.phantommask.repository.service;

import kdan.jessica.phantommask.repository.dao.MaskPriceRecordsDao;
import kdan.jessica.phantommask.repository.entity.MaskPriceRecords;
import kdan.jessica.phantommask.repository.relation.PharmacyPriceMaskRelation;
import kdan.jessica.phantommask.repository.relation.TransactionReport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void findTotalTransaction(){
        LocalDate startDate = LocalDate.of(2021,01,01);
        LocalDate endDate = LocalDate.of(2021,04,30);
        List<TransactionReport> result = dbService.findTotalTransaction(startDate, endDate);
        assertEquals(2,result.get(0).getAmountOfItem());
        assertTrue(new BigDecimal("18.52").compareTo(result.get(0).getAmountOfDollar())==0);
    }

    @Test
    public void findByItemNoAndPharmacy(){
        Long itemNo=1L;
        Long pharmacy=1L;
        Optional<MaskPriceRecords> result = dbService.findByItemNoAndPharmacy(itemNo, pharmacy);
        assertNotNull(result.get());
        assertEquals(1,result.get().getSeqNo());
    }
}
