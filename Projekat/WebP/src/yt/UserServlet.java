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
		String subs = "";
		User user = UserDAO.get(username);
		ArrayList<Video> videos = VideoDAO.getVideosByUser(username);
		System.out.println("parametar za usera je: " + username);
		
		if(loggedInUser != null) {
			if(UserDAO.isSubscribed(loggedInUser.getUsername(), user.getUsername()) == false) {
				subs = "notFollowing";
			}else {
				subs = "following";
			}
		}
		
		
		Map<String, Object> data = new HashMap<>();
		
		data.put("loggedInUser", loggedInUser);
		data.put("user", user);
		data.put("subs", subs);
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
			if(UserDAO.isSubscribed(loggedInUser.getUsername(), user.getUsername()) == false) {
				
				UserDAO.addSub(loggedInUser.getUsername(), user.getUsername());
				subs = "following";
			}else {
				
				UserDAO.deleteSub(loggedInUser.getUsername(), user.getUsername());
				subs = "notFollowing";
			}
		}
		
	}

}
