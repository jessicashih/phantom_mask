package kdan.jessica.phantommask.service.impl;

import kdan.jessica.phantommask.model.TopUserRq;
import kdan.jessica.phantommask.model.TopUserRs;
import kdan.jessica.phantommask.repository.entity.relation.TopUser;
import kdan.jessica.phantommask.repository.service.RelationalQueryService;
import kdan.jessica.phantommask.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private RelationalQueryService relationalQueryService;

    @Override
    public List<TopUserRs> findTopUsers(TopUserRq request) {
        log.info("FindTopUsers Start");
//      1. get all request parameter
        String startDateStr = request.getStartDate();
        String endDateStr = request.getEndDate();
        Integer top = request.getTop();
        LocalDate startDate = null;
        LocalDate endDate = null;

//      2. startDate and endDate default is today;
        if (StringUtils.isBlank(startDateStr)) {
            startDate = LocalDate.now();
        } else {
            startDate = LocalDate.parse(startDateStr, DateTimeFormatter.ISO_DATE);
        }
        if (StringUtils.isBlank(endDateStr)) {
            endDate = LocalDate.now();
        } else {
            endDate = LocalDate.parse(endDateStr, DateTimeFormatter.ISO_DATE);
        }
        log.info("Start Data: {}" ,startDate);
        log.info("End Date: {}",endDate);

//      3. Query DataBase
        List<TopUser> topUserResult = relationalQueryService.findTopUser(startDate, endDate, top);

//      4. convert to response
        List<TopUserRs> response = new ArrayList<>();
        topUserResult.stream().forEach(u -> {
            response.add(new TopUserRs(u.getCustomerId(), u.getName(), u.getTotalPrice()));
        });
        log.info("FindTopUsers Start");
        return response;
    }
}
