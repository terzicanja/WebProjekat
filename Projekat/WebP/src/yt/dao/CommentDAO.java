package yt.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.sql.PreparedStatement;

import yt.model.Comment;
import yt.model.User;
import yt.model.Video;

public class CommentDAO {
	
	public static ArrayList<Comment> getAll(int videoId){
		Connection conn = ConnectionManager.getConnection();
		ArrayList<Comment> comments = new ArrayList<Comment>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = "SELECT * FROM comments WHERE videoId = ? AND deleted = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, videoId);
			pstmt.setBoolean(2, false);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				int id = rset.getInt("id");
				String content = rset.getString("content");
				Date date = rset.getDate("comment_date");
				String username = rset.getString("author");
				User author = UserDAO.get(username);
				Video video = VideoDAO.getVideo(videoId);
				int likesNumber = rset.getInt("likesNumber");
				int dislikesNumber = rset.getInt("dislikesNumber");
				boolean deleted = rset.getBoolean("deleted");
				
				Comment comment = new Comment(id, content, date, author, video, likesNumber, dislikesNumber, deleted);
				comments.add(comment);
			}
			
		} catch (Exception e) {
			System.out.println("Greska u SQL upitu!");
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
			try {
				rset.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return comments;
	}
	
	
	public static Comment getComment(int id) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = "SELECT * FROM comments WHERE id = ? AND deleted = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			pstmt.setBoolean(2, false);
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
//				int id = rset.getInt("id");
				String content = rset.getString("content");
				Date date = rset.getDate("comment_date");
				String username = rset.getString("author");
				User author = UserDAO.get(username);
				int videoId = rset.getInt("videoId");
				Video video = VideoDAO.getVideo(videoId);
				int likesNumber = rset.getInt("likesNumber");
				int dislikesNumber = rset.getInt("dislikesNumber");
				boolean deleted = rset.getBoolean("deleted");
				
				return new Comment(id, content, date, author, video, likesNumber, dislikesNumber, deleted);
			}
		} catch (Exception e) {
			System.out.println("Greska u SQL upitu!");
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
			try {
				rset.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return null;
	}
	
	
	public static boolean addComment(Comment comment) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		
		try {
			String query = "INSERT INTO comments (content, author, videoId) VALUES (?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, comment.getContent());
			pstmt.setString(2, comment.getAuthor().getUsername());
			pstmt.setInt(3, comment.getVideo().getId());
			
			return pstmt.executeUpdate() == 1;
		} catch (Exception e) {
			System.out.println("Greska u SQL upitu!");
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return false;
	}
	
	
	public static boolean updateComment(Comment comment) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		
		try {
			String query = "UPDATE comments SET content = ?, comment_date = '2010-10-10', likesNumber = ?, dislikesNumber = ?, deleted = ? WHERE id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, comment.getContent());
			pstmt.setBoolean(4, comment.isDeleted());
			pstmt.setInt(2, comment.getLikesNumber());
			pstmt.setInt(3, comment.getDislikesNumber());
			pstmt.setInt(5, comment.getId());
			
			return pstmt.executeUpdate() == 1;
		} catch (Exception e) {
			System.out.println("Greska u SQL upitu!");
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return false;
	}
	
	

}
