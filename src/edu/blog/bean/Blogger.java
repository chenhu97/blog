package edu.blog.bean;

import java.io.Serializable;

public class Blogger implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3050880804402827961L;
	
	private Long memberId;
	private String nickName;
	private String blogTitle;
	private String blogTitleEx;
	private String blogFolder;
	private Long isEnable;
	
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getBlogTitle() {
		return blogTitle;
	}
	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}
	public String getBlogTitleEx() {
		return blogTitleEx;
	}
	public void setBlogTitleEx(String blogTitleEx) {
		this.blogTitleEx = blogTitleEx;
	}
	public String getBlogFolder() {
		return blogFolder;
	}
	public void setBlogFolder(String blogFolder) {
		this.blogFolder = blogFolder;
	}
	public Long getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(Long isEnable) {
		this.isEnable = isEnable;
	}
	
	

}
