package kdan.jessica.phantommask.service.impl;

import kdan.jessica.phantommask.model.TopUserRq;
import kdan.jessica.phantommask.model.TopUserRs;
import kdan.jessica.phantommask.repository.relation.TopUser;
import kdan.jessica.phantommask.repository.service.CustomerDbService;
import kdan.jessica.phantommask.service.CustomerService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDbService customerDbService;

    @Override
    public List<TopUserRs> findTopUsers(TopUserRq request){
//      1. get all request parameter
        String startDateStr=request.getStartDate();
        String endDateStr=request.getEndDate();
        Integer top=request.getTop();
        LocalDate startDate = null;
        LocalDate endDate = null;

//      2. startDate and endDate default is today;
        if(StringUtils.isBlank(startDateStr)){
            startDate = LocalDate.now();
        }else{
            startDate =LocalDate.parse(startDateStr, DateTimeFormatter.ISO_DATE);
        }
        if(StringUtils.isBlank(endDateStr)){
            endDate=LocalDate.now();
        }else {
            endDate=LocalDate.parse(endDateStr, DateTimeFormatter.ISO_DATE);
        }

//      3. Query DataBase
        List<TopUser> topUserResult = customerDbService.findTopUser(startDate,endDate, top);

//      4. convert to response
        List<TopUserRs> response = new ArrayList<>();
        topUserResult.stream().forEach(u -> {
            response.add(new TopUserRs(u.getCustomerId(),u.getName(),u.getTotalPrice()));
        });
        return  response;
    }
}
