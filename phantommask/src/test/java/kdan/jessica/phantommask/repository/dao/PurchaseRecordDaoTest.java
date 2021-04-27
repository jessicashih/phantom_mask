package kdan.jessica.phantommask.repository.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kdan.jessica.phantommask.repository.entity.PurchaseRecord;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class PurchaseRecordDaoTest {
	@Autowired
	private PurchaseRecordDao dao;
	
	@Test
	@Order(1)
	public void testInsert() {
		PurchaseRecord record = new PurchaseRecord();
		record.setCustomerId(1L);
		record.setPriceRecord(2L);
		record.setUuid(UUID.randomUUID().toString());
		record.setCreateDate(LocalDate.now());
		record.setCreateTime(LocalTime.now());
		PurchaseRecord result =dao.save(record);
		assertNotNull(result, "result not be null");
	}
	
	@Test
	@Order(2)
	public void testQuery() {
		List<PurchaseRecord> result =dao.findAll();
		assertEquals(1, result.size(),"dao didn't delete data");
	}
	
	@Test
	@Order(3)
	public void testUpdate() {
		List<PurchaseRecord> query =dao.findAll();
		for (PurchaseRecord record : query) {
			record.setPriceRecord(2L);
			PurchaseRecord result =dao.save(record);
			assertEquals(2L, result.getPriceRecord(),"name not match,update fail");
		}
	}
	
	@Test
	@Order(4)
	public void testDelete() throws InterruptedException {
		dao.deleteAll();
		List<PurchaseRecord> result=dao.findAll();
		assertEquals(0, result.size(),"dao didn't delete data");
	}
}
