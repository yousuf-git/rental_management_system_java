package vehicle_rental_system;

import vehicle_rental_system.repos.CustomerRepository;
import vehicle_rental_system.repos.RentalRepository;
import vehicle_rental_system.repos.VehicleRepository;

public class AdminService {
    private static AdminService instance;
    private Admin admin;
    private VehicleRepository vehicleRepo;
    private CustomerRepository customerRepo;
    private RentalRepository rentalRepo;

    private AdminService() {
        admin = new Admin("Admin", "admin", "password123");
        vehicleRepo = new VehicleRepository();
        customerRepo = new CustomerRepository();
        rentalRepo = new RentalRepository();
    }

    public static AdminService getInstance() {
        if (instance == null) {
            instance = new AdminService();
        }
        return instance;
    }

    public boolean authenticate(String username, String password) {
        return admin.authenticate(username, password);
    }

    // -------------------------- Utility Methods --------------------------
    public void addVehicle(Vehicle vehicle) {
        if (vehicleRepo.addVehicle(vehicle)) {
            System.out.println("Vehicle added successfully.");
        } else {
            System.out.println("Failed to add vehicle. Try Again...");
        }
    }

    public void removeVehicle(int vehicleId) {
        if (vehicleRepo.removeVehicle(vehicleId)) {
            System.out.println("✅ Vehicle removed successfully!");
        } else {
            System.out.println("⚠️ Vehicle not found!");
        }
    }

    public void viewAllRentals() {
        for (Rental rental : rentalRepo.getAllRentalRecords()) {
            System.out.println(rental.getRentalDetails());
            System.out.println("----------------------------");
        } 
    }
    public void viewAllCustomers() {
        for (Customer customer : customerRepo.getAllCustomers()) {
            System.out.println(customer);
            System.out.println("----------------------------");
        }
    }
    public void viewAllVehicles() {
        for (Vehicle vehicle : vehicleRepo.getAllVehicles()) {
            System.out.println(vehicle);
            System.out.println("----------------------------");
        }
    }
    public void updateVehicle(int vehicleId, Vehicle updatedVehicle) {
        if (vehicleRepo.updateVehicle(vehicleId, updatedVehicle) == null) {
            System.out.println("Vehicle not found");
            return;
        };
        System.out.println("Vehicle updated successfully.");
    }
    public void updateCustomer(int customerId, Customer updatedCustomer) {
        if (customerRepo.updateCustomer(customerId, updatedCustomer) == null) {
            System.out.println("Customer not found");
            return;
        };
        System.out.println("Customer updated successfully.");
    }
    public void updateRental(int rentalId, Rental updatedRental) {
        if (rentalRepo.updateRentalRecord(rentalId, updatedRental) == null) {
            System.out.println("Rental not found");
            return;
        };
        System.out.println("Rental updated successfully.");
    }

    public boolean changePassword(String oldPassword, String newPassword) {
        if (admin.authenticate(oldPassword)) {
            admin.setPassword(newPassword);
            return true;
        } 
        return false;
    }

    public Vehicle getVehicleById(int vehicleId) {
        return vehicleRepo.findVehicleById(vehicleId);
    }

    public void viewRentedVehicles() {
        for (Vehicle vehicle : vehicleRepo.getAllVehicles()) {
            if (vehicle.isRented()) {
                System.out.println(vehicle.getDetails());
                System.out.println("----------------------------");
            }
        }
    }

    public void viewAvailableVehicles() {
        for (Vehicle vehicle : vehicleRepo.getAllVehicles()) {
            if (!vehicle.isRented()) {
                System.out.println(vehicle.getDetails());
                System.out.println("----------------------------");
            }
        }
    }

    // -------------------------- Customer Methods --------------------------
    public void addCustomer(Customer customer) {
        if (customerRepo.addCustomer(customer)) {
            System.out.println("✅ Customer added successfully!");
        } else {
            System.out.println("❌ Failed to add customer. Try Again...");
        }
    }

    // public void removeCustomer(int customerId) {
    //     if (customerRepo.removeCustomer(customerId)) {
    //         System.out.println("✅ Customer removed successfully!");
    //     } else {
    //         System.out.println("⚠️ Customer not found!");
    //     }
    // }


    // -------------------------- Rental Methods --------------------------
    public void addRental(Rental rental) {
        if (rentalRepo.addRentalRecord(rental)) {
            System.out.println("✅ Rental added successfully!");
        } else {
            System.out.println("❌ Failed to add rental. Try Again...");
        }
    }

    public void findRentalByCustomerId(int customerId) {
        System.out.println("\n══════ Rentals for Customer ID: " + customerId + " ══════");
        for (Rental rental : rentalRepo.findRentalByCustomerId(customerId)) {
            System.out.println(rental.getRentalDetails());
            System.out.println("----------------------------");
        }
    }

    public void findRentalByVehicleId(int vehicleId) {
        System.out.println("\n══════ Rentals for Vehicle ID: " + vehicleId + " ══════");
        for (Rental rental : rentalRepo.findRentalByVehicleId(vehicleId)) {
            System.out.println(rental.getRentalDetails());
            System.out.println("----------------------------");
        }
    }

    public void setCustomerRepo(CustomerRepository mockCustomerRepo) {
        this.customerRepo = mockCustomerRepo;
    }

    public void setVehicleRepo(VehicleRepository mockVehicleRepo) {
        this.vehicleRepo = mockVehicleRepo;
    }

    public void setRentalRepo(RentalRepository mockRentalRepo) {
        this.rentalRepo = mockRentalRepo;
    }

    public void setAdmin(Admin mockAdmin) {
        this.admin = mockAdmin;
    }

}
