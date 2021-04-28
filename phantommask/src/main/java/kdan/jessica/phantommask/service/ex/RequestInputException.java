package kdan.jessica.phantommask.service.ex;


public class RequestInputException extends ServiceException {

	private static final long serialVersionUID = -1224474172375139228L;
	
	public RequestInputException() {
		super();
	}

	public RequestInputException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RequestInputException(String message, Throwable cause) {
		super(message, cause);
	}

	public RequestInputException(String message) {
		super(message);
	}

	public RequestInputException(Throwable cause) {
		super(cause);
	}
}
