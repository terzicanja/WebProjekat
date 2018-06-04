package yt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import yt.model.User;
import yt.model.User.Role;

public class UserDAO {

	public static ArrayList<User> getAll() {

		Connection conn = ConnectionManager.getConnection();
		ArrayList<User> users = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			String query = "SELECT * FROM users";
			pstmt = conn.prepareStatement(query);
			// pstmt.setBoolean(1, false);
			rset = pstmt.executeQuery();

			while (rset.next()) {
//				 int id = rset.getInt("id");
				int id = 2;
				String korisnicko = rset.getString("username");
				String password = rset.getString("password");
				String name = rset.getString("name");
				String lastname = rset.getString("lastname");
				String email = rset.getString("email");
				String description = rset.getString("description");
				String registrationDate = rset.getString("registrationDate");
				Role role = Role.valueOf(rset.getString("role"));
				boolean blocked = rset.getBoolean("blocked");
				boolean deleted = rset.getBoolean("deleted");
				int subsNumber = rset.getInt("subsNumber");

				User user = new User(id, korisnicko, password, name, lastname, email, description, registrationDate, role,
						blocked, deleted, subsNumber, null, null, null);
//				pstmt.close();
//				rset.close();

				users.add(user);
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
		return users;
	}
	

	public static User get(String username) {

		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			String query = "SELECT * FROM users WHERE username = ? AND deleted = 0";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, username);
			// pstmt.setBoolean(2, false);
			rset = pstmt.executeQuery();

			while (rset.next()) {
//				 int id = rset.getInt("id");
				int id = 2;
				String korisnicko = rset.getString("username");
				String password = rset.getString("password");
				String name = rset.getString("name");
				String lastname = rset.getString("lastname");
				String email = rset.getString("email");
				String description = rset.getString("description");
				String registrationDate = rset.getString("registrationDate");
				Role role = Role.valueOf(rset.getString("role"));
				boolean blocked = rset.getBoolean("blocked");
				boolean deleted = rset.getBoolean("deleted");
				int subsNumber = rset.getInt("subsNumber");

				User user = new User(id, korisnicko, password, name, lastname, email, description, registrationDate, role,
						blocked, deleted, subsNumber, null, null, null);
				pstmt.close();
				rset.close();

				return user;
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
	
	
	
	
	
	public static ArrayList<User> getMostPopular() {

		Connection conn = ConnectionManager.getConnection();
		ArrayList<User> users = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			String query = "SELECT * FROM users WHERE deleted = 0 ORDER BY subsNumber desc LIMIT 5";
			
//			"Select distinct(s.mainUser),count(s.mainUser) as broj,u.deleted from subscribe as s inner join users as u on s.mainUser=u.userName\r\n" + 
//			"where u.deleted = ?\r\n" + 
//			"group by s.mainUser\r\n" + 
//			"order by broj desc"
//			
//"SELECT A.* FROM users A INNER JOIN "
//+ "(SELECT user_id, COUNT(*) follow_count  FROM follow GROUP BY user_id) "
//+ "B ON A.id=B.user_id "
//+ "ORDER BY B.follow_count DESC LIMIT 5"
			
//			String sql = "SELECT * FROM video LEFT OUTER JOIN user ON video.user_id = user.username "
//					+ "LEFT OUTER JOIN subscribes ON user.username = subscribes.subscribed_user_id GROUP BY video.id LIMIT 5";
			
			pstmt = conn.prepareStatement(query);
			// pstmt.setBoolean(1, false);
			rset = pstmt.executeQuery();

			while (rset.next()) {
//				int id = rset.getInt("id");
				int id = 1;
				String korisnicko = rset.getString("username");
				String password = rset.getString("password");
				String name = rset.getString("name");
				String lastname = rset.getString("lastname");
				String email = rset.getString("email");
				String description = rset.getString("description");
				String registrationDate = rset.getString("registrationDate");
				Role role = Role.valueOf(rset.getString("role"));
				boolean blocked = rset.getBoolean("blocked");
				boolean deleted = rset.getBoolean("deleted");
				int subsNumber = rset.getInt("subsNumber");

				User user = new User(id, korisnicko, password, name, lastname, email, description, registrationDate, role,
						blocked, deleted, subsNumber, null, null, null);
//				pstmt.close();
//				rset.close();

				users.add(user);
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
		return users;
	}
	
	
	public static boolean create(User user) {
		Connection conn = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO users (username, password, name, lastname, description, email, registrationDate) VALUES (?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, user.getUsername());
			pstmt.setString(index++, user.getPassword());
			pstmt.setString(index++, user.getName());
			pstmt.setString(index++, user.getLastname());
			pstmt.setString(index++, user.getDescription());
			pstmt.setString(index++, user.getEmail());
			pstmt.setString(index++, user.getRegistrationDate());
			
			return pstmt.executeUpdate() == 1;
		} catch (SQLException e) {
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
	
	
	public static boolean update(User user) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		
		try {
			String query = "UPDATE users SET password = ?, name = ?, lastname = ?, email = ?, description = ?, role = ?, blocked = ?, deleted = ?, subsNumber = ? WHERE username = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getLastname());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getDescription());
			pstmt.setString(6, user.getRole().toString());
			pstmt.setBoolean(7, user.isBlocked());
			pstmt.setBoolean(8, user.isDeleted());
			pstmt.setInt(9, user.getSubsNumber());
			pstmt.setString(10, user.getUsername());
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
	
	
	
	
	
	public static boolean isSubscribed(String subscriber, String subscribedTo) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = "SELECT * FROM subs WHERE subscriber = ? AND subsribed_to = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, subscriber);
			pstmt.setString(2, subscribedTo);
			rset = pstmt.executeQuery();
			
			if (rset.next()) {				
				return true;
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

		return false;
	}
	
	
	
	
	
	public static String dateToStringForWrite(Date date) {
		SimpleDateFormat formatvr = new SimpleDateFormat("yyyy-MM-dd");
		String datum;
		datum = formatvr.format(date);
		return datum;
	}
	
	public static Date stringToDateForWrite(String datum) {
		try {
			DateFormat formatvr = new SimpleDateFormat("yyyy-MM-dd");

			return formatvr.parse(datum);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}
