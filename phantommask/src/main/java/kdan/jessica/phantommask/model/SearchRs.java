package kdan.jessica.phantommask.model;

import kdan.jessica.phantommask.model.MaskRs;
import kdan.jessica.phantommask.model.PharmacyRs;
import lombok.Data;

import java.util.List;

@Data
public class SearchRs {
    List<PharmacyRs> pharmacyRsList;
    List<MaskRs> maskRsList;
}
