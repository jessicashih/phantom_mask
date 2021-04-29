package kdan.jessica.phantommask.controller;

import kdan.jessica.phantommask.model.*;
import kdan.jessica.phantommask.service.MaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/mask")
public class MaskController extends  BaseController{
    @Autowired
    private MaskService maskService;

    @GetMapping(path = "/v1/price")
    public JsonResult<List<PharmacyRs>> findOpenPharmaciesAtCertainDate(QueryMaskPriceRq request) {
        List<PharmacyRs> response = maskService.queryMaskPrice(request.getPriceMoreThan(),request.getPriceLessThan());
        return new JsonResult<>(SUCCESS,response);
    }

    @GetMapping(path = "/v1/totalReport")
    public JsonResult<TransactionRepostRs> findTotalTransaction(TransactionReportRq request){
        TransactionRepostRs response = maskService.findTotalTransaction(request.getStartDate(),request.getEndDate());
        return new JsonResult<>(SUCCESS,response);
    }

    @PostMapping(path = "/v1/updateName")
    public JsonResult<Void> updateName(@RequestBody UpdateMaskNameRq request){
        maskService.updateName(request.getItemNo(),request.getUpdateName());
        return new JsonResult<>(SUCCESS);
    }

}
