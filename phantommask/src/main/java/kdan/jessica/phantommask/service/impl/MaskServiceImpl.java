package kdan.jessica.phantommask.service.impl;

import kdan.jessica.phantommask.model.MaskRs;
import kdan.jessica.phantommask.model.PharmacyRs;
import kdan.jessica.phantommask.repository.relation.PharmacyPriceMaskRelation;
import kdan.jessica.phantommask.repository.service.MaskPriceRecordsDbService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MaskServiceImpl implements  MaskService{

    @Autowired
    private MaskPriceRecordsDbService priceRecordService;

    @Override
    public List<PharmacyRs> queryMaskPrice(BigDecimal priceMoreThan,BigDecimal priceLessThan){
        log.info("MaskServiceImpl.queryMaskPrice Start");
//       1. DB query
        List<PharmacyPriceMaskRelation> queryResult = priceRecordService.pharmacyRelationQuery(priceMoreThan, priceLessThan);
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

    private MaskRs convertMaskRs(PharmacyPriceMaskRelation result) {
        MaskRs maskRs = new MaskRs();
        maskRs.setPrice(result.getMaskPrice());
        maskRs.setColor(result.getMaskColor());
        maskRs.setName(result.getMaskName());
        maskRs.setItemNo(result.getItemNo());
        return maskRs;
    }

}
