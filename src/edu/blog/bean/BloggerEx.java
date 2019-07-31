package edu.blog.bean;

import java.io.Serializable;

public class BloggerEx implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4913554405910743267L;
	
	private Long flowId;
	private Long memberId;
	private String contentType;
	private String content;
	
	public Long getFlowId() {
		return flowId;
	}
	public void setFlowId(Long flowId) {
		this.flowId = flowId;
	}
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
