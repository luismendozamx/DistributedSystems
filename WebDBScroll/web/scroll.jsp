<%-- 
    Document   : scroll.jsp
    Created on : Feb 20, 2015, 5:32:22 PM
    Author     : luismendoza
--%>

<%@page import="servlets.Dato"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Scrolling DB</h1>
        <%
            HttpSession mySession = request.getSession();
            String state = (String) mySession.getAttribute("session");
            
            if(state != null && state.equals("valid")){
                ArrayList<Dato> lista = (ArrayList<Dato>) mySession.getAttribute("data");
                int cont = (Integer) mySession.getAttribute("cont");
                
                //Print
                %>
                <p>ID: <%= lista.get(cont).id %>, Name: <%= lista.get(cont).name %></p>
                <hr>
                
                <form action="DataExtractor" method="GET">
                
                <% 
                
                if(cont-- > 0){ %>
                    <input type="submit" value="<" />
              <%}else{%>
                    <input type="hidden" name="click" value="previous" />
                    <input type="submit" value="<" disabled="disabled" />
              <% }
                %>
                </form>
                
                <form action="DataExtractor" method="GET">               
                <% 
                
                if(cont++ < lista.size()){ %>
                    <input type="submit" value=">" />
              <%}else{%>
                    <input type="hidden" name="click" value="next" />
                    <input type="submit" value=">" disabled="disabled" />
              <% }
                %>
                </form>
        <%    }
        %>
        
    </body>
</html>
