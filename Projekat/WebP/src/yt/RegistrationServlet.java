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

import com.fasterxml.jackson.databind.ObjectMapper;

import yt.dao.UserDAO;
import yt.model.User;

public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
//		String username = request.getParameter("username");
//		String username = request.getParameter("username");
		String status = "success";
		
		User u = UserDAO.get(username);
		if(u != null) {
			System.out.println("korisnik vec postoji");
			status = "existing";
		} else {
//			Date date = new Date();
//			SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String currentTime = simpleDate.format(date);
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
//			user.setRegistrationDate(simpleDate);
			
			UserDAO.create(user);
			
			Map<String, Object> data = new HashMap<>();
			data.put("status", status);
			
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			System.out.println(jsonData);

			response.setContentType("application/json");
			response.getWriter().write(jsonData);
			
		}
//		response.sendRedirect("./home.html");
		
		
	}

}
