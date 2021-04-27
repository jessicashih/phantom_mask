package kdan.jessica.phantommask.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mask")
public class Mask{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="item_no")
	private Long item_no;

	@Column(name="name",length=50)
	private String name;
	
	@Column(name="color",length = 20)
	private String color;
	
	@Column(name="num_of_pack")
	private int numOfPack;
	
	public Long getItem_no() {
		return item_no;
	}

	public void setItem_no(Long item_no) {
		this.item_no = item_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getNumOfPack() {
		return numOfPack;
	}

	public void setNumOfPack(int numOfPack) {
		this.numOfPack = numOfPack;
	}
	
}
