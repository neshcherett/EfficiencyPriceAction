<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%--@elvariable id="product" type="com.tneshcheret.entity.Product"--%>
<%--@elvariable id="requestAction" type="com.tneshcheret.entity.RequestAction"--%>
<html>
<head>
    <title>Profile request for action</title>
</head>
<body>
<c:forEach items="${requestAction.products}" var="product">
    <br>
    <c:out value="${product.id}"/>
    <c:out value="${product.name}"/>
    <br>
</c:forEach>

<a href="products">view all products</a>
</body>
</html>
