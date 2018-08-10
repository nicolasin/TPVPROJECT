package es.nicolas.uca.tpv.exceptions;

public class ExceptionResponse {
	private int code;
	private String message;
	private long timestamp;

	public ExceptionResponse(int code, String message, long timestamp) {
		super();
		this.code = code;
		this.message = message;
		this.timestamp = timestamp;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
