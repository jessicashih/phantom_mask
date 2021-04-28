package kdan.jessica.phantommask.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class FindOpenPharmaciesRs {
	private List<PharmacyRs> pharmacies;

	public List<PharmacyRs> getPharmacies() {
		return pharmacies;
	}

	public void setPharmacies(List<PharmacyRs> pharmacies) {
		this.pharmacies = pharmacies;
	}

}

