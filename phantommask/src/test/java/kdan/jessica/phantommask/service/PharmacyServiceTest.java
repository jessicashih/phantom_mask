package kdan.jessica.phantommask.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import kdan.jessica.phantommask.model.PharmacyRs;
import kdan.jessica.phantommask.service.ex.DataNotFoundException;
import kdan.jessica.phantommask.service.ex.RequestInputException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kdan.jessica.phantommask.model.FindOpenPharmaciesRs;


@SpringBootTest
public class PharmacyServiceTest {
	
	@Autowired
	private PharmacyService service;
	
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
		PharmacyRs response = service.findPharmacyMask(pharmacySeqno,"name");
		assertEquals("Neighbors",response.getName(), "Pharmacy name not match");
		assertEquals("Free to Roam",response.getMasks().get(0).getName(),"Product name not match");
	}

	@Test
	public void testFindPharmaciesMaskSortByPrice() {
		Long pharmacySeqno = 1L;
		PharmacyRs response = service.findPharmacyMask(pharmacySeqno,"price");
		assertEquals("Neighbors",response.getName(), "Pharmacy name not match");
		assertEquals("Masquerade",response.getMasks().get(0).getName(),"Product name not match");
	}

	@Test
	public void testFindPharmaciesMaskAndDataNotFound() {
		Long pharmacySeqno = 10L;
		Exception exception = assertThrows(DataNotFoundException.class,()->service.findPharmacyMask(pharmacySeqno,"name"));
		assertEquals("Pharmacy data is not found. Please check your input Seqno.",exception.getMessage(),
				"Error message not match");
	}
	@Test
	public void testFindPharmaciesMaskAndInputError() {
		Long pharmacySeqno = 10L;
		Exception exception = assertThrows(RequestInputException.class,()->service.findPharmacyMask(pharmacySeqno,"xxx"));
		assertEquals("SortBy Column must be name or price. Pleace check your input.",exception.getMessage(),
				"Error message not match");
	}

}
