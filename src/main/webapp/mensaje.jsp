<%-- 
    Document   : mensaje
    Created on : 13/06/2018, 06:44:17 PM
    Author     : SANTIAGO
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mensaje</title>
    </head>
    <body>
        <h3>
            <c:out value="${sessionScope.MENSAJE}"></c:out><br>
            <a href="${pageContext.servletContext.contextPath}/index.jsp">Regresar</a>
        </h3>
    </body>
</html>
