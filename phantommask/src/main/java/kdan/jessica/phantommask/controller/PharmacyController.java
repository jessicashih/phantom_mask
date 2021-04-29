package kdan.jessica.phantommask.controller;

import kdan.jessica.phantommask.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import kdan.jessica.phantommask.service.PharmacyService;

@RestController
@RequestMapping(path = "/pharmacy")
public class PharmacyController extends BaseController{

	@Autowired
	private PharmacyService pharamacyService;

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
		PharmacyRs response = pharamacyService.findPharmacyMask(request.getPharmacySeqno(),request.getSortBy());
		return new JsonResult<PharmacyRs>(SUCCESS, response);
	}

	@PostMapping(path="/v1/editNameAndPrice")
	public  JsonResult<Void> updatePharmacyInfo(@RequestBody EditPharmacyNameAndPriceRq request){
		pharamacyService.updatePharmacyInfo(request);
		return new JsonResult<>(SUCCESS);
	}
}
