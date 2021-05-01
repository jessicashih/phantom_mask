package kdan.jessica.phantommask.service;

import kdan.jessica.phantommask.model.PharmacyRs;

import java.math.BigDecimal;
import java.util.List;

public interface MaskService {
    /**
     * 依價格查詢口罩
     * @param priceMoreThan 查詢價格上限
     * @param priceLessThan 查詢價格下限
     * @return 查詢結果
     */
    List<PharmacyRs> queryMaskPrice(BigDecimal priceMoreThan, BigDecimal priceLessThan);

    /**
     * 修改口罩名稱
     * @param itemNo 口罩貨號
     * @param itemName 修改名稱
     */
    void updateName(Long itemNo, String itemName);
}
