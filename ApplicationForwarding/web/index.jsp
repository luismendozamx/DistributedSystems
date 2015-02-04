<%-- 
    Document   : index
    Created on : Feb 3, 2015, 9:10:07 PM
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
        <h1>Hello World!</h1>
       
        <form action="ServletForwarder" method="POST">
            <input type="text" name="mensaje" value="" />
            <input type="submit" value="Enviar" />
        </form>
    </body>
</html>
