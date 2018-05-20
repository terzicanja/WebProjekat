package yt.model;

import java.util.Date;

public class Rating {
	
	private int id;
	private boolean likeDislike;
	private Date date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isLikeDislike() {
		return likeDislike;
	}
	public void setLikeDislike(boolean likeDislike) {
		this.likeDislike = likeDislike;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	

}
