/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 * @author sdist
 */
@Path("MyPath2")
public class MyPath2Ressource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of StaffResource
     */
    public MyPath2Ressource() {
    }

    /**
     * Retrieves representation of an instance of restwebservices.StaffResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/html")
    public String getText() {
        String resp = "";
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Enterprise", "root", "root");
            Statement query = con.createStatement();
            ResultSet rs = query.executeQuery("SELECT * FROM CUSTOMERS");
            while (rs.next()) {
                resp = resp + " Id: " + rs.getInt("ID") + "<br>";
                resp = resp + " Name: " + rs.getString("NAME") + "<br>";
            }
            con.commit();
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MyPath2Ressource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MyPath2Ressource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resp;
    }

    /**
     * PUT method for updating or creating an instance of StaffResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("text/html")
    public void putText(@QueryParam("id")String id, @QueryParam("name")String name) {
        System.out.println("Holaa2");
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Enterprise", "root", "root");
            Statement query = con.createStatement();
            query.executeUpdate("INSERT INTO CUSTOMERS VALUES(" + Integer.parseInt(id) +
                            ",'" + name + "')");
            con.commit();
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MyPath2Ressource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MyPath2Ressource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @DELETE
    //@Consumes("text/plain")
    public void delete(@QueryParam("id")String id){
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Enterprise", "root", "root");
            Statement query = con.createStatement();
            query.executeUpdate("DELETE FROM CUSTOMERS WHERE ID=" + Integer.parseInt(id));
            con.commit();
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MyPath2Ressource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MyPath2Ressource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @POST
    @Consumes("text/html")
    public void update(@QueryParam("id")String id, @QueryParam("name")String name){
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Enterprise", "root", "root");
            Statement query = con.createStatement();
            query.executeUpdate("UPDATE CUSTOMERS SET NAME='" + name +
                            "' where ID=" + Integer.parseInt(id));
            con.commit();
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MyPath2Ressource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MyPath2Ressource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
