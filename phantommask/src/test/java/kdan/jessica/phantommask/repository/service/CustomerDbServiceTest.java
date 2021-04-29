package kdan.jessica.phantommask.repository.service;

import kdan.jessica.phantommask.repository.relation.TopUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CustomerDbServiceTest {
    @Autowired
    private CustomerDbService customerDbService;

    @Test
    public void testFindTopUser(){
        List<TopUser> topUsersResult = customerDbService.findTopUser
                (LocalDate.of(2021,04,01),
                        LocalDate.of(2021,04,30),5);
        assertEquals(1,topUsersResult.size(),"Result size not match");
        assertEquals("Eric Underwood",topUsersResult.get(0).getName(),"Customer name not match.");
    }

}
