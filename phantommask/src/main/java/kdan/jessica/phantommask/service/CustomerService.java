package kdan.jessica.phantommask.service;

import kdan.jessica.phantommask.model.TopUserRq;
import kdan.jessica.phantommask.model.TopUserRs;

import java.time.LocalDate;
import java.util.List;

public interface CustomerService {
    /**
     * 找出消費前幾名的用戶
     * @param request 查詢條件
     * @return 前幾名的用戶
     */
    List<TopUserRs> findTopUsers(TopUserRq request);
}
