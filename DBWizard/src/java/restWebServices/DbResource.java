/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restWebServices;

import database.DBUserManager;
import database.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 *
 * @author luismendoza
 */
@Path("db")
public class DbResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of DbResource
     */
    public DbResource() {
    }

    /**
     * Retrieves representation of an instance of restWebServices.DbResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml(@QueryParam("username") String username) {
        String res = "";
        
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        }catch(ClassNotFoundException cnfe){
            System.err.println(cnfe.toString());
        }
        
        DBUserManager users = new DBUserManager("jdbc:derby://localhost:1527/UsersDBW");
        User user = users.getUser(username);
        
        ArrayList<String> tables = users.getUserTables(user);
        
        int i = 0;
        while(i < tables.size()){
            res += "<option value='" + tables.get(i) + "'>" + tables.get(i) + "</option>";
            i++;
        }
        
        return res;
    }

    /**
     * PUT method for updating or creating an instance of DbResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
    
    @DELETE
    //@Consumes("text/plain")
    public String delete(@QueryParam("tableName") String tableName, @QueryParam("username") String username){
        boolean flag = true;
        try {
            flag = deleteUserTable(username, tableName);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        if(flag){
            return getXml(username);
        }else{
            return "Error";
        }
    }

    private static Boolean deleteUserTable(java.lang.String username, java.lang.String tableName) {
        soapClients.DeleteTableWebService_Service service = new soapClients.DeleteTableWebService_Service();
        soapClients.DeleteTableWebService port = service.getDeleteTableWebServicePort();
        return port.deleteUserTable(username, tableName);
    }    
}
