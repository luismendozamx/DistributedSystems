/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luismendoza
 */
public class DBUserManager {
    private User[] users;
    private int cont, lastIndex;
    private ArrayList<User> registeredUsers;
    private String connectionString, dbUser, dbPass;
    private String userDatabase;
    
    public DBUserManager(String connectionString){
        this(connectionString, "root", "root");
    }
    
    public DBUserManager(String connectionString, String dbUser, String dbPass){
        
        this.users = new User[10];
        this.cont = 0;
        this.registeredUsers = new ArrayList<User>();
        this.connectionString = connectionString;
        this.dbUser = dbUser;
        this.dbPass = dbPass;
        
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection(this.connectionString, dbUser, dbPass);
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select * from Users");
            String username = null, password = null, database = null;
            while(rs.next()){
                try{
                    this.cont++;
                    username = rs.getString(2);
                    password = rs.getString(3);
                    database = rs.getString(4);
                    
                    User user = new User(username, password, database);
                    
                    this.registeredUsers.add(user);
                }catch(SQLException sqle){
                    System.err.println(sqle.toString());
                }
            }
            // close connection
            con.close();
        }catch(SQLException sqle){
            System.err.println("SQLE: " + sqle.toString());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBUserManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Returns 0 if user can be registered
    // Returns 1 if user's db is the same
    // Returns 2 if user's username is the same
    // Returns -1 if there was an error
    public int registerUser(User user){
        int res = 0;
        int i = 0;
        
        reloadUserList();
        
        while(i < this.registeredUsers.size() && res == 0) {
            int aux = this.registeredUsers.get(i).userExists(user);
            if(aux != 0){
                res = aux;
            }
            i++;
        }
        
        // if res == 0, user can be registered
        if(res == 0){
            // try to save user
            int save = saveUserToDB(user);
            
            // error al guardar
            if(save == -1){
                res = -1;
            }
        }
        
        return res;
    }
    
    public boolean containsUser(String username){
        boolean flag = false;
        int i = 0;
        
        while(i < this.registeredUsers.size() && !flag) {
            if(this.registeredUsers.get(i).equalsUser(username))
                flag = true;
            i++;
        }
        return flag;
    }
    
    public User getUser(String username){
        boolean flag = false;
        int i = 0;
        User res = null;
        
        while(i < this.registeredUsers.size() && !flag) {
            if(this.registeredUsers.get(i).equalsUser(username)){
                flag = true;
                res = this.registeredUsers.get(i);
            }
            i++;
        }
        return res;
    }
    
    public ArrayList<String> getUserTables(User user){
        ArrayList<String> tables = null;
        
        try{
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/" + user.getDatabase(), this.dbUser, this.dbPass);
            DatabaseMetaData dbmeta = con.getMetaData();
            String[] tableTypes = new String[1];
            tableTypes[0] = "TABLE";
            ResultSet rs = dbmeta.getTables(null, null, null, tableTypes);
            tables = new ArrayList<String>();
            while(rs.next()){
                tables.add(rs.getString("TABLE_NAME").toUpperCase());
            }
            con.close();            
        }catch(SQLException sqle){
            System.err.println(sqle.toString());
        }
        
        return tables;
    }
    
    public int createTable(User user, String tableName, DBColumn[] columns, int n){
        int res;
        
        try{
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/" + user.getDatabase(), this.dbUser, this.dbPass);
            StringBuilder sb = new StringBuilder();
            
            Statement query = con.createStatement();
            sb.append("create table " + tableName + "(");
            
            for (int i = 0; i < n; i++) {
                
                if( i == 0){
                    sb.append(columns[i].name + " " + columns[i].type + " not null");
                }else{
                    sb.append(columns[i].name + " " + columns[i].type);
                }
                
                if(i < n-1){
                    sb.append(", ");
                }else{
                    sb.append(", primary key(" + columns[0].name + "))");
                }
                
            }
            query.executeUpdate(sb.toString());
            res = 0;
            
            con.close();            
        }catch(SQLException sqle){
            System.err.println(sqle.toString());
            res = -1;
        }
        
        return res;
    }
    
    
    public boolean deletUserTable(String username, String tableName){
        boolean res = true;
        
        User user = getUser(username);
        
        try{
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/" + user.getDatabase(), this.dbUser, this.dbPass);
            StringBuilder sb = new StringBuilder();
            
            // always delet from root schema
            sb.append("drop table root." + tableName);
            
            Statement query = con.createStatement();
            query.executeUpdate(sb.toString());

            // close the connection
            con.commit();
            con.close();
        }catch(SQLException sqle){
            System.err.println(sqle.toString());
            res = false;
        }
        
        return res;
    }
    
    
    public int saveUserToDB(User user){
        int res = 0;
        
        try{
            Connection con = DriverManager.getConnection(this.connectionString, this.dbUser, this.dbPass);
            StringBuilder sb = new StringBuilder();
            
            sb.append("insert into Users values (").append(this.cont + 1);
            sb.append(",'").append(user.getUsername());
            sb.append("','").append(user.getPassword());
            sb.append("','").append(user.getDatabase()).append("')\n");
            
            Statement query = con.createStatement();
            query.executeUpdate(sb.toString());

            // close the connection
            con.commit();
            con.close();
            
            // create database for user
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/" + user.getDatabase() + ";create=true;", user.getUsername(), user.getPassword());
            con.commit();
            con.close();
            
        }catch(SQLException sqle){
            System.err.println(sqle.toString());
            res = -1;
        }
        
        reloadUserList();
        
        return res;
    }
    
    public boolean loginUser(String username, String password){
        reloadUserList();
        User registeredUser = getUser(username);
        
        if( registeredUser != null ){
            return registeredUser.getPassword().equals(password);
        }else{
            return false;
        }
    }
    
    public void reloadUserList(){
        this.cont = 0;
        try{
            Connection con = DriverManager.getConnection(this.connectionString, dbUser, dbPass);
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select * from Users");
            String username = null, password = null, database = null;
            while(rs.next()){
                try{
                    this.cont++;
                    username = rs.getString(2);
                    password = rs.getString(3);
                    database = rs.getString(4);
                    
                    User user = new User(username, password, database);
                    
                    this.registeredUsers.add(user);
                }catch(SQLException sqle){
                    System.err.println(sqle.toString());
                }
            }
            // close connection
            con.close();
        }catch(SQLException sqle){
            System.err.println("SQLE: " + sqle.toString());
        }
    }
}
