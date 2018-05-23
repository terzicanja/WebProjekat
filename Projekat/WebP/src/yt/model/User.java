package yt.model;

import java.util.ArrayList;
import java.util.Date;

public class User {
	
	public enum Role{
		USER, ADMIN
	}
	
	private int id;
	private String username;
	private String password;
	private String name;
	private String lastname;
	private String email;
	private String description;
	private Date registrationDate;
	private Role role;
	private boolean blocked;
	private boolean deleted;
	private int subsNumber;
	private ArrayList<User> subscribers;
	private ArrayList<Rating> videoLikes;
	private ArrayList<Rating> commentLikes;
	
	
	
	
	
	
	public User(int id, String username, String password, String name, String lastname, String email,
			String description, Date registrationDate, Role role, boolean blocked, boolean deleted, int subsNumber,
			ArrayList<User> subscribers, ArrayList<Rating> videoLikes, ArrayList<Rating> commentLikes) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.description = description;
		this.registrationDate = registrationDate;
		this.role = role;
		this.blocked = blocked;
		this.deleted = deleted;
		this.subsNumber = subsNumber;
		this.subscribers = subscribers;
		this.videoLikes = videoLikes;
		this.commentLikes = commentLikes;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public boolean isBlocked() {
		return blocked;
	}
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public ArrayList<User> getSubscribers() {
		return subscribers;
	}
	public void setSubscribers(ArrayList<User> subscribers) {
		this.subscribers = subscribers;
	}
	public ArrayList<Rating> getVideoLikes() {
		return videoLikes;
	}
	public void setVideoLikes(ArrayList<Rating> videoLikes) {
		this.videoLikes = videoLikes;
	}
	public ArrayList<Rating> getCommentLikes() {
		return commentLikes;
	}
	public void setCommentLikes(ArrayList<Rating> commentLikes) {
		this.commentLikes = commentLikes;
	}
	public int getSubsNumber() {
		return subsNumber;
	}
	public void setSubsNumber(int subsNumber) {
		this.subsNumber = subsNumber;
	}
	
	
	
	
	
	

}
