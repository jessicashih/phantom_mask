package kdan.jessica.phantommask.repository.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import kdan.jessica.phantommask.repository.ex.DataException;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import kdan.jessica.phantommask.repository.dao.MaskDao;
import kdan.jessica.phantommask.repository.entity.Mask;

/**
 * Mask BD Service
 */
@Service
public class MaskDbService {

    @Autowired
    private MaskDao maskDao;

    public Optional<Mask> findById(Long itemNo) {
        return maskDao.findById(itemNo);
    }

    public Mask update(Mask mask) {
        Long id = mask.getItemNo();
        if(ObjectUtils.isNotEmpty(id)&&maskDao.existsById(id)){
            return maskDao.save(mask);
        }else {
            throw new DataException("The Data is not exist, please use insert");
        }
    }

    public List<Mask> findAll() {
        return maskDao.findAll();
    }

    /**
     *
     * @param itemNos 查詢條件:Mask.ItemNo
     * @return query result
     */
    public List<Mask> findByItemNoIn(List<Long> itemNos) {
        Specification<Mask> specification = new Specification<>() {
            @Override
            public Predicate toPredicate(Root<Mask> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate itemNoIn = null;
                if (itemNos != null && !itemNos.isEmpty()) {
                    itemNoIn = root.get("itemNo").in(itemNos);
                }
                return cb.and(itemNoIn);
            }
        };
        return maskDao.findAll(specification);
    }
}
