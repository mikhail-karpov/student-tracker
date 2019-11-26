<%@ page contentType="text/html; UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<title>Student Tracker</title>
<body>
<h2>Student List</h2>

<input type="button" value="Add student" onclick="window.location.href='add-student.jsp'; return false"/>

<table>
    <tr>
        <th>id</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <th>Action</th>
    </tr>

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
