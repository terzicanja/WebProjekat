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

import yt.dao.CommentDAO;
import yt.dao.RatingDAO;
import yt.dao.VideoDAO;
import yt.model.Comment;
import yt.model.Rating;
import yt.model.User;
import yt.model.Video;
import yt.model.Video.Visibility;

public class VideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
//		if (loggedInUser != null)
//			loggedInUser.setVideoLikes(new LikeDAO().getAllVideoLikes(loggedInUser));

		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("id parametar jee: " + id);
		String status = "unrated";
		
		if(loggedInUser != null) {
			Rating rating = RatingDAO.getUserVideoLikes(id, loggedInUser.getUsername());
			if(rating == null) {
				status = "unrated";
			} else if(rating != null) {
				if(rating.isLikeDislike()) {
					status = "liked";
				} else {
					status = "disliked";
				}
			}
		} else {
			status = "cannotLike";
		}

		Video video = VideoDAO.getVideo(id);
		video.setViews(video.getViews() + 1);
		VideoDAO.update(video);
		int videoLikes = RatingDAO.getCountVideoLikes(id);
		int videoDislikes = RatingDAO.getCountVideoDislikes(id);
		ArrayList<Comment> comments = CommentDAO.getAll(id);

		Map<String, Object> data = new HashMap<>();
		data.put("video", video);
		data.put("status", status);
		data.put("videoLikes", videoLikes);
		data.put("videoDislikes", videoDislikes);
		data.put("comments", comments);
		data.put("loggedInUser", loggedInUser);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(data);
		System.out.println(json);

		response.setContentType("application/json");
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		int id = Integer.parseInt(request.getParameter("id"));
		String doing = request.getParameter("doing");
		String url = request.getParameter("url");
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String visibility = request.getParameter("visibility");
		boolean comments = Boolean.parseBoolean(request.getParameter("comments"));
		boolean rating = Boolean.parseBoolean(request.getParameter("rating"));
		Visibility visi;
		if(visibility.equals("Public")) {
			visi = Visibility.PUBLIC;
		}else if(visibility.equals("Private")) {
			visi = Visibility.PRIVATE;
		}else if(visibility.equals("Unlisted")) {
			visi = Visibility.UNLISTED;
		}
		System.out.println("description: " + description);
		
		if(doing.equals("add")) {
			Video v = new Video();
			v.setVideoURL(url);
			v.setName(name);
			v.setDescription(description);
//			v.setVisibility(visi);
			v.setCommentsAllowed(comments);
			v.setRatingAllowed(rating);
			v.setOwner(loggedInUser);
			VideoDAO.create(v);
			
			Map<String, Object> data = new HashMap<>();
			data.put("status", "success");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			System.out.println(jsonData);

			response.setContentType("application/json");
			response.getWriter().write(jsonData);
		} else {
			Video video = VideoDAO.getVideo(id);
//			video.setVideoURL(url);
			video.setName(name);
			video.setDescription(description);
//			video.setVisibility(visi);
			video.setCommentsAllowed(comments);
			video.setRatingAllowed(rating);
			video.setOwner(loggedInUser);
			VideoDAO.update(video);
			
			Map<String, Object> data = new HashMap<>();
			data.put("status", "edited");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			System.out.println(jsonData);

			response.setContentType("application/json");
			response.getWriter().write(jsonData);
		}
		
		
		
		
	}

}
