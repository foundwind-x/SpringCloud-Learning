package com.didispace.api.constant;

public enum ResultEnum {
	SUCCESS(200, "操作成功"),ERROR(500, "操作失败"),TIME_OUT(100, "登陆超时");
	
	private Integer code;
	private String info;
	private ResultEnum(Integer code, String info) {
		this.code = code;
		this.info = info;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
}
