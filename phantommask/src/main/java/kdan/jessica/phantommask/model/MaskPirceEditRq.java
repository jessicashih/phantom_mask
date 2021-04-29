package kdan.jessica.phantommask.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MaskPirceEditRq {

    private Long itemNo;
    private BigDecimal price;
}
