package vehicle_rental_system;

public class Bike extends Vehicle {
    private boolean hasBasket;

    public Bike(int id, String brand, double rentalRate, boolean isRented, boolean hasBasket) {
        super(id, brand, rentalRate, isRented);
        this.hasBasket = hasBasket;
    }
    public boolean hasBasket() {
        return hasBasket;
    }
    public void setHasBasket(boolean hasBasket) {
        this.hasBasket = hasBasket;
    }

    @Override
    public String getDetails() {
        return "Bike [ID: " + getId() + ", Brand: " + getBrand() + ", Rate: $" + getRentalRate() + ", Basket: " + hasBasket + "]";
    }
}
