package kdan.jessica.phantommask.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kdan.jessica.phantommask.model.FindOpenPharmaciesRs;


@SpringBootTest
public class PharmacyServiceTest {
	
	@Autowired
	private PharmacyService service;
	
	@Test
	public void testFindOpenPharmacies() {
		String dateTime = "2021-04-26 11:50";
		FindOpenPharmaciesRs response = service.findOpenPharmacies(dateTime);
		assertEquals(response.getPharmacies().get(0), "Neighbors");
	}

}
