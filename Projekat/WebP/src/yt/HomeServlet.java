package yt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		String sort = request.getParameter("sort");
		List<Video> videos = new ArrayList<>();
		
		if(sort.equals("none")) {
			videos = VideoDAO.getAll();
		}else if(sort.equals("mostPopular")) {
			videos = VideoDAO.getAllSorted("views desc");
		}else if(sort.equals("leastPopular")) {
			videos = VideoDAO.getAllSorted("views asc");
		}else if(sort.equals("newest")) {
			videos = VideoDAO.getAllSorted("dateCreated desc");
		}else if(sort.equals("oldest")) {
			videos = VideoDAO.getAllSorted("dateCreated asc");
		}else if(sort.equals("alphabetic")) {
			videos = VideoDAO.getAllSorted("name asc");
		}else if(sort.equals("alphabeticReverse")) {
			videos = VideoDAO.getAllSorted("name desc");
		}else if(sort.equals("alphabeticAuthor")) {
			videos = VideoDAO.getAllSorted("user_id asc");
		}else if(sort.equals("alphabeticAuthorReverse")) {
			videos = VideoDAO.getAllSorted("user_id desc");
		}
		
		
		List<User> topFive = UserDAO.getMostPopular();
		
		Map<String, Object> data = new HashMap<>();
		data.put("videos", videos);
		data.put("topFive", topFive);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		System.out.println(jsonData);
		
		response.setContentType("application/json");
		response.getWriter().write(jsonData);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
