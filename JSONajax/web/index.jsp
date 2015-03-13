<%-- 
    Document   : index
    Created on : Mar 10, 2015, 9:15:59 PM
    Author     : luismendoza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <script>
            function loadNewContent(id, target) {
                var ajaxRequest;
                if (window.XMLHttpRequest){
                    ajaxRequest=new XMLHttpRequest(); // IE7+, Firefox, Chrome, Opera, Safari
                } else {
                    ajaxRequest=new ActiveXObject("Microsoft.XMLHTTP"); // IE6, IE5
                }
                ajaxRequest.onreadystatechange = function(){
                    if (ajaxRequest.readyState==4 && ajaxRequest.status==200){
                        var JSONObject = JSON.parse(ajaxRequest.responseText);
                        var txt = "field1: "+ JSONObject.field1 + ", field2: " + JSONObject.field1 + ", field3: " + JSONObject.field3;
                        document.getElementById(id).innerHTML=txt;
                    }
                }
                ajaxRequest.open("GET", target, true /*async*/);
                ajaxRequest.send();
            }
        </script>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <div id="content"></div>
        
        <button onclick="loadNewContent('content', 'JSONServlet')">Send</button>
  
    </body>
</html>
