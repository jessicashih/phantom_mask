package kdan.jessica.phantommask.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kdan.jessica.phantommask.model.FindOpenPharmaciesRs;
import kdan.jessica.phantommask.repository.entity.Pharmacy;
import kdan.jessica.phantommask.repository.service.PharmacieDbService;
import kdan.jessica.phantommask.service.PharmacyService;

@Service
public class PharmacyServiceImpl implements PharmacyService{
	
	@Autowired
	private PharmacieDbService dbService;

	@Override
	public FindOpenPharmaciesRs findOpenPharmacies(String dateTimeStr) {
		System.out.println("findOpenPharmacies Start");
		LocalDateTime dateTime=LocalDateTime.parse(dateTimeStr,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		List<Pharmacy> queryResult = dbService.findOpenedPharmacy(dateTime.getDayOfWeek(), dateTime.toLocalTime());
		FindOpenPharmaciesRs response = new FindOpenPharmaciesRs();
		List<String> pharmacies = new ArrayList<>();
		response.setPharmacies(pharmacies);
		queryResult.stream().forEach(p->{
			pharmacies.add(p.getName());
		});
		System.out.println("findOpenPharmacies End");
		return response;
	}

}
