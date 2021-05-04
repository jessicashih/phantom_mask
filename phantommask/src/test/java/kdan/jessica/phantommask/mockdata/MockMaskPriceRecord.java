package kdan.jessica.phantommask.mockdata;

import kdan.jessica.phantommask.repository.entity.MaskPriceRecord;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class MockMaskPriceRecord {

    public static MaskPriceRecord mock() {
        MaskPriceRecord mockData = new MaskPriceRecord();
        mockData.setCreateTime(LocalTime.now());
        mockData.setCreateDate(LocalDate.now());
        mockData.setPrice(BigDecimal.TEN);
        mockData.setItemNo(1L);
        mockData.setPharmacySeqno(1L);
        return mockData;
    }

    public static MaskPriceRecord mockPriceHistory() {
        MaskPriceRecord mockData = mock();
        mockData.setUpdateDate(LocalDate.now());
        mockData.setUpdateTime(LocalTime.now());
        mockData.setIsDelete(true);
        return mockData;
    }

}
