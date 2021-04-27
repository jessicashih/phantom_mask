package kdan.jessica.phantommask.repository.service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

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

	public List<Pharmacy> findOpenedPharmacy(DayOfWeek week, LocalTime time) {
		Specification<Pharmacy> specification = new Specification<Pharmacy>() {
			@Override
			public Predicate toPredicate(Root<Pharmacy> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate openPredicate= null;
				Predicate closePredicate= null;
				String columnOpen=null;
				String columnClose=null;
				switch (week) {
				case MONDAY:
					columnOpen="monOpen";
					columnClose="monClose";
					break;
				case TUESDAY:
					columnOpen="tueOpen";
					columnClose="tueClose";
					break;
				case WEDNESDAY:
					columnOpen="wedOpen";
					columnClose="wedClose";
					break;
				case THURSDAY:
					columnOpen="thuOpen";
					columnClose="thuClose";
					break;
				case FRIDAY:
					columnOpen="friOpen";
					columnClose="friClose";
					break;
				case SATURDAY:
					columnOpen="satOpen";
					columnClose="satClose";
					break;
				case SUNDAY:
					columnOpen="sunOpen";
					columnClose="sunClose";
					break;
				}
				openPredicate= cb.greaterThanOrEqualTo(root.get(columnClose), time);
				closePredicate= cb.lessThanOrEqualTo(root.get(columnOpen), time);
				return cb.and(openPredicate,closePredicate);
			}
		};
		return dao.findAll(specification);
	}

}
