package kdan.jessica.phantommask.repository.service;

import kdan.jessica.phantommask.repository.dao.CustomerDao;
import kdan.jessica.phantommask.repository.entity.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Customer DB Service
 */
@Service
@Slf4j
public class CustomerDbService {

    @Autowired
    private CustomerDao customerDao;

    public Optional<Customer> findById(Long customerId) {
        return customerDao.findById(customerId);
    }
}
