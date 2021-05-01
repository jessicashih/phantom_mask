package kdan.jessica.phantommask.repository.service;

import kdan.jessica.phantommask.repository.entity.Pharmacy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Pharmacy DbService Test
 */
@SpringBootTest
public class PharmacyDbServiceTest {

    @Autowired
    private PharmacyDbService dbService;

    @Test
    public void testOpenAtCertionDateTime() {
        LocalDateTime datetime = LocalDateTime.of(2021, 05, 1, 11, 50);
        List<Pharmacy> results = dbService.findOpenedPharmacy(datetime.getDayOfWeek(), datetime.toLocalTime());
        assertEquals(8, results.size(), "Result size is not match");
    }

    @Test
    public void testOpenAtCertionDate() {
        LocalDate datetime = LocalDate.of(2021, 05, 1);
        List<Pharmacy> results = dbService.findOpenedPharmacy(datetime.getDayOfWeek());
        assertEquals(16, results.size(), "Result size is not match");
    }
}
