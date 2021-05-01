package kdan.jessica.phantommask.repository.service;

import kdan.jessica.phantommask.repository.dao.MaskPriceRecordDao;
import kdan.jessica.phantommask.repository.entity.MaskPriceRecord;
import kdan.jessica.phantommask.repository.ex.DataException;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Mask_Price_Record DB Service
 */
@Service
public class MaskPriceRecordDbService {
    @Autowired
    private MaskPriceRecordDao maskPriceRecordDao;

    public Optional<MaskPriceRecord> findById(Long seqno) {
        return maskPriceRecordDao.findById(seqno);
    }

    public MaskPriceRecord update(MaskPriceRecord priceRecord) {
        Long id = priceRecord.getSeqNo();
        if (ObjectUtils.isNotEmpty(id) && maskPriceRecordDao.existsById(id)) {
            return maskPriceRecordDao.save(priceRecord);
        } else {
            throw new DataException("The Data is not exist, please use insert");
        }
    }

    public List<MaskPriceRecord> updateAll(List<MaskPriceRecord> updateDatas) {
        List<MaskPriceRecord> afterUpdate = new ArrayList<>();
        for (MaskPriceRecord priceRecord : updateDatas) {
            afterUpdate.add(update(priceRecord));
        }
        return afterUpdate;
    }

    /**
     * 依藥局編號查詢價格
     *
     * @param pharmacySeqnos 藥局編號
     * @return 目前藥局的口罩價格
     */
    public List<MaskPriceRecord> findByPharmacySeqno(List<Long> pharmacySeqnos) {

        Specification<MaskPriceRecord> specification = new Specification<>() {
            @Override
            public Predicate toPredicate(Root<MaskPriceRecord> record, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate pharmacySeqnoIn = null;
                if (pharmacySeqnos != null && !pharmacySeqnos.isEmpty()) {
                    pharmacySeqnoIn = record.get("pharmacySeqno").in(pharmacySeqnos);
                }
                Predicate notDelete = record.get("isDelete").isNull();
                return cb.and(pharmacySeqnoIn, notDelete);
            }
        };
        return maskPriceRecordDao.findAll(specification);
    }

    /**
     * 依照口罩編號以及藥局編號查詢價格紀錄
     *
     * @param itemNo        口罩貨號
     * @param pharmacySeqno 藥局編號
     * @return query result
     */
    public Optional<MaskPriceRecord> findByItemNoAndPharmacy(Long itemNo, Long pharmacySeqno) {

        Specification<MaskPriceRecord> specification = new Specification<>() {
            @Override
            public Predicate toPredicate(Root<MaskPriceRecord> record, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate itemNoEqual = cb.equal(record.get("itemNo"), (itemNo));
                Predicate pharmacySeqnoEqual = cb.equal(record.get("pharmacySeqno"), (pharmacySeqno));
                Predicate notDelete = record.get("isDelete").isNull();
                return cb.and(itemNoEqual, pharmacySeqnoEqual, notDelete);
            }
        };
        return maskPriceRecordDao.findOne(specification);
    }
}
