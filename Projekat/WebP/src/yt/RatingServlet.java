package yt;

import java.io.IOException;
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

public class RatingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		int id = Integer.parseInt(request.getParameter("id"));
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		String what = request.getParameter("what");
		System.out.println("parametar videa je: " + id);
		Video video = VideoDAO.getVideo(id);
		Comment comment = CommentDAO.getComment(commentId);
		String status = "unrated";
		String commentStatus = "unrated";
		int commentLikes = 0;
		int commentDislikes = 0;
		
		if(loggedInUser != null) {
//		if(loggedInUser != null && !loggedInUser.getUsername().equals(video.getOwner().getUsername())) {
			Rating rating = RatingDAO.getUserVideoLikes(id, loggedInUser.getUsername());
			Rating ratingComment = RatingDAO.getUserCommentLikes(commentId, loggedInUser.getUsername());
			System.out.println("rating je ovo: " + rating);
			System.out.println("rating komentara je ovo: " + ratingComment);
			
			if(what.equals("video")) {
				if(rating == null) {
//					status = "notRatedYet";
					Rating r = new Rating();
					r.setLikeDislike(true);
					r.setWhoLiked(loggedInUser);
					r.setLikedVideo(video);
					r.setDate(new Date());
					RatingDAO.addRating(r);
					System.out.println("uslo je ovde");
					status = "liked";
				}else if(rating != null) {
					if(rating.isLikeDislike()) {
						RatingDAO.deleteVideoRating(rating);
						status = "unrated";
					} else {
//						status = "dislajkovano";
						rating.setLikeDislike(true);
						RatingDAO.updateVideoRating(rating);
						System.out.println("ili ovde");
						status = "liked";
					}
				}
			} else if(what.equals("comment")) {
				if(ratingComment == null) {
//					status = "notRatedYet";
					Rating r = new Rating();
					r.setLikeDislike(true);
					r.setWhoLiked(loggedInUser);
					r.setLikedComment(comment);
					r.setDate(new Date());
					RatingDAO.addRatingComment(r);
					System.out.println("uslo je ovde kod komentaraa");
					commentStatus = "commentLiked";
				}else if(ratingComment != null) {
					if(ratingComment.isLikeDislike()) {
						RatingDAO.deleteCommentRating(ratingComment);
						commentStatus = "unrated";
					} else {
//						status = "dislajkovano";
						ratingComment.setLikeDislike(true);
						RatingDAO.updateCommentRating(rating);
						System.out.println("ili ovde");
						commentStatus = "commentLiked";
					}
				}
				commentLikes = RatingDAO.getCountCommentLikes(commentId);
				commentDislikes = RatingDAO.getCountCommentDislikes(commentId);
				comment.setLikesNumber(commentLikes);
				comment.setDislikesNumber(commentDislikes);
				CommentDAO.updateComment(comment);
			}
			
		} else {
			status = "cannotLike";
		}
		
		int numberOfLikes = RatingDAO.getCountVideoLikes(id);
		int numberOfDislikes = RatingDAO.getCountVideoDislikes(id);
//		int commentLikes = RatingDAO.getCountCommentLikes(commentId);
//		int commentDislikes = RatingDAO.getCountCommentDislikes(commentId);
		video.setLikes(numberOfLikes);
		video.setDislikes(numberOfDislikes);
		VideoDAO.update(video);
