package kdan.jessica.phantommask.json.model;

import kdan.jessica.phantommask.json.model.MaskJson;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PharmacyJson {

    private String name;
    private BigDecimal cashBalance;
    private String openingHours;
    private List<MaskJson> masks;
}
