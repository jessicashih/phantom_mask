package kdan.jessica.phantommask.repository.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kdan.jessica.phantommask.repository.entity.Customer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

/**
 * Customer Dao CRUD test
 */
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@ActiveProfiles("test")
public class CustomerDaoTest {
	
	@Autowired
	private CustomerDao dao;
	private Long customerId;

	@Test
	@Order(1)
	public void testInsert() {
		String customerName="Eric Underwood";
		Customer customer = new Customer();
		customer.setName(customerName);
		customer.setBalance(new BigDecimal("952.69"));
		Customer result =dao.save(customer);
		customerId = result.getCustomerId();
		assertNotNull(result, "result not be null");
	}
	
	@Test
	@Order(2)
	public void testQuery() {
		String customerName="Eric Underwood";
		Customer customer = new Customer();
		customer.setName(customerName);
		customer.setBalance(new BigDecimal("952.69"));
		Customer insertData =dao.save(customer);
		Optional<Customer> result =dao.findById(insertData.getCustomerId());
		assertNotNull(result.get());
		assertEquals(customerName,result.get().getName(),"customer name not match");
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
