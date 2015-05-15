package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.ArrayList;

public class UserManager {
    private User[] users;
    private int n, lastIndex;
    private final ArrayList<String> addedUsers;
    private final String connectionString, dbUser, dbPass;
    private String userDatabase;
    
    public UserManager(String connectionString){
        this(connectionString, "useradmin", "AdminPW");
    }
    
    public UserManager(String connectionString, String dbUser, String dbPass){
        users = new User[10];
        n = 0;
        addedUsers = new ArrayList<String>();
        this.connectionString = connectionString;
        this.dbUser = dbUser;
        this.dbPass = dbPass;
        try{
            Connection con = DriverManager.getConnection(this.connectionString, dbUser, dbPass);
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select * from Users");
            String username = null, password = null, database = null;
            while(rs.next()){
                try{
                    lastIndex = rs.getInt(1);
                    username = rs.getString(2);
                    password = rs.getString(3);
                    database = rs.getString(4);
                }catch(SQLException sqle){
                    System.err.println(sqle.toString());
                }
                if(username!=null){
                    addUser(username, password, database, true);
                    username = null;
                }
            }
            // cerramos la conexión
            con.commit();
            con.close();
        }catch(SQLException sqle){
            System.err.println("SQLE: " + sqle.toString());
        }
    }
    
    private int find(String username){
        int i = 0;
        while(i<n && !users[i].getUsername().equals(username)){
            i++;
        }
        if(i==n){
            i = -1;
        }
        return i;
    }
    
    private void expand(){
        User[] newUsers = new User[2*n];
        for(int i=0;i<n;i++){
            newUsers[i] = users[i];
        }
        users = newUsers;
    }
    
    private int contains(String username, String database){
        int result = 0;
        int i = 0;
        while(i<n && result==0){
            if(users[i].getUsername().equals(username)){
                result = 1;
            }else{
                if(users[i].getDatabase().equals(database)){
                    result = 2;
                }
            }
            i++;
        }
        return result;
    }
    
    /**
     *
     * @param username
     * @param password
     * @param database
     * @param load
     * @return Will return 0 if it was a success
                           1 if the username exists
                           2 if the database exists
     */
    public final int addUser(String username, String password, String database, boolean load){
        int success;
        success = contains(username, database);
        if(success==0){
            if(n==users.length){
                expand();
            }
            users[n] = new User(username, password, database);
            n++;
            if(!load){
                addedUsers.add(username);
            }
        }
        return success;
    }
    
    public int addUser(String username, String password, String database){
        return addUser(username, password, database, false);
    }
    
    public boolean isValid(String username, String password){
        int index = find(username);
        boolean valid;
        if(index<0 || !users[index].isValid(password)){
            valid = false;
            userDatabase = null;
        }else{
            valid = true;
            userDatabase = users[index].getDatabase();
        }
        return valid;
    }
    
    public String getUserDatabase(){
        return userDatabase;
    }
    
    public Iterator<User> iterator(){
        return new ArrayIterator<User>(users, n);
    }
    
    public int save(){
        int result = 0;
        try{
            Connection con = DriverManager.getConnection(connectionString, dbUser, dbPass);
            StringBuilder sb = new StringBuilder();
            Iterator<String> it = addedUsers.iterator();
            int index;
            while(it.hasNext()){
                index = find(it.next());
                if(index>-1){
                    lastIndex++;
                    sb.append("insert into Users values (").append(lastIndex);
                    sb.append(",'").append(users[index].getUsername());
                    sb.append("','").append(users[index].getPassword());
                    sb.append("','").append(users[index].getDatabase()).append("')\n");
                }else{
                    result++;
                }
            }
            Statement s = con.createStatement();
            s.executeUpdate(sb.toString());
            addedUsers.clear();
            // close the connection
            con.commit();
            con.close();
        }catch(SQLException sqle){
            System.err.println(sqle.toString());
            result = -1;
        }
        return result;
    }
    
}
