package kdan.jessica.phantommask.service.impl;

import kdan.jessica.phantommask.model.PharmacyRs;

import java.math.BigDecimal;
import java.util.List;

public interface MaskService {

    List<PharmacyRs> queryMaskPrice(BigDecimal priceMoreThan, BigDecimal priceLessThan);
}
