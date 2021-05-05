package kdan.jessica.phantommask.service;

import kdan.jessica.phantommask.model.MaskRs;
import kdan.jessica.phantommask.model.PharmacyRs;
import kdan.jessica.phantommask.model.SearchRs;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class SearchServiceTest extends BaseServiceTest{

    @Autowired
    private SearchService service;

    /**
     * GIVEN: Original json data
     * WHEN : Search string "Better"
     * THEN : Find pharmacy "Better You"
     */
    @Test
    public void search(){
        String searchString = "Better";
        SearchRs response = service.search(searchString);
        List<PharmacyRs> pharmacyRs = response.getPharmacyRsList();
        List<MaskRs> maskRs=response.getMaskRsList();
        assertTrue(pharmacyRs.stream().anyMatch(p-> StringUtils.equals(p.getName(),"Better You")));
        assertEquals(0,maskRs.size());
    }
}
