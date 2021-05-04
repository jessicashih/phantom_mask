package kdan.jessica.phantommask.repository.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kdan.jessica.phantommask.repository.entity.Mask;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mask Dao CRUD test
 */
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@ActiveProfiles("test")
public class MaskDaoTest {

	@Autowired
	private MaskDao dao;
	
	@Test
	@Order(1)
	public void testInsert() {
		Mask mask = new Mask();
		mask.setName("Masquerade");
		mask.setColor("black");
		mask.setNumOfPack(3);
		Mask result =dao.save(mask);
		assertNotNull(result, "result not be null");
	}
	
	@Test
	@Order(2)
	public void testQuery() {
		String maskName = "Masquerade";
		Mask mask = new Mask();
		mask.setName(maskName);
		mask.setColor("black");
		mask.setNumOfPack(3);
		Mask insertData =dao.save(mask);
		Optional<Mask> result =dao.findById(insertData.getItemNo());
		assertNotNull(result.get());
		assertEquals(maskName, result.get().getName(),"dao didn't delete data");
	}
	
	@Test
	@Order(3)
	public void testUpdate() {
		List<Mask> query =dao.findAll();
		for (Mask mask : query) {
			mask.setName("MaskT");
			Mask result =dao.save(mask);
			assertEquals("MaskT", result.getName(),"name not match, update fail");
		}
	}
	
	@Test
	@Order(4)
	public void testDelete() throws InterruptedException {
		dao.deleteAll();
		List<Mask> result=dao.findAll();
		assertEquals(0, result.size(),"dao didn't delete data");
	}
}
