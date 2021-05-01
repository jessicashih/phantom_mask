package kdan.jessica.phantommask.service;

import kdan.jessica.phantommask.model.TransactionRepostRs;
import org.springframework.transaction.annotation.Transactional;

public interface TransactionService {

    /**
     * 購買口罩交易
     *
     * @param pharmacySeqNo 藥局編號
     * @param customerId    顧客編號
     * @param itemNo        口罩貨號
     * @return 交易編號
     */
    @Transactional(rollbackFor = Exception.class)
    String purchasesTransaction(Long pharmacySeqNo, Long customerId, Long itemNo);

    /**
     * 查詢總額及總量
     *
     * @param startDate 查詢起日
     * @param endDate   查詢迄日
     * @return 查詢結果
     */
    TransactionRepostRs findTotalTransaction(String startDate, String endDate);
}
