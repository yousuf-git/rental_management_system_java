package vehicle_rental_system;

public class Customer {

    private int customerId;
    private String name;
    private String phone; // In real app, store hashed passwords
    private String gender;

    public Customer(int customerId, String name, String password, String gender) {
        this.customerId = customerId;
        this.name = name;
        this.phone = password;
        this.gender = gender;
    }

    public boolean authenticate(String inputPassword) {
        return this.phone.equals(inputPassword);
    }

    public void changePassword(String newPassword) {
        this.phone = newPassword;
    }

    // Getters and other methods
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int userId) {
        this.customerId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String username) {
        this.name = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "[ID: " + customerId + ", Name: " + name + ", Phone: " + phone + ", Gender: " + gender + "]";
    }
}
