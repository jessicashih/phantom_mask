package kdan.jessica.phantommask.model;

import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.List;

@Data
public class TransactionRepostRs {

    private int totalAmountOfMask;
    private BigDecimal totalAmountOfDollarValue;
    private List<MaskAmountDetail> detail;

    public String getTotalAmountOfMask() {
        return totalAmountOfMask+" (片)";
    }


    public String getTotalAmountOfDollarValue() {
        return totalAmountOfDollarValue.toString()+" (元)";
    }
}
