package yt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import yt.model.Korisnik;
import yt.model.Korisnik.TipKorisnika;

public class KorisnikDAO {
	
	public static Korisnik pronadjiKorisnika(String korisnickoIme) {
		Connection conn = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			
			String query = "SELECT * FROM korisnici WHERE username = ?;";
			
			//kreiranje SQL naredbe jednom za svaki upit
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, korisnickoIme);
			System.out.println(pstmt);
			
			//izvrsavanje naredbe i prihvatanje rezultata 
			rset = pstmt.executeQuery();
			
			//citanje rezultata upita
			if(rset.next()) {
				int index = 1;
				//int id = Integer.parseInt(rset.getString(index++));
				String korisnicko = rset.getString(index++);
				String password = rset.getString(index++);
				String ime = rset.getString(index++);
				String prezime = rset.getString(index++);
				
				//TIPKORISNIKA tipKorisnika = TIPKORISNIKA.valueOf(rset.getString(index++));
				String email = rset.getString(index++);
				
				
				
				Korisnik pronadjenKorisnik = new Korisnik(korisnickoIme, password);
//				pronadjenKorisnik.setUsername(korisnickoIme);
//				pronadjenKorisnik.setPassword(password);
				//pronadjenKorisnik.setTipKorisnika(tipKorisnika);
				
				return pronadjenKorisnik;
			}
		}catch (SQLException sqlEx) {
			System.out.println("Greska u SQL upitu!");
			sqlEx.printStackTrace();
		}finally {
			try {
				pstmt.close();
			}catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
			try {
				rset.close();
			}catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
		}
		return null;
	}
	
	
	
	/*
	public static Korisnik get(String username) {
		Connection conn = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
//			String query = "SELECT password, ime, prezime, email, opisKanala, datumRegistracije, tip, blockedUser, pratioci, likeVideo, dislikeVideo, likeComment, dislikeComment FROM korisnici WHERE username = ?";
			String query = "SELECT * FROM korisnici WHERE username = ?;";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, username);
			System.out.println(pstmt);
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				int index = 1;
//				String password = rset.getString(index++);
//				String ime = rset.getString(index++);
//				String prezime = rset.getString(index++);
//				String email = rset.getString(index++);
//				String opisKanala = rset.getString(index++);
//				//DateFormat format = new SimpleDateFormat("yyyy-MM-dd"); //mozda treba prepraviti
//				//Date datumRegistracije = format.parse(rset.getString(index++));
//				Date datumRegistracije = new Date();
//				TipKorisnika tip = TipKorisnika.valueOf(rset.getString(index++));
//				boolean blockedUser = Boolean.parseBoolean(rset.getString(index++));
//				int pratioci = Integer.parseInt(rset.getString(index++));
//				int likeVideo = Integer.parseInt(rset.getString(index++));
//				int dislikeVideo = Integer.parseInt(rset.getString(index++));
//				int likeComment = Integer.parseInt(rset.getString(index++));
//				int dislikeComment = Integer.parseInt(rset.getString(index++));
				String usernamePronadjen = rset.getString(index++);
				String password = rset.getString(index++);
				Korisnik novi = new Korisnik();
				novi.setUsername(usernamePronadjen);
				novi.setPassword(password);
				return novi;
				
//				System.out.println("password: " + password);
//				
//				return new Korisnik(username, password, ime, prezime, email, opisKanala, datumRegistracije, tip, blockedUser, pratioci, likeVideo, dislikeVideo, likeComment, dislikeComment);
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		
		
		return null;
	}*/
	
	public static List<Korisnik> getAll(){
		return new ArrayList<>();
	}
	
	public static List<Korisnik> getAll(String ime, TipKorisnika tip){
		return new ArrayList<>();
	}
	
	public static boolean add(Korisnik user) {
		Connection conn = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO korisnici (username, password, email, tip) VALUES (?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, user.getUsername());
			pstmt.setString(index++, user.getPassword());
			pstmt.setString(index++, user.getEmail());
			pstmt.setString(index++, user.getTip().toString());
			System.out.println(pstmt);
			return pstmt.executeUpdate() == 1;
			
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return false;
	}
	
	public static boolean update(Korisnik user) {
		return false;
	}

	public static boolean delete(String username) {
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
