<%-- 
    Document   : auto
    Created on : Jan 30, 2015, 5:02:37 PM
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
        
        <%
            String nombre = request.getParameter("nombre");           
            String apellido = request.getParameter("apellido");
            String genero = request.getParameter("genero");
            String edad = request.getParameter("edad");
            String estado = request.getParameter("estado");
            String bienvenida = "";
            
            if(genero.equals("masculino")){
                bienvenida = "Estimado Sr. " + nombre + " " + apellido;
            }else{
                bienvenida = "Estimada Sra. " + nombre + " " + apellido;
            }
        %>
             
        <h1>Sistema de cotización de Seguros de Auto</h1>
        
        <p><strong>Datos personales:</strong></p>
        <p><%= bienvenida %></p>
        <p>Género: <%= genero %></p>
        <p>Edad: <%= edad %></p>
        <p>Estado: <%= estado %></p>
        
        <form action="cotizacion.jsp" method="GET">
            <input type="hidden" name="nombre" value="<%= nombre %>" />
            <input type="hidden" name="apellido" value="<%= apellido %>" />
            <input type="hidden" name="genero" value="<%= genero %>" />
            <input type="hidden" name="estado" value="<%= estado %>" />
            <input type="hidden" name="edad" value="<%= edad %>" />
            
            <table border="1">
                <thead>
                    <tr>
                        <th>Campo</th>
                        <th>Valor</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Marca</td>
                        <td><select name="marca">
                                <option>Toyota</option>
                                <option>Nissan</option>
                                <option>Honda</option>
                                <option>Mazda</option>
                                <option>BMW</option>
                                <option>VW</option>
                                <option>Audi</option>
                            </select></td>
                    </tr>
                    <tr>
                        <td>Modelo</td>
                        <td><input type="text" name="modelo" value="" /></td>
                    </tr>
                    <tr>
                        <td>Placas</td>
                        <td><input type="text" name="placas" value="" /></td>
                    </tr>                    
                    <tr>
                        <td><input type="reset" value="Limpiar" /></td>
                        <td><input type="submit" value="Enviar" /></td>
                    </tr>
                </tbody>
            </table>
    </body>
</html>
