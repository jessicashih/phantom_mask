package kdan.jessica.phantommask.service;

import kdan.jessica.phantommask.model.EditPharmacyNameAndPriceRq;
import kdan.jessica.phantommask.model.MaskPirceEditRq;
import kdan.jessica.phantommask.model.PharmacyRs;
import kdan.jessica.phantommask.repository.dao.MaskPriceRecordDao;
import kdan.jessica.phantommask.repository.dao.PharmacyDao;
import kdan.jessica.phantommask.repository.entity.Mask;
import kdan.jessica.phantommask.repository.entity.MaskPriceRecord;
import kdan.jessica.phantommask.repository.entity.Pharmacy;
import kdan.jessica.phantommask.repository.service.MaskPriceRecordDbService;
import kdan.jessica.phantommask.repository.service.PharmacyDbService;
import kdan.jessica.phantommask.service.ex.DataNotFoundException;
import kdan.jessica.phantommask.service.ex.RequestInputException;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kdan.jessica.phantommask.model.FindOpenPharmaciesRs;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
@ActiveProfiles("test")
public class PharmacyServiceTest extends BaseServiceTest {

    @Autowired
    private PharmacyService service;
    @Autowired
    private MaskPriceRecordDbService maskPriceRecordDbService;

    /**
     * GIVEN: Original json data.
     * WHEN : Query date time at 2021-04-30(FRI) 23:50
     * THEN : There are four pharmacies open at FRI 23:50
     * (Better You/MedSavvy/Medlife/Discount Drugs)
     */
    @Test
    public void findOpenPharmaciesWithDateTime() {
        String dateTime = "2021-04-30 23:50";
        FindOpenPharmaciesRs response = service.findOpenPharmaciesAtCertainDateTime(dateTime);

        List<String> exceptPharmacy = List.of("Better You", "MedSavvy", "Medlife", "Discount Drugs");
        assertEquals(exceptPharmacy.size(), response.getPharmacies().size(), "Response size not match.");
        response.getPharmacies().forEach(p -> {
            assertTrue(exceptPharmacy.contains(p.getName()), "The pharmacy name is not in expected.");
        });
    }

    /**
     * GIVEN: Original json data.
     * WHEN : Query date time at 2021-04-30(FRI)
     * THEN : There are 13 pharmacies open at FRI
     * (Better You/MedSavvy/Medlife/Discount Drugs)
     */
    @Test
    public void findOpenPharmaciesDate() {
        String date = "2021-04-30";
        FindOpenPharmaciesRs response = service.findOpenPharmaciesAtCertainDate(date);
        List<String> exceptPharmacy = pharmacyDao.findAll().stream()
                .map(p -> p.getName())
                .filter(name -> StringUtils.isNotBlank(name))
                .collect(Collectors.toList());
        response.getPharmacies().forEach(pharmacyRs -> {
            assertTrue(exceptPharmacy.contains(pharmacyRs.getName()), "The pharmacy name is not in expected.");
        });
    }

    /**
     * Find masks that are sold by a given pharmacy, sort by name.
     * GIVEN: original json data
     * WHEN : Query pharmacy "DFW Wellness" ,sort by mask name
     * THEN : MaskRs[0] = "AniMask" , MaskRs[1]="Second Smile"
     */
    @Test
    public void findPharmaciesMaskSortByName() {
        String pharmacyName = "DFW Wellness";
        Pharmacy pharmacy = pharmacyDao.findAll()
                .stream()
                .filter(p -> StringUtils.equals(p.getName(), pharmacyName))
                .findAny().orElseThrow();
        Long pharmacySeqno = pharmacy.getSeqNo();
        PharmacyRs response = service.findPharmacyMask(pharmacySeqno, "name");
        assertEquals(pharmacyName, response.getName(), "Pharmacy name not match");
        assertEquals("AniMask", response.getMasks().get(0).getName(), "Product name not match");
        assertEquals("Second Smile", response.getMasks().get(1).getName(), "Product name not match");
    }

