<%-- 
    Document   : index
    Created on : Feb 6, 2015, 4:48:50 PM
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
        <h1>Bienvenido</h1>
        <form action="LoginServlet" method="POST">
            Usuario: <input type="text" name="usuario" value="" /></br>
            Password: <input type="password" name="password" value="" /></br>
            <input type="submit" value="Enviar" />
        </form>
    </body>
</html>
