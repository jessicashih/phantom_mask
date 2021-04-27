package kdan.jessica.phantommask.service;

import kdan.jessica.phantommask.model.FindOpenPharmaciesRs;

public interface PharmacyService {
	FindOpenPharmaciesRs findOpenPharmacies(String dateTimeStr);
}
