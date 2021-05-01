package kdan.jessica.phantommask.service.impl;

import kdan.jessica.phantommask.model.MaskAmountDetail;
import kdan.jessica.phantommask.model.MaskRs;
import kdan.jessica.phantommask.model.PharmacyRs;
import kdan.jessica.phantommask.model.TransactionRepostRs;
import kdan.jessica.phantommask.repository.entity.Mask;
import kdan.jessica.phantommask.repository.entity.relation.PharmacyPriceMaskRelation;
import kdan.jessica.phantommask.repository.entity.relation.TransactionReport;
import kdan.jessica.phantommask.repository.service.MaskDbService;
import kdan.jessica.phantommask.repository.service.RelationalQueryService;
import kdan.jessica.phantommask.service.MaskService;
import kdan.jessica.phantommask.service.ex.DataNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Slf4j
public class MaskServiceImpl implements MaskService {
    @Autowired
    private MaskDbService dbService;
    @Autowired
    private RelationalQueryService relationalQueryService;

    @Override
    public List<PharmacyRs> queryMaskPrice(BigDecimal priceMoreThan,BigDecimal priceLessThan){
        log.info("MaskServiceImpl.queryMaskPrice Start");
//       1. DB query
        List<PharmacyPriceMaskRelation> queryResult = relationalQueryService.findPharmacyWithMaskByPrice(priceMoreThan, priceLessThan);
//       2. Convert to Response
        List<PharmacyRs> response = new ArrayList<>();
        Map<Long,List<MaskRs>> pharmacyGroup = new HashMap<>();
        queryResult.stream().forEach(result->{
            if(pharmacyGroup.containsKey(result.getPharmacySeqNo())){
                List<MaskRs> maskRsList =  pharmacyGroup.get(result.getPharmacySeqNo());
                MaskRs maskRs = convertMaskRs(result);
                maskRsList.add(maskRs);
            }else{
                PharmacyRs pharmacyRs = new PharmacyRs();
                pharmacyRs.setSeqNo(result.getPharmacySeqNo());
                pharmacyRs.setName(result.getPharmacyName());

                List<MaskRs> maskRsList = new ArrayList<>();
                MaskRs maskRs = convertMaskRs(result);
                maskRsList.add(maskRs);
                pharmacyGroup.put(result.getPharmacySeqNo(),maskRsList);
                pharmacyRs.setMasks(maskRsList);
                response.add(pharmacyRs);

            }
        });
        log.info("MaskServiceImpl.queryMaskPrice End");
        return response;
    }

    @Override
    public TransactionRepostRs findTotalTransaction(String startDateStr, String endDateStr) {
        LocalDate startDate=null;
        LocalDate endDate=null;
        if(StringUtils.isBlank(startDateStr)){
            startDate = LocalDate.now();
        }else{
            startDate = LocalDate.parse(startDateStr, DateTimeFormatter.ISO_DATE);
        }

        if(StringUtils.isBlank(endDateStr)){
            endDate = LocalDate.now();
        }else{
            endDate = LocalDate.parse(endDateStr, DateTimeFormatter.ISO_DATE);
        }

//        1. Query data
        List<TransactionReport> dbResult = relationalQueryService.findTotalTransaction(startDate, endDate);

//        2. count total amount of masks and dollars value
        List<MaskAmountDetail> maskAmountDetails = new ArrayList<>();
        int totalAmountOfMask = 0;
        BigDecimal totalAmountOfDollarValue = BigDecimal.ZERO;
        for (TransactionReport report:dbResult) {
            MaskAmountDetail detail = new MaskAmountDetail();
            int amountOfMask=report.getAmountOfItem();
            int perPack = report.getItemNumOfPack();
            detail.setItemNo(report.getItemNo());
            detail.setName(report.getItemName());
            detail.setColor(report.getItemColor());
            detail.setItemNumOfPack(perPack);
            detail.setAmountOfItem(amountOfMask);
            detail.setAmountOfItem(report.getAmountOfItem());
            totalAmountOfMask+=(amountOfMask*perPack);
            totalAmountOfDollarValue = totalAmountOfDollarValue.
                    add(report.getAmountOfDollar().multiply(new BigDecimal(amountOfMask)));
            maskAmountDetails.add(detail);
        }

//        3.Convert to response
        TransactionRepostRs response = new TransactionRepostRs();
        response.setTotalAmountOfMask(totalAmountOfMask);
        response.setTotalAmountOfDollarValue(totalAmountOfDollarValue);
        response.setDetail(maskAmountDetails);
        return response;
    }

    @Override
    public void updateName(Long itemNo,String itemName){
        Optional<Mask> result=dbService.findById(itemNo);
        Mask mask = result
                .orElseThrow(()-> new DataNotFoundException("Can't find mask data with itemNo, please check the itemNo is correct."));
        mask.setName(itemName);
        dbService.update(mask);
    }

    private MaskRs convertMaskRs(PharmacyPriceMaskRelation result) {
        MaskRs maskRs = new MaskRs();
        maskRs.setPrice(result.getMaskPrice());
        maskRs.setColor(result.getMaskColor());
        maskRs.setName(result.getMaskName());
        maskRs.setItemNo(result.getItemNo());
        return maskRs;
    }

}
