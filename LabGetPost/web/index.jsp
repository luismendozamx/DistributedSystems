<%-- 
    Document   : index
    Created on : Jan 30, 2015, 4:54:58 PM
    Author     : luismendoza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Seguro de Automóviles</title>
    </head>
    <body>
        <h1>Sistema de cotización de Seguros de Auto</h1>
        <form action="auto.jsp" method="GET">
            <table border="1">
                <thead>
                    <tr>
                        <th>Campo</th>
                        <th>Valor</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Nombre</td>
                        <td><input type="text" name="nombre" value="" /></td>
                    </tr>
                    <tr>
                        <td>Apellido</td>
                        <td><input type="text" name="apellido" value="" /></td>
                    </tr>
                    <tr>
                        <td>Género</td>
                        <td>
                            Masculino <input type="radio" name="genero" value="masculino" />
                            Femenino <input type="radio" name="genero" value="femenino" />
                        </td>
                    </tr>
                    <tr>
                        <td>Edad</td>
                        <td><input type="text" name="edad" value="" /></td>
                    </tr>
                    <tr>
                        <td>Estado</td>
                        <td><select name="estado">
                                <option>Aguascalientes</option>
                                <option>Baja California</option>
                                <option>Baja California Sur</option>
                                <option>Campeche</option>
                                <option>Chiapas</option>
                                <option>Chihuahua</option>
                                <option>Coahuila</option>
                                <option>Colima</option>
                                <option>Distrito Federal</option>
                                <option>Durango</option>
                                <option>Estado de México</option>
                                <option>Guanajuato</option>
                                <option>Guerrero</option>
                                <option>Hidalgo</option>
                                <option>Jalisco</option>
                                <option>Michoacán</option>
                                <option>Morelos</option>
                                <option>Nayarit</option>
                                <option>Nuevo León</option>
                                <option>Oaxaca</option>
                                <option>Puebla</option>
                                <option>Querétaro</option>
                                <option>Quintana Roo</option>
                                <option>San Luis Potosí</option>
                                <option>Sinaloa</option>
                                <option>Sonora</option>
                                <option>Tabasco</option>
                                <option>Tamaulipas</option>
                                <option>Tlaxcala</option>
                                <option>Veracruz</option>
                                <option>Yucatán</option>
                                <option>Zacatecas</option>
                            </select></td>
                    </tr>
                    <tr>
                        <td><input type="reset" value="Limpiar" /></td>
                        <td><input type="submit" value="Enviar" /></td>
                    </tr>
                </tbody>
            </table>

        </form>
    </body>
</html>
