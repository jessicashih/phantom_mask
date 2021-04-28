package kdan.jessica.phantommask.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;

import kdan.jessica.phantommask.model.JsonResult;
import kdan.jessica.phantommask.service.ex.DataNotFoundException;
import kdan.jessica.phantommask.service.ex.RequestInputException;
import kdan.jessica.phantommask.service.ex.ServiceException;

public class BaseController {
	public static final int SUCCESS =200;
	public static final int KNOWN_ERROR =400;
	public static final int INPUT_ERROR =401;	
	public static final int UNKNOW_ERROR=500;
	
	@ExceptionHandler(ServiceException.class)
	public JsonResult<Void> handleException(Throwable e) {
		JsonResult<Void> errorJs = null;
		if(e instanceof RequestInputException) {
			errorJs= new JsonResult<Void>(INPUT_ERROR, e.getMessage());
		}else if(e instanceof DataNotFoundException) {
			errorJs = new JsonResult<Void>(KNOWN_ERROR, e.getMessage());
		}else {
			errorJs=new JsonResult<Void>(UNKNOW_ERROR, e.getMessage());
		}

		return errorJs;
	}
}
