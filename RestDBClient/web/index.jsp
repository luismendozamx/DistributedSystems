<%-- 
    Document   : index
    Created on : Mar 27, 2015, 5:42:08 PM
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
                
                //target = target + "?from="+ document.getElementById("from").value;
                //target = target + "&to=" + document.getElementById("to").value;
                //target = target + "&amount=" + document.getElementById("amount").value;
                
                ajaxRequest.open(method, target, true /*async*/);
                ajaxRequest.setRequestHeader("Content-Type", "text/html");
                ajaxRequest.send(msg);
            }
        </script>
    </head>
    <body onload="callRESTfulWebService('data', 'GET', 'http://localhost:8080/RESTfulDB/webresources/clients', '')">
        <div id="data">
            
        </div>
    </body>
</html>
