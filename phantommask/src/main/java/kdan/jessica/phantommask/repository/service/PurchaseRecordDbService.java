package kdan.jessica.phantommask.repository.service;

import kdan.jessica.phantommask.repository.dao.PurchaseRecordDao;
import kdan.jessica.phantommask.repository.entity.PurchaseRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

/**
 * PurchaseRecord DbService
 */
@Service
public class PurchaseRecordDbService {

    @Autowired
    private PurchaseRecordDao purchaseRecordDao;

    public PurchaseRecord insert(Long priceRecordSeqno, Long customerId) {
        PurchaseRecord purchaseRecord = new PurchaseRecord();
        purchaseRecord.setUuid(UUID.randomUUID().toString());
        purchaseRecord.setPriceRecord(priceRecordSeqno);
        purchaseRecord.setCustomerId(customerId);
        purchaseRecord.setCreateDate(LocalDate.now());
        purchaseRecord.setCreateTime(LocalTime.now());
        return purchaseRecordDao.save(purchaseRecord);
    }

    public List<PurchaseRecord> findAll() {
        return purchaseRecordDao.findAll();
    }
}
