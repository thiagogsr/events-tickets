package eventstickets.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eventstickets.dao.UserDAO;
import eventstickets.models.User;

@WebServlet("/signIn")
public class AuthenticationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
        String password = request.getParameter("password");

        HttpSession session = request.getSession(true);
        try {
            UserDAO userDAO = new UserDAO();
            User authenticatedUser = userDAO.authenticate(username, password);
            
            if (authenticatedUser != null) {
            	session.setAttribute("currentUser", authenticatedUser);
            	response.sendRedirect("Success");
            } else {
            	response.sendRedirect("Error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
