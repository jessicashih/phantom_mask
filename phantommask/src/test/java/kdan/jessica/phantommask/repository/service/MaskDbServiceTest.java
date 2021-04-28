package kdan.jessica.phantommask.repository.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kdan.jessica.phantommask.repository.entity.Mask;

@SpringBootTest
public class MaskDbServiceTest {
	
	@Autowired
	private MaskDbService dbService;

	@Test
	public void testFindByIdIn() {
		List<Long> itemNos = List.of(1L, 2L);
		List<Mask> result = dbService.findByItemNoIn(itemNos);
		for (Mask mask : result) {
			System.out.println("Item_no:" + mask.getItemNo());
			System.out.println("Name:" + mask.getName());
		}
	}


}
