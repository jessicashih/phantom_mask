package kdan.jessica.phantommask.repository.service;

import kdan.jessica.phantommask.repository.dao.MaskPriceRecordsDao;
import kdan.jessica.phantommask.repository.entity.MaskPriceRecords;
import kdan.jessica.phantommask.repository.entity.Pharmacy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class MaskPriceRecordsDbService {
    @Autowired
    private MaskPriceRecordsDao dao;

    public List<MaskPriceRecords> findByPharmacySeqno(List<Long> pharmacySeqnos){

        Specification<MaskPriceRecords> specification = new Specification<>() {
            @Override
            public Predicate toPredicate(Root<MaskPriceRecords> record, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                Predicate pharmacySeqnoIn =null;
                if(pharmacySeqnos!=null && !pharmacySeqnos.isEmpty()) {
                    pharmacySeqnoIn = record.get("pharmacySeqno").in(pharmacySeqnos);
                }
                Predicate notDelete = record.get("isDelete").isNull();
                return cb.and(pharmacySeqnoIn,notDelete);
            }
        };
        return dao.findAll(specification);
    }
}
