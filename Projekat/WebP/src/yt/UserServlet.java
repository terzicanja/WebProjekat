package yt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import yt.dao.UserDAO;
import yt.dao.VideoDAO;
import yt.model.User;
import yt.model.Video;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		String username = request.getParameter("id");
		String sort = request.getParameter("sort");
		String subs = "";
		String userStatus = "ok";
		User user = UserDAO.get(username);
		ArrayList<Video> videos = new ArrayList<>();
//		ArrayList<Video> videos = VideoDAO.getVideosByUser(username);
		System.out.println("parametar za usera je: " + username);
		
		try {
			if(loggedInUser != null) {
				if(loggedInUser.getUsername().equals(user.getUsername()) || loggedInUser.getRole().toString().equals("ADMIN")) {
					videos = VideoDAO.getVideosByUser(username);
				}else if(user.isBlocked() == true){
					userStatus = "blokiran";
				}else if(user.isDeleted() == true){
					userStatus = "obrisan";
				}else {
					videos = VideoDAO.getPublicVideosByUser(username);
				}
				
				if(UserDAO.isSubscribed(loggedInUser.getUsername(), user.getUsername()) == false) {
					subs = "notFollowing";
				}else if(UserDAO.isSubscribed(loggedInUser.getUsername(), user.getUsername()) == true) {
					subs = "following";
				}else if(loggedInUser.getUsername().equals(user.getUsername())) {
					subs = "nemere";
				}
			}else if(loggedInUser == null) {
				videos = VideoDAO.getPublicVideosByUser(username);
				
				if(user.isBlocked() == true) {
					userStatus = "blokiran";
				}else if(user.isDeleted() == true) {
					userStatus = "obrisan";
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
//		if(sort.equals("none")) {
//			userStatus = "ok";
////			videos = VideoDAO.getAll();
//		}else if(sort.equals("mostPopular")) {
//			videos = VideoDAO.getAllSorted("views desc");
//		}else if(sort.equals("leastPopular")) {
//			videos = VideoDAO.getAllSorted("views asc");
//		}else if(sort.equals("newest")) {
//			videos = VideoDAO.getAllSorted("dateCreated desc");
//		}else if(sort.equals("oldest")) {
//			videos = VideoDAO.getAllSorted("dateCreated asc");
//		}
		
		Map<String, Object> data = new HashMap<>();
		
		data.put("loggedInUser", loggedInUser);
		data.put("user", user);
		data.put("subs", subs);
		data.put("userStatus", userStatus);
		data.put("videos", videos);
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(data);
		System.out.println(json);
		
		response.setContentType("application/json");
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		String username = request.getParameter("id");
		String status = request.getParameter("status");
		String subs = "";
		User user = UserDAO.get(username);
		
		if(status.equals("block")) {
			if(user.isBlocked() == false) {
				user.setBlocked(true);
			}else if(user.isBlocked()) {
				user.setBlocked(false);
			}
			UserDAO.update(user);
		}else if(status.equals("delete")) {
			if(user.isDeleted() == false) {
				user.setDeleted(true);
			}else if(user.isDeleted()) {
				user.setDeleted(false);
			}
			UserDAO.update(user);
		}else if(status.equals("follow")) {
			if(loggedInUser.getUsername().equals(user.getUsername())) {
				subs = "cantFollowYourself";
			}else {
				if(UserDAO.isSubscribed(loggedInUser.getUsername(), user.getUsername()) == false) {
					
					UserDAO.addSub(loggedInUser.getUsername(), user.getUsername());
					user.setSubsNumber(user.getSubsNumber() + 1);
					UserDAO.update(user);
					subs = "following";
				}else {
					
					UserDAO.deleteSub(loggedInUser.getUsername(), user.getUsername());
					user.setSubsNumber(user.getSubsNumber() - 1);
					UserDAO.update(user);
					subs = "notFollowing";
				}
			}
			
		}
		
		Map<String, Object> data = new HashMap<>();
		
		data.put("loggedInUser", loggedInUser);
		data.put("user", user);
		data.put("subs", subs);
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(data);
		System.out.println(json);
		
		response.setContentType("application/json");
		response.getWriter().write(json);
		
	}

}
