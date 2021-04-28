package kdan.jessica.phantommask.model;

public class FindPharmacyProductsRq {
	
	private Long pharmacySeqno;
	
	private String sortBy;
	
	public Long getPharmacySeqno() {
		return pharmacySeqno;
	}
	public void setPharmacySeqno(Long pharmacySeqno) {
		this.pharmacySeqno = pharmacySeqno;
	}
	public String getSortBy() {
		return sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	
}
