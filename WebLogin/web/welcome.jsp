<%-- 
    Document   : welcome
    Created on : Feb 6, 2015, 4:54:27 PM
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
        <h1>¡Bienvenido <%= request.getParameter("usuario") %>!</h1>
    </body>
</html>
