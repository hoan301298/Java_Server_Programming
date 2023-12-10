<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Student</title>
</head>
<body>
	<body>
            <h3 style="color: red;">${message}</h3>
            <h3>Insert Student</h3>
            <c:url var="add_student_url" value="/auth/add-student"/>
            <form:form action="${add_student_url}" method="post" modelAttribute="student">
                <form:label path="firstName">First Name: </form:label> <form:input type="text" path="firstName"/><br>
                <form:label path="lastName">Last Name: </form:label> <form:input type="text" path="lastName"/><br>
                <form:label path="email">Email: </form:label> <form:input type="text" path="email"/><br>
                <input type="submit" value="submit"/>
            </form:form>    
	</body>
</body>
</html>