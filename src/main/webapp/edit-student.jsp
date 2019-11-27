<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Student</title>
</head>
<body>

<h2>Edit Student</h2>

<form action="students" method="post">
    <input type="hidden" name="command" value="EDIT"/>
    <input type="hidden" name="studentId" value="${student.id}"/>

    <label>First Name:</label>
    <input type="text" name="firstName" value="${student.firstName}"/>

    <label>Last Name:</label>
    <input type="text" name="lastName" value="${student.lastName}"/>

    <label>Email:</label>
    <input type="text" name="email" value="${student.email}"/>

    <input type="submit" value="Save" />
</form>

<a href="students">Back to list</a>

</body>
</html>