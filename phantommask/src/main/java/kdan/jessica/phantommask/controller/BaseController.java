package kdan.jessica.phantommask.controller;

import org.hibernate.service.spi.ServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import kdan.jessica.phantommask.model.JsonResult;

public class BaseController {
	public static final int SUCCESS =200;
	public static final int KNOWN_ERROR =400;
	public static final int UNKNOW_ERROR=500;
	
	@ExceptionHandler(ServiceException.class)
	public JsonResult<Void> handleException(Throwable e) {

		return new JsonResult<Void>(UNKNOW_ERROR, e.getMessage());
	}
}
