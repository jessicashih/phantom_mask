package kdan.jessica.phantommask.repository.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="purchase_record")
public class PurchaseRecord{
	
	@Id
	@Column(name="uuid")
	private String uuid;
	
	@Column(name="customer_id")
	private Long customerId;
	
	@Column(name="price_record")
	private Long priceRecord;
	
	@Column(name="create_date")
	private LocalDate createDate;
	
	@Column(name="create_time")
	private LocalTime createTime;
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getPriceRecord() {
		return priceRecord;
	}

	public void setPriceRecord(Long priceRecord) {
		this.priceRecord = priceRecord;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public LocalTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalTime createTime) {
		this.createTime = createTime;
	}

}
