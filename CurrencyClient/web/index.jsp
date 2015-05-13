<%-- 
    Document   : index
    Created on : Mar 27, 2015, 4:28:03 PM
    Author     : luismendoza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
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
                
                target = target + "?from="+ document.getElementById("from").value;
                target = target + "&to=" + document.getElementById("to").value;
                target = target + "&amount=" + document.getElementById("amount").value;
                
                ajaxRequest.open(method, target, true /*async*/);
                ajaxRequest.setRequestHeader("Content-Type", "text/html");
                ajaxRequest.send(msg);
            }
        </script>
    </head>
    <body>
        
        <table border="1">
            <thead>
                <tr>
                    <th>De</th>
                    <th>Monto</th>
                    <th>A</th>
                    <th>Resultado</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>
                        <select id="from" name="from" onchange="callRESTfulWebService('resultado', 'GET','http://localhost:8080/WebApplicationWebServices/webresources/curr', '')">
                            <option>Pesos</option>
                            <option>Libras</option>
                            <option>Dolares C</option>
                            <option>USD</option>
                        </select>
                    </td>
                    <td><input type="text" name="amount" value="1" id="amount" /></td>
                    <td>
                        <select id="to" name="from" onchange="callRESTfulWebService('resultado', 'GET','http://localhost:8080/WebApplicationWebServices/webresources/curr', '')">
                            <option>Pesos</option>
                            <option>Libras</option>
                            <option>Dolares C</option>
                            <option>USD</option>
                        </select>
                    </td>
                    <td id="resultado"></td>
                </tr>
            </tbody>
        </table>
    </body>
</html>
