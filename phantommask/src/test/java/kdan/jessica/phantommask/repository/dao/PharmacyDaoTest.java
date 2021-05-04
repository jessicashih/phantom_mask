package kdan.jessica.phantommask.repository.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kdan.jessica.phantommask.repository.entity.Pharmacy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

/**
 * Pharmacy Dao CRUD test
 */
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@ActiveProfiles("test")
public class PharmacyDaoTest {

	@Autowired
	private PharmacyDao dao;
	
	@Test
	@Order(1)
	public void testInsert() {
		Pharmacy pharmacy = new Pharmacy();
		pharmacy.setName("Neighbors");
		pharmacy.setBalance(new BigDecimal("151.65"));
//		Mon
		pharmacy.setMonOpen(LocalTime.of(10, 9));
		pharmacy.setMonClose(LocalTime.of(14, 26));
//		Tue
		pharmacy.setTueOpen(null);
		pharmacy.setTueClose(null);
//		Wed
		pharmacy.setWedOpen(LocalTime.of(15, 26));
		pharmacy.setWedClose(LocalTime.of(17, 32));
//		Thu
		pharmacy.setThuOpen(LocalTime.of(15, 31));
		pharmacy.setThuClose(LocalTime.of(17, 46));
//		Fri
		pharmacy.setFriOpen(null);
		pharmacy.setFriClose(null);
//		Sat
		pharmacy.setSatOpen(LocalTime.of(13, 14));
		pharmacy.setSatClose(LocalTime.of(20, 24));
//		Sun
		pharmacy.setSunOpen(LocalTime.of(00, 02));
		pharmacy.setSunClose(LocalTime.of(16, 40));
		
		Pharmacy result =dao.save(pharmacy);
		assertNotNull(result, "result not be null");
	}
	
	@Test
	@Order(2)
	public void testQuery() {
		String pharmacyName= "Neighbors";
		Pharmacy pharmacy = new Pharmacy();
		pharmacy.setName(pharmacyName);
		pharmacy.setBalance(new BigDecimal("151.65"));
		Pharmacy insertData =dao.save(pharmacy);
		Optional<Pharmacy> result = dao.findById(insertData.getSeqNo());
		assertNotNull(result.get());
		assertEquals(pharmacyName, result.get().getName(),"dao didn't delete data");
	}
	
	@Test
	@Order(3)
	public void testUpdate() {
		List<Pharmacy> query =dao.findAll();
		for (Pharmacy mask : query) {
			mask.setName("Cash Saver Pharmacy");
			Pharmacy result =dao.save(mask);
			assertEquals("Cash Saver Pharmacy", result.getName(),"name not match,update fail");
		}
	}
	
	@Test
	@Order(4)
	public void testDelete() throws InterruptedException {
		dao.deleteAll();
		List<Pharmacy> result=dao.findAll();
		assertEquals(0, result.size(),"dao didn't delete data");
	}
}
