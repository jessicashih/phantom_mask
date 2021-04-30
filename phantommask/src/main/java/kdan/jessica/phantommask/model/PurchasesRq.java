package kdan.jessica.phantommask.model;

import lombok.Data;

@Data
public class PurchasesRq {
    private Long pharmacySeqNo;
    private Long customerId;
    private Long itemNo;
}
