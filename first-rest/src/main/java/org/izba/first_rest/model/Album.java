package org.izba.first_rest.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Album {
	private long userId;
	private long id;
	private String title;
	
	public Album() {
		
	}

	public Album(long userId, long id, String title) {
		super();
		this.userId = userId;
		this.id = id;
		this.title = title;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