//		comment.setLikesNumber(commentLikes);
//		comment.setDislikesNumber(commentDislikes);
//		CommentDAO.updateComment(comment);
		System.out.println(status);
		
		Map<String, Object> data = new HashMap<>();
		data.put("video", video);
		data.put("comment", comment);
		data.put("commentStatus", commentStatus);
		data.put("status", status);
		data.put("numberOfLikes", numberOfLikes);
		data.put("numberOfDislikes", numberOfDislikes);
		data.put("commentLikes", commentLikes);
		data.put("commentDislikes", commentDislikes);
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
		System.out.println("parametar videa je: " + id);
		Video video = VideoDAO.getVideo(id);
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		String what = request.getParameter("what");
		Comment comment = CommentDAO.getComment(commentId);
		int commentLikes = 0;
		int commentDislikes = 0;
		String status = "unrated";
		String commentStatus = "unrated";
		System.out.println("loggedinuser kod dislajkovanja je: "+loggedInUser);
		
		if(loggedInUser != null) {
//			if(loggedInUser != null && !loggedInUser.getUsername().equals(video.getOwner().getUsername())) {
				Rating rating = RatingDAO.getUserVideoLikes(id, loggedInUser.getUsername());
				Rating ratingComment = RatingDAO.getUserCommentLikes(commentId, loggedInUser.getUsername());
				System.out.println("rating je ovo: " + rating);
				
				if(what.equals("video")) {
					if(rating == null) {
//						status = "notRatedYet";
						Rating r = new Rating();
						r.setLikeDislike(false);
						r.setWhoLiked(loggedInUser);
						r.setLikedVideo(video);
						r.setDate(new Date());
						RatingDAO.addRating(r);
						System.out.println("uslo je ovde kod dislajkova");
						status = "disliked";
					}else if(rating != null) {
						if(!rating.isLikeDislike()) {
							RatingDAO.deleteVideoRating(rating);
							status = "unrated";
						} else {
//							status = "dislajkovano";
							rating.setLikeDislike(false);
							RatingDAO.updateVideoRating(rating);
							System.out.println("ili ovde");
							status = "disliked";
						}
					}
				} else if(what.equals("comment")) {
					if(ratingComment == null) {
//						status = "notRatedYet";
						Rating r = new Rating();
						r.setLikeDislike(false);
						r.setWhoLiked(loggedInUser);
						r.setLikedComment(comment);
						r.setDate(new Date());
						RatingDAO.addRatingComment(r);
						System.out.println("uslo je ovde");
						commentStatus = "commentDisliked";
					}else if(ratingComment != null) {
						if(!ratingComment.isLikeDislike()) {
							RatingDAO.updateCommentRating(ratingComment);
							commentStatus = "unrated";
						} else {
//							status = "dislajkovano";
							ratingComment.setLikeDislike(false);
							RatingDAO.updateCommentRating(rating);
							System.out.println("ili ovde");
							commentStatus = "commentDisliked";
						}
					}
					commentLikes = RatingDAO.getCountCommentLikes(commentId);
					commentDislikes = RatingDAO.getCountCommentDislikes(commentId);
					comment.setLikesNumber(commentLikes);
					comment.setDislikesNumber(commentDislikes);
					CommentDAO.updateComment(comment);
				}
				
			} else {
				status = "cannotLike";
			}
		
		
		
//		if(loggedInUser != null && !loggedInUser.getUsername().equals(video.getOwner().getUsername())) {
//			Rating rating = RatingDAO.getUserVideoLikes(id, loggedInUser.getUsername());
//			System.out.println("rating je ovo: " + rating);
//			if(rating == null) {
////				status = "notRatedYet";
//				Rating r = new Rating();
//				r.setLikeDislike(false);
//				r.setWhoLiked(loggedInUser);
//				r.setLikedVideo(video);
//				r.setDate(new Date());
//				RatingDAO.addRating(r);
//				System.out.println("uslo je ovde kod dislajkova");
//				status = "disliked";
//			}else if(rating != null) {
//				if(!rating.isLikeDislike()) {
//					RatingDAO.deleteVideoRating(rating);
//					status = "unrated";
//				} else {
////					status = "dislajkovano";
//					rating.setLikeDislike(false);
//					RatingDAO.updateVideoRating(rating);
//					System.out.println("ili ovde");
//					status = "disliked";
//				}
//			}
//		} else {
//			status = "cannotLike";
//		}
		
		int numberOfLikes = RatingDAO.getCountVideoLikes(id);
		int numberOfDislikes = RatingDAO.getCountVideoDislikes(id);
		video.setLikes(numberOfLikes);
		video.setDislikes(numberOfDislikes);
		VideoDAO.update(video);
		System.out.println(status);
		
		Map<String, Object> data = new HashMap<>();
		data.put("video", video);
		data.put("status", status);
		data.put("commentStatus", commentStatus);
		data.put("numberOfLikes", numberOfLikes);
		data.put("numberOfDislikes", numberOfDislikes);
		data.put("loggedInUser", loggedInUser);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(data);
		System.out.println(json);

		response.setContentType("application/json");
		response.getWriter().write(json);
	}

}
