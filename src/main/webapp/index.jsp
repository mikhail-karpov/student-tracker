<%@ page import="com.mikhailkarpov.student.model.Student" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--<% List<Student> students = (List<Student>) request.getAttribute("students"); %>--%>

<html>
<title>Student Tracker</title>
<body>
<h2>Student List</h2>

<table>
    <tr>
        <th>id</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <th>Action</th>
    </tr>

<%--    <% for (Student student : students) { %>--%>
<%--    <tr>--%>
<%--        <td><%= student.getId()%></td>--%>
<%--        <td><%= student.getFirstName()%></td>--%>
<%--        <td><%= student.getLastName()%></td>--%>
<%--        <td><%= student.getEmail()%></td>--%>
<%--        <td>Edit | Delete</td>--%>
<%--    </tr>--%>
<%--    <% } %>--%>

    <c:forEach var="student" items="${students}" >
        <tr>
            <td>${student.id}</td>
            <td>${student.firstName}</td>
            <td>${student.lastName}</td>
            <td>${student.email}</td>
            <td>Edit | Delete</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
