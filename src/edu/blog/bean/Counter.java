package edu.blog.bean;

import java.io.Serializable;

public class Counter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7727952314356418237L;
	private Long countId;
	private Long num;

	public Long getCountId() {
		return countId;
	}

	public void setCountId(Long countId) {
		this.countId = countId;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

}
