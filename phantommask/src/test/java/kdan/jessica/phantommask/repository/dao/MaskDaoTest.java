package kdan.jessica.phantommask.repository.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kdan.jessica.phantommask.repository.entity.Mask;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
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
		List<Mask> result =dao.findAll();
		assertEquals(1, result.size(),"dao didn't delete data");
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
