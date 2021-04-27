package kdan.jessica.phantommask.model;

public class JsonResult<T> {

	/**
	 * 200-OK
	 * 400-Known Exception
	 * 500-Unknow Exception
	 */
	private Integer state;
	private String message;
	private T data;

	public JsonResult(Integer state, T data) {
		super();
		this.state = state;
		this.data = data;
	}

	public JsonResult(Integer state, String message) {
		super();
		this.state = state;
		this.message = message;
	}



	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
