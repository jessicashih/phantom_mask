package kdan.jessica.phantommask.json.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PurchaseHistoriesJson {
    private String pharmacyName;
    private String maskName;
    private BigDecimal transactionAmount;
    private String transactionDate;
}
