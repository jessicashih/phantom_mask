package kdan.jessica.phantommask.model;

import lombok.Data;

import javax.persistence.Column;

@Data
public class MaskAmountDetail {

    private Long itemNo;
    private String name;
    private String color;
    private String numOfPack;
    private int itemNumOfPack;
    private int amountOfItem;
}
