<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Service</title>
</head>
<body>
    <c:if test = "${user_service}">
        <h1>Already had account: Select Login</h1>
        <h1>Create a new account: Select Register</h1>
    </c:if>
    <c:if test="${badRequest}">
        <h3 style="color: red;">${message}</h3>
    </c:if>
	<c:if test="${register}">
        <h1>Insert Username and Password</h1>
        <c:url var="register_url" value="/registerForm"/>
        <form:form action="${register_url}" method="post" modelAttribute="registerRequest">
            <form:label path="username">Username: </form:label> <form:input type="text" path="username" aria-required="true"/><br>
            <form:label path="password">Password: </form:label> <form:input type="password" path="password" aria-required="true"/><br>
            <input type="submit" value="Register"/>
        </form:form>
        <p>The password will be saved in hash text!</p>
    </c:if>    
    <c:if test="${login}">
        <c:url var="authenticate_url"  value="/loginForm"/>
        <form:form action="${authenticate_url}" method="post" modelAttribute="authenticationRequest">
            <form:label path="username">Username: </form:label> <form:input type="text" path="username"/><br>
            <form:label path="password">Password: </form:label> <form:input type="password" path="password"/><br>
            <input type="submit" value="Login"/>
        </form:form>
    </c:if>
    <c:if test="${user_service}">
        <c:url var="login_url" value="/loginForm"/>
        <form action="${login_url}" method="get">
            <input type="submit" value="Login">
        </form>
        <c:url var="register_url" value="/registerForm"/>
        <form action="${register_url}" method="get">
            <input type="submit" value="Register">
        </form>
    </c:if>
</body>
</html>