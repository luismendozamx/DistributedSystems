<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dropbase login</title>
    </head>
    <body>
        <a href="index.jsp">Home</a>
        <a href="register.jsp">Register</a>
        <hr>
        <form action="Login" method="POST">
            <table>
                <tbody>
                    <tr>
                        <td>Username:</td>
                        <td><input type="text" name="username"/></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type="password" name="password"/></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Login"/></td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>
