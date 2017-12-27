package org.izba.first_rest.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Comment {
	private long postId;
	private long id;
	private String name;
	private String email;
	private String body;
	
	public Comment() {
		
	}

	public Comment(long postId, long id, String name, String email, String body) {
		super();
		this.postId = postId;
		this.id = id;
		this.name = name;
		this.email = email;
		this.body = body;
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
}
