package kdan.jessica.phantommask.service;

import kdan.jessica.phantommask.json.TransformJsonData;
import kdan.jessica.phantommask.model.TopUserRq;
import kdan.jessica.phantommask.model.TopUserRs;
import kdan.jessica.phantommask.repository.dao.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class CustomerServiceTest extends BaseServiceTest{

    @Autowired
    private CustomerService customerService;


    @Test
    public void findTopUsersByTime() {
        TopUserRq request = new TopUserRq();
        request.setStartDate("2021-01-01");
        request.setEndDate("2021-04-30");
        List<TopUserRs> response = customerService.findTopUsers(request);
        assertEquals(20, response.size(), "size not match");
        assertEquals("Mae Hill", response.get(0).getName(), "The top user not match");
    }

    @Test
    public void findTopUsersByTimeAndTop() {
        int top = 1;
        TopUserRq request = new TopUserRq();
        request.setStartDate("2021-01-01");
        request.setEndDate("2021-04-30");
        request.setTop(top);
        List<TopUserRs> response = customerService.findTopUsers(request);
        assertEquals(top, response.size(), "size not match");
        assertEquals("Mae Hill", response.get(0).getName(), "The top user not match");
    }

    @Test
    public void findTopUsersEmptyRequest() {
        TopUserRq request = new TopUserRq();
        List<TopUserRs> response = customerService.findTopUsers(request);
        assertEquals(0, response.size(), "size not match");
    }
}
