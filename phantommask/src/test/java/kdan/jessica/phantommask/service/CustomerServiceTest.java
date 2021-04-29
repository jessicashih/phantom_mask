package kdan.jessica.phantommask.service;

import kdan.jessica.phantommask.model.TopUserRq;
import kdan.jessica.phantommask.model.TopUserRs;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test
    public void test(){
        TopUserRq request = new TopUserRq();
        List<TopUserRs> response = customerService.findTopUsers(request);
        assertEquals(0,response.size(),"size not match");

    }
}
