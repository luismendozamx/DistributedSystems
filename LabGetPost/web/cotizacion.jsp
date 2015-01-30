<%-- 
    Document   : cotizacion
    Created on : Jan 30, 2015, 5:17:12 PM
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
            String marca = request.getParameter("marca");
            String modelo = request.getParameter("modelo");
            String placas = request.getParameter("placas");
            
            String cotizacion = "";
            
            if(Integer.parseInt(modelo) > 2005){
                cotizacion = "$ 7,800.00 pesos";
            }else{
                cotizacion = "$ 4,200.00 pesos";
            }
            
            String bienvenida = "";
            
            if(genero.equals("masculino")){
                bienvenida = "Estimado Sr. " + nombre + " " + apellido;
            }else{
                bienvenida = "Estimada Sra. " + nombre + " " + apellido;
            }
        %>
        
        <h1>Cotización</h1>
        
        <h2><%= bienvenida %></h2>
        
        <p>Género: <%= genero %></p>
        <p>Edad: <%= edad %></p>
        <p>Estado: <%= estado %></p>
        <p>Marca: <%= marca %></p>
        <p>Modelo:  <%= modelo %></p>
        <p>Placas:  <%= placas %></p>
        
        <p><strong>La cotización es de:</strong></p>
        <h3><%= cotizacion %></h3>
    </body>
</html>
