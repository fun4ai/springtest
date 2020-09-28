<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/24
  Time: 1:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>=upload a file</title>
</head>
<body>
    <h1>please upload a file</h1>
    <form action="/form" method="post" enctype="multipart/form-data">
        <input type="text" name="name" />
        <input type="file" name="file" />
        <input type="submit" />
    </form>

</body>
</html>
