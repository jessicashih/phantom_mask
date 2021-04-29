package kdan.jessica.phantommask.repository.service;

import kdan.jessica.phantommask.repository.dao.MaskPriceRecordsDao;
import kdan.jessica.phantommask.repository.entity.MaskPriceRecords;
import kdan.jessica.phantommask.repository.relation.PharmacyPriceMaskRelation;
import kdan.jessica.phantommask.repository.relation.TransactionReport;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MaskPriceRecordsDbService {
    @Autowired
    private MaskPriceRecordsDao dao;

    @PersistenceContext
    private EntityManager em;

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

    public List<PharmacyPriceMaskRelation> pharmacyRelationQuery(BigDecimal priceMoreThan, BigDecimal priceLessThan){
        // 查詢條件
        String sql =
                "SELECT " +
                "        UUID() as uuid,"+
                "        p.seq_no as pharmacy_seqno," +
                "        p.name as pharmacy_name," +
                "        p.balance as pharmacy_balance," +
                "        m.item_no as item_no," +
                "        m.name as mask_name," +
                "        m.color as mask_color," +
                "        m.num_of_pack as mask_num_of_pack," +
                "        mpr.price as mask_price " +
                "FROM " +
                "        pharmacie as p " +
                "left join" +
                "        mask_price_records as mpr on mpr.pharmacy_seqno = p.seq_no " +
                "left join" +
                "        mask as m on mpr.item_no = m.item_no " +
                "where" +
                "        mpr.is_delete is null ";
        Query query = null;
        if(ObjectUtils.isNotEmpty(priceMoreThan)&&ObjectUtils.isNotEmpty(priceLessThan)){
            sql=sql+"and mpr.price between ?1 and ?2";
            query = em.createNativeQuery(sql, PharmacyPriceMaskRelation.class);
            query.setParameter(1, priceMoreThan);
            query.setParameter(2, priceLessThan);
        }else if(ObjectUtils.isNotEmpty(priceMoreThan)){
            sql=sql+"and mpr.price > ?1 ";
            query = em.createNativeQuery(sql, PharmacyPriceMaskRelation.class);
            query.setParameter(1, priceMoreThan);
        }else if(ObjectUtils.isNotEmpty(priceLessThan)){
            sql=sql+"and mpr.price < ?1 ";
            query = em.createNativeQuery(sql, PharmacyPriceMaskRelation.class);
            query.setParameter(1, priceLessThan);
        }else{
            query = em.createNativeQuery(sql, PharmacyPriceMaskRelation.class);
        }
        return query.getResultList();
    }

    public List<TransactionReport> findTotalTransaction(LocalDate startDate, LocalDate endDate){
        // 查詢條件
        String sql ="Select  " +
                "       UUID() as uuid, " +
                "       mpr.item_no as item_no, " +
                "       m.name as item_name, " +
                "       m.color as item_color, " +
                "       m.num_of_pack as item_num_of_pack, " +
                "       count(mpr.item_no) as amount_of_item, " +
                "       sum(mpr.price) as amount_of_dollar "+
                "From " +
                "        purchase_record  as pc " +
                "left join " +
                "        mask_price_records as mpr on mpr.seq_no=pc.price_record " +
                "left join " +
                "        mask as m on mpr.item_no = m.item_no " +
                "where  " +
                "        pc.create_date between ?1 and ?2 " +
                "group by " +
                "        mpr.item_no " ;
        Query query= em.createNativeQuery(sql, TransactionReport.class);
        query.setParameter(1, startDate);
        query.setParameter(2, endDate);
        return query.getResultList();
    }
}
