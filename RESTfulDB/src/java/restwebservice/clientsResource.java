/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restwebservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author luismendoza
 */
@Path("clients")
public class clientsResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of clientsResource
     */
    public clientsResource() {
    }

    /**
     * Retrieves representation of an instance of restwebservice.clientsResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/html")
    public String getHtml() {
        String res = "";
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/dev",
                                                     "root",
                                                     "root");
            Statement query = con.createStatement();
            ResultSet rs = query.executeQuery("SELECT * FROM CUSTOMER");
            

            while(rs.next()){
                res = res + "<p>ID: " + rs.getInt("ID") + ", NAME: " + rs.getString("NAME") + "</p>";
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(clientsResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(clientsResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }

    /**
     * PUT method for updating or creating an instance of clientsResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("text/html")
    public void putHtml(String content) {
    }
}
