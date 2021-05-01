package kdan.jessica.phantommask.repository.entity.relation;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class PharmacyPriceMaskRelation {

    @Id
    @Column(name="uuid")
    private String uuid;

    @Column(name="pharmacy_seqno")
    private Long pharmacySeqNo;

    @Column(name="pharmacy_name",length=100)
    private String pharmacyName;

    @Column(name="pharmacy_balance")
    private BigDecimal pharmacyBalance;

    @Column(name="item_no")
    private Long itemNo;

    @Column(name="mask_name")
    private String maskName;

    @Column(name="mask_color")
    private String maskColor;

    @Column(name="mask_num_of_pack")
    private int maskNumOfPack;

    @Column(name="mask_price")
    private BigDecimal maskPrice;

}
