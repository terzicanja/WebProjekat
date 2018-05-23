package yt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import yt.dao.UserDAO;
import yt.model.User;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String korisnickoIme = request.getParameter("username");
		String lozinka = request.getParameter("password");
		System.out.println("username getparameter: "+korisnickoIme);
		System.out.println("pass getparameter: "+lozinka);
		
		String message = "Uspesna prijava!";
		String status = "success";
		try {
			User user = UserDAO.get(korisnickoIme);
			
			if(user == null) {
				throw new Exception("Ne postoji korisnik sa unetim podacima!");
			}
			if(!user.getPassword().equals(lozinka)) {
				throw new Exception("Pogresno korisnicko ime i/ili lozinka!");
			}
//			Korisnik korisnik = KorisnikDAO.pronadjiKorisnika(korisnickoIme);
//			System.out.println("korisnikdaooooo" + korisnik);
//			if(korisnik == null) {
//				throw new Exception("Ne postoji korisnik sa unetim podacima!");
//			}
//			if(!korisnik.getPassword().equals(lozinka)) {
//				throw new Exception("Pogresno korisnicko ime i/ili lozinka!");
//			}
//			
			HttpSession session = request.getSession();
			session.setAttribute("loggedIn", user);		
		}catch (Exception e) {
			message = e.getMessage();
			status = "failure";
		}
		
		Map<String, Object> data = new HashMap<>();
		data.put("poruka", message);
		data.put("status", status);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		System.out.println(jsonData);
		
		response.setContentType("application/json");
		response.getWriter().write(jsonData);
		
		
		
		
		
		
		
		/*
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String message = "Uspesna prijava";
		
		System.out.println("pre try");
		String status = "success";
		try {
			Korisnik korisnik = KorisnikDAO.get(username);
			System.out.println(korisnik.getUsername());

			if (korisnik == null) throw new Exception("Neispravno korisnicko ime ili lozinka");
			if (!korisnik.getPassword().equals(password)) throw new Exception("neeee");
			
			HttpSession session = request.getSession();
			session.setAttribute("loggedInUser", korisnik);
		} catch (Exception ex) {
			message = ex.getMessage();
			status = "failure";
		}
		
		Map<String, Object> data = new HashMap<>();
		data.put("message", message);
		data.put("status", status);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		System.out.println(jsonData);
		
		response.setContentType("application/json");
		response.getWriter().write(jsonData);*/
	}

}
