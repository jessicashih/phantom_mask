package kdan.jessica.phantommask.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kdan.jessica.phantommask.json.model.CustomerJson;
import kdan.jessica.phantommask.json.model.MaskJson;
import kdan.jessica.phantommask.json.model.PharmacyJson;
import kdan.jessica.phantommask.json.model.PurchaseHistoriesJson;
import kdan.jessica.phantommask.repository.dao.*;
import kdan.jessica.phantommask.repository.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Slf4j
@ActiveProfiles("test")
public class TransformJsonData {
    @Autowired
    private PharmacyDao pharmacyDao;
    @Autowired
    private MaskDao maskDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private MaskPriceRecordDao maskPriceRecordDao;
    @Autowired
    private PurchaseRecordDao purchaseRecordDao;
    private ObjectMapper mapper = new ObjectMapper();

    public TransformJsonData(PharmacyDao pharmacyDao, MaskDao maskDao, CustomerDao customerDao,
                             MaskPriceRecordDao maskPriceRecordDao, PurchaseRecordDao purchaseRecordDao) {
        this.pharmacyDao = pharmacyDao;
        this.maskDao = maskDao;
        this.customerDao = customerDao;
        this.maskPriceRecordDao = maskPriceRecordDao;
        this.purchaseRecordDao = purchaseRecordDao;
    }

    @Test
    public void transform() throws IOException {
        LocalDateTime now = LocalDateTime.now();
        Map<String, Mask> maskMap = new HashMap<>();
        Map<String, Pharmacy> pharmacyMap = new HashMap<>();

        this.pharmacyDataProcess(now, maskMap, pharmacyMap);
        this.customerDataProcess(now, maskMap, pharmacyMap);
    }

    private void customerDataProcess(LocalDateTime now, Map<String, Mask> maskMap, Map<String, Pharmacy> pharmacyMap) throws IOException {
        List<CustomerJson> customerJsons = mapper.readValue(new File("src/test/resources/Customer.json"), new TypeReference<List<CustomerJson>>() {
        });
        for (CustomerJson customerJson : customerJsons) {
            log.info("Customer name:{}", customerJson.getName());
//            1.Insert Customer
            Customer customer = insertCustomer(customerJson);
            List<PurchaseHistoriesJson> purchaseHistoriesJsons = customerJson.getPurchaseHistories();
            for (PurchaseHistoriesJson historiesJson : purchaseHistoriesJsons) {
//            2.Insert price record
                LocalDateTime createDateTime = LocalDateTime
                        .parse(historiesJson.getTransactionDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                MaskPriceRecord priceRecord = insertPriceRecord(now, maskMap, pharmacyMap, historiesJson, createDateTime);
//            3.Insert purchase record
                insertPurchaseRecord(customer, createDateTime, priceRecord);
            }
        }
    }

    private void pharmacyDataProcess(LocalDateTime now, final Map<String, Mask> maskMap, final Map<String, Pharmacy> pharmacyMap) throws IOException {
        List<PharmacyJson> pharmacyJsons = mapper.readValue(new File("src/test/resources/Pharmacy.json"), new TypeReference<List<PharmacyJson>>() {
        });
        for (PharmacyJson pharmacyJson : pharmacyJsons) {
            log.info("Pharmacy.name:{}", pharmacyJson.getName());
            Pharmacy pharmacy = getPharmacy(pharmacyJson);
            pharmacyMap.put(pharmacy.getName(), pharmacy);
            List<MaskJson> maskJsons = pharmacyJson.getMasks();
            for (MaskJson maskJson : maskJsons) {
                String maskFullName = maskJson.getName();
                log.info("mask full name:{}", maskFullName);
                Mask mask = null;
                if (maskMap.containsKey(maskFullName)) {
                    mask = maskMap.get(maskFullName);
                } else {
                    mask = new JsonToMaskConvertor(maskFullName).convert();
                    mask = maskDao.save(mask);
                    maskMap.put(maskFullName, mask);
                }

                MaskPriceRecord priceRecord = getPriceRecord(now, pharmacy, maskJson.getPrice(), mask);
                maskPriceRecordDao.save(priceRecord);
            }
        }
    }

    private Customer insertCustomer(CustomerJson customerJson) {
        Customer customer = new Customer();
        customer.setName(customerJson.getName());
        customer.setBalance(customerJson.getCashBalance());
        customer = customerDao.save(customer);
        return customer;
    }

    private void insertPurchaseRecord(Customer customer, LocalDateTime createDateTime, MaskPriceRecord priceRecord) {
        PurchaseRecord purchaseRecord = new PurchaseRecord();
        purchaseRecord.setPriceRecord(priceRecord.getSeqNo());
        purchaseRecord.setCustomerId(customer.getCustomerId());
        purchaseRecord.setUuid(UUID.randomUUID().toString());
        purchaseRecord.setCreateTime(createDateTime.toLocalTime());
        purchaseRecord.setCreateDate(createDateTime.toLocalDate());
        purchaseRecordDao.save(purchaseRecord);
    }

    private MaskPriceRecord insertPriceRecord(LocalDateTime now, Map<String, Mask> maskMap, Map<String, Pharmacy> pharmacyMap, PurchaseHistoriesJson historiesJson, LocalDateTime createDateTime) {
        String pharmacyName = historiesJson.getPharmacyName();
        String maskName = historiesJson.getMaskName();
        Pharmacy pharmacy = pharmacyMap.get(pharmacyName);
        Mask mask = maskMap.get(maskName);
        MaskPriceRecord priceRecord = getHistoryPriceRecord(createDateTime, now, pharmacy, historiesJson.getTransactionAmount(), mask);
        priceRecord = maskPriceRecordDao.save(priceRecord);
        return priceRecord;
    }


    private MaskPriceRecord getHistoryPriceRecord(LocalDateTime createDateTime, LocalDateTime now, Pharmacy pharmacy, BigDecimal maskPrice, Mask mask) {
        MaskPriceRecord priceRecords = new MaskPriceRecord();
        priceRecords.setPrice(maskPrice);
        priceRecords.setItemNo(mask.getItemNo());
        priceRecords.setIsDelete(true);
        priceRecords.setCreateDate(createDateTime.toLocalDate());
        priceRecords.setCreateTime(createDateTime.toLocalTime());
        priceRecords.setPharmacySeqno(pharmacy.getSeqNo());
        priceRecords.setUpdateDate(now.toLocalDate());
        priceRecords.setUpdateTime(now.toLocalTime());
        return priceRecords;
    }

    private MaskPriceRecord getPriceRecord(LocalDateTime now, Pharmacy pharmacy, BigDecimal maskPrice, Mask mask) {
        MaskPriceRecord priceRecords = new MaskPriceRecord();
        priceRecords.setPrice(maskPrice);
        priceRecords.setItemNo(mask.getItemNo());
        priceRecords.setPharmacySeqno(pharmacy.getSeqNo());
        priceRecords.setCreateDate(now.toLocalDate());
        priceRecords.setCreateTime(now.toLocalTime());
        return priceRecords;
    }

    private Pharmacy getPharmacy(PharmacyJson pharmacyJson) {
        JsonToPharmacyConvertor pharmacyConvertor = new JsonToPharmacyConvertor(pharmacyJson);
        Pharmacy pharmacy = pharmacyConvertor.convert();
        pharmacy = pharmacyDao.save(pharmacy);
        return pharmacy;
    }

}
