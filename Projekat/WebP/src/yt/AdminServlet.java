package yt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import yt.dao.UserDAO;
import yt.model.User;

public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		List<User> users = new ArrayList<>();
		String status = "";
		
		System.out.println("logged in user je: "+ loggedInUser);
		if(loggedInUser == null || !loggedInUser.getRole().toString().equals("ADMIN")) {
			status = "neMoze";
		}else if(loggedInUser.getRole().toString().equals("ADMIN")) {
			users = UserDAO.getAll();
		}
		
//		if(loggedInUser != null & loggedInUser.getRole().toString().equals("ADMIN")) {
//			users = UserDAO.getAll();
//		}else if(loggedInUser == null){
//			status = "neMoze";
//		}
		
		Map<String, Object> data = new HashMap<>();
		
		data.put("loggedInUser", loggedInUser);
		data.put("users", users);
		data.put("status", status);
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(data);
		System.out.println(json);
		
		response.setContentType("application/json");
		response.getWriter().write(json);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
