package kdan.jessica.phantommask.service;

import kdan.jessica.phantommask.model.MaskRs;
import kdan.jessica.phantommask.model.PharmacyRs;
import kdan.jessica.phantommask.repository.entity.Mask;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class MaskServiceTest extends BaseServiceTest {
    @Autowired
    private MaskService maskService;


    /**
     * GIVEN: JSON DATA
     * WHEN : Don't given price range
     * THEN : Get all current mask price of pharmacy.
     */
    @Test
    public void queryMaskPriceAndDidntBringPrice() {
        List<PharmacyRs> result = maskService.queryMaskPrice(null, null);
        assertEquals(20, result.size(), "This test case need to show all pharmacy.");
    }

    /**
     * GIVEN: JSON DATA
     * WHEN : Don't given price range
     * THEN : Get all current mask price of pharmacy.
     */
    @Test
    public void queryMaskPrice() {
        List<PharmacyRs> result = maskService.queryMaskPrice(BigDecimal.valueOf(10), BigDecimal.valueOf(11));
        assertEquals(4, result.size(), "This test case need to show all pharmacy.");
        PharmacyRs pharmacy1 = result.get(0);
        assertEquals("Longhorn Pharmacy", pharmacy1.getName(), "Pharmacy name not match.");
        assertEquals("AniMask", pharmacy1.getMasks().get(0).getName(), "Mask name not match.");
        assertEquals("green", pharmacy1.getMasks().get(0).getColor(), "Mask color not match.");
        assertEquals("10 pre pack", pharmacy1.getMasks().get(0).getNumOfPack(), "Mask num of pack not match.");

        PharmacyRs pharmacy2 = result.get(1);
        assertEquals("Neighbors", pharmacy2.getName(), "Pharmacy name not match.");
        assertEquals("Masquerade", pharmacy2.getMasks().get(0).getName(), "Mask name not match.");
        assertEquals("black", pharmacy2.getMasks().get(0).getColor(), "Mask color not match.");
        assertEquals("3 pre pack", pharmacy2.getMasks().get(0).getNumOfPack(), "Mask num of pack not match.");

        PharmacyRs pharmacy3 = result.get(2);
        assertEquals("Medlife", pharmacy3.getName(), "Pharmacy name not match.");
        assertEquals("Free to Roam", pharmacy3.getMasks().get(0).getName(), "Mask name not match.");
        assertEquals("black", pharmacy3.getMasks().get(0).getColor(), "Mask color not match.");
        assertEquals("3 pre pack", pharmacy3.getMasks().get(0).getNumOfPack(), "Mask num of pack not match.");
        assertEquals("MaskT", pharmacy3.getMasks().get(1).getName(), "Mask name not match.");
        assertEquals("black", pharmacy3.getMasks().get(1).getColor(), "Mask color not match.");
        assertEquals("3 pre pack", pharmacy3.getMasks().get(1).getNumOfPack(), "Mask num of pack not match.");

        PharmacyRs pharmacy4 = result.get(3);
        assertEquals("Drug Blend", pharmacy4.getName(), "Pharmacy name not match.");
        assertEquals("AniMask", pharmacy4.getMasks().get(0).getName(), "Mask name not match.");
        assertEquals("black", pharmacy4.getMasks().get(0).getColor(), "Mask color not match.");
        assertEquals("3 pre pack", pharmacy3.getMasks().get(0).getNumOfPack(), "Mask num of pack not match.");
    }

    @Test
    public void updateName() {
        Mask oriMask = maskDao.findAll().stream()
                .findFirst()
                .orElseThrow();
        Long itemNo = oriMask.getItemNo();
        String oriMaskName = oriMask.getName();
        String itemName = oriMaskName + "-aaaaaaaaa";
        maskService.updateName(itemNo, itemName);

        String nameAfterUpdate = maskDao.findById(itemNo)
                .orElseThrow().getName();
        assertEquals(itemName,nameAfterUpdate,"Update name fail.");

    }
}