    /**
     * Find masks that are sold by a given pharmacy, sort by price.
     * GIVEN: original json data
     * WHEN : Query pharmacy "Neighbors" ,sort by mask name
     * THEN : MaskRs[0] = "Masquerade" , MaskRs[1] = "Free to Roam", item[2] = "MaskT"
     */
    @Test
    public void testFindPharmaciesMaskSortByPrice() {
        String pharmacyName = "Neighbors";
        Pharmacy pharmacy = pharmacyDao.findAll()
                .stream()
                .filter(p -> StringUtils.equals(p.getName(), pharmacyName))
                .findAny().orElseThrow();
        Long pharmacySeqno = pharmacy.getSeqNo();
        PharmacyRs response = service.findPharmacyMask(pharmacySeqno, "price");
        assertEquals(pharmacyName, response.getName(), "Pharmacy name not match");
        assertEquals("Masquerade", response.getMasks().get(0).getName(), "Product name not match");
        assertEquals("Free to Roam", response.getMasks().get(1).getName(), "Product name not match");
        assertEquals("MaskT", response.getMasks().get(2).getName(), "Product name not match");
    }

    /**
     * GIVEN: Original json data
     * WHEN : Query pharmacy with seqno = 0(this raw is not exist)
     * THEN : Throw exception DataNotFound
     */
    @Test
    public void findPharmaciesMaskAndDataNotFound() {
        Long pharmacySeqno = 0L;
        Exception exception = assertThrows(DataNotFoundException.class, () -> service.findPharmacyMask(pharmacySeqno, "name"));
        assertEquals("Pharmacy data is not found. Please check your input Seqno.", exception.getMessage(),
                "Error message not match");
    }

    /**
     * GIVEN: original json data
     * WHEN : Query pharmacy with wrong sort request string
     * THEN : Throw exception RequestInputException
     */
    @Test
    public void testFindPharmaciesMaskAndSortInputError() {
        String sortBy = "xxx";
        Exception exception = assertThrows(RequestInputException.class, () -> service.findPharmacyMask(10L, sortBy));
        assertEquals("SortBy column must be name or price. Please check your input.", exception.getMessage(),
                "Error message not match");
    }

    /**
     * GIVEN: original json data
     * WHEN : Query pharmacy with null seqno request string
     * THEN : Throw exception RequestInputException
     */
    @Test
    public void testFindPharmaciesMaskAndSeqnoInputError() {
        Exception exception = assertThrows(RequestInputException.class, () -> service.findPharmacyMask(null, "name"));
        assertEquals("PharmacySeqno can't be null. Please check your input.", exception.getMessage(),
                "Error message not match");
    }

    /**
     * GIVEN: Original json data
     * WHEN : Pharmacy "MedSavvy" seqno ,pharmacyName = "MedSavvy-test" and item[0] price 49.0
     * THEN : Pharmacy "MedSavvy" update to "MedSavvy-test" and item_no= 11 price 49.21 to 49.0
     */
    @Test
    public void editNameAndPrice() {
        String pharmacyName = "MedSavvy";
        String pharmacyUpdateName = pharmacyName + "-test";
        BigDecimal updatePrice = new BigDecimal("49.0");
//        GIVEN: original json data
        Pharmacy pharmacy = pharmacyDao.findAll().stream()
                .filter(p -> StringUtils.equals(p.getName(), pharmacyName))
                .findAny()
                .orElseThrow();
        Long pharmacySeqno = pharmacy.getSeqNo();
        List<MaskPriceRecord> result = maskPriceRecordDbService.findByPharmacySeqno(List.of(pharmacySeqno));
        MaskPriceRecord maskPriceRecord = result.stream().findFirst().orElseThrow();
        Long recordSeqNo = maskPriceRecord.getSeqNo();
        Long itemNo = maskPriceRecord.getItemNo();

//        WHEN : Phramacy "MedSavvy" seqno ,pharmacyName = "MedSavvy-test" and item[0] price 49.0
        EditPharmacyNameAndPriceRq request = new EditPharmacyNameAndPriceRq();
        request.setPharmacySeqno(pharmacySeqno);
        request.setPharmacyName(pharmacyUpdateName);
        List<MaskPirceEditRq> updatePrices = List.of(new MaskPirceEditRq());
        updatePrices.get(0).setItemNo(itemNo);
        updatePrices.get(0).setPrice(updatePrice);
        request.setMaskPrices(updatePrices);
        service.updatePharmacyInfo(request);

//      THEN : Pharmacy "MedSavvy" update to "MedSavvy-test" and item_no= 11 price 49.21 to 49.0
        Pharmacy pharmacyAfter = pharmacyDao.findById(pharmacySeqno).orElseThrow();
        assertEquals(pharmacyUpdateName, pharmacyAfter.getName(), "Pharmacy name didn't update.");
        MaskPriceRecord priceHistory = maskPriceRecordDao.findById(recordSeqNo).orElseThrow();
        assertTrue(priceHistory.getIsDelete(), "Price didn't update.");
        assertNotNull(priceHistory.getUpdateDate(), "Update date can't be null");
        assertNotNull(priceHistory.getUpdateTime(), "Update time can't be null");
        MaskPriceRecord priceNew = maskPriceRecordDbService.findByItemNoAndPharmacy(itemNo, pharmacySeqno).orElseThrow();
        assertTrue(updatePrice.compareTo(priceNew.getPrice()) == 0, "Price didn't update." + priceNew.getPrice());
    }

