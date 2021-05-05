package kdan.jessica.phantommask.service;

import kdan.jessica.phantommask.model.MaskRs;
import kdan.jessica.phantommask.model.PharmacyRs;
import kdan.jessica.phantommask.model.SearchRs;
import kdan.jessica.phantommask.service.ex.RequestInputException;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class SearchServiceTest extends BaseServiceTest {

    @Autowired
    private SearchService service;

    /**
     * GIVEN: Original json data
     * WHEN : Search string "T"
     * THEN : Find pharmacy [Thrifty Way Pharmacy/RxToMe] and mask [MaskT]
     */
    @Test
    public void search() {
        String searchString = "T";
        SearchRs response = service.search(searchString);
        List<PharmacyRs> pharmacyRs = response.getPharmacyRsList();
        List<MaskRs> maskRs = response.getMaskRsList();
        assertEquals(2, pharmacyRs.size(), "Pharmacy search result not match");
        assertEquals(1, maskRs.size(), "Mask search result not match");

        List<String> exceptedPhramacy = List.of("Thrifty Way Pharmacy", "RxToMe");
        List<String> exceptedMask = List.of("MaskT");
        pharmacyRs.stream().forEach(p -> {
            assertTrue(exceptedPhramacy.contains(p.getName()), "The pharmacy name is not excepted: " + p.getName());
        });
        maskRs.stream().forEach(m -> {
            assertTrue(exceptedMask.contains(m.getName()), "The mask is not excepted: " + m.getName());
        });
    }

    /**
     * GIVEN: Original json data
     * WHEN : Search string ""
     * THEN : Throw exception RequestInputException
     */
    @Test
    public void searchWithEmptyString() {
        String searchString = "";
        Exception e = assertThrows(RequestInputException.class, () -> service.search(searchString));
        assertEquals("SearchString can't be null, Please check input data.",e.getMessage(),"Exception not match.");
    }
}
