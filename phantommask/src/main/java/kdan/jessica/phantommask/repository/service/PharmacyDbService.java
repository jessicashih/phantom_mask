package kdan.jessica.phantommask.repository.service;

import kdan.jessica.phantommask.repository.dao.PharmacyDao;
import kdan.jessica.phantommask.repository.entity.Pharmacy;
import kdan.jessica.phantommask.repository.ex.DataException;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

/**
 * Pharmacy DbService
 */
@Service
public class PharmacyDbService {
    @Autowired
    private PharmacyDao pharmacyDao;

    public Optional<Pharmacy> findById(Long id) {
        return pharmacyDao.findById(id);
    }

    public Pharmacy update(Pharmacy pharmacy) {
        Long id = pharmacy.getSeqNo();
        if (ObjectUtils.isNotEmpty(id) && pharmacyDao.existsById(id)) {
            return pharmacyDao.save(pharmacy);
        } else {
            throw new DataException("The Data is not exist, please use insert");
        }
    }

    public List<Pharmacy> findAll() {
        return pharmacyDao.findAll();
    }

    /**
     * 查詢某一"日期時間"有開店的藥局
     *
     * @param dayOfWeek 星期幾
     * @param time      查詢時間
     * @return 查詢結果
     */
    public List<Pharmacy> findOpenedPharmacy(DayOfWeek dayOfWeek, LocalTime time) {
        Specification<Pharmacy> specification = new Specification<Pharmacy>() {
            @Override
            public Predicate toPredicate(Root<Pharmacy> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                String dayOfWeekStr = getDayOfWeekStr(dayOfWeek);
                Predicate openPredicate = null;
                Predicate closePredicate = null;
                if (ObjectUtils.isNotEmpty(time)) {
                    openPredicate = cb.greaterThanOrEqualTo(root.get(dayOfWeekStr + "Close"), time);
                    closePredicate = cb.lessThanOrEqualTo(root.get(dayOfWeekStr + "Open"), time);
                } else {
                    openPredicate = cb.isNotNull(root.get(dayOfWeekStr + "Close"));
                    closePredicate = cb.isNotNull(root.get(dayOfWeekStr + "Open"));
                }

                return cb.and(openPredicate, closePredicate);
            }
        };
        return pharmacyDao.findAll(specification);
    }

    /**
     * 查詢某一"日期"有開店的藥局
     *
     * @param dayOfWeek 星期幾
     * @return 查詢結果
     */
    public List<Pharmacy> findOpenedPharmacy(DayOfWeek dayOfWeek) {
        return findOpenedPharmacy(dayOfWeek, null);
    }

    /**
     * 取得星期幾-DB欄位開頭名稱
     *
     * @param week 星期"幾"
     * @return DB欄位開頭名稱
     */
    private String getDayOfWeekStr(DayOfWeek week) {
        String column = null;
        switch (week) {
            case MONDAY:
                column = "mon";
                break;
            case TUESDAY:
                column = "tue";
                break;
            case WEDNESDAY:
                column = "wed";
                break;
            case THURSDAY:
                column = "thu";
                break;
            case FRIDAY:
                column = "fri";
                break;
            case SATURDAY:
                column = "sat";
                break;
            case SUNDAY:
                column = "sun";
                break;
        }
        return column;
    }

}
