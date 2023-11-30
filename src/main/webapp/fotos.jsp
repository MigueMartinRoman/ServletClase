<%--
  Created by IntelliJ IDEA.
  User: sonia
  Date: 30/10/2023
  Time: 12:16
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>   // Importa que esta en JAVA
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>                                     // Para que lea el EL de JSTL

<html>
<head>
    <title>Title</title>
</head>
<body>
<p>Aqui irá el titulo!!1</p>
<c:out value="hola mon! 2"></c:out>
<c:out value="${titulo}"></c:out>
<h2>${titulo}</h2>
<c:out value="${picture.url}"></c:out>
<img src="${picture.url}" alt="${picture.descripcion}">

<c:forEach items="${pictures}" var="f">
    <c:if test="${!f.privada}"><img src="${f.url}" alt="${f.descripcion}" width="160px" height="140px"></c:if>

</c:forEach>

</body>
</html>
