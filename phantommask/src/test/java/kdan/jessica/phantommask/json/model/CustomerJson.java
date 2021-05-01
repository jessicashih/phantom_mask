package kdan.jessica.phantommask.json.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CustomerJson {
    private String name;
    private BigDecimal cashBalance;
    private List<PurchaseHistoriesJson> purchaseHistories;
}
