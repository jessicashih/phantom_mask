package kdan.jessica.phantommask.service;

import kdan.jessica.phantommask.model.MaskRs;
import kdan.jessica.phantommask.model.PharmacyRs;
import kdan.jessica.phantommask.model.SearchRs;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SearchServiceTest {

    @Autowired
    private SearchService service;

    @Test
    public void search(){
        String searchString = "Batter";
        SearchRs response = service.search(searchString);
        List<PharmacyRs> pharmacyRs = response.getPharmacyRsList();
        List<MaskRs> maskRs=response.getMaskRsList();
        assertTrue(pharmacyRs.stream().anyMatch(p->p.getSeqNo()==1));
        assertEquals(0,maskRs.size());
    }
}
