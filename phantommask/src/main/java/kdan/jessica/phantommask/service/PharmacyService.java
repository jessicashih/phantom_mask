package kdan.jessica.phantommask.service;

import kdan.jessica.phantommask.model.EditPharmacyNameAndPriceRq;
import kdan.jessica.phantommask.model.FindOpenPharmaciesRs;
import kdan.jessica.phantommask.model.PharmacyRs;

public interface PharmacyService {
	
	/**
	 * 以特定「日期時間」查詢開店的藥局
	 * @param dateTimeStr 日期時間(yyyy-MM-dd HH:mm)
	 * @return 查詢到的藥局資訊
	 */
	FindOpenPharmaciesRs findOpenPharmaciesAtCertainDateTime(String dateTimeStr);

	/**
	 * 以特定「日期」查詢開店的藥局
	 * @param dateStr 日期時間(yyyy-MM-dd)
	 * @return 查詢到的藥局資訊
	 */
	FindOpenPharmaciesRs findOpenPharmaciesAtCertainDate(String dateStr);

	/**
	 * 查詢指定藥局所賣口罩
	 * @param pharmacySeqno 藥局Id
	 * @param sortBy 以什麼條件作為排序
	 * @return 查詢資料結果
	 */
	PharmacyRs findPharmacyMask(Long pharmacySeqno, String sortBy);

	void updatePharmacyInfo(EditPharmacyNameAndPriceRq request);
}
