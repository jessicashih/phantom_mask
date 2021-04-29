package kdan.jessica.phantommask.model;

import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Data
public class EditPharmacyNameAndPriceRq {

    private Long pharmacySeqno;
    private String pharmacyName;
    private List<MaskPirceEditRq> maskPrices;

}
