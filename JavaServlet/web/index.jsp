<%-- 
    Document   : index
    Created on : Feb 3, 2015, 8:26:49 PM
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
        <h1>Porfavor dé su opinión de los Servlets:</h1>
        <form action="ServletIndex">
            <div style="margin: 10px 0px">
                Nombre: <input type="text" name="nombre" value="" /></br>
            </div>
            
            <div style="margin: 10px 0px">
                Apellido: <input type="text" name="apellido" value="" /></br>
            </div>
            
            <div style="margin: 10px 0px">
                Opinión de los servlets:</br>
                mala <input type="radio" name="opinion" value="mala" />
                buena <input type="radio" name="opinion" value="regular" />
                regular <input type="radio" name="opinion" value="buena" /></br>            
            </div>
            
            <div style="margin: 10px 0px">
                Comentarios</br>
                <textarea name="comentarios" rows="4" cols="20">
                </textarea></br>
            </div>
            <input type="submit" value="Enviar" />
        </form>
    </body>
</html>
