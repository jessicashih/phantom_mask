package kdan.jessica.phantommask.repository.service;

import kdan.jessica.phantommask.mockdata.MockMask;
import kdan.jessica.phantommask.repository.dao.MaskDao;
import kdan.jessica.phantommask.repository.entity.Mask;
import kdan.jessica.phantommask.repository.ex.DataException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Mask DBService Test
 */
@SpringBootTest
@ActiveProfiles("test")
public class MaskDbServiceTest {

    @Autowired
    private MaskDbService dbService;
    @Autowired
    private MaskDao dao;
    private Mask exceptData;

    @BeforeEach
    public void initData() {
        Mask mockdata = MockMask.mock();
        exceptData = dao.save(mockdata);
    }

    @Test
    public void findById() {
        Long itemNo = exceptData.getItemNo();
        Optional<Mask> result = dbService.findById(itemNo);
        assertTrue(result.isPresent(), "Result can't be null.");
        assertEquals(exceptData.getName(), result.get().getName(), "Mask name not match.");
    }

    /**
     * GIVEN: The update mask data itemNo is null
     * WHEN : call dbService
     * THEN : throw DataException
     */
    @Test
    public void updateAndItemIsNull() {
        Mask mask = MockMask.mock();
        assertThrows(DataException.class, () -> dbService.update(mask), "The test need throw exception.");
    }

    @Test
    public void update() {
        String updateStr = "testName";
        Mask maskUpdate = new Mask();
        maskUpdate.setItemNo(exceptData.getItemNo());
        maskUpdate.setName(updateStr);
        Mask maskAfter = dbService.update(maskUpdate);
        assertEquals(updateStr, maskAfter.getName(), "Update name not match.");

    }

    @Test
    public void findAll() {
        List<Mask> results = dbService.findAll();
        assertEquals(1, results.size(), "Result size not match.");
    }

    @Test
    public void findByIdIn() {
        List<Long> itemNos = List.of(exceptData.getItemNo());
        List<Mask> result = dbService.findByItemNoIn(itemNos);
        for (Mask mask : result) {
            assertEquals(exceptData.getName(), mask.getName(), "Mask name not match.");
        }
    }

    @AfterEach
    public void deleteMockData() {
        dao.deleteAll();
    }

}
