package hoan.server;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet(urlPatterns = "/SumServlet")
public class AddNumberServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		int n1 = Integer.parseInt(req.getParameter("Number_1"));
		int n2 = Integer.parseInt(req.getParameter("Number_2"));
		int sum = n1 + n2;
		try {		
			res.setContentType("text/html");
			PrintWriter out = res.getWriter();
			out.println("<html>");
			out.println("<body>");
			out.println("<h1> Sum of Number1 and Number2 is: " + sum);
			out.println("</body>");
			out.println("</html>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
