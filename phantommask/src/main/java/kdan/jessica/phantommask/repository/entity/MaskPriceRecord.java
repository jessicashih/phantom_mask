package kdan.jessica.phantommask.repository.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mask_price_record")
public class MaskPriceRecord {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="seq_no")
	private Long seqNo;
	
	@Column(name="pharmacy_seqno")
	private Long pharmacySeqno;
	
	@Column(name="item_no")
	private Long itemNo;

	@Column(name="price")
	private BigDecimal price;
	
	@Column(name="is_delete")
	private Boolean isDelete;
	
	@Column(name="create_date")
	private LocalDate createDate;
	
	@Column(name="create_time")
	private LocalTime createTime;
	
	@Column(name="update_date")
	private LocalDate updateDate;
	
	@Column(name="update_time")
	private LocalTime updateTime;

	public Long getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Long seqNo) {
		this.seqNo = seqNo;
	}


	public Long getPharmacySeqno() {
		return pharmacySeqno;
	}

	public void setPharmacySeqno(Long pharmacySeqno) {
		this.pharmacySeqno = pharmacySeqno;
	}

	public Long getItemNo() {
		return itemNo;
	}

	public void setItemNo(Long itemNo) {
		this.itemNo = itemNo;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
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

	public LocalDate getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDate updateDate) {
		this.updateDate = updateDate;
	}

	public LocalTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalTime updateTime) {
		this.updateTime = updateTime;
	}
	
	

}
