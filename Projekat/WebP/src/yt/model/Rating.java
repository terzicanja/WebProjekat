package yt.model;

import java.util.Date;

public class Rating {
	
	private int id;
	private boolean likeDislike;
	private Date date;
	private User whoLiked;
	private Video likedVideo;
	private Comment likedComment;
	
	public Rating() {
		
	}
	
	public Rating(int id, boolean likeDislike, Date date, User whoLiked, Video likedVideo, Comment likedComment) {
		super();
		this.id = id;
		this.likeDislike = likeDislike;
		this.date = date;
		this.whoLiked = whoLiked;
		this.likedVideo = likedVideo;
		this.likedComment = likedComment;
	}
	
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
	public User getWhoLiked() {
		return whoLiked;
	}
	public void setWhoLiked(User whoLiked) {
		this.whoLiked = whoLiked;
	}
	public Video getLikedVideo() {
		return likedVideo;
	}
	public void setLikedVideo(Video likedVideo) {
		this.likedVideo = likedVideo;
	}
	public Comment getLikedComment() {
		return likedComment;
	}
	public void setLikedComment(Comment likedComment) {
		this.likedComment = likedComment;
	}
	
	
	

}
