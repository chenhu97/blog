package edu.blog.bean;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4913263385017591481L;

	private Long articleId;
	private String title;
	private String folder;
	private Long catId;
	private Long memberId;
	private String nickName;
	private Date publishOn;
	private Date updateOn;
	private String content;
	//类目名称
	private String catName;
	
	
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public Long getArticleId() {
		return articleId;
	}
	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFolder() {
		return folder;
	}
	public void setFolder(String folder) {
		this.folder = folder;
	}
	public Long getCatId() {
		return catId;
	}
	public void setCatId(Long catId) {
		this.catId = catId;
	}
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
	public Date getPublishOn() {
		return publishOn;
	}
	public void setPublishOn(Date publishOn) {
		this.publishOn = publishOn;
	}
	public Date getUpdateOn() {
		return updateOn;
	}
	public void setUpdateOn(Date updateOn) {
		this.updateOn = updateOn;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
