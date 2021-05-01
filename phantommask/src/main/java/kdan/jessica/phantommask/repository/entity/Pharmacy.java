package kdan.jessica.phantommask.repository.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
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

	@Column(name="wed_open")
	private LocalTime wedOpen;
	
	@Column(name="wed_close")
	private LocalTime wedClose;

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

}
