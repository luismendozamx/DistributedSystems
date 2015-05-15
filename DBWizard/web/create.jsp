<%-- 
    Document   : index
    Created on : 14-may-2015, 15:40:18
    Author     : luismendoza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title> 
        <meta charset="utf-8">
        <title>DataWeb Wizard</title>
        <script>
          function agregaCampo() {
            document.getElementById("field").innerHTML += "<div class='x'>Field name: <input type='text' name='campo'> Type: <select> <option value='varchar'> Varchar </option> <option value='integer'> Integer </option> <option value='double'> Double </option> </select> <br></div>";
          }

          function eliminaCampo() {
            if (document.getElementsByClassName('x').length == 1) {
            } else {
              var n = document.getElementById("field").innerHTML;
              var n2 = n.lastIndexOf("<div class");
              document.getElementById("field").innerHTML = n.substring(0, n2);
            }
          }
        </script>   
    </head>
    <body>
        <h1>DB Wizard</h1>
        <div id="field">
            <div class="x">
                Field name: <input type="text" name="campo">
                Type: <select>
                <option value="varchar"> Varchar </option>
                <option value="integer"> Integer </option>
                <option value="double"> Double </option>
                </select>(Primaria)<br>
            </div>
        </div>
        <button type="submit" onclick="agregaCampo()">Add field</button>
        <button type="submit" onclick="eliminaCampo()">Remove field</button>
        <button type="submit">Save field</button> <br>
        <button type="submit">Create database</button>
    </body>
</html>

