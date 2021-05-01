package kdan.jessica.phantommask.repository.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kdan.jessica.phantommask.repository.entity.MaskPriceRecord;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mask_Price_Record Dao CRUD test
 */
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@Transactional
public class MaskPriceRecordDaoTest {

	@Autowired
	private MaskPriceRecordDao dao;

	@Test
	@Order(1)
	public void testInsert() {
		MaskPriceRecord maskPriceRecord = new MaskPriceRecord();
		maskPriceRecord.setItemNo(2L);
		maskPriceRecord.setPharmacySeqno(1L);
		maskPriceRecord.setPrice(new BigDecimal("14.18"));
		maskPriceRecord.setCreateDate(LocalDate.now());
		maskPriceRecord.setCreateTime(LocalTime.now());
		MaskPriceRecord result = dao.save(maskPriceRecord);
		assertNotNull(result, "result not be null");
	}

	@Test
	@Order(2)
	public void testQuery() {
		MaskPriceRecord maskPriceRecord = new MaskPriceRecord();
		maskPriceRecord.setItemNo(2L);
		maskPriceRecord.setPharmacySeqno(1L);
		maskPriceRecord.setPrice(new BigDecimal("14.18"));
		maskPriceRecord.setCreateDate(LocalDate.now());
		maskPriceRecord.setCreateTime(LocalTime.now());
		MaskPriceRecord insertData = dao.save(maskPriceRecord);
		Optional<MaskPriceRecord> result = dao.findById(insertData.getSeqNo());
		assertNotNull(result.get());
		assertEquals(insertData.getSeqNo(), result.get().getSeqNo(), "dao didn't delete data");
	}

	@Test
	@Order(3)
	public void testUpdate() {
		List<MaskPriceRecord> query = dao.findAll();
		BigDecimal updatePrice = new BigDecimal("8.09");
		for (MaskPriceRecord maskPriceRecord : query) {
			maskPriceRecord.setPrice(updatePrice);
			MaskPriceRecord result = dao.save(maskPriceRecord);
			assertTrue(result.getPrice().compareTo(updatePrice) == 0, "update fail");
		}
	}

	@Test
	@Order(4)
	public void testDelete() throws InterruptedException {
		dao.deleteAll();
		List<MaskPriceRecord> result = dao.findAll();
		assertEquals(0, result.size(), "dao didn't delete data");
	}

}
