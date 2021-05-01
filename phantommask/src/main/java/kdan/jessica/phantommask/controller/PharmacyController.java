package kdan.jessica.phantommask.controller;

import kdan.jessica.phantommask.model.*;
import kdan.jessica.phantommask.service.PharmacyService;
import kdan.jessica.phantommask.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 藥局相關操作
 */
@RestController
@RequestMapping(path = "/pharmacy")
public class PharmacyController extends BaseController {

    @Autowired
    private PharmacyService pharamacyService;
    @Autowired
    private SearchService searchService;

    @GetMapping(path = "/v1/open/dateTime")
    public JsonResult<FindOpenPharmaciesRs> findOpenPharmaciesAtCertainDateTime(FindOpenPharmaciesRq request) {
        FindOpenPharmaciesRs response = pharamacyService.findOpenPharmaciesAtCertainDateTime(request.getDateTime());
        return new JsonResult<FindOpenPharmaciesRs>(SUCCESS, response);
    }

    @GetMapping(path = "/v1/open/date")
    public JsonResult<FindOpenPharmaciesRs> findOpenPharmaciesAtCertainDate(FindOpenPharmaciesRq request) {
        FindOpenPharmaciesRs response = pharamacyService.findOpenPharmaciesAtCertainDate(request.getDateTime());
        return new JsonResult<FindOpenPharmaciesRs>(SUCCESS, response);
    }

    @GetMapping(path = "/v1/mask")
    public JsonResult<PharmacyRs> findPharmacyMask(FindPharmacyProductsRq request) {
        PharmacyRs response = pharamacyService.findPharmacyMask(request.getPharmacySeqno(), request.getSortBy());
        return new JsonResult<PharmacyRs>(SUCCESS, response);
    }

    @PostMapping(path = "/v1/editNameAndPrice")
    public JsonResult<Void> updatePharmacyInfo(@RequestBody EditPharmacyNameAndPriceRq request) {
        pharamacyService.updatePharmacyInfo(request);
        return new JsonResult<>(SUCCESS);
    }

    @DeleteMapping(path = "/v1/deleteItem")
    public JsonResult<Void> deleteItemFromPharmacy(@RequestBody DeleteItemRq request) {
        pharamacyService.deleteItemFromPharmacy(request.getItemNo(), request.getPharmacySeqno());
        return new JsonResult<>(SUCCESS);
    }

    @GetMapping(path = "v1/search")
    public JsonResult<List<PharmacyRs>> search(@RequestParam String searchName) {
        SearchRs response = searchService.search(searchName);
        return new JsonResult<>(SUCCESS, response.getPharmacyRsList());
    }
}
