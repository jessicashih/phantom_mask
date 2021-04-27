package kdan.jessica.phantommask.repository.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kdan.jessica.phantommask.repository.entity.Customer;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class CustomerDaoTest {
	
	@Autowired
	private CustomerDao dao;
	
	@Test
	@Order(1)
	public void testInsert() {
		Customer customer = new Customer();
		customer.setName("Eric Underwood");
		customer.setBalance(new BigDecimal("952.69"));
		Customer result =dao.save(customer);
		assertNotNull(result, "result not be null");
	}
	
	@Test
	@Order(2)
	public void testQuery() {
		List<Customer> result =dao.findAll();
		assertEquals(1, result.size(),"dao didn't delete data");
	}
	
	@Test
	@Order(3)
	public void testUpdate() {
		List<Customer> query =dao.findAll();
		for (Customer customer : query) {
			customer.setName("Eric");
			Customer result =dao.save(customer);
			assertEquals("Eric", result.getName(),"name not match, update fail");
		}
	}
	
	@Test
	@Order(4)
	public void testDelete() throws InterruptedException {
		dao.deleteAll();
		List<Customer> result=dao.findAll();
		assertEquals(0, result.size(),"dao didn't delete data");
	}
	
}
