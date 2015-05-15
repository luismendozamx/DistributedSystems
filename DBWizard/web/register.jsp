<%-- 
    Document   : register
    Created on : 14-may-2015, 16:26:21
    Author     : luismendoza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro a DB Wizard</title>
    </head>
    <body>
        <h1>Bienvenido a DB Wizard!</h1>
        <p>Regístrate aquí:</p>
        
        <% String error = (String)request.getAttribute("error"); %>
        
        <% if(error != null){ %>
            <p style="color: red;">Error: <%= error %></p>
        <% } %>
        
        <form action="UserRegister" method="POST">
            <input type="text" name="username" value="" placeholder="username"/><br>
            <input type="password" name="password" value="" placeholder="password"/><br>
            <input type="database" name="database" value="" placeholder="database"/><br>
            <input type="submit" value="Registrar" />
        </form>
        
        <p>¿Ya tienes cuenta? <a href="login.jsp">Ingresa aquí</a></p>
    </body>
</html>
