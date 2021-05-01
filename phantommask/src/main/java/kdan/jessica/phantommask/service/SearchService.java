package kdan.jessica.phantommask.service;

import kdan.jessica.phantommask.model.SearchRs;

public interface SearchService {
    /**
     * 查詢藥局或口罩
     * @param searchString 查詢名稱
     * @return 查詢結果
     */
    SearchRs search(String searchString);
}
