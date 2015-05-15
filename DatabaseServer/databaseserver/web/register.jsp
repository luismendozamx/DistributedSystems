<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dropbase registration</title>
    </head>
    <body>
        <a href="index.jsp">Home</a>
        <a href="login.jsp">Login</a>
        <hr>
        <%
            HttpSession s = request.getSession();
            String rUsername = (String)s.getAttribute("rUsername");
            String rPassword = (String)s.getAttribute("rPassword");
            String rDatabase = (String)s.getAttribute("rDatabase");
            String rPrompt = (String)s.getAttribute("rPrompt");
            if(rPrompt!=null){
                out.println(rPrompt);
                s.setAttribute("rPrompt", null);
            }else{
                out.println("Please enter your desired username, password and database name.");
            }
        %>
        <form action="Register" method="POST">
            <table>
                <tbody>
                    <tr>
                        <td>Username:</td>
                        <td><input type="text" name="username" pattern="[A-Za-z][A-Za-z0-9]{4}[A-Za-z0-9]*"
                                   title="The username can only contain letters and numbers, must start with a letter and be at least 5 characters long."
                                   <%
                                        if(rUsername!=null){
                                            out.println("value=\"" + rUsername + "\"");
                                            s.setAttribute("rUsername", null);
                                        }
                                   %> /></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type="password" name="password" pattern=".{5}.*"
                                   title="A password must be at least five characters long."
                                   <%
                                        if(rPassword!=null){
                                            out.println("value=\"" + rPassword + "\"");
                                            s.setAttribute("rPassword", null);
                                        }
                                   %> /></td>
                    </tr>
                    <tr>
                        <td>Database:</td>
                        <td><input type="text" name="database" pattern="[A-Za-z][A-Za-z0-9]{4}[A-Za-z0-9]*"
                                   title="The database can only contain letters and numbers, must start with a letter and be at least 5 characters long."
                                   <%
                                        if(rUsername!=null){
                                            out.println("value=\"" + rDatabase + "\"");
                                            s.setAttribute("rDatabase", null);
                                        }
                                   %> /></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Register"/></td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>
