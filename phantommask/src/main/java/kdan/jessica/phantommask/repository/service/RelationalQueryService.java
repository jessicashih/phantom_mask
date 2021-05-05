package kdan.jessica.phantommask.repository.service;

import kdan.jessica.phantommask.repository.entity.relation.PharmacyPriceMaskRelation;
import kdan.jessica.phantommask.repository.entity.relation.TopUser;
import kdan.jessica.phantommask.repository.entity.relation.TransactionReport;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * 關聯查詢
 */
@Service
@Slf4j
public class RelationalQueryService {

    @PersistenceContext
    private EntityManager em;

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
                "                mask_price_record as mpr on mpr.seq_no=pc.price_record " +
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

    public List<PharmacyPriceMaskRelation> findPharmacyWithMaskByPrice(BigDecimal priceMoreThan, BigDecimal priceLessThan) {
        // 查詢條件
        String sql =
                "SELECT " +
                        "        UUID() as uuid," +
                        "        p.seq_no as pharmacy_seqno," +
                        "        p.name as pharmacy_name," +
                        "        m.item_no as item_no," +
                        "        m.name as mask_name," +
                        "        m.color as mask_color," +
                        "        m.num_of_pack as mask_num_of_pack," +
                        "        mpr.price as mask_price " +
                        "FROM " +
                        "        pharmacy as p " +
                        "left join" +
                        "        mask_price_record as mpr on mpr.pharmacy_seqno = p.seq_no " +
                        "left join" +
                        "        mask as m on mpr.item_no = m.item_no " +
                        "where" +
                        "        mpr.is_delete is null ";
        Query query = null;
        if (ObjectUtils.isNotEmpty(priceMoreThan) && ObjectUtils.isNotEmpty(priceLessThan)) {
            sql = sql + "and mpr.price between ?1 and ?2";
            query = em.createNativeQuery(sql, PharmacyPriceMaskRelation.class);
            query.setParameter(1, priceMoreThan);
            query.setParameter(2, priceLessThan);
        } else if (ObjectUtils.isNotEmpty(priceMoreThan)) {
            sql = sql + "and mpr.price > ?1 ";
            query = em.createNativeQuery(sql, PharmacyPriceMaskRelation.class);
            query.setParameter(1, priceMoreThan);
        } else if (ObjectUtils.isNotEmpty(priceLessThan)) {
            sql = sql + "and mpr.price < ?1 ";
            query = em.createNativeQuery(sql, PharmacyPriceMaskRelation.class);
            query.setParameter(1, priceLessThan);
        } else {
            query = em.createNativeQuery(sql, PharmacyPriceMaskRelation.class);
        }
        return query.getResultList();
    }

    public List<TransactionReport> findTotalTransaction(LocalDate startDate, LocalDate endDate) {
        // 查詢條件
        String sql = "Select  " +
                "       UUID() as uuid, " +
                "       mpr.item_no as item_no, " +
                "       m.name as item_name, " +
                "       m.color as item_color, " +
                "       m.num_of_pack as item_num_of_pack, " +
                "       count(mpr.item_no) as amount_of_item, " +
                "       sum(mpr.price) as amount_of_dollar " +
                "From " +
                "        purchase_record  as pc " +
                "left join " +
                "        mask_price_record as mpr on mpr.seq_no=pc.price_record " +
                "left join " +
                "        mask as m on mpr.item_no = m.item_no " +
                "where  " +
                "        pc.create_date between ?1 and ?2 " +
                "group by " +
                "        mpr.item_no ";
        Query query = em.createNativeQuery(sql, TransactionReport.class);
        query.setParameter(1, startDate);
        query.setParameter(2, endDate);
        return query.getResultList();
    }
}
