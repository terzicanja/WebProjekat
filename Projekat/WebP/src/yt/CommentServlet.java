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
import yt.dao.VideoDAO;
import yt.model.Comment;
import yt.model.User;
import yt.model.Video;

public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		int id = Integer.parseInt(request.getParameter("id"));
		String status = request.getParameter("status");
		System.out.println("parametar videa je: " + id);
		Video video = VideoDAO.getVideo(id);
		
		String commentsAllowed = "";
		if(video.isCommentsAllowed()) {
			commentsAllowed = "yes";
		}else {
			commentsAllowed = "no";
		}
		
		if(status.equals("add")) {
			if(loggedInUser!=null && !loggedInUser.isBlocked()) {
				if(commentsAllowed.equals("yes")) {
					String content = request.getParameter("content");
					Comment comment = new Comment();
					comment.setContent(content);
					Date dt = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String currentTime = sdf.format(dt);
					comment.setDate(currentTime);
					comment.setAuthor(loggedInUser);
					comment.setVideo(video);
					CommentDAO.addComment(comment);
				}else {
					commentsAllowed = "no";
				}
			}
		}else if(status.equals("delete")) {
			System.out.println("ok aj sad jedan");
			if(loggedInUser != null && !loggedInUser.isBlocked()) {
				System.out.println("po jedan");
				int i = Integer.parseInt(request.getParameter("commentId"));
				Comment comment = CommentDAO.getComment(i);
				comment.setDeleted(true);
				CommentDAO.updateComment(comment);
			}
		}else if(status.equals("edit")) {
			if(loggedInUser != null && !loggedInUser.isBlocked()) {
				int newId = Integer.parseInt(request.getParameter("newId"));
				String newComment = request.getParameter("newComment");
				Comment c = CommentDAO.getComment(newId);
				if(newComment != null) {
					c.setContent(newComment);
					CommentDAO.updateComment(c);
				}
			}
		}
		
		
		Map<String, Object> data = new HashMap<>();
		data.put("video", video);
		data.put("status", status);
		data.put("commentsAllowed", commentsAllowed);
		data.put("loggedInUser", loggedInUser);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(data);
		System.out.println(json);

		response.setContentType("application/json");
		response.getWriter().write(json);
		
		
		
		
	}

}
