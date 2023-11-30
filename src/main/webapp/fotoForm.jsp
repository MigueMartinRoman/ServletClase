<%--
  Created by IntelliJ IDEA.
  User: Migue
  Date: 27/11/2023
  Time: 11:48
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
<form method="post" action="photoForm">
    <label>Nombre</label>
    <input name="nombre">

    <label>Descripción</label>
    <textarea name="descripcion"></textarea>

    <label>URL</label>
    <input type="url" name="url">

    <label>Privada?</label>
    <input type="checkbox" name="privada" value="si">

    <input type="submit" value="Enviar">
</form>


</body>
</html>
