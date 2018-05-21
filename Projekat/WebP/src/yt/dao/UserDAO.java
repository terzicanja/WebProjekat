package yt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
			String query = "SELECT * FROM users WHERE deleted = 0";
			pstmt = conn.prepareStatement(query);
//			pstmt.setBoolean(1, false);
			rset = pstmt.executeQuery();

			while (rset.next()) {
//				int id = rset.getInt("id");
				String korisnicko = rset.getString("username");
				String password = rset.getString("password");
				String name = rset.getString("name");
				String lastname = rset.getString("lastname");
				String email = rset.getString("email");
				String description = rset.getString("description");
				Date registrationDate = rset.getDate("registrationDate");
				Role role = Role.valueOf(rset.getString("role"));
				boolean blocked = rset.getBoolean("blocked");
				boolean deleted = rset.getBoolean("deleted");
				
				User user = new User(korisnicko, password, name, lastname, email, description, registrationDate, role, blocked, deleted, null, null, null);
				pstmt.close();
				rset.close();
				
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
//			pstmt.setBoolean(2, false);
			rset = pstmt.executeQuery();

			while (rset.next()) {
//				int id = rset.getInt("id");
				String korisnicko = rset.getString("username");
				String password = rset.getString("password");
				String name = rset.getString("name");
				String lastname = rset.getString("lastname");
				String email = rset.getString("email");
				String description = rset.getString("description");
				Date registrationDate = rset.getDate("registrationDate");
				Role role = Role.valueOf(rset.getString("role"));
				boolean blocked = rset.getBoolean("blocked");
				boolean deleted = rset.getBoolean("deleted");
				
				User user = new User(korisnicko, password, name, lastname, email, description, registrationDate, role, blocked, deleted, null, null, null);
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

}
