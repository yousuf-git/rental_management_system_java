package vehicle_rental_system.repos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import vehicle_rental_system.Rental;

/**
 * Saves data related to customers in a text file that is in resource folder
 * customers.txt
 */
public class RentalRepository {
    private static final String FILE_PATH = "app/src/main/resources/rentals.txt";
    
    // Example method to add a rental record
    public boolean addRentalRecord(Rental rental) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(rental.getRentalId() + "," + rental.getVehicleId() + "," + rental.getCustomerId() + "," +
                    rental.getStartDate() + "," + rental.getEndDate());
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.err.println("Error writing to rentals file: " + e.getMessage());
            return false;
        }
    }

    // Example method to retrieve all rental records
    public List<Rental> getAllRentalRecords() {
        List<Rental> rentals = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                rentals.add(new Rental(Integer.valueOf(parts[0]), Integer.valueOf(parts[1]), Integer.valueOf(parts[2]), parts[3], parts[4]));
            }
        } catch (IOException e) {
            System.err.println("Error reading rentals file: " + e.getMessage());
        }
        return rentals;
    }

    // Example method to find a rental record by customer ID
    public List<Rental> findRentalByCustomerId(int customerId) {
        return getAllRentalRecords().stream()
            .filter(rental -> rental.getCustomerId() == customerId)
            .collect(Collectors.toList());
                
    }
    // Example method to find a rental record by vehicle ID
    public List<Rental> findRentalByVehicleId(int vehicleId) {
        return getAllRentalRecords().stream()
                .filter(rental -> rental.getVehicleId() == vehicleId)
                .collect(Collectors.toList());
    }
    // Example method to find a rental record by rental date
    public Rental findRentalByDate(String rentalDate) {
        return getAllRentalRecords().stream()
                .filter(rental -> rental.getStartDate().equals(rentalDate))
                .findFirst()
                .orElse(null);
    }
    // Example method to update a rental record
    public Rental updateRentalRecord(int rentalId, Rental updatedRental) {
        List<Rental> rentals = getAllRentalRecords();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Rental rental : rentals) {
                if (rental.getRentalId() == rentalId) {
                    writer.write(updatedRental.getRentalId() + "," + updatedRental.getVehicleId() + "," +
                            updatedRental.getCustomerId() + "," + updatedRental.getStartDate() + "," +
                            updatedRental.getEndDate());
                } else {
                    writer.write(rental.getRentalId() + "," + rental.getVehicleId() + "," +
                            rental.getCustomerId() + "," + rental.getStartDate() + "," +
                            rental.getEndDate());

                }
                writer.newLine();

            }
        } catch (IOException e) {
            System.err.println("Error updating rentals file: " + e.getMessage());
        }
        return updatedRental;
    }
    

}
