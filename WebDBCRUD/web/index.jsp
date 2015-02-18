<%-- 
    Document   : index
    Created on : Feb 17, 2015, 8:43:21 PM
    Author     : luismendoza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import ="java.sql.Connection" %>
<%@page import ="java.sql.DriverManager" %>
<%@page import ="java.sql.ResultSet" %>
<%@page import ="java.sql.SQLException" %>
<%@page import ="java.sql.Statement" %>
<%@page import ="java.util.logging.Level" %>
<%@page import ="java.util.logging.Logger" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            try {
                // import client driver
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                
                // create connection
                Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/dev",
                                                             "root",
                                                             "root");
                
                // Create query
                Statement query = con.createStatement();
                
                String id, nombre, balance, consulta;
                
                // Check for action
                if(request.getParameter("action") != null && request.getParameter("action").equals("add")){
                    id = request.getParameter("id");
                    nombre = request.getParameter("name");
                    balance = request.getParameter("balance");
                    
                    // Execute insert
                    consulta = "INSERT INTO CUSTOMER VALUES (" + id + ", '" + nombre + "', " + balance + ")";
                    query.executeUpdate(consulta);
                } else if(request.getParameter("action") != null && request.getParameter("action").equals("delete")){
                    id = request.getParameter("id");
                    
                    // Execute delete
                    consulta = "DELETE FROM CUSTOMER where ID = " + id;
                    query.executeUpdate(consulta);
                } else if(request.getParameter("action") != null && request.getParameter("action").equals("update")){
                    id = request.getParameter("id");
                    balance = request.getParameter("balance");
                    
                    // Execute update
                    consulta = "UPDATE CUSTOMER SET balance = " + balance + "where id =  " + id;
                    query.executeUpdate(consulta);
                }
                
                ResultSet rs = query.executeQuery("SELECT * FROM CUSTOMER");
                
                // Get latest data from DB
                while(rs.next()){
                    out.println("<p>id: " + rs.getInt("ID") + "</p>");
                    out.println("<p>name: " + rs.getString("NAME") + "</p>");
                    out.println("<p>balance: " + rs.getString("BALANCE") + "</p>");
                }
                
            } catch (ClassNotFoundException ex) {
                
            } finally {
                
            }
        %>
        <p><strong>Add a record</strong></p>
        <form action="index.jsp" method="POST">
            <input type="hidden" name="action" value="add" />
            <table border="1">
                <tbody>
                    <tr>
                        <td>id:</td>
                        <td><input type="text" name="id" value="" /></td>
                        <td>Name:</td>
                        <td><input type="text" name="name" value="" /></td>
                        <td>Balance:</td>
                        <td><input type="text" name="balance" value="" /></td>
                    </tr>
                </tbody>
            </table>
            <input type="submit" value="Ok" />
        </form>
        
        <p><strong>Delete a record</strong></p>
        <form action="index.jsp" method="POST">
            <input type="hidden" name="action" value="delete" />
            <table border="1">
                <tbody>
                    <tr>
                        <td>id:</td>
                        <td><input type="text" name="id" value="" /></td>
                    </tr>
                </tbody>
            </table>
            <input type="submit" value="Ok" />
        </form>
        
        <p><strong>Update a record</strong></p>
        <form action="index.jsp" method="POST">
            <input type="hidden" name="action" value="update" />
            <table border="1">
                <tbody>
                    <tr>
                        <td>id:</td>
                        <td><input type="text" name="id" value="" /></td>
                        <td>Balance:</td>
                        <td><input type="text" name="balance" value="" /></td>
                    </tr>
                </tbody>
            </table>
            <input type="submit" value="Ok" />
        </form>
    </body>
</html>
