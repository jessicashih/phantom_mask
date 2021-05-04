package kdan.jessica.phantommask.repository.service;

import kdan.jessica.phantommask.mockdata.MockPharmacy;
import kdan.jessica.phantommask.repository.dao.PharmacyDao;
import kdan.jessica.phantommask.repository.entity.MaskPriceRecord;
import kdan.jessica.phantommask.repository.entity.Pharmacy;
import kdan.jessica.phantommask.repository.ex.DataException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pharmacy DbService Test
 */
@SpringBootTest
@ActiveProfiles("test")
public class PharmacyDbServiceTest {

    @Autowired
    private PharmacyDbService dbService;
    @Autowired
    private PharmacyDao dao;
    private List<Long> pharmacySeqnos = new ArrayList<>();

    @BeforeEach
    public void initData() {
        List<Pharmacy> mockDatas = MockPharmacy.mock();
        for (Pharmacy pharmacy:mockDatas) {
            pharmacy=dao.save(pharmacy);
            pharmacySeqnos.add(pharmacy.getSeqNo());
        }
    }

    /**
     * GIVEN: The update mask data seqno is null
     * WHEN : call dbService
     * THEN : throw DataException
     */
    @Test
    public void updateAndItemIsNull() {
        Pharmacy mockData = MockPharmacy.mock().get(0);
        assertThrows(DataException.class, () -> dbService.update(mockData), "The test need throw exception.");
    }

    @Test
    public void update() {
        String updateName = "UpdateName";
        Pharmacy updateData = MockPharmacy.mock().get(0);
        updateData.setSeqNo(pharmacySeqnos.get(0));
        updateData.setName(updateName);
        Pharmacy dataAfter = dbService.update(updateData);
        assertEquals(updateName,dataAfter.getName(), "Update test fail.");
    }

    @Test
    public void findAll(){
        List<Pharmacy> result = dbService.findAll();
        assertEquals(pharmacySeqnos.size(),result.size(),"FindAll size not match.");
    }

    @Test
    public void openAtCertionDateTime() {
        LocalDateTime datetime = LocalDateTime.of(2021, 05, 3, 11, 55);
        List<Pharmacy> results = dbService.findOpenedPharmacy(datetime.getDayOfWeek(), datetime.toLocalTime());
        assertEquals(1, results.size(), "Result size is not match");
    }

    @Test
    public void openAtCertionDate() {
        LocalDate datetime = LocalDate.of(2021, 05, 4);
        List<Pharmacy> results = dbService.findOpenedPharmacy(datetime.getDayOfWeek());
        assertEquals(2, results.size(), "Result size is not match");
    }


    @AfterEach
    public void deleteData(){
        dao.deleteAll();
    }
}
