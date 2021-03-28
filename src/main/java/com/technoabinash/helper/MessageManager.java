package com.technoabinash.helper;

public class MessageManager {
	private String content;
	private String type;
	public MessageManager(String content, String type) {
		super();
		this.content = content;
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public MessageManager() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
