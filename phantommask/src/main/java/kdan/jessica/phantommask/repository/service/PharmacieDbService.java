package kdan.jessica.phantommask.repository.service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import kdan.jessica.phantommask.repository.dao.PharmacyDao;
import kdan.jessica.phantommask.repository.entity.Pharmacy;

@Service
public class PharmacieDbService {
	@Autowired
	private PharmacyDao dao;

	public Optional<Pharmacy> findById(Long id){
		return dao.findById(id);
	}
	public Pharmacy update(Pharmacy pharmacy){
		return  dao.save(pharmacy);
	}
	public List<Pharmacy> findAll(){return dao.findAll();}
	/**
	 * 查詢某一日期時間有開店的藥局
	 * @param dayOfWeek 星期幾
	 * @param time 查詢時間
	 * @return 查詢結果
	 */
	public List<Pharmacy> findOpenedPharmacy(DayOfWeek dayOfWeek, LocalTime time) {
		Specification<Pharmacy> specification = new Specification<Pharmacy>() {
			@Override
			public Predicate toPredicate(Root<Pharmacy> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				String dayOfWeekStr=getDayOfWeekStr(dayOfWeek);
				Predicate openPredicate= cb.greaterThanOrEqualTo(root.get(dayOfWeekStr+"Close"), time);
				Predicate closePredicate= cb.lessThanOrEqualTo(root.get(dayOfWeekStr+"Open"), time);
				return cb.and(openPredicate,closePredicate);
			}
		};
		return dao.findAll(specification);
	}

	/**
	 * 查詢某一日期有開店的藥局
	 * @param dayOfWeek 星期幾
	 * @return 查詢結果
	 */
	public List<Pharmacy>findOpenedPharmacy(DayOfWeek dayOfWeek){
		Specification<Pharmacy> specification = new Specification<Pharmacy>() {
			@Override
			public Predicate toPredicate(Root<Pharmacy> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				String dayOfWeekStr=getDayOfWeekStr(dayOfWeek);
				Predicate openPredicate= cb.isNotNull(root.get(dayOfWeekStr+"Close"));
				Predicate closePredicate= cb.isNotNull(root.get(dayOfWeekStr+"Open"));
				
				return cb.and(openPredicate,closePredicate);
			}
		};
		return dao.findAll(specification);
	}
	
	/**
	 * 取得星期幾-DB欄位開頭名稱
	 * @param week 星期"幾"
	 * @return DB欄位開頭名稱
	 */
	private String getDayOfWeekStr(DayOfWeek week) {
		String column=null;
		switch (week) {
		case MONDAY:
			column="mon";
			break;
		case TUESDAY:
			column="tue";
			break;
		case WEDNESDAY:
			column="wed";
			break;
		case THURSDAY:
			column="thu";
			break;
		case FRIDAY:
			column="fri";
			break;
		case SATURDAY:
			column="sat";
			break;
		case SUNDAY:
			column="sun";
			break;
		}
		return column;
	}

}
