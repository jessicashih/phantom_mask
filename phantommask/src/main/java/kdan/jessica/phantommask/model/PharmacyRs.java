package kdan.jessica.phantommask.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class PharmacyRs {
	
	private Long seqNo;

	private String name;

	private List<MaskRs> masks; 
	
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

	public List<MaskRs> getMasks() {
		return masks;
	}

	public void setMasks(List<MaskRs> masks) {
		this.masks = masks;
	}
	
}
