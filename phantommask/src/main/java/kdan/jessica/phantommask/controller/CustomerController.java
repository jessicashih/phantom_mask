package kdan.jessica.phantommask.controller;

import kdan.jessica.phantommask.model.JsonResult;
import kdan.jessica.phantommask.model.TopUserRs;
import kdan.jessica.phantommask.model.TopUserRq;
import kdan.jessica.phantommask.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController extends BaseController{

    @Autowired
    private CustomerService customerService;

    @GetMapping(path="/v1/top_purchaser")
    public JsonResult<List<TopUserRs>> findTopUsers(TopUserRq request){
        List<TopUserRs> response = customerService.findTopUsers(request);
        return new JsonResult<>(SUCCESS,response);
    }

}
