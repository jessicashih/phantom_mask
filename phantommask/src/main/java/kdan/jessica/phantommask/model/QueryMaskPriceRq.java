package kdan.jessica.phantommask.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class QueryMaskPriceRq {

    private BigDecimal priceMoreThan;

    private BigDecimal priceLessThan;

}
