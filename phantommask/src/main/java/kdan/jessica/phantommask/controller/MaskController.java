package kdan.jessica.phantommask.controller;

import kdan.jessica.phantommask.model.*;
import kdan.jessica.phantommask.service.impl.MaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
