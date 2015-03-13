<%-- 
    Document   : index
    Created on : Mar 12, 2015, 8:16:31 PM
    Author     : luismendoza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <script>
            function loadData(id, target) {
                var ajaxRequest;
                if (window.XMLHttpRequest){
                    ajaxRequest=new XMLHttpRequest(); // IE7+, Firefox, Chrome, Opera, Safari
                } else {
                    ajaxRequest=new ActiveXObject("Microsoft.XMLHTTP"); // IE6, IE5
                }
                ajaxRequest.onreadystatechange = function(){
                    if (ajaxRequest.readyState==4 && ajaxRequest.status==200){
                        var JSONArray = JSON.parse(ajaxRequest.responseText);
                        var result = "<option value='0'>Select a customer..</option>";
                        
                        for(var i = 0; i < JSONArray.length; i++){
                            result = result + "<option value='" + JSONArray[i].id + "'>" + JSONArray[i].name + "</option>";
                        }
                        
                        document.getElementById(id).innerHTML=result;
                    }
                }
                ajaxRequest.open("GET", target, true /*async*/);
                ajaxRequest.send();
            }
            
            function loadCustomer(id, target, value){
                if(value != 0){
                    var ajaxRequest;
                    if (window.XMLHttpRequest){
                        ajaxRequest=new XMLHttpRequest(); // IE7+, Firefox, Chrome, Opera, Safari
                    } else {
                        ajaxRequest=new ActiveXObject("Microsoft.XMLHTTP"); // IE6, IE5
                    }
                    ajaxRequest.onreadystatechange = function(){
                        if (ajaxRequest.readyState==4 && ajaxRequest.status==200){
                            var JSONArray = JSON.parse(ajaxRequest.responseText);
                            var customer = "<p>ID: " + JSONArray.id + "</p>" + "<p>NAME: " + JSONArray.name + "</p>" + "<p>ADDRESS:  " + JSONArray.address + "</p>" + "<p>BALANCE:  " + JSONArray.balance + "</p>";
                            document.getElementById(id).innerHTML=customer;
                        }
                    }
                    ajaxRequest.open("GET", target, true /*async*/);
                    ajaxRequest.send();
                }
            }
        </script>
    </head>
    <body onload="loadData('list', 'DataServlet')">
        <h1>JSON, AJAX, Java DB</h1>
        
        <select id="list" name="customerList" onchange="loadCustomer('content', 'InfoServlet?id='+this.value, this.value)">
            <option>Select a customer..</option>
        </select>
        
        <div id="content" style="padding-top: 20px;"></div>
    </body>
</html>
