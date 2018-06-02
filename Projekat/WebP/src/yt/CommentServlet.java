package yt;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		if(status.equals("add")) {
			if(loggedInUser!=null && !loggedInUser.isBlocked()) {
				String content = request.getParameter("content");
				Comment comment = new Comment();
				comment.setContent(content);
				comment.setAuthor(loggedInUser);
				comment.setVideo(video);
				CommentDAO.addComment(comment);
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
		
		
		
		
		
		
		
	}

}