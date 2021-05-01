package kdan.jessica.phantommask.service;

import kdan.jessica.phantommask.model.EditPharmacyNameAndPriceRq;
import kdan.jessica.phantommask.model.MaskPirceEditRq;
import kdan.jessica.phantommask.model.PharmacyRs;
import kdan.jessica.phantommask.repository.dao.MaskPriceRecordDao;
import kdan.jessica.phantommask.repository.dao.PharmacyDao;
import kdan.jessica.phantommask.repository.entity.MaskPriceRecord;
import kdan.jessica.phantommask.repository.service.MaskPriceRecordDbService;
import kdan.jessica.phantommask.service.ex.DataNotFoundException;
import kdan.jessica.phantommask.service.ex.RequestInputException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kdan.jessica.phantommask.model.FindOpenPharmaciesRs;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class PharmacyServiceTest {

    @Autowired
    private PharmacyService service;

    @Autowired
    private PharmacyDao dao;

    @Autowired
    private MaskPriceRecordDao priceRecordsDao;

    @Autowired
    private MaskPriceRecordDbService priceRecordsDbService;

    @Test
    public void testFindOpenPharmaciesDateTime() {
        String dateTime = "2021-04-26 11:50";
        FindOpenPharmaciesRs response = service.findOpenPharmaciesAtCertainDateTime(dateTime);
        assertEquals(response.getPharmacies().get(0).getName(), "Neighbors");
    }

    @Test
    public void testFindOpenPharmaciesDate() {
        String date = "2021-04-26";
        FindOpenPharmaciesRs response = service.findOpenPharmaciesAtCertainDate(date);
        assertEquals(response.getPharmacies().get(0).getName(), "Neighbors");
    }

    @Test
    public void testFindPharmaciesMaskSortByName() {
        Long pharmacySeqno = 1L;
        PharmacyRs response = service.findPharmacyMask(pharmacySeqno, "name");
        assertEquals("Neighbors", response.getName(), "Pharmacy name not match");
        assertEquals("Free to Roam", response.getMasks().get(0).getName(), "Product name not match");
    }

    @Test
    public void testFindPharmaciesMaskSortByPrice() {
        Long pharmacySeqno = 1L;
        PharmacyRs response = service.findPharmacyMask(pharmacySeqno, "price");
        assertEquals("Neighbors", response.getName(), "Pharmacy name not match");
        assertEquals("Masquerade", response.getMasks().get(0).getName(), "Product name not match");
    }

    @Test
    public void testFindPharmaciesMaskAndDataNotFound() {
        Long pharmacySeqno = 10L;
        Exception exception = assertThrows(DataNotFoundException.class, () -> service.findPharmacyMask(pharmacySeqno, "name"));
        assertEquals("Pharmacy data is not found. Please check your input Seqno.", exception.getMessage(),
                "Error message not match");
    }

    @Test
    public void testFindPharmaciesMaskAndInputError() {
        Long pharmacySeqno = 10L;
        Exception exception = assertThrows(RequestInputException.class, () -> service.findPharmacyMask(pharmacySeqno, "xxx"));
        assertEquals("SortBy Column must be name or price. Pleace check your input.", exception.getMessage(),
                "Error message not match");
    }

    @Test
    public void testEditNameAndPrice() {
        Long itemNo = 1L;
        Long pharmacySeqno = 1L;
        MaskPriceRecord result = priceRecordsDbService.findByItemNoAndPharmacy(itemNo, pharmacySeqno)
                .orElseThrow(()->new RuntimeException());
        Long seqNo = result.getSeqNo();

        EditPharmacyNameAndPriceRq request = new EditPharmacyNameAndPriceRq();
        request.setPharmacySeqno(pharmacySeqno);
        request.setPharmacyName("AAA");
        List<MaskPirceEditRq> updatePrices = new ArrayList<>();
        MaskPirceEditRq updatePriceRq = new MaskPirceEditRq();
        updatePriceRq.setItemNo(itemNo);
        updatePriceRq.setPrice(new BigDecimal("9.99"));
        updatePrices.add(updatePriceRq);
        request.setMaskPrices(updatePrices);

        service.updatePharmacyInfo(request);

        Optional<MaskPriceRecord> afterUpdateData =priceRecordsDbService.findById(seqNo);
        assertNotNull(afterUpdateData.get());
        assertTrue(afterUpdateData.get().getIsDelete());
    }

    @Test
    public void deleteItem(){
        service.deleteItemFromPharmacy(1L,1L);
    }

}
