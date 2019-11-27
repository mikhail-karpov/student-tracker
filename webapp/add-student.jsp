<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Student</title>
</head>
<body>

<h2>Add Student</h2>

<form action="students" method="post">
    <input type="hidden" name="command" value="ADD"/>

    <label>First Name:</label>
    <input type="text" name="firstName"/>

    <label>Last Name:</label>
    <input type="text" name="lastName"/>

    <label>Email:</label>
    <input type="text" name="email"/>

    <input type="submit" value="Save" />
</form>

<a href="students">Back to list</a>

</body>
</html>
