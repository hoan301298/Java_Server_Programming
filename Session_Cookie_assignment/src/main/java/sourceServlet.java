

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet(urlPatterns="/sourceServlet")
public class sourceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection conn;
	PreparedStatement ps;
	RequestDispatcher reqDis;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String dbName = request.getParameter("dbName");
		String dbPwd = request.getParameter("dbPwd");
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(60);
		response.addCookie(new Cookie("token", "sometoken"));
		session.setAttribute("dbName", dbName);
		session.setAttribute("dbPwd", dbPwd);
				
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://mariadb.vamk.fi:3306/e2000575_student", dbName, dbPwd);
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("<a href='targetServlet'> Click here to get the user name</a>");

		} catch (Exception e) {
			reqDis = request.getRequestDispatcher("login.html");
			reqDis.include(request, response);
			e.printStackTrace();
		}
	}

}
