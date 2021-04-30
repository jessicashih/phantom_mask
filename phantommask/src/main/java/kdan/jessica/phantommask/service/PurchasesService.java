package kdan.jessica.phantommask.service;

import org.springframework.transaction.annotation.Transactional;

public interface PurchasesService {
    @Transactional(rollbackFor = Exception.class)
    String purchasesTransaction(Long pharmacySeqNo, Long customerId, Long itemNo);
}
