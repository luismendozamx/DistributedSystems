/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author luismendoza
 */
public class User {
    private String username, database, password;

    public User(String username, String password, String database){
        this.username = username;
        this.password = password;
        this.database = database;
    }

    public String getUsername() {
        return username;
    }

    public String getDatabase() {
        return database;
    }

    public String getPassword() {
        return password;
    }

    public boolean isValid(String password) {
        return this.password.equals(password);
    }
    
    // Returns 0 if user does not match
    // Returns 1 if user's db is the same
    // Returns 2 if user's username is the same
    public int userExists(User otherUser){
        if(this.database.equals(otherUser.database)){
            return 1;
        }else if(this.username.equals(otherUser.username)){
            return 2;
        }else{
            return 0;
        }
    }
    
    public boolean equalsUser(String username){
        return this.username.equals(username);
    }
    
    public String toFile(){
        StringBuilder sb = new StringBuilder();
        sb.append(username).append(" <");
        sb.append(password).append("> ");
        sb.append(database).append("\n");
        return sb.toString();
    }
}
