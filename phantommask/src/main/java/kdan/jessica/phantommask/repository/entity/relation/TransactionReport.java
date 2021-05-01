package kdan.jessica.phantommask.repository.entity.relation;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
public class TransactionReport {

    @Column(name = "uuid")
    @Id
    private String uuid;

    @Column(name = "item_no")
    private Long itemNo;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_color")
    private String itemColor;

    @Column(name = "item_num_of_pack")
    private int itemNumOfPack;

    @Column(name = "amount_of_item")
    private int amountOfItem;

    @Column(name = "amount_of_dollar")
    private BigDecimal amountOfDollar;
}
