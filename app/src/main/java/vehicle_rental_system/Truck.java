package vehicle_rental_system;

public class Truck extends Vehicle {
    private double loadCapacity;

    public Truck(int id, String brand, double rentalRate, boolean isRented, double loadCapacity) {
        super(id, brand, rentalRate, isRented);
        this.loadCapacity = loadCapacity;
    }
    public double getLoadCapacity() {
        return loadCapacity;
    }
    public void setLoadCapacity(double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    @Override
    public String getDetails() {
        return "Truck [ID: " + getId() + ", Brand: " + getBrand() + ", Rate: $" + getRentalRate() + ", Load Capacity: " + loadCapacity + " tons]";
    }
}