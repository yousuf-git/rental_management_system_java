package vehicle_rental_system;

public class Admin {
    private String name;
    private String username;
    private String password;

    public Admin(String name, String username, String password) {
        this.username = username;
        this.password = password;
    }

    // -------------------------- Getters and Setters --------------------------
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    // -------------------------------------------------------------------------

    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
    
    public boolean authenticate(String inputPassword) {
        return this.password.equals(inputPassword);
    }

}
