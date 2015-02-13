<%-- 
    Document   : index
    Created on : Feb 6, 2015, 5:07:29 PM
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
        <h1>¡Bienvenido a CineMas!</h1>
        <p>Permitenos recomendarte una película.</p>
        
        <form action="ServletRecomendacion" method="POST">
            Zona: 
            <select name="zona">
                <option value="centro">Centro</option>
                <option value="sur">Sur</option>
                <option value="norte">Norte</option>
            </select></br>
            Género: 
            <select name="tipo">
                <option value="Terror">Terror</option>
                <option value="Comedia">Comedia</option>
                <option value="Accion">Acción</option>
            </select></br>
            <input type="submit" value="Enviar" />
        </form>
    </body>
</html>
