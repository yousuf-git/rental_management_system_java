package vehicle_rental_system.repos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import vehicle_rental_system.Bike;
import vehicle_rental_system.Car;
import vehicle_rental_system.Truck;
import vehicle_rental_system.Vehicle;
import vehicle_rental_system.VehicleFactory;

/**
 * Saves data related to vehicles in a text file that is in resource folder
 * vehicles.txt
 * This class will handle vehicle data storage and retrieval
 * For simplicity, we can use a list to store vehicles in text file
 * In a real-world application, this would likely involve database operations
 * This class is responsible for managing vehicle data, including adding, removing, and retrieving vehicles.
 */
public class VehicleRepository {
    private static final String FILE_PATH = "app/src/main/resources/vehicles.txt";

    public boolean addVehicle(Vehicle vehicle) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            if (vehicle instanceof Car) {
                writer.write("Car," + vehicle.getId() + "," + vehicle.getBrand() + "," + vehicle.getRentalRate() + "," + vehicle.isRented()  + "," + ((Car) vehicle).getNumberOfDoors());
            } else if (vehicle instanceof Truck) {
                writer.write("Truck," + vehicle.getId() + "," + vehicle.getBrand() + "," + vehicle.getRentalRate() + "," + vehicle.isRented() + "," + ((Truck) vehicle).getLoadCapacity());
            } else if (vehicle instanceof Bike) {
                writer.write("Bike," + vehicle.getId() + "," + vehicle.getBrand() + "," + vehicle.getRentalRate() + "," + vehicle.isRented() + "," + ((Bike) vehicle).hasBasket());
            }
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.err.println("Error writing to vehicles file: " + e.getMessage());
            return false;
        }
    }
    
    public boolean removeVehicle(int vehicleId) {
        List<Vehicle> vehicles = getAllVehicles();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Vehicle vehicle : vehicles) {
                if (vehicle.getId() != vehicleId) {
                    addVehicle(vehicle);
                }
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error deleting customer: " + e.getMessage());
            return false;
        }
        
    }
    public Vehicle findVehicleById(int vehicleId) {
        List<Vehicle> vehicles = getAllVehicles();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId() == vehicleId) {
                return vehicle;
            }
        }
        return null;
    }
    public Vehicle updateVehicle(int vehicleId, Vehicle updatedVehicle) {
        Vehicle vehicleToUpdate = findVehicleById(vehicleId);
        if (vehicleToUpdate == null) {
            System.out.println("Vehicle not found");
            return null;
        }
        List<Vehicle> vehicles = getAllVehicles();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Vehicle vehicle : vehicles) {
                if (vehicle.getId() == vehicleId) {
                    addVehicle(updatedVehicle);
                } else {
                    addVehicle(vehicle);
                }
            }
        } catch (IOException e) {
            System.err.println("Error updating vehicle: " + e.getMessage());
        }
        return vehicleToUpdate;
        
    }
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                char ch = line.charAt(0);
                switch (ch) {
                    case 'C':
                        String[] carParts = line.split(",");
                        vehicles.add(VehicleFactory.createVehicle("car", Integer.parseInt(carParts[1]), carParts[2], Double.parseDouble(carParts[3]), Boolean.parseBoolean(carParts[4]), carParts[5]));
                    break;
                    case 'B': // for bike
                        String[] bikeParts = line.split(",");
                        vehicles.add(VehicleFactory.createVehicle("bike", Integer.parseInt(bikeParts[1]), bikeParts[2], Double.parseDouble(bikeParts[3]), Boolean.parseBoolean(bikeParts[4]), bikeParts[5]));

                    break;
                    case 'T': // for truck
                        String[] truckParts = line.split(",");
                        vehicles.add(VehicleFactory.createVehicle("truck", Integer.parseInt(truckParts[1]), truckParts[2], Double.parseDouble(truckParts[3]), Boolean.parseBoolean(truckParts[4]), truckParts[5]));
                    break;
                    default:
                        System.err.println("Unknown vehicle type: " + ch);
                    break;
                        
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading vehicles file: " + e.getMessage());
        }
        return vehicles;
        
    }
    public List<Vehicle> getAvailableVehicles() {
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Vehicle vehicle : getAllVehicles()) {
            if (!vehicle.isRented()) {
                availableVehicles.add(vehicle);
            }
        }
        return availableVehicles;

    }
    public List<Vehicle> getRentedVehicles() {
        List<Vehicle> rentedVehicles = new ArrayList<>();
        for (Vehicle vehicle : getAllVehicles()) {
            if (vehicle.isRented()) {
                rentedVehicles.add(vehicle);
            }
        }
        return rentedVehicles;
    }
    public void getVehicleByType(String vehicleType) {
        List<Vehicle> vehicles = getAllVehicles();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getClass().getSimpleName().equalsIgnoreCase(vehicleType)) {
                System.out.println(vehicle.getDetails());
            }
        }
        
    }
    

}
