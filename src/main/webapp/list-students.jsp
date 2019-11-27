<%@ page contentType="text/html; UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<title>Student Tracker</title>
<body>
<h2>Student List</h2>

<%--<input type="button" value="Add student" onclick="window.location.href='/WEB-INF/view/add-student.jsp'; return false"/>--%>
<a href="add-student.jsp">Add student</a>
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
<%--            <td>Edit | Delete</td>--%>
            <td>
                <form action="students" method="post">
                    <input type="hidden" name="command" value="LOAD"/>
                    <input type="hidden" name="studentId" value="${student.id}"/>
                    <input type="submit" value="Edit"/>
                </form>
                &nbsp;|&nbsp;
                <form action="students" method="post">
                    <input type="hidden" name="command" value="DELETE"/>
                    <input type="hidden" name="studentId" value="${student.id}"/>
                    <input type="submit" value="Delete"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
