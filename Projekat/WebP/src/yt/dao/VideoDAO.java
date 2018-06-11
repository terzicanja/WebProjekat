package yt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import yt.model.User;
import yt.model.Video;
import yt.model.Video.Visibility;

public class VideoDAO {
	
	public static ArrayList<Video> getAll() {
//		ConnectionManager.open();
		Connection conn = ConnectionManager.getConnection();
		ArrayList<Video> videos = new ArrayList<Video>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM videos";
			pstmt = conn.prepareStatement(query);
//			pstmt.setBoolean(1, false);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				int id = rset.getInt("id");
				String videoURL = rset.getString("videoURL");
				String videoImg = rset.getString("videoImg");
				String name = rset.getString("name");
				String description = rset.getString("description");
				Visibility visibility = Visibility.valueOf(rset.getString("visibility"));
				boolean commentsAllowed = rset.getBoolean("commentsAllowed");
				boolean blocked = rset.getBoolean("blocked");
				boolean ratingAllowed = rset.getBoolean("ratingAllowed");
				boolean deleted = rset.getBoolean("deleted");
				int views = rset.getInt("views");
				int likes = rset.getInt("likes");
				int dislikes = rset.getInt("dislikes");
				String dateCreated = rset.getString("dateCreated");
				String userId = rset.getString("user_id");
				User user = UserDAO.get(userId);
//				User user = 
				
				Video video = new Video(id, videoURL, videoImg, name, description, visibility, commentsAllowed, blocked, ratingAllowed, deleted, views, likes, dislikes, dateCreated, user);
				videos.add(video);
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
		return videos;
	}
	
	
	public static Video getVideo(int idVideo) {
//		ConnectionManager.open();
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM videos WHERE id = ?";
			pstmt = conn.prepareStatement(query);
//			pstmt.setBoolean(1, false);
			pstmt.setInt(1, idVideo);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				int id = rset.getInt("id");
				String videoURL = rset.getString("videoURL");
				String videoImg = rset.getString("videoImg");
				String name = rset.getString("name");
				String description = rset.getString("description");
				Visibility visibility = Visibility.valueOf(rset.getString("visibility"));
				boolean commentsAllowed = rset.getBoolean("commentsAllowed");
				boolean blocked = rset.getBoolean("blocked");
				boolean ratingAllowed = rset.getBoolean("ratingAllowed");
				boolean deleted = rset.getBoolean("deleted");
				int views = rset.getInt("views");
				int likes = rset.getInt("likes");
				int dislikes = rset.getInt("dislikes");
				String dateCreated = rset.getString("dateCreated");
				String userId = rset.getString("user_id");
				User user = UserDAO.get(userId);
				
				
				Video video = new Video(id, videoURL, videoImg, name, description, visibility, commentsAllowed, blocked, ratingAllowed, deleted, views, likes, dislikes, dateCreated, user);
				return video;
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
	
	public static ArrayList<Video> getVideosByUser(String username) {
//		ConnectionManager.open();
		Connection conn = ConnectionManager.getConnection();
		ArrayList<Video> videos = new ArrayList<Video>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM videos WHERE user_id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, username);
//			pstmt.setBoolean(2, false);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				int id = rset.getInt("id");
				String videoURL = rset.getString("videoURL");
				String videoImg = rset.getString("videoImg");
				String name = rset.getString("name");
				String description = rset.getString("description");
				Visibility visibility = Visibility.valueOf(rset.getString("visibility"));
				boolean commentsAllowed = rset.getBoolean("commentsAllowed");
				boolean blocked = rset.getBoolean("blocked");
				boolean ratingAllowed = rset.getBoolean("ratingAllowed");
				boolean deleted = rset.getBoolean("deleted");
				int views = rset.getInt("views");
				int likes = rset.getInt("likes");
				int dislikes = rset.getInt("dislikes");
				String dateCreated = rset.getString("dateCreated");
				String userId = rset.getString("user_id");
				User user = UserDAO.get(userId);
//				User user = 
				
				Video video = new Video(id, videoURL, videoImg, name, description, visibility, commentsAllowed, blocked, ratingAllowed, deleted, views, likes, dislikes, dateCreated, user);
				videos.add(video);
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
		return videos;
	}
	
	
	public static ArrayList<Video> getPublicVideosByUser(String username) {
//		ConnectionManager.open();
		Connection conn = ConnectionManager.getConnection();
		ArrayList<Video> videos = new ArrayList<Video>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM videos WHERE user_id = ? AND visibility = 'PUBLIC'";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, username);
//			pstmt.setBoolean(2, false);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				int id = rset.getInt("id");
				String videoURL = rset.getString("videoURL");
				String videoImg = rset.getString("videoImg");
				String name = rset.getString("name");
				String description = rset.getString("description");
				Visibility visibility = Visibility.valueOf(rset.getString("visibility"));
				boolean commentsAllowed = rset.getBoolean("commentsAllowed");
				boolean blocked = rset.getBoolean("blocked");
				boolean ratingAllowed = rset.getBoolean("ratingAllowed");
				boolean deleted = rset.getBoolean("deleted");
				int views = rset.getInt("views");
				int likes = rset.getInt("likes");
				int dislikes = rset.getInt("dislikes");
				String dateCreated = rset.getString("dateCreated");
				String userId = rset.getString("user_id");
				User user = UserDAO.get(userId);
//				User user = 
				
				Video video = new Video(id, videoURL, videoImg, name, description, visibility, commentsAllowed, blocked, ratingAllowed, deleted, views, likes, dislikes, dateCreated, user);
				videos.add(video);
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
		return videos;
	}
	
	
	public static ArrayList<Video> searchVideos(String search, String title, String userr, String comment) {
//		ConnectionManager.open();
		Connection conn = ConnectionManager.getConnection();
		ArrayList<Video> videos = new ArrayList<Video>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM videos WHERE "+title+userr;
			pstmt = conn.prepareStatement(query);
//			pstmt.setBoolean(1, false);
			pstmt.setString(1, '%'+search+'%');
			pstmt.setString(2, '%'+search+'%');
			rset = pstmt.executeQuery();
			while (rset.next()) {
				int id = rset.getInt("id");
				String videoURL = rset.getString("videoURL");
				String videoImg = rset.getString("videoImg");
				String name = rset.getString("name");
				String description = rset.getString("description");
				Visibility visibility = Visibility.valueOf(rset.getString("visibility"));
				boolean commentsAllowed = rset.getBoolean("commentsAllowed");
				boolean blocked = rset.getBoolean("blocked");
				boolean ratingAllowed = rset.getBoolean("ratingAllowed");
				boolean deleted = rset.getBoolean("deleted");
				int views = rset.getInt("views");
				int likes = rset.getInt("likes");
				int dislikes = rset.getInt("dislikes");
				String dateCreated = rset.getString("dateCreated");
				String userId = rset.getString("user_id");
				User user = UserDAO.get(userId);
//				User user = 
				
				Video video = new Video(id, videoURL, videoImg, name, description, visibility, commentsAllowed, blocked, ratingAllowed, deleted, views, likes, dislikes, dateCreated, user);
				videos.add(video);
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
		return videos;
	}
	
	
	public static ArrayList<Video> searchVideosByName(String search) {
//		ConnectionManager.open();
		Connection conn = ConnectionManager.getConnection();
		ArrayList<Video> videos = new ArrayList<Video>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM videos WHERE name LIKE ?";
			pstmt = conn.prepareStatement(query);
//			pstmt.setBoolean(1, false);
			pstmt.setString(1, '%'+search+'%');
			rset = pstmt.executeQuery();
			while (rset.next()) {
				int id = rset.getInt("id");
				String videoURL = rset.getString("videoURL");
				String videoImg = rset.getString("videoImg");
				String name = rset.getString("name");
				String description = rset.getString("description");
				Visibility visibility = Visibility.valueOf(rset.getString("visibility"));
				boolean commentsAllowed = rset.getBoolean("commentsAllowed");
				boolean blocked = rset.getBoolean("blocked");
				boolean ratingAllowed = rset.getBoolean("ratingAllowed");
				boolean deleted = rset.getBoolean("deleted");
				int views = rset.getInt("views");
				int likes = rset.getInt("likes");
				int dislikes = rset.getInt("dislikes");
				String dateCreated = rset.getString("dateCreated");
				String userId = rset.getString("user_id");
				User user = UserDAO.get(userId);
//				User user = 
				
				Video video = new Video(id, videoURL, videoImg, name, description, visibility, commentsAllowed, blocked, ratingAllowed, deleted, views, likes, dislikes, dateCreated, user);
				videos.add(video);
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
		return videos;
	}
	
	
	public static ArrayList<Video> searchVideosByOwner(String search) {
//		ConnectionManager.open();
		Connection conn = ConnectionManager.getConnection();
		ArrayList<Video> videos = new ArrayList<Video>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM videos WHERE user_id LIKE ?";
			pstmt = conn.prepareStatement(query);
//			pstmt.setBoolean(1, false);
			pstmt.setString(1, '%'+search+'%');
			rset = pstmt.executeQuery();
			while (rset.next()) {
				int id = rset.getInt("id");
				String videoURL = rset.getString("videoURL");
				String videoImg = rset.getString("videoImg");
				String name = rset.getString("name");
				String description = rset.getString("description");
				Visibility visibility = Visibility.valueOf(rset.getString("visibility"));
				boolean commentsAllowed = rset.getBoolean("commentsAllowed");
				boolean blocked = rset.getBoolean("blocked");
				boolean ratingAllowed = rset.getBoolean("ratingAllowed");
				boolean deleted = rset.getBoolean("deleted");
				int views = rset.getInt("views");
				int likes = rset.getInt("likes");
				int dislikes = rset.getInt("dislikes");
				String dateCreated = rset.getString("dateCreated");
				String userId = rset.getString("user_id");
				User user = UserDAO.get(userId);
//				User user = 
				
				Video video = new Video(id, videoURL, videoImg, name, description, visibility, commentsAllowed, blocked, ratingAllowed, deleted, views, likes, dislikes, dateCreated, user);
				videos.add(video);
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
		return videos;
	}
	
	
	public static boolean update(Video video) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE videos SET name = ?, description = ?, visibility = ?, commentsAllowed = ?, ratingAllowed = ?, blocked = ?, deleted = ?, views = ?, likes = ?, dislikes = ? WHERE id = ?";
			
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, video.getName());
			pstmt.setString(index++, video.getDescription());
			pstmt.setString(index++, video.getVisibility().toString());
			pstmt.setBoolean(index++, video.isCommentsAllowed());
			pstmt.setBoolean(index++, video.isRatingAllowed());
			pstmt.setBoolean(index++, video.isBlocked());
			pstmt.setBoolean(index++, video.isDeleted());
			pstmt.setInt(index++, video.getViews());
			pstmt.setInt(index++, video.getLikes());
			pstmt.setInt(index++, video.getDislikes());
			pstmt.setInt(index++, video.getId());
			
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
	
	
	public static boolean create(Video video) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		
		try {
			String query = "INSERT INTO videos(name, videoURL, videoImg, description, commentsAllowed, ratingAllowed, user_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, video.getName());
			pstmt.setString(2, video.getVideoURL());
			if(video.getVideoImg() != null) {
				pstmt.setString(3, video.getVideoImg());
			}
			pstmt.setString(3, "https://i.ytimg.com/vi/6ItdYJaQOjQ/maxresdefault.jpg");
			pstmt.setString(4, video.getDescription());
//			pstmt.setString(4, video.getVisibility().toString());
			pstmt.setBoolean(5, video.isCommentsAllowed());
			pstmt.setBoolean(6, video.isRatingAllowed());
			pstmt.setString(7, video.getOwner().getUsername());
			
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
	
	
	public static ArrayList<Video> getAllSorted(String sort) {
		Connection conn = ConnectionManager.getConnection();
		ArrayList<Video> videos = new ArrayList<Video>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM videos ORDER BY " + sort;
			pstmt = conn.prepareStatement(query);
//			pstmt.setBoolean(1, false);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				int id = rset.getInt("id");
				String videoURL = rset.getString("videoURL");
				String videoImg = rset.getString("videoImg");
				String name = rset.getString("name");
				String description = rset.getString("description");
				Visibility visibility = Visibility.valueOf(rset.getString("visibility"));
				boolean commentsAllowed = rset.getBoolean("commentsAllowed");
				boolean blocked = rset.getBoolean("blocked");
				boolean ratingAllowed = rset.getBoolean("ratingAllowed");
				boolean deleted = rset.getBoolean("deleted");
				int views = rset.getInt("views");
				int likes = rset.getInt("likes");
				int dislikes = rset.getInt("dislikes");
				String dateCreated = rset.getString("dateCreated");
				String userId = rset.getString("user_id");
				User user = UserDAO.get(userId);
				
				Video video = new Video(id, videoURL, videoImg, name, description, visibility, commentsAllowed, blocked, ratingAllowed, deleted, views, likes, dislikes, dateCreated, user);
				videos.add(video);
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
		return videos;
	}
	
	
	

}
