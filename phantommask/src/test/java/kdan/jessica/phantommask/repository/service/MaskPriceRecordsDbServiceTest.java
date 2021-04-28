package kdan.jessica.phantommask.repository.service;

import kdan.jessica.phantommask.repository.entity.MaskPriceRecords;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

}
