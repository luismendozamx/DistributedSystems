/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soapWebServices;

import database.DBUserManager;
import database.User;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
//import javax.ejb.Stateless;

/**
 *
 * @author luismendoza
 */
@WebService(serviceName = "DeleteTableWebService")
//@Stateless()
public class DeleteTableWebService {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "deleteUserTable")
    public Boolean deleteUserTable(@WebParam(name = "username") String username, @WebParam(name = "tableName") String tableName) {
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        }catch(ClassNotFoundException cnfe){
            System.err.println(cnfe.toString());
        }
         
        DBUserManager users = new DBUserManager("jdbc:derby://localhost:1527/UsersDBW");
        User user = users.getUser(username);
        return users.deletUserTable(username, tableName);
    }
}
