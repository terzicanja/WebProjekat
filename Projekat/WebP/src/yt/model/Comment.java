package yt.model;

import java.util.Date;

public class Comment {
	
	private int id;
	private String content;
	private Date date;
	private User author;
	private Video video;
	private boolean deleted;
	
	
	
	public Comment(int id, String content, Date date, User author, Video video, boolean deleted) {
		super();
		this.id = id;
		this.content = content;
		this.date = date;
		this.author = author;
		this.video = video;
		this.deleted = deleted;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public Video getVideo() {
		return video;
	}
	public void setVideo(Video video) {
		this.video = video;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
	

}
