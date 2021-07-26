<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%--@elvariable id="user" type="com.tneshcheret.entity.Product"--%>
<html>
<head>
    <title>Profile </title>
</head>
<body>
<a href="requestAction">view my request</a>

<c:forEach items="${products}" var="product">
    <br>
    <c:out value="${product.id}"/>
    <c:out value="${product.name}"/>
    <a href="addToRequest?productId=${product.id}">Add these product</a>
</c:forEach>
</body>
</html>
