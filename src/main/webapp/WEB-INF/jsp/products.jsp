<%--
  Created by IntelliJ IDEA.
  User: Нещерет
  Date: 14.07.2021
  Time: 21:03
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%--@elvariable id="user" type="com.tneshcheret.entity.Product"--%>
<html>
<head>
    <title>Profile </title>
</head>
<body>
<a href="basket">view my basket</a>

<c:forEach items="${products}" var="product">
    <br>
    <c:out value="${product.id}"/>
    <c:out value="${product.name}"/>
    <a href="addToBasket?carId=${product.id}">Add these product</a>
</c:forEach>
</body>
</html>
