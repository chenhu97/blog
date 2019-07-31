package edu.blog.bean;

import java.io.Serializable;

public class ArticleCat implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4594022191033761673L;

	private Long catId;
	private String catName;
	private Long memberId;
	
	public Long getCatId() {
		return catId;
	}
	public void setCatId(Long catId) {
		this.catId = catId;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	
	
}
