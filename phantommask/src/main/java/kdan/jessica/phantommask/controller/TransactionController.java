package kdan.jessica.phantommask.controller;

import kdan.jessica.phantommask.model.JsonResult;
import kdan.jessica.phantommask.model.PurchasesRq;
import kdan.jessica.phantommask.model.PurchasesRs;
import kdan.jessica.phantommask.service.PurchasesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/transaction")
public class TransactionController extends BaseController{

    @Autowired
    private PurchasesService service;

    @PostMapping(path="/v1/purchases")
    public JsonResult<PurchasesRs> purchasesTransaction(@RequestBody PurchasesRq request) {
        String transactionId=service.purchasesTransaction(request.getPharmacySeqNo(), request.getCustomerId(), request.getItemNo());
        return new JsonResult<>(SUCCESS,new PurchasesRs(transactionId));
    }
}
