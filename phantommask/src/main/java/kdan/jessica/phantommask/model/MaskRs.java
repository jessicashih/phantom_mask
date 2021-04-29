package kdan.jessica.phantommask.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

@JsonInclude(Include.NON_NULL)
@Data
public class MaskRs {

	private Long itemNo;
	private String name;
	private String color;
	private String numOfPack;
	private BigDecimal price;

	public void setNumOfPack(int numOfPack) {
		this.numOfPack = numOfPack+" pre pack";
	}
	
}
