package kdan.jessica.phantommask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kdan.jessica.phantommask.model.FindOpenPharmaciesRq;
import kdan.jessica.phantommask.model.FindOpenPharmaciesRs;
import kdan.jessica.phantommask.model.JsonResult;
import kdan.jessica.phantommask.service.PharmacyService;

@RestController
@RequestMapping(path = "/pharmacy")
public class PharmacyController extends BaseController{
	@Autowired
	private PharmacyService pharamacyService;

	@GetMapping(path = "/v1/findOpenPharmacies")
	public JsonResult<FindOpenPharmaciesRs> findOpenPharmacies(FindOpenPharmaciesRq request) {
		FindOpenPharmaciesRs response = pharamacyService.findOpenPharmacies(request.getDateTime());
		return new JsonResult<FindOpenPharmaciesRs>(SUCCESS, response);
	}
}
