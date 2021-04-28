package kdan.jessica.phantommask.repository.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kdan.jessica.phantommask.repository.entity.Pharmacy;

@SpringBootTest
public class PharmacieDbServiceTest {

	@Autowired
	private PharmacieDbService dbService;

	@Test
	public void testOpenAtCertionDateTime() {
		LocalDateTime datetime = LocalDateTime.of(2021, 04, 26, 11, 50);
		List<Pharmacy> results = dbService.findOpenedPharmacy(datetime.getDayOfWeek(), datetime.toLocalTime());
		assertTrue(results.size() > 0, "result is empty");
		assertEquals("Neighbors", results.get(0).getName(), "name not match");
	}
	
	@Test
	public void testNotOpenAtCertionDateTime() {
		LocalDateTime datetime = LocalDateTime.of(2021, 04, 27, 11, 50);
		List<Pharmacy> results = dbService.findOpenedPharmacy(datetime.getDayOfWeek(), datetime.toLocalTime());
		assertTrue(results.size() == 0, "result is not empty");
	}
	
	@Test
	public void testOpenAtCertionDate() {
		LocalDate datetime = LocalDate.of(2021, 04, 26);
		List<Pharmacy> results = dbService.findOpenedPharmacy(datetime.getDayOfWeek());
		assertTrue(results.size() > 0, "result is empty");
		assertEquals("Neighbors", results.get(0).getName(), "name not match");
	}
	
	@Test
	public void testNotOpenAtCertionDate() {
		LocalDate datetime = LocalDate.of(2021, 04, 27);
		List<Pharmacy> results = dbService.findOpenedPharmacy(datetime.getDayOfWeek());
		assertTrue(results.size() == 0, "result is not empty");
	}
}
