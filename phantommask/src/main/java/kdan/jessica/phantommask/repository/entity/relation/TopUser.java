package kdan.jessica.phantommask.repository.entity.relation;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class TopUser {

    @Id
    @Column(name="customer_id")
    private String customerId;

    @Column(name="name",length=100)
    private String name;

    @Column(name="total_price")
    private BigDecimal totalPrice;
}
