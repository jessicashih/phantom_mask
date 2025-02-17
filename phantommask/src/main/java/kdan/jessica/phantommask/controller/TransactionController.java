package kdan.jessica.phantommask.controller;

import kdan.jessica.phantommask.model.*;
import kdan.jessica.phantommask.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/transaction")
public class TransactionController extends BaseController {

    @Autowired
    private TransactionService service;

    @PostMapping(path = "/v1/purchases")
    public JsonResult<PurchasesRs> purchasesTransaction(@RequestBody PurchasesRq request) {
        String transactionId = service.purchasesTransaction(request.getPharmacySeqNo(), request.getCustomerId(), request.getItemNo());
        return new JsonResult<>(SUCCESS, new PurchasesRs(transactionId));
    }

    @GetMapping(path = "/v1/totalReport")
    public JsonResult<TransactionRepostRs> findTotalTransaction(TransactionReportRq request) {
        TransactionRepostRs response = service.findTotalTransaction(request.getStartDate(), request.getEndDate());
        return new JsonResult<>(SUCCESS, response);
    }
}
