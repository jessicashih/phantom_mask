package kdan.jessica.phantommask.model;

import lombok.Data;

@Data
public class TopUserRq {

    private String startDate;
    private String endDate;
    private Integer top;
}
