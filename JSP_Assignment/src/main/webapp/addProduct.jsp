<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Result</title>
</head>
<body>
	<%!
	   Connection conn;
	   PreparedStatement ps;
	   RequestDispatcher reqDis;
	   
	   public void jspInit() {
			try {
				Class.forName("org.mariadb.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mariadb://mariadb.vamk.fi:3306/e2000575_JSP", "e2000575", "wdmSq94C8Qm");
				ps = conn.prepareStatement("INSERT INTO product (name, price ) VALUES (?, ?)");
			} catch(Exception e) {
				e.printStackTrace();
			} 
	   }
	   
	   public void jspDestroy() {
		   try {
			   ps.close();
			   conn.close();
		   } catch(Exception e) {
			   e.printStackTrace();
		   }
	   }
	%>
	<%
	   try {
		   String Name = request.getParameter("name");
		   Float Price = Float.parseFloat(request.getParameter("price"));
		   
		   ps.setString(1, Name);
		   ps.setFloat(2, Price);
		   ps.executeUpdate();
		   
		   
	   } catch (Exception e) {
		   e.printStackTrace();
		   
	   }
	%>
	
	<button href='./addProduct.html'>Add Product again?</button>
	<input type="submit" name="View Products" formaction="viewProduct.jsp">
</body>
</html>