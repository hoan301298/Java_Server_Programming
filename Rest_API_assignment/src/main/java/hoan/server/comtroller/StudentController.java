package hoan.server.comtroller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

import hoan.server.DAO.StudentDAO;
import hoan.server.model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/student/*")
public class StudentController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private StudentDAO studentDAO;
	
	private Gson gson;
	
	public void init() {
		studentDAO = new StudentDAO();
		gson = new Gson();
	}
	
	private void sendAsJSON(HttpServletResponse response, Object obj) throws ServletException, IOException {
		response.setContentType("application/json");
		String result = gson.toJson(obj);
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String pathInfo = req.getPathInfo();
		
		res.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
		
		// Return all users
		if(pathInfo == null || pathInfo.equals("/")) {
			List<Student> students = studentDAO.selectAllStudents();
			sendAsJSON(res, students);
			return;
		}
		
		String splits[] = pathInfo.split("/");
		if(splits.length != 2) {
			res.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		int studentID = Integer.parseInt(splits[1]);
		Student student = studentDAO.selectStudentByID(studentID);
		if(student == null) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		} else {
			sendAsJSON(res, student);
			return;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
		
		String pathInfo = req.getPathInfo();
		System.out.println(pathInfo);
		if(pathInfo == null | pathInfo == "/") {
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = req.getReader();
			
			String line;
			while((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			String payload = buffer.toString();
			Student student = gson.fromJson(payload, Student.class);
			studentDAO.insertStudent(student);
		} else {
			res.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
	}
}