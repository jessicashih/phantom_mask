package kdan.jessica.phantommask.repository.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kdan.jessica.phantommask.repository.dao.MaskPriceRecordsDao;
import kdan.jessica.phantommask.repository.entity.MaskPriceRecords;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class MaskPriceRecordsDaoTest {

	@Autowired
	private MaskPriceRecordsDao dao;

	@Test
	@Order(1)
	public void testInsert() {
		MaskPriceRecords maskPriceRecords = new MaskPriceRecords();
		maskPriceRecords.setItemNo(2L);
		maskPriceRecords.setPharmacySeqno(1L);
		maskPriceRecords.setPrice(new BigDecimal("14.18"));
		maskPriceRecords.setCreateDate(LocalDate.now());
		maskPriceRecords.setCreateTime(LocalTime.now());
		MaskPriceRecords result = dao.save(maskPriceRecords);
		assertNotNull(result, "result not be null");
	}

	@Test
	@Order(2)
	public void testQuery() {
		List<MaskPriceRecords> result = dao.findAll();
		assertEquals(1, result.size(), "dao didn't delete data");
	}

	@Test
	@Order(3)
	public void testUpdate() {
		List<MaskPriceRecords> query = dao.findAll();
		BigDecimal updatePrice = new BigDecimal("8.09");
		for (MaskPriceRecords maskPriceRecords : query) {
			maskPriceRecords.setPrice(updatePrice);
			MaskPriceRecords result = dao.save(maskPriceRecords);
			assertTrue(result.getPrice().compareTo(updatePrice) == 0, "update fail");
		}
	}

	@Test
	@Order(4)
	public void testDelete() throws InterruptedException {
		dao.deleteAll();
		List<MaskPriceRecords> result = dao.findAll();
		assertEquals(0, result.size(), "dao didn't delete data");
	}

}
