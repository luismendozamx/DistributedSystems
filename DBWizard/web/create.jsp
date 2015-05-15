<%-- 
    Document   : index
    Created on : 14-may-2015, 15:40:18
    Author     : luismendoza
--%>

<%@page import="database.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title> 
        <meta charset="utf-8">
        <title>DataWeb Wizard</title>
        
        <style>
            .columna{
                margin-bottom: 10px;
            }
            
            .table-name{
                margin-bottom: 20px;
            }
        </style>
        
        <script>
          function agregaCampo() {
                var num = parseInt(document.getElementById("numColumnas").value);
                document.getElementById("numColumnas").value = num + 1;                
                document.getElementById("columnas").innerHTML += "<div class='columna'>Nombre: <input type='text' name='campo-"+ (num + 1) +"' required='true'> Tipo: <select name='tipo-" + (num + 1) + "'> <option value='integer'> Integer </option> <option value='varchar'> Varchar </option> <option value='double'> Double </option> </select> <br></div>";
          }

          function eliminaCampo() {
            if (document.getElementsByClassName('columna').length !== 1) {
                var num = parseInt(document.getElementById("numColumnas").value);
                document.getElementById("numColumnas").value = num - 1;
                var n = document.getElementById("columnas").innerHTML;
                var n2 = n.lastIndexOf("<div class");
                document.getElementById("columnas").innerHTML = n.substring(0, n2);
            }
          }
        </script>   
    </head>
    <body>
        <%
            session = request.getSession();
            User currentUser = (User) session.getAttribute("currentUser");
            
            if(currentUser == null){
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
                rd.forward(request, response);
            }
        %>
        <a href="Logout">Log Out</a>
        <a href="Profile">Mi Perfil</a>
        <hr>
        <h1>DB Wizard: Table Manager</h1>
        <p>Crea una tabla.</p>
        <form action="UserCreateTable" method="POST">
            <div class="table-name">
                Nombre de la tabla: <input type="text" name="tableName" required="true">
            </div>
            <input id="numColumnas" type="hidden" name="numColumnas" value="1">
            <div id="columnas">
                <div class="columna">
                    Nombre: <input type="text" name="campo-1" required="true">
                    Tipo: <select name="tipo-1">
                    <option value="integer"> Integer </option>
                    <option value="varchar"> Varchar </option>
                    <option value="double"> Double </option>
                    </select> <span style="padding-left: 5px;">(Primaria)</span><br>
                </div>
            </div>
            <br>
            <button onclick="agregaCampo()">Agregar columna</button>
            <button onclick="eliminaCampo()">Quitar columna</button>
            <br><br>
            <button type="submit" onclick>Crear Tabla</button>
        </form>
    </body>
</html>

