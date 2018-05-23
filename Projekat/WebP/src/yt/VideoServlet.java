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

import yt.dao.VideoDAO;
import yt.model.User;
import yt.model.Video;

public class VideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
//		if (loggedInUser != null)
//			loggedInUser.setVideoLikes(new LikeDAO().getAllVideoLikes(loggedInUser));

		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("id parametar jee: " + id);
//		String load = request.getParameter("load");

		Video video = VideoDAO.getVideo(id);
//		if (load != null) {
//			long currentViews = video.getViews();
//			video.setViews(currentViews + 1);
//			videoDAO.update(video);
//		}

		Map<String, Object> data = new HashMap<>();
		data.put("video", video);
		data.put("loggedInUser", loggedInUser);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(data);
		System.out.println(json);

		response.setContentType("application/json");
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
