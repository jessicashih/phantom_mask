package kdan.jessica.phantommask.service;

import kdan.jessica.phantommask.json.TransformJsonData;
import kdan.jessica.phantommask.repository.dao.*;
import kdan.jessica.phantommask.repository.service.RelationalQueryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class BaseServiceTest {
    @Autowired
    protected PharmacyDao pharmacyDao;
    @Autowired
    protected MaskDao maskDao;
    @Autowired
    protected CustomerDao customerDao;
    @Autowired
    protected MaskPriceRecordDao maskPriceRecordDao;
    @Autowired
    protected PurchaseRecordDao purchaseRecordDao;
    @Autowired
    protected RelationalQueryService relationalQueryService;

    @BeforeEach
    public void init() throws IOException {
        TransformJsonData jsonConvertor = new TransformJsonData(pharmacyDao, maskDao, customerDao, maskPriceRecordDao, purchaseRecordDao);
        jsonConvertor.transform();
    }

    @AfterEach
    public void deleteAll() {
        pharmacyDao.deleteAll();
        maskDao.deleteAll();
        customerDao.deleteAll();
        maskPriceRecordDao.deleteAll();
        purchaseRecordDao.deleteAll();
    }
}
