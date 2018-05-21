package yt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import yt.model.Video;
import yt.model.Video.Visibility;

public class VideoDAO {
	
	public static ArrayList<Video> getAll() {
		Connection conn = ConnectionManager.getConnection();
		ArrayList<Video> videos = new ArrayList<Video>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM videos WHERE deleted = 0";
			pstmt = conn.prepareStatement(query);
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
				Date dateCreated = rset.getDate("dateCreated");
//				User user = 
				
				Video video = new Video(id, videoURL, videoImg, name, description, visibility, commentsAllowed, blocked, ratingAllowed, deleted, views, likes, dislikes, dateCreated, null);
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
