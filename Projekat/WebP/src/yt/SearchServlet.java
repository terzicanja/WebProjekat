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
		ArrayList<Video> searchVideos = new ArrayList<>();
		
		String srchTitle = "";
		String srchUser = "";
		String srchComment = "";
		
		if(title.equals("true")) {
			ArrayList<Video> byName = VideoDAO.searchVideosByName(search);
//			searchVideos.addAll(byName);
//			for (Video x : byName){
//				   if (!searchVideos.contains(x))
//				      searchVideos.add(x);
//				}
			
			
			ArrayList<Video> twoCopy = new ArrayList<>(byName);
			twoCopy.removeAll(searchVideos);
			searchVideos.addAll(twoCopy);
			
			
			srchTitle = "name LIKE ?";
		}
		if(user.equals("true")) {
			ArrayList<Video> byOwner = VideoDAO.searchVideosByOwner(search);
//			searchVideos.addAll(byOwner);
//			for(Video x : byOwner) {
//				if(!searchVideos.contains(x)) {
//					searchVideos.add(x);
//				}
//			}
			
			
			ArrayList<Video> twoCopy = new ArrayList<>(byOwner);
			twoCopy.removeAll(searchVideos);
			searchVideos.addAll(twoCopy);
			
			
			srchUser = "user_id LIKE ?";
			if(title.equals("true") && user.equals("true")) {
				searchVideos.clear();
				ArrayList<Video> byName = VideoDAO.searchVideosByName(search);
				searchVideos.addAll(byName);
				
				ArrayList<Video> twoCopyy = new ArrayList<>(byOwner);
				twoCopyy.removeAll(searchVideos);
				searchVideos.addAll(twoCopyy);
				
				srchUser = "OR user_id LIKE ?";
			}
		}
		if(comment.equals("true")) {
			srchComment = search;
		}
		
//		ArrayList<Video> videos = VideoDAO.searchVideos(search);
//		ArrayList<Video> videos = VideoDAO.searchVideos(search, srchTitle, srchUser, srchComment);
		
		Map<String, Object> data = new HashMap<>();
//		data.put("videos", videos);
		data.put("videos", searchVideos);
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
