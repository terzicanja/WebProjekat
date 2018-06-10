package yt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import yt.model.Rating;


public class RatingDAO {
	
	public static int getCountVideoLikes(int videoId) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int likes = 0;
		try {
			String query = "SELECT COUNT(*) AS likes FROM videoratings WHERE liked = true AND rated_video = ?";
			pstmt = conn.prepareStatement(query);
//			pstmt.setString(1, liked);
			pstmt.setInt(1, videoId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				likes = rset.getInt(1);
			}
			return likes;
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
		return likes;
	}
	
	
	public static int getCountVideoDislikes(int videoId) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int likes = 0;
		try {
			String query = "SELECT COUNT(*) AS likes FROM videoratings WHERE liked = false AND rated_video = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, videoId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				likes = rset.getInt(1);
			}
			return likes;
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
		return likes;
	}
	
	
	public static Rating getUserVideoLikes(int videoId, String username) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = "SELECT * FROM videoratings WHERE rated_video = ? AND who_rated = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, videoId);
			pstmt.setString(2, username);
			rset = pstmt.executeQuery();

			if (rset.next()) {
//				int index = 2;
//				int id = rset.getInt("id");
				int likeId = rset.getInt("id");
				boolean isLike = rset.getBoolean("liked");
				Date d = rset.getDate("rated_time");
//				String owner = rset.getString(index++);
//				String date = VideoDAO.dateToString(d);
				return new Rating(likeId, isLike, d, UserDAO.get(username), VideoDAO.getVideo(videoId), null);
			}

		} catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
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
	
	
	public static boolean addRating(Rating rating) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		
		try {
			String query = "INSERT INTO videoratings (liked, rated_time, who_rated, rated_video) VALUES (?, '2013-12-12', ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setBoolean(1, rating.isLikeDislike());
			pstmt.setString(2, rating.getWhoLiked().getUsername());
			pstmt.setInt(3, rating.getLikedVideo().getId());
			
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
	
	
	public static boolean updateVideoRating(Rating rating) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		
		try {
			String query = "UPDATE videoratings SET liked = ? WHERE id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setBoolean(1, rating.isLikeDislike());
			pstmt.setInt(2, rating.getId());
			
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
	
	
	public static boolean deleteVideoRating(Rating rating) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		
		try {
			String query = "DELETE FROM videoratings WHERE who_rated = ? AND rated_video = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, rating.getWhoLiked().getUsername());
			pstmt.setInt(2, rating.getLikedVideo().getId());
			
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
	
	
	
	
	
	
	
	
	
	
	public static int getCountCommentLikes(int videoId) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int likes = 0;
		try {
			String query = "SELECT COUNT(*) AS likes FROM commentratings WHERE liked = true AND rated_comment = ?";
			pstmt = conn.prepareStatement(query);
//			pstmt.setString(1, liked);
			pstmt.setInt(1, videoId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				likes = rset.getInt(1);
			}
			return likes;
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
		return likes;
	}
	
	
	public static int getCountCommentDislikes(int videoId) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int likes = 0;
		try {
			String query = "SELECT COUNT(*) AS likes FROM commentratings WHERE liked = false AND rated_comment = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, videoId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				likes = rset.getInt(1);
			}
			return likes;
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
		return likes;
	}
	
	
	public static Rating getUserCommentLikes(int videoId, String username) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = "SELECT * FROM commentratings WHERE rated_comment = ? AND who_rated = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, videoId);
			pstmt.setString(2, username);
			rset = pstmt.executeQuery();

			if (rset.next()) {
//				int index = 2;
//				int id = rset.getInt("id");
				int likeId = rset.getInt("id");
				boolean isLike = rset.getBoolean("liked");
				Date d = rset.getDate("rated_time");
//				String owner = rset.getString(index++);
//				String date = VideoDAO.dateToString(d);
				return new Rating(likeId, isLike, d, UserDAO.get(username), null, CommentDAO.getComment(videoId));
			}

		} catch (Exception ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
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
	
	
	public static boolean addRatingComment(Rating rating) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		
		try {
			String query = "INSERT INTO commentratings (liked, rated_time, who_rated, rated_comment) VALUES (?, '2013-12-12', ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setBoolean(1, rating.isLikeDislike());
			pstmt.setString(2, rating.getWhoLiked().getUsername());
			pstmt.setInt(3, rating.getLikedComment().getId());
			
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
	
	
	public static boolean updateCommentRating(Rating rating) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		
		try {
			String query = "UPDATE commentratings SET liked = ? WHERE id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setBoolean(1, rating.isLikeDislike());
			pstmt.setInt(2, rating.getId());
			
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
