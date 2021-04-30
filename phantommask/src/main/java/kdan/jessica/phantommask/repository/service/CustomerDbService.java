package kdan.jessica.phantommask.repository.service;

import kdan.jessica.phantommask.repository.dao.CustomerDao;
import kdan.jessica.phantommask.repository.entity.Customer;
import kdan.jessica.phantommask.repository.relation.TopUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomerDbService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private CustomerDao dao;

    public Optional<Customer> findById(Long customerId) {
        return dao.findById(customerId);
    }

    public List<TopUser> findTopUser(LocalDate startDate, LocalDate endDate, Integer top) {
        log.debug("findTopUser.startDate: {},endDate: {},top: {}", startDate, endDate, top);
        String sql = "select  " +
                "        c.customer_id, " +
                "        c.name, " +
                "        total_price " +
                "from( " +
                "        Select  " +
                "                pc.customer_id, " +
                "                sum(mpr.price) as total_price " +
                "        From " +
                "                purchase_record as pc " +
                "         " +
                "        left join " +
                "                mask_price_records as mpr on mpr.seq_no=pc.price_record " +
                "        where " +
                "                pc.create_date between ?1 and ?2 " +
                "        group by " +
                "                pc.customer_id " +
                "        order by " +
                "                total_price desc " +
                ")as price_count " +
                "left join " +
                "        customer as c on price_count.customer_id = c.customer_id ";

        if (ObjectUtils.isNotEmpty(top)) {
            sql = sql + "LIMIT ?3";
        }
        log.debug("findTopUser query sql: {}", sql);
        Query query = em.createNativeQuery(sql, TopUser.class);
        query.setParameter(1, startDate);
        query.setParameter(2, endDate);
        if (ObjectUtils.isNotEmpty(top)) {
            query.setParameter(3, top);
        }
        return query.getResultList();
    }
}
