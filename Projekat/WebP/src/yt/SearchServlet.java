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

import yt.dao.VideoDAO;
import yt.model.User;
import yt.model.Video;

public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		String search = request.getParameter("search");
		String title = request.getParameter("title");
		String user = request.getParameter("user");
		String comment = request.getParameter("comment");
		System.out.println("parametar jee: " + search);
		
		String srchTitle = "";
		String srchUser = "";
		String srchComment = "";
		
		if(title.equals("true")) {
			srchTitle = search;
		}
		if(user.equals("true")) {
			srchUser = search;
		}
		if(comment.equals("true")) {
			srchComment = search;
		}
		
//		ArrayList<Video> videos = VideoDAO.searchVideos(search);
		ArrayList<Video> videos = VideoDAO.searchVideos(search, srchTitle, srchUser, srchComment);
		
		Map<String, Object> data = new HashMap<>();
		data.put("videos", videos);
//		data.put("comments", comments);
		data.put("loggedInUser", loggedInUser);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(data);
		System.out.println(json);

		response.setContentType("application/json");
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
