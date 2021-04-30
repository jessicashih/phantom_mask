package kdan.jessica.phantommask.repository.entity;

import java.math.BigDecimal;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pharmacy")
public class Pharmacy{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="seq_no")
	private Long seqNo;

	@Column(name="name",length=100)
	private String name;
	
	@Column(name="balance")
	private BigDecimal balance;
	
	@Column(name="mon_open")
	private LocalTime monOpen;
	
	@Column(name="mon_close")
	private LocalTime monClose;
	
	@Column(name="tue_open")
	private LocalTime tueOpen;
	
	@Column(name="tue_close")
	private LocalTime tueClose;

	@Column(name="wen_open")
	private LocalTime wenOpen;
	
	@Column(name="wen_close")
	private LocalTime wenClose;

	@Column(name="thu_open")
	private LocalTime thuOpen;
	
	@Column(name="thu_close")
	private LocalTime thuClose;

	@Column(name="fri_open")
	private LocalTime friOpen;
	
	@Column(name="fri_close")
	private LocalTime friClose;

	@Column(name="sat_open")
	private LocalTime satOpen;
	
	@Column(name="sat_close")
	private LocalTime satClose;

	@Column(name="sun_open")
	private LocalTime sunOpen;
	
	@Column(name="sun_close")
	private LocalTime sunClose;

	public Long getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Long seqNo) {
		this.seqNo = seqNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public LocalTime getMonOpen() {
		return monOpen;
	}

	public void setMonOpen(LocalTime monOpen) {
		this.monOpen = monOpen;
	}

	public LocalTime getMonClose() {
		return monClose;
	}

	public void setMonClose(LocalTime monClose) {
		this.monClose = monClose;
	}

	public LocalTime getTueOpen() {
		return tueOpen;
	}

	public void setTueOpen(LocalTime tueOpen) {
		this.tueOpen = tueOpen;
	}

	public LocalTime getTueClose() {
		return tueClose;
	}

	public void setTueClose(LocalTime tueClose) {
		this.tueClose = tueClose;
	}

	public LocalTime getWenOpen() {
		return wenOpen;
	}

	public void setWenOpen(LocalTime wenOpen) {
		this.wenOpen = wenOpen;
	}

	public LocalTime getWenClose() {
		return wenClose;
	}

	public void setWenClose(LocalTime wenClose) {
		this.wenClose = wenClose;
	}

	public LocalTime getThuOpen() {
		return thuOpen;
	}

	public void setThuOpen(LocalTime thuOpen) {
		this.thuOpen = thuOpen;
	}

	public LocalTime getThuClose() {
		return thuClose;
	}

	public void setThuClose(LocalTime thuClose) {
		this.thuClose = thuClose;
	}

	public LocalTime getFriOpen() {
		return friOpen;
	}

	public void setFriOpen(LocalTime friOpen) {
		this.friOpen = friOpen;
	}

	public LocalTime getFriClose() {
		return friClose;
	}

	public void setFriClose(LocalTime friClose) {
		this.friClose = friClose;
	}

	public LocalTime getSatOpen() {
		return satOpen;
	}

	public void setSatOpen(LocalTime satOpen) {
		this.satOpen = satOpen;
	}

	public LocalTime getSatClose() {
		return satClose;
	}

	public void setSatClose(LocalTime satClose) {
		this.satClose = satClose;
	}

	public LocalTime getSunOpen() {
		return sunOpen;
	}

	public void setSunOpen(LocalTime sunOpen) {
		this.sunOpen = sunOpen;
	}

	public LocalTime getSunClose() {
		return sunClose;
	}

	public void setSunClose(LocalTime sunClose) {
		this.sunClose = sunClose;
	}

}
