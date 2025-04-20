package vehicle_rental_system;

public abstract class Vehicle {
    private int id;
    private String brand;
    private double rentalRate;
    private boolean isRented;

    public Vehicle(int id, String brand, double rentalRate, boolean isRented) {
        this.id = id;
        this.brand = brand;
        this.rentalRate = rentalRate;
        this.isRented = isRented;
    }

    public int getId() { return id; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    
    public double getRentalRate() { return rentalRate; }
    public void setRentalRate(double rentalRate) { this.rentalRate = rentalRate; }
    
    public boolean isRented() { return isRented; }
    public void setRented(boolean rented) { isRented = rented; }

    public abstract String getDetails();
}