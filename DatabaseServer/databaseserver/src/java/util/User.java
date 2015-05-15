package util;

public class User {
    private final String username, database, password;

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
    
    public String toFile(){
        StringBuilder sb = new StringBuilder();
        sb.append(username).append(" <");
        sb.append(password).append("> ");
        sb.append(database).append("\n");
        return sb.toString();
    }
}
