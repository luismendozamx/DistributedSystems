<%-- 
    Document   : welcome
    Created on : Feb 13, 2015, 4:30:41 PM
    Author     : luismendoza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
    </head>
    <body>
        <% if( !request.getParameter("name").equals("") &&
                request.getParameter("password").equals("123456")){
            
            String userName = request.getParameter("name");
            HttpSession mySession = request.getSession();
            mySession.setAttribute("username", userName);
            
        %>
            <h4>Welcome <%= userName %></h4>
            <a href="profile.jsp">Visit your profile</a>
        <% }else{ 
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        }%>
    </body>
</html>
