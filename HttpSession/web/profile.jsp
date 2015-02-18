<%-- 
    Document   : profile
    Created on : Feb 13, 2015, 5:09:03 PM
    Author     : luismendoza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
    </head>
    <body>
        <% 
            HttpSession mySession = request.getSession();
            if(mySession.getAttribute("username") != null){
        %>
            <h4>Hi <%= mySession.getAttribute("username") %>!</h4>
            <a href="LogoutServlet">Log Out</a>
        <% }else{ 
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        }%>
    </body>
</html>
