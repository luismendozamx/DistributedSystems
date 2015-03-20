<%-- 
    Document   : index
    Created on : Mar 3, 2015, 8:54:31 PM
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
                        var xmlDoc=ajaxRequest.responseXML;
                        var txt="";
                        var x=xmlDoc.getElementsByTagName("ARTIST");
                        for (var i=0;i<x.length;i++) {
                            txt=txt + x[i].childNodes[0].nodeValue + "</br>";
                        }
                        document.getElementById(id).innerHTML=txt;
                    }
                }
                ajaxRequest.open("GET", target, true /*async*/);
                ajaxRequest.send();
            }
        </script>
    </head>
    <body>
        <h1>Hello from Index!</h1>
        <div id="content">
            <p>Texto Inicial</p>
        </div>
        <button onclick="loadNewContent('content','cdCatalog.xml')">Change Content</button>
    </body>
</html>
