<%-- 
    Document   : destino
    Created on : Feb 3, 2015, 9:17:40 PM
    Author     : luismendoza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Mensaje:</h1>
        <p><%= request.getParameter("mensaje") %></p>
    </body>
</html>
