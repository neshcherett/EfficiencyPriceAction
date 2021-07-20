
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable id="user" type="com.tneshcheret.entity.User"--%>
<html>
<head>
    <title>Profile </title>
</head>
<body>
<p>Id : ${user.id}</p>
<p>Name : ${user.userName}</p>
<p>Password : ${user.password}</p>

<a href="products">view all products</a>
<a href="basket">view my request action</a>

</body>
</html>