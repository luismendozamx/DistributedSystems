<%-- 
    Document   : index
    Created on : Feb 5, 2015, 8:35:26 PM
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
        <h1>Zodiaco</h1>
        <% if(request.getAttribute("error") != null){ %>
            <p>Hubo un error, intente de nuevo.</p>
        <% } %>
        
        <form action="SignZodiac">
            <table border="1">
                <thead>
                    <tr>
                        <th>¿Fecha de nacimiento?</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Día</td>
                        <td><input type="text" name="dia" value="" /></td>
                    </tr>
                    <tr>
                        <td>Mes</td>
                        <td><input type="text" name="mes" value="" /></td>
                    </tr>
                    <tr>
                        <td>Año</td>
                        <td><input type="text" name="anio" value="" /></td>
                    </tr>
                    <tr>
                        <td><input type="reset" value="Reset" /></td>
                        <td><input type="submit" value="Enviar" /></td>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>
