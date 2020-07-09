package com.example.domain;

public class Join {

	private Integer id;

	private String name;

	private String content;

	private Integer ComId;

	private String comName;

	private String comContent;

	private Integer articleId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getComId() {
		return ComId;
	}

	public void setComId(Integer comId) {
		ComId = comId;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public String getComContent() {
		return comContent;
	}

	public void setComContent(String comContent) {
		this.comContent = comContent;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	@Override
	public String toString() {
		return "Join [id=" + id + ", name=" + name + ", content=" + content + ", ComId=" + ComId + ", comName="
				+ comName + ", comContent=" + comContent + ", articleId=" + articleId + "]";
	}
	
	
	

}
