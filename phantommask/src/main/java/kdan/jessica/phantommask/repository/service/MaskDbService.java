package kdan.jessica.phantommask.repository.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import kdan.jessica.phantommask.repository.dao.MaskDao;
import kdan.jessica.phantommask.repository.entity.Mask;

@Service
public class MaskDbService {

    @Autowired
    private MaskDao dao;

    public Optional<Mask> findById(Long itemNo){return  dao.findById(itemNo);}
    public Mask update(Mask mask){return dao.save(mask);}

    public List<Mask> findByItemNoIn(List<Long> itemNos){
        Specification<Mask> specification = new Specification<>() {
            @Override
            public Predicate toPredicate(Root<Mask> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate itemNoIn=null;
                if(itemNos!=null && !itemNos.isEmpty()) {
                    itemNoIn= root.get("itemNo").in(itemNos);
                }
                return cb.and(itemNoIn);
            }
        };
        return dao.findAll(specification);
    }
}
