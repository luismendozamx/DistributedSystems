<%-- 
    Document   : otra
    Created on : Jan 30, 2015, 4:30:34 PM
    Author     : luismendoza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Otra Página</title>
    </head>
    <body>
        <%
            String campo1 = request.getParameter("campo1");           
            String campo2 = request.getParameter("campo2");
            String campo3 = request.getParameter("campo3");
            
            /*
            if(campo1 != null){
                out.println("Este es el valor de campo 1: " + campo1);
            }
            
            if(campo2 != null){
                out.println("Este es el valor de campo 2: " + campo2);
            }
            
            if(campo3 != null){
                out.println("Este es el valor de campo 3: " + campo3);
            }*/
        %>
        
        <p>Valor Campo 1: <%= campo1 %></p>
        <p>Valor Campo 2: <%= campo2 %></p>
        <p>Valor Campo 3: <%= campo3 %></p>
    </body>
</html>
