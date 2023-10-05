

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class targetServlet
 */
public class targetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {		
		HttpSession session = req.getSession(false);
		PrintWriter out = res.getWriter();
		Cookie[] cookies = req.getCookies();
		for(int i = 0; i < cookies.length; i++) {
			out.println(cookies[i].getName());
			out.println(cookies[i].getValue());
		}
		if(session != null) {
			String userName = (String) session.getAttribute("dbName");
			out.println("Login successfully with user name: " + userName);
		} else {
			out.println("Session has ended");
		}
	}
	
	

}
