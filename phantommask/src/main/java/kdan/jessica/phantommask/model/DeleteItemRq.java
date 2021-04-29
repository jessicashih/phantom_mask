package kdan.jessica.phantommask.model;

import lombok.Data;

@Data
public class DeleteItemRq {
    private Long itemNo;
    private Long pharmacySeqno;
}
