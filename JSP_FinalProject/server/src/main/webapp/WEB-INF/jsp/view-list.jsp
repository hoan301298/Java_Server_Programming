<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Students</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        /* Add a border between rows */
        tr {
            border-bottom: 2px solid #dddddd;
        }
    </style>
</head>
<body>
    <c:if test="${StudentSuccess}">
        <div>
            <h1>Student List</h1>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${students}" var="student">
                        <tr>
                            <td>${student.studentId}</td>
                            <td>${student.firstName}</td>
                            <td>${student.lastName}</td>
                            <td>${student.email}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <h3>Login or Sign Up to add and edit student:</h3>
        <c:url var = "user_page" value = "/userPage"/>
        <form:form action="${user_page}" method="post">
            <input type="submit" value="Click Me!">
        </form:form>
    </c:if>
    <c:if test="${CoursesSuccess}">
        <div>
            <h1>Course List</h1>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Course Name</th>
                        <th>Teacher Name</th>
                        <c:if test="${CourseSuccess}">
                            <div>
                                <th>Student Joined</th>
                            </div>
                        </c:if>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${courses}" var="course">
                        <tr>
                            <td>${course.courseId}</td>
                            <td>${course.name}</td>
                            <td>${course.teacherName}</td>
                            <c:if test="${CourseSuccess}">
                                <td>${course.studentName}</td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>
</body>
</html>