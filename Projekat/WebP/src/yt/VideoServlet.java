package yt;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
		String sort = request.getParameter("sort");
		System.out.println("id parametar jee: " + id);
		String status = "unrated";
		String videoStatus = "";
		String ratingStatus = "";
		Video video = new Video();
		ArrayList<Comment> comments = new ArrayList<>();
		
		if(VideoDAO.getVideo(id).isRatingAllowed()) {
			ratingStatus = "moze";
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
		}else {
			ratingStatus = "neMozee";
		}
		
		String commentsAllowed = "";
		
		
		
		
//		if(video != null && video.isCommentsAllowed()) {
//			comments = CommentDAO.getAll(id);
//			commentsAllowed = "yes";
//		}else {
//			commentsAllowed = "no";
//		}
		
		
//		if(VideoDAO.getVideo(id).isDeleted() || VideoDAO.getVideo(id).isBlocked()) {
//			if(loggedInUser == null || !loggedInUser.getRole().toString().equals("ADMIN") || !loggedInUser.getUsername().equals(VideoDAO.getVideo(id).getOwner().getUsername())) {
//				videoStatus = "cantSeeVideo";
//			}else if(loggedInUser.getRole().toString().equals("ADMIN") || loggedInUser.getUsername().equals(VideoDAO.getVideo(id).getOwner().getUsername())) {
//				video = VideoDAO.getVideo(id);
//				video.setViews(video.getViews() + 1);
//				VideoDAO.update(video);
//			}
//		}else {
//			video = VideoDAO.getVideo(id);
//			video.setViews(video.getViews() + 1);
//			VideoDAO.update(video);
//		}
		
		if(loggedInUser != null && (loggedInUser.getRole().toString().equals("ADMIN") || loggedInUser.getUsername().equals(VideoDAO.getVideo(id).getOwner().getUsername()))) {
			video = VideoDAO.getVideo(id);
			video.setViews(video.getViews() + 1);
			VideoDAO.update(video);
		}else {
			if(VideoDAO.getVideo(id).isDeleted() || VideoDAO.getVideo(id).isBlocked() || VideoDAO.getVideo(id).getOwner().isBlocked() || VideoDAO.getVideo(id).getOwner().isDeleted()) {
				video = null;
				videoStatus = "cantSeeVideo";
			}else if(VideoDAO.getVideo(id).getVisibility().toString() == "PRIVATE"){
				video = null;
				videoStatus = "privateVideo";
			}else {
				video = VideoDAO.getVideo(id);
				video.setViews(video.getViews() + 1);
				VideoDAO.update(video);
			}
		}
		
//		if(loggedInUser == null || !loggedInUser.getRole().toString().equals("ADMIN") || !loggedInUser.getUsername().equals(VideoDAO.getVideo(id).getOwner().getUsername())) {
//			if(VideoDAO.getVideo(id).isDeleted() || VideoDAO.getVideo(id).isBlocked()) {
//				videoStatus = "cantSeeVideo";
//			}else {
//				video = VideoDAO.getVideo(id);
//				video.setViews(video.getViews() + 1);
//				VideoDAO.update(video);
//			}
//		}else {
//			video = VideoDAO.getVideo(id);
//			video.setViews(video.getViews() + 1);
//			VideoDAO.update(video);
//		}
		
		
		
		
		if(sort.equals("none")) {
//			comments = VideoDAO.getAll();
//			ArrayList<Comment> comments = new ArrayList<>();
			if(video != null && video.isCommentsAllowed()) {
				comments = CommentDAO.getAll(id);
				commentsAllowed = "yes";
				System.out.println("ovo su komentari koje treba odma da ispise"+comments);
			}else {
				commentsAllowed = "no";
			}
		}else if(sort.equals("mostPopular")) {
			comments = CommentDAO.getAllSorted(id, "dislikesNumber desc");
		}else if(sort.equals("leastPopular")) {
			comments = CommentDAO.getAllSorted(id, "likesNumber asc");
		}else if(sort.equals("newest")) {
			comments = CommentDAO.getAllSorted(id, "comment_date desc");
		}else if(sort.equals("oldest")) {
			comments = CommentDAO.getAllSorted(id, "comment_date asc");
		}
		
		
		
		
		
		System.out.println("video je: "+video);

//		Video video = VideoDAO.getVideo(id);
//		video.setViews(video.getViews() + 1);
//		VideoDAO.update(video);
		int videoLikes = RatingDAO.getCountVideoLikes(id);
		int videoDislikes = RatingDAO.getCountVideoDislikes(id);
		
		

		Map<String, Object> data = new HashMap<>();
		data.put("video", video);
		data.put("status", status);
		data.put("ratingStatus", ratingStatus);
		data.put("videoStatus", videoStatus);
		data.put("commentsAllowed", commentsAllowed);
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
		
		String status = "";
		int id = Integer.parseInt(request.getParameter("id"));
		String doing = request.getParameter("doing");
		String url = request.getParameter("url");
		String img = request.getParameter("img");
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String visibility = request.getParameter("visibility");
		boolean comments = Boolean.parseBoolean(request.getParameter("comments"));
		boolean rating = Boolean.parseBoolean(request.getParameter("rating"));
		boolean blocked = Boolean.parseBoolean(request.getParameter("blocked"));
		boolean deleted = Boolean.parseBoolean(request.getParameter("deleted"));
		Visibility visi = null;
		if(visibility.equals("Public")) {
			visi = Visibility.PUBLIC;
		}else if(visibility.equals("Private")) {
			visi = Visibility.PRIVATE;
		}else if(visibility.equals("Unlisted")) {
			visi = Visibility.UNLISTED;
		}
		System.out.println("description: " + description);
		
		if(doing.equals("add")) {
			if(loggedInUser == null) {
				status = "notLoggedIn";
			}else {
				Video v = new Video();
				v.setVideoURL(url);
				v.setVideoImg(img);
				v.setName(name);
				v.setDescription(description);
				Date dt = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String currentTime = sdf.format(dt);
				v.setDate(currentTime);
//				v.setVisibility(visi);
				v.setCommentsAllowed(comments);
				v.setRatingAllowed(rating);
				v.setOwner(loggedInUser);
				VideoDAO.create(v);
			}
			
			
			Map<String, Object> data = new HashMap<>();
			data.put("status", "success");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			System.out.println(jsonData);

			response.setContentType("application/json");
			response.getWriter().write(jsonData);
		} else if(doing.equals("edit")) {
			Video video = VideoDAO.getVideo(id);
//			video.setVideoURL(url);
			video.setVideoImg(img);
			video.setName(name);
			video.setDescription(description);
			video.setVisibility(visi);
			video.setCommentsAllowed(comments);
			video.setRatingAllowed(rating);
			video.setOwner(loggedInUser);
			if(loggedInUser.getRole().toString().equals("ADMIN")) {
				video.setBlocked(blocked);
				video.setDeleted(deleted);
			}
			System.out.println("blokiranje je: "+blocked+"brisanje je: "+deleted);
			VideoDAO.update(video);
			
			Map<String, Object> data = new HashMap<>();
			data.put("status", "edited");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			System.out.println(jsonData);

			response.setContentType("application/json");
			response.getWriter().write(jsonData);
		} else if(doing.equals("delete")) {
			Video video = VideoDAO.getVideo(id);
			video.setDeleted(true);
			VideoDAO.update(video);
		}
		
		
		
		
	}

}
