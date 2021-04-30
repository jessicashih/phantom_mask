package kdan.jessica.phantommask.controller;

import kdan.jessica.phantommask.model.JsonResult;
import kdan.jessica.phantommask.model.SearchRs;
import kdan.jessica.phantommask.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/search")
public class SearchController extends BaseController{

    @Autowired
    private SearchService searchService;

    @GetMapping(path = "/v1")
    public JsonResult<SearchRs> search(@RequestParam String searchName){
        SearchRs response = searchService.search(searchName);
        return new JsonResult<>(SUCCESS,response);
    }

}