    /**
     * GIVEN: Original json data
     * WHEN : Empty pharmacy seqno
     * THEN : Throw exception RequestInputException
     */
    @Test
    public void editNameAndPriceWithEmptyPharmacySeqNo() {
        EditPharmacyNameAndPriceRq request = new EditPharmacyNameAndPriceRq();
        request.setPharmacySeqno(null);
        List<MaskPirceEditRq> updatePrices = List.of(new MaskPirceEditRq());
        request.setMaskPrices(updatePrices);
        Exception e = assertThrows(RequestInputException.class, () -> service.updatePharmacyInfo(request));
        assertEquals("You must input pharmacy seqNo", e.getMessage(), "Exception message not match.");
    }

    /**
     * GIVEN: Original json data
     * WHEN : Empty itemNo
     * THEN : Throw exception RequestInputException
     */
    @Test
    public void editNameAndPriceWithEmptyItemNo() {
        EditPharmacyNameAndPriceRq request = new EditPharmacyNameAndPriceRq();
        request.setPharmacySeqno(1L);
        List<MaskPirceEditRq> updatePrices = List.of(new MaskPirceEditRq());
        updatePrices.get(0).setPrice(BigDecimal.ONE);
        request.setMaskPrices(updatePrices);
        Exception e = assertThrows(RequestInputException.class, () -> service.updatePharmacyInfo(request));
        assertEquals("You must input Mask item_no", e.getMessage(), "Exception message not match.");
    }

    /**
     * GIVEN: Original json data
     * WHEN : Pharmacy "MedSavvy" seqno , item[0] seqno
     * THEN : Pharmacy didn't have item[0]
     */
    @Test
    public void deleteItemFromPharmacy() {
        String pharmacyName = "MedSavvy";
        Pharmacy pharmacy = pharmacyDao.findAll().stream()
                .filter(p -> StringUtils.equals(p.getName(), pharmacyName))
                .findAny()
                .orElseThrow();
        Long pharmacySeqno = pharmacy.getSeqNo();
        List<MaskPriceRecord> result = maskPriceRecordDbService.findByPharmacySeqno(List.of(pharmacySeqno));
        MaskPriceRecord maskPriceRecord = result.stream().findFirst().orElseThrow();
        Long recordSeqNo = maskPriceRecord.getSeqNo();
        Long itemNo = maskPriceRecord.getItemNo();

        service.deleteItemFromPharmacy(itemNo, pharmacySeqno);

        MaskPriceRecord priceRecord = maskPriceRecordDao.findById(recordSeqNo).orElseThrow();
        assertTrue(priceRecord.getIsDelete(), "Price didn't update.");
        assertNotNull(priceRecord.getUpdateDate(), "Update date can't be null");
        assertNotNull(priceRecord.getUpdateTime(), "Update time can't be null");
    }

    /**
     * GIVEN: Original json data
     * WHEN : Empty itemNo
     * THEN : Throw exception RequestInputException
     */
    @Test
    public void deleteItemFromPharmacyWithEmptyItemNo() {
        Exception e = assertThrows(RequestInputException.class, () -> service.deleteItemFromPharmacy(null, 1L));
        assertEquals("Item_no can't be null. Please check your input.", e.getMessage(), "Exception message not match.");
    }

    /**
     * GIVEN: Original json data
     * WHEN : Empty pharmacy seqNo
     * THEN : Throw exception RequestInputException
     */
    @Test
    public void deleteItemFromPharmacyWithEmptyPharmacySeqNo() {
        Exception e = assertThrows(RequestInputException.class, () -> service.deleteItemFromPharmacy(1L, null));
        assertEquals("Pharmacy_Seqno can't be null. Please check your input.", e.getMessage(), "Exception message not match.");
    }

}
