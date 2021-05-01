package kdan.jessica.phantommask.service.impl;

import kdan.jessica.phantommask.model.*;
import kdan.jessica.phantommask.repository.entity.Mask;
import kdan.jessica.phantommask.repository.entity.MaskPriceRecord;
import kdan.jessica.phantommask.repository.entity.Pharmacy;
import kdan.jessica.phantommask.repository.service.MaskDbService;
import kdan.jessica.phantommask.repository.service.MaskPriceRecordDbService;
import kdan.jessica.phantommask.repository.service.PharmacyDbService;
import kdan.jessica.phantommask.service.PharmacyService;
import kdan.jessica.phantommask.service.ex.DataNotFoundException;
import kdan.jessica.phantommask.service.ex.RequestInputException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PharmacyServiceImpl implements PharmacyService {

    @Autowired
    private PharmacyDbService pharmacyDbService;

    @Autowired
    private MaskDbService maskDbService;

    @Autowired
    private MaskPriceRecordDbService priceRecordsDbService;


    @Override
    public FindOpenPharmaciesRs findOpenPharmaciesAtCertainDateTime(String dateTimeStr) {
        log.info("findOpenPharmaciesAtCertainDateTime Start");
        log.info("Query Date Time:{}", dateTimeStr);

//		Query
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        List<Pharmacy> queryResult = pharmacyDbService.findOpenedPharmacy(dateTime.getDayOfWeek(), dateTime.toLocalTime());

//		Response
        FindOpenPharmaciesRs response = new FindOpenPharmaciesRs();
        List<PharmacyRs> pharmacies = new ArrayList<>();
        response.setPharmacies(pharmacies);
        queryResult.stream().forEach(p -> {
            PharmacyRs pharmacyRs = new PharmacyRs();
            pharmacyRs.setName(p.getName());
            pharmacyRs.setSeqNo(p.getSeqNo());
            pharmacies.add(pharmacyRs);
        });
        log.info("findOpenPharmaciesAtCertainDateTime End");
        return response;
    }

    @Override
    public FindOpenPharmaciesRs findOpenPharmaciesAtCertainDate(String dateStr) {
        log.info("findOpenPharmaciesAtCertainDate Start");
        log.info("Query Date Time:{}", dateStr);

//		Query
        LocalDate dateTime = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<Pharmacy> queryResult = pharmacyDbService.findOpenedPharmacy(dateTime.getDayOfWeek());

//		Response
        FindOpenPharmaciesRs response = new FindOpenPharmaciesRs();
        List<PharmacyRs> pharmacies = new ArrayList<>();
        response.setPharmacies(pharmacies);
        queryResult.stream().forEach(p -> {
            PharmacyRs pharmacyRs = new PharmacyRs();
            pharmacyRs.setName(p.getName());
            pharmacyRs.setSeqNo(p.getSeqNo());
            pharmacies.add(pharmacyRs);
        });

        log.info("findOpenPharmaciesAtCertainDate End");
        return response;
    }

    @Override
    public PharmacyRs findPharmacyMask(Long pharmacySeqno, String sortBy) {
        log.info("findPharmacyMask End");
        log.info("pharmacySeqno:{}, sortBy:{}", pharmacySeqno, sortBy);
//		1.Check Input Data
        if (!"name".equals(sortBy) && !"price".equals(sortBy)) {
            throw new RequestInputException("SortBy Column must be name or price. Pleace check your input.");
        }
//		2. Query Pharmacy 
        Optional<Pharmacy> pharmacyOpt = pharmacyDbService.findById(pharmacySeqno);
        Pharmacy pharmacy = pharmacyOpt.orElseThrow(() -> new DataNotFoundException("Pharmacy data is not found. Please check your input Seqno."));
//		3. Query Mask Price Record
        List<MaskPriceRecord> priceRecords = priceRecordsDbService.findByPharmacySeqno(List.of(pharmacy.getSeqNo()));
        List<Long> itemNos = priceRecords.stream().map(price -> price.getItemNo()).collect(Collectors.toList());
//		4. Query Mask item
        List<Mask> masks = maskDbService.findByItemNoIn(itemNos);
        Map<Long, Mask> maskMap = masks.stream().collect(Collectors.toMap(m -> m.getItemNo(), m -> m));
//		5. Convert to Response
        PharmacyRs response = new PharmacyRs();
        response.setSeqNo(pharmacy.getSeqNo());
        response.setName(pharmacy.getName());
        List<MaskRs> maskRsList = new ArrayList<>();
        for (MaskPriceRecord priceRecord : priceRecords) {
            MaskRs maskRs = new MaskRs();

            maskRs.setItemNo(priceRecord.getItemNo());
            maskRs.setPrice(priceRecord.getPrice());

            Mask maskDetail = maskMap.get(priceRecord.getItemNo());
            maskRs.setName(maskDetail.getName());
            maskRs.setColor(maskDetail.getColor());
            maskRs.setNumOfPack(maskDetail.getNumOfPack());
            maskRsList.add(maskRs);
        }
        if ("name".equals(sortBy)) {
            response.setMasks(sortByName(maskRsList));
        } else {
            response.setMasks(sortByPrice(maskRsList));
        }
        log.info("findPharmacyMask End");
        return response;
    }

    @Override
    public void updatePharmacyInfo(EditPharmacyNameAndPriceRq request) {
        log.info("updatePharmacyInfo Start");
//		1.Verify input
        verifyEditRequest(request);

//		2. If pharmacyName exist update pharmacy.name
        if (StringUtils.isNoneBlank(request.getPharmacyName())) {
            updatePharmacyName(request.getPharmacySeqno(), request.getPharmacyName());
        }

//		3. if maskPrices not empty update mask_price_record
        if (!CollectionUtils.isEmpty(request.getMaskPrices())) {
            updateMaskPrice(request.getPharmacySeqno(), request.getMaskPrices());
        }
        log.info("updatePharmacyInfo End");
    }

    @Override
    public void deleteItemFromPharmacy(Long itemNo, Long pharmacySeqno) {
        log.info("deleteItemFromPharmacy Start");
        LocalDateTime now = LocalDateTime.now();
        Optional<MaskPriceRecord> result = priceRecordsDbService.findByItemNoAndPharmacy(itemNo, pharmacySeqno);
        MaskPriceRecord updateData = result
                .orElseThrow(() -> new DataNotFoundException("Not Found with Mask item_no with pharmacySeqNp"));
        updateData.setIsDelete(true);
        updateData.setUpdateDate(now.toLocalDate());
        updateData.setUpdateTime(now.toLocalTime());
        priceRecordsDbService.update(updateData);
        log.info("deleteItemFromPharmacy End");
    }


    private void updateMaskPrice(Long pharmacySeqno, List<MaskPirceEditRq> maskPrices) {
        LocalDateTime now = LocalDateTime.now();
//		3.1 check itemNo is exist
        List<MaskPriceRecord> updateDatas = new ArrayList<>();
        for (MaskPirceEditRq updatePrice : maskPrices) {
            Optional<MaskPriceRecord> result = priceRecordsDbService.findByItemNoAndPharmacy(updatePrice.getItemNo(), pharmacySeqno);
            MaskPriceRecord updateData = result
                    .orElseThrow(() -> new DataNotFoundException("Not Found with Mask item_no with pharmacySeqNp"));
            updateData.setIsDelete(true);
            updateData.setUpdateDate(now.toLocalDate());
            updateData.setUpdateTime(now.toLocalTime());
            updateDatas.add(updateData);
            MaskPriceRecord insertData = new MaskPriceRecord();
            insertData.setItemNo(updatePrice.getItemNo());
            insertData.setPharmacySeqno(pharmacySeqno);
            insertData.setPrice(updatePrice.getPrice());
            insertData.setCreateDate(now.toLocalDate());
            insertData.setCreateTime(now.toLocalTime());
            updateDatas.add(insertData);
        }
        priceRecordsDbService.updateAll(updateDatas);
    }

    private void updatePharmacyName(Long pharmacySeqno, String pharmacyName) {
        Optional<Pharmacy> result = pharmacyDbService.findById(pharmacySeqno);
        Pharmacy pharmacy = result.orElseThrow(() -> new DataNotFoundException("Pharmacy not found, check your input seqno"));
        pharmacy.setName(pharmacyName);
        pharmacyDbService.update(pharmacy);
    }

    private List<MaskRs> sortByName(List<MaskRs> maskRsList) {
        return maskRsList.stream().sorted(Comparator.comparing(MaskRs::getName)).collect(Collectors.toList());
    }

    private void verifyEditRequest(EditPharmacyNameAndPriceRq request) {
        if (ObjectUtils.isEmpty(request.getPharmacySeqno())) {
            throw new RequestInputException("You must input pharmacy seqNo");
        }

        if (!CollectionUtils.isEmpty(request.getMaskPrices())) {
            for (MaskPirceEditRq maskPrice : request.getMaskPrices()) {
                if (ObjectUtils.isEmpty(maskPrice.getItemNo())) {
                    throw new RequestInputException("You must input Mask item_no");
                }
            }
        }
    }

    private List<MaskRs> sortByPrice(List<MaskRs> maskRsList) {
        return maskRsList.stream().sorted(Comparator.comparing(MaskRs::getPrice)).collect(Collectors.toList());
    }
}
