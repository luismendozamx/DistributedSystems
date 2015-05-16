<%-- 
    Document   : profile
    Created on : May 16, 2015, 3:09:53 PM
    Author     : luismendoza
--%>

<%@page import="database.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
        <script>
            function callRESTfulWebService(id, method, target, msg) {
                var ajaxRequest;
                if (window.XMLHttpRequest){
                    ajaxRequest=new XMLHttpRequest(); // IE7+, Firefox, Chrome, Opera, Safari
                } else {
                    ajaxRequest=new ActiveXObject("Microsoft.XMLHTTP"); // IE6, IE5
                }
                ajaxRequest.onreadystatechange = function(){
                    if (ajaxRequest.readyState==4 &&
                        (ajaxRequest.status==200 || ajaxRequest.status==204)){
                        document.getElementById(id).innerHTML=ajaxRequest.responseText;
                    }
                }
                
                //target = target + "?from="+ document.getElementById("from").value;
                //target = target + "&to=" + document.getElementById("to").value;
                //target = target + "&amount=" + document.getElementById("amount").value;
                
                ajaxRequest.open(method, target, true /*async*/);
                ajaxRequest.setRequestHeader("Content-Type", "text/html");
                ajaxRequest.send(msg);
            }
        </script>
    </head>
    
    <% 
        session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        session.setAttribute("index", 0);
        session.setAttribute("tableData", null);
        session.setAttribute("columnNames", null);
        session.setAttribute("columnTypes", null);
        
        // if no user redirect to login
        if(currentUser == null){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
            rd.forward(request, response);
        }
    %>
    
    <script>
        window.currentUser = '<%= currentUser.getUsername() %>';
    </script>
    
    <body onload="callRESTfulWebService('tables', 'GET', 'http://localhost:8080/DBWizard/api/db?username=' + window.currentUser, '')">
        
        
        <a href='Logout'>Log Out</a>
        <a href='create.jsp'>Crea Tabla</a>
        <hr>
        <h1>Perfil de <%= currentUser.getUsername() %></h1>
        
        <p>Tablas registradas:</p>
        <form action='ViewTable' method=POST>
            <select id="tables" name='userTables'>
                
            </select>
            <input type='submit' value='Ver tabla' />
        </form>
        <input type='submit' value='Eliminar tabla' 
                   onclick="callRESTfulWebService(
                               'tables', 
                               'DELETE', 
                               'http://localhost:8080/DBWizard/api/db?username=' + window.currentUser + '&tableName=' + document.getElementById('tables').value,
                               ''
                               )" />
        <p><a href='create.jsp'>Crea tabla nueva</a></p>
        
    </body>
</html>
