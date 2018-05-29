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
				boolean deleted = rset.getBoolean("deleted");
				
				Comment comment = new Comment(id, content, date, author, video, deleted);
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

}