package kdan.jessica.phantommask.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TopUserRs {

    private String customerId;
    private String name;
    private BigDecimal totalPrice;

    public TopUserRs(String customerId, String name, BigDecimal totalPrice) {
        this.customerId = customerId;
        this.name = name;
        this.totalPrice = totalPrice;
    }
}
