package com.api.studentapi.model;

public class ResponseModel implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer status;
	private String message;
	private Object result;
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "RespuestaController [estado=" + status + ", mensaje=" + message
				+ ", resultado=" + result + "]";
	}
}