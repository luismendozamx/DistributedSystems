<%-- 
    Document   : index
    Created on : 3/03/2014, 09:09:01 PM
    Author     : Alejandro Escalante
                 Javier Sagastuy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dropbase</title>
    </head>
    <body>
        <h2>Welcome to Dropbase!</h2>
        <%
            request.getSession().setMaxInactiveInterval(0); //Just for now
        %>
        This website lets you create a Database and modify it for FREE!
        <br/> <a href="login.jsp">Login</a> <a href="register.jsp">Register</a>
    </body>
</html>
