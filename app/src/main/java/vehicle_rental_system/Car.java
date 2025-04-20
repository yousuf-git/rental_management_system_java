package vehicle_rental_system;

public class Car extends Vehicle {
    private int numberOfDoors;

    public Car(int id, String brand, double rentalRate, boolean isRented, int numberOfDoors) {
        super(id, brand, rentalRate, isRented);
        this.numberOfDoors = numberOfDoors;
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }
    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    @Override
    public String getDetails() {
        return "Car [ID: " + getId() + ", Brand: " + getBrand() + ", Rate: $" + getRentalRate() + ", Doors: " + numberOfDoors + "]";
    }
}
