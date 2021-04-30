package kdan.jessica.phantommask.model;

import lombok.Data;

@Data
public class PurchasesRs {
    private String transactionId;

    public PurchasesRs() {}

    public PurchasesRs(String transactionId) {
        this.transactionId = transactionId;
    }
}
