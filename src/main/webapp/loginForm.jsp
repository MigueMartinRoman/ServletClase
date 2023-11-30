<%--
  Created by IntelliJ IDEA.
  User: Migue
  Date: 30/11/2023
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${error!= null}">
    <p>ERROR:${error}</p>
</c:if>
<form action="./login" method="post">
    <label for="usuario">Username</label>
    <input id="usuario" type="text" name="user" placeholder="migue">
    <label>
        Password
        <input type="password" name="password" placeholder="1234">
    </label>
    <input type="submit" value="Entrar">
</form>
</body>
</html>
