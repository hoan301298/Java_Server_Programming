package hoan.server.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hoan.server.model.Student;


public class StudentDAO {
	private String jdbcURL = "jdbc:mariadb://mariadb.vamk.fi:3306/e2000575_JSP/student";
	private String jdbcUserName = "e2000575";
	private String jdbcPassword = "wdmSq94C8Qm";

	// Constructors
	public StudentDAO(String url, String userName, String password) {
		this.jdbcURL = url;
		this.jdbcUserName = userName;
		this.jdbcPassword = password;
	}

	public StudentDAO() {
	}

	private static final String SELECT_ALL_STUDENT_QUERY = "SELECT * FROM student";
	private static final String SELECT_STUDENT_BY_ID = "SELECT * FROM student WHERE id=?";
	private static final String INSERT_STUDENT_QUERY = "INSERT INTO student (first_name, last_name) VALUES (?, ?)";
	private static final String DELETE_STUDENT_QUERY = "DELETE FROM student WHERE id=?";
	private static final String UPDATE_STUDENT_QUERY = "UPDATE student SET first_name=?, last_name=? WHERE id=?";

	protected Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public void insertStudent(Student student) {
		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(INSERT_STUDENT_QUERY);) {
			ps.setString(1, student.getFirstName());
			ps.setString(2, student.getLastName());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Student> selectAllStudents() {
		List<Student> students = new ArrayList<Student>();

		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_ALL_STUDENT_QUERY);) {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);

				students.add(new Student(id, firstName, lastName));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return students;
 	}

	public Student selectStudentByID(int id) {
		Student student = null;

		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_STUDENT_BY_ID);) {

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");

				student = new Student(id, firstName, lastName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return student;
	}
	
	public boolean updateStudentByID(int id, String firstName, String lastName) {
		boolean rowUpdated = false;
		Student student = null;

		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(UPDATE_STUDENT_QUERY);) {
			student = selectStudentByID(id);
			
			if (student != null) {
				ps.setString(1, firstName);
				ps.setString(2, lastName);
				ps.setInt(3, id);
				ps.executeQuery();
				rowUpdated = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rowUpdated;
	}

	public boolean deleteStudent(int id) {
		boolean rowDeleted = false;
		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(DELETE_STUDENT_QUERY);) {
			ps.setInt(1, id);
			rowDeleted = ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowDeleted;
	}
}
