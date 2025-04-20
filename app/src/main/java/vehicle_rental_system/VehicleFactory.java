package vehicle_rental_system;

public class VehicleFactory {
    public static Vehicle createVehicle(String type, int id, String brand, double rentalRate, Boolean isRented, String specialArg) {
        try {
            switch (type.toLowerCase()) {
                case "bike":
                    return new Bike(id, brand, rentalRate, isRented, Boolean.parseBoolean(specialArg));
                case "car":
                    return new Car(id, brand, rentalRate, isRented, Integer.parseInt(specialArg));
                case "truck":
                    return new Truck(id, brand, rentalRate, isRented, Double.parseDouble(specialArg));
                default:
                    throw new IllegalArgumentException("Invalid vehicle type");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Error creating vehicle: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            return null;
        }
        
    }
}
