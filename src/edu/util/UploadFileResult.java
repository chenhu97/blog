package edu.util;

import java.io.Serializable;

public class UploadFileResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2812649475084206607L;

	public UploadFileResult() {
		// TODO Auto-generated constructor stub
		code = -1L;
		desc = "不成功";
	}

	private Long code;
	private String desc;

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
