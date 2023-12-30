package it6020003.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import it6020003.objects.UserObject;
import it6020003.process.User;

@WebServlet("/login-user")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			User u = new User();
			UserObject user = u.getLoginedUser(email, password);
			if (user.getUser_id() != 0) {
				request.getSession().setAttribute("auth", user);
				response.sendRedirect(request.getContextPath()+"/doctorhome");
			} else {
				request.setAttribute("status", "failed");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}
	}

}
