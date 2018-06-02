package yt.model;

import java.util.Date;

public class Video {
	
	public enum Visibility {
		PUBLIC, PRIVATE, UNLISTED
	}
	
	private int id;
	private String videoURL;
	private String videoImg;
	private String name;
	private String description;
	private Visibility visibility;
	private boolean commentsAllowed;
	private boolean blocked;
	private boolean ratingAllowed;
	private boolean deleted;
	private int views;
	private int likes;
	private int dislikes;
	private Date date;
	private User owner;
	
	public Video() {
		
	}
	
	
	public Video(int id, String videoURL, String videoImg, String name, String description, Visibility visibility,
			boolean commentsAllowed, boolean blocked, boolean ratingAllowed, boolean deleted, int views, int likes,
			int dislikes, Date date, User owner) {
		super();
		this.id = id;
		this.videoURL = videoURL;
		this.videoImg = videoImg;
		this.name = name;
		this.description = description;
		this.visibility = visibility;
		this.commentsAllowed = commentsAllowed;
		this.blocked = blocked;
		this.ratingAllowed = ratingAllowed;
		this.deleted = deleted;
		this.views = views;
		this.likes = likes;
		this.dislikes = dislikes;
		this.date = date;
		this.owner = owner;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVideoURL() {
		return videoURL;
	}
	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}
	public String getVideoImg() {
		return videoImg;
	}
	public void setVideoImg(String videoImg) {
		this.videoImg = videoImg;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Visibility getVisibility() {
		return visibility;
	}
	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}
	public boolean isCommentsAllowed() {
		return commentsAllowed;
	}
	public void setCommentsAllowed(boolean commentsAllowed) {
		this.commentsAllowed = commentsAllowed;
	}
	public boolean isBlocked() {
		return blocked;
	}
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
	public boolean isRatingAllowed() {
		return ratingAllowed;
	}
	public void setRatingAllowed(boolean ratingAllowed) {
		this.ratingAllowed = ratingAllowed;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getDislikes() {
		return dislikes;
	}
	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	
	
	
	
	
	
	
	
	
	

}
