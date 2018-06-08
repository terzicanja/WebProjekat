package yt;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import yt.model.User.Role;

public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		String doing = request.getParameter("doing");
		String id = request.getParameter("id");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String lastname = request.getParameter("lastname");
		String description = request.getParameter("description");
		String role = request.getParameter("role");
		boolean blocked = Boolean.parseBoolean(request.getParameter("blocked"));
		boolean deleted = Boolean.parseBoolean(request.getParameter("deleted"));
		Role r;
		if(role.equals("USER")) {
			r = Role.USER;
		}
		else if(role.equals("ADMIN")) {
			r = Role.ADMIN;
		}else {
			r = Role.USER;
		}
		String status = "success";
		
		User u = UserDAO.get(username);
		if(u != null && doing.equals("add")) {
			System.out.println("korisnik vec postoji");
			status = "existing";
		} else if(doing.equals("add")) {
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setEmail(email);
			user.setName(name);
			user.setLastname(lastname);
			user.setDescription(description);
			Date dt = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentTime = sdf.format(dt);
			user.setRegistrationDate(currentTime);
			
			UserDAO.create(user);
			
			Map<String, Object> data = new HashMap<>();
			data.put("status", status);
			
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			System.out.println(jsonData);

			response.setContentType("application/json");
			response.getWriter().write(jsonData);
			
		}else if(doing.equals("edit")) {
			User korisnik = UserDAO.get(id);
			if(loggedInUser == null) {
				status = "notLoggedIn";
			}else if(loggedInUser.getRole().toString().equals("USER") && !loggedInUser.getUsername().equals(korisnik.getUsername())) {
				status = "cannot";
			}else {
				korisnik.setPassword(password);
				korisnik.setName(name);
				korisnik.setLastname(lastname);
				korisnik.setDescription(description);
				korisnik.setEmail(email);
				
				if(loggedInUser.getRole().toString().equals("ADMIN")) {
					korisnik.setRole(r);
					korisnik.setBlocked(blocked);
					korisnik.setDeleted(deleted);
				}
				
				UserDAO.update(korisnik);
				status = "success";
			}
			
			
		}
		Map<String, Object> data = new HashMap<>();
		data.put("status", status);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		System.out.println(jsonData);

		response.setContentType("application/json");
		response.getWriter().write(jsonData);
//		response.sendRedirect("./home.html");
		
		
	}

}
