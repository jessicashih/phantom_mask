package kdan.jessica.phantommask.service.impl;

import kdan.jessica.phantommask.model.MaskRs;
import kdan.jessica.phantommask.model.PharmacyRs;
import kdan.jessica.phantommask.repository.entity.Mask;
import kdan.jessica.phantommask.repository.entity.relation.PharmacyPriceMaskRelation;
import kdan.jessica.phantommask.repository.service.MaskDbService;
import kdan.jessica.phantommask.repository.service.RelationalQueryService;
import kdan.jessica.phantommask.service.MaskService;
import kdan.jessica.phantommask.service.ex.DataNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@Slf4j
public class MaskServiceImpl implements MaskService {
    @Autowired
    private MaskDbService maskDbService;
    @Autowired
    private RelationalQueryService relationalQueryService;

    @Override
    public List<PharmacyRs> queryMaskPrice(BigDecimal priceMoreThan, BigDecimal priceLessThan) {
        log.info("MaskServiceImpl.queryMaskPrice Start");
//       1. DB query
        List<PharmacyPriceMaskRelation> queryResult = relationalQueryService.findPharmacyWithMaskByPrice(priceMoreThan, priceLessThan);
//       2. Convert to Response
        List<PharmacyRs> response = new ArrayList<>();
        Map<Long, List<MaskRs>> pharmacyGroup = new HashMap<>();
        queryResult.stream().forEach(result -> {
            if (pharmacyGroup.containsKey(result.getPharmacySeqNo())) {
                List<MaskRs> maskRsList = pharmacyGroup.get(result.getPharmacySeqNo());
                MaskRs maskRs = convertMaskRs(result);
                maskRsList.add(maskRs);
            } else {
                PharmacyRs pharmacyRs = new PharmacyRs();
                pharmacyRs.setSeqNo(result.getPharmacySeqNo());
                pharmacyRs.setName(result.getPharmacyName());

                List<MaskRs> maskRsList = new ArrayList<>();
                MaskRs maskRs = convertMaskRs(result);
                maskRsList.add(maskRs);
                pharmacyGroup.put(result.getPharmacySeqNo(), maskRsList);
                pharmacyRs.setMasks(maskRsList);
                response.add(pharmacyRs);

            }
        });
        log.info("MaskServiceImpl.queryMaskPrice End");
        return response;
    }

    @Override
    public void updateName(Long itemNo, String itemName) {
        log.info("UpdateName Start");
        log.info("itemNo:{},itemName:{}", itemNo, itemName);
        Optional<Mask> result = maskDbService.findById(itemNo);
        Mask mask = result
                .orElseThrow(() -> new DataNotFoundException("Can't find mask data with itemNo, please check the itemNo is correct."));
        mask.setName(itemName);
        maskDbService.update(mask);
        log.info("UpdateName End");
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
