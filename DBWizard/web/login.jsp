<%-- 
    Document   : login
    Created on : 14-may-2015, 21:10:07
    Author     : luismendoza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login DB Wizard</title>
    </head>
    <body>
        <h1>Bienvenido de regreso a DB Wizard!</h1>
        <p>Ingresa a tu cuenta:</p>
        
        <% String error = (String)request.getAttribute("error"); %>
        
        <% if(error != null){ %>
            <p style="color: red;">Error: <%= error %></p>
        <% } %>
        
        <form action="UserLogIn" method="POST">
            <input type="text" name="username" value="" placeholder="username"/><br>
            <input type="password" name="password" value="" placeholder="password"/><br>
            <input type="submit" value="Login" />
        </form>
        
        <p>¿No tienes cuenta? <a href="register.jsp">Registrate aquí</a></p>
    </body>
</html>
