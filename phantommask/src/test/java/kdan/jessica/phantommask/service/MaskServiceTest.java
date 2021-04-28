package kdan.jessica.phantommask.service;

import kdan.jessica.phantommask.model.PharmacyRs;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MaskServiceTest {
    @Autowired
    private MaskService maskService;

    @Test
    public void testQueryMaskPrice(){
        List<PharmacyRs> result = maskService.queryMaskPrice(new BigDecimal("9"),null);
        assertEquals(1,result.size(),"size not match");
        assertEquals(2,result.get(0).getMasks().size(),"Mask size not match");
    }
}
