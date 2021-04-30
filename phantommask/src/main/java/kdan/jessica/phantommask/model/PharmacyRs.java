package kdan.jessica.phantommask.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

@JsonInclude(Include.NON_NULL)
@Data
public class PharmacyRs {
	
	private Long seqNo;
	private String name;
	private List<MaskRs> masks;
	
}
