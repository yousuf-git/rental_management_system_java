package vehicle_rental_system;

import java.util.Scanner;

public class VehicleRentalSystem {
    private static AdminService adminService = AdminService.getInstance();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        displayWelcome();
        login();
        showMainMenu();
    }

    private static void displayWelcome() {
        System.out.println("══════════════════════════════════════");
        System.out.println("   🚗 Vehicle Rental System 🚚");
        System.out.println("══════════════════════════════════════");
    }

    private static boolean login() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        if (adminService.authenticate(username, password)) {
            System.out.println("✅ Login successful!");
            return true;
        } else {
            System.out.println("❌ Invalid credentials. Try Again...");
            return login();
        }
    }

    private static void showMainMenu() {
        while (true) {
            System.out.println("\n══════ Main Menu ══════");
            System.out.println("1. Manage Vehicles");
            System.out.println("2. Manage Customers");
            System.out.println("3. Manage Rentals");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    manageVehicles();
                    break;
                case "2":
                    manageCustomers();
                    // System.out.println("👤 Customer Management is not implemented yet.");
                    break;
                case "3":
                    manageRentals();
                    // System.out.println("📦 Rental Management is not implemented yet.");
                    break;
                case "4":
                    System.out.println("👋 Exiting system...");
                    return;
                default:
                    System.out.println("⚠️ Invalid option!");
            }
        }
    }


    private static void manageVehicles() {
        while (true) {
            System.out.println("\n══════ Vehicle Management ══════");
            System.out.println("1. Add Vehicle");
            System.out.println("2. View Available Vehicles");
            System.out.println("3. View Rented Vehicles");
            System.out.println("4. Remove Vehicle");
            System.out.println("5. Update Vehicle");
            System.out.println("6. Back to Main Menu");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addVehicle();
                    break;
                case "2":
                    viewAvailableVehicles();
                    break;
                case "3":
                    viewRentedVehicles();
                    break;
                case "4":
                    removeVehicle();
                    break;
                case "5":
                    updateVehicle();
                    break;
                case "6":
                    return;
                default:
                    System.out.println("⚠️ Invalid option!");
            }
        }
    }
    private static void updateVehicle() {
        System.out.print("Enter Vehicle ID to update: ");
        int id = Integer.parseInt(scanner.nextLine());
        Vehicle vehicle = adminService.getVehicleById(id);
        if (vehicle == null) {
            System.out.println("⚠️ Vehicle not found!");
            return;
        }
        System.out.println("Current Vehicle Details: " + vehicle.getDetails());
        System.out.print("Enter Updated Details: ");
        System.out.print("Vehicle Type (Car/Bike/Truck): ");
        String vehicleType = scanner.nextLine();
        System.out.print("Vehicle ID: ");
        int vehicleId = Integer.parseInt(scanner.nextLine());
        System.out.print("Vehicle Brand: ");
        String vehicleBrand = scanner.nextLine();
        System.out.print("Vehicle Rental Rate per day: ");
        double rentalRate = Double.parseDouble(scanner.nextLine());
        System.out.print("Is Available (true/false): ");
        boolean isAvailable = Boolean.parseBoolean(scanner.nextLine());

        String specialArg = null;
        if (vehicleType.equalsIgnoreCase("Car")) {
            System.out.print("Number of doors: ");
            specialArg = scanner.nextLine();
        } else if (vehicleType.equalsIgnoreCase("Bike")) {
            System.out.print("Does it have a basket? (true/false): ");
            specialArg = scanner.nextLine();
        } else if (vehicleType.equalsIgnoreCase("Truck")) {
            System.out.print("Load capacity in tons: ");
            specialArg = scanner.nextLine();
        } else {
            System.out.print("⚠️ Invalid vehicle type!");
            return;
            
        }
        adminService.updateVehicle(vehicleId, VehicleFactory.createVehicle(vehicleType.toLowerCase(), vehicleId, vehicleBrand, rentalRate, !isAvailable, specialArg));
    }

    private static void removeVehicle() {
        System.out.print("Enter Vehicle ID to remove: ");
        int vehicleId = Integer.parseInt(scanner.nextLine());
        adminService.removeVehicle(vehicleId);
    }

    private static void viewRentedVehicles() {
        adminService.viewRentedVehicles();
        System.out.println("══════════════════════════════════════");
    }

    private static void viewAvailableVehicles() {
        adminService.viewAvailableVehicles();
        System.out.println("══════════════════════════════════════");
    }

    public static void addVehicle() {
        System.out.println("\nInput Vehicle Details:");
        System.out.print("Vehicle Type (Car/Bike/Truck): ");
        String vehicleType = scanner.nextLine();
        System.out.print("Vehicle ID: ");
        int vehicleId = Integer.parseInt(scanner.nextLine());
        System.out.print("Vehicle Brand: ");
        String vehicleBrand = scanner.nextLine();
        System.out.print("Vehicle Rental Rate per day: ");
        double rentalRate = Double.parseDouble(scanner.nextLine());
        System.out.print("Is Available (true/false): ");
        boolean isAvailable = Boolean.parseBoolean(scanner.nextLine());

        String specialArg = null;
        if (vehicleType.equalsIgnoreCase("Car")) {
            System.out.print("Number of doors: ");
            specialArg = scanner.nextLine();
        } else if (vehicleType.equalsIgnoreCase("Bike")) {
            System.out.print("Does it have a basket? (true/false): ");
            specialArg = scanner.nextLine();
        } else if (vehicleType.equalsIgnoreCase("Truck")) {
            System.out.print("Load capacity in tons: ");
            specialArg = scanner.nextLine();
        } else {
            System.out.print("⚠️ Invalid vehicle type!");
            return;
            
        }

        adminService.addVehicle(VehicleFactory.createVehicle(vehicleType.toLowerCase(), vehicleId, vehicleBrand, rentalRate, !isAvailable, specialArg));
    }

    private static void manageCustomers() {
        while (true) {
            System.out.println("\n══════ Customer Management ══════");
            System.out.println("1. Add Customer");
            System.out.println("2. View All Customers");
            // System.out.println("3. Remove Customer");
            System.out.println("3. Back to Main Menu");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();
    
            switch (choice) {
                case "1":
                    addCustomer();
                    break;
                case "2":
                    viewAllCustomers();
                    break;
                // case "3":
                //     removeCustomer();
                //     break;
                case "3":
                    return;
                default:
                    System.out.println("⚠️ Invalid option!");
            }
        }
    }
    
    private static void addCustomer() {
        System.out.println("\nInput Customer Details:");
        System.out.print("Customer ID: ");
        int customerId = Integer.parseInt(scanner.nextLine());
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Gender: ");
        String role = scanner.nextLine();
    
        adminService.addCustomer(new Customer(customerId, name, phone, role));
        System.out.println("✅ Customer added successfully!");
    }
    
    private static void viewAllCustomers() {
        System.out.println("\n══════ Customer List ══════");
        adminService.viewAllCustomers();
        System.out.println("══════════════════════════════════════");
    }
    
    // private static void removeCustomer() {
    //     System.out.print("Enter Customer ID to remove: ");
    //     int customerId = Integer.parseInt(scanner.nextLine());
    //     adminService.removeCustomer(customerId);
    //     System.out.println("✅ Customer removed successfully!");
    // }
    
    private static void manageRentals() {
        while (true) {
            System.out.println("\n══════ Rental Management ══════");
            System.out.println("1. Add Rental");
            System.out.println("2. View All Rentals");
            System.out.println("3. Find Rental by Customer ID");
            System.out.println("4. Find Rental by Vehicle ID");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();
    
            switch (choice) {
                case "1":
                    addRental();
                    break;
                case "2":
                    viewAllRentals();
                    break;
                case "3":
                    findRentalByCustomerId();
                    break;
                case "4":
                    findRentalByVehicleId();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("⚠️ Invalid option!");
            }
        }
    }
    
    private static void addRental() {
        System.out.println("\nInput Rental Details:");
        System.out.print("Rental ID: ");
        int rentalId = Integer.parseInt(scanner.nextLine());
        System.out.print("Vehicle ID: ");
        int vehicleId = Integer.parseInt(scanner.nextLine());
        System.out.print("Customer ID: ");
        int customerId = Integer.parseInt(scanner.nextLine());
        System.out.print("Start Date (YYYY-MM-DD): ");
        String startDate = scanner.nextLine();
        System.out.print("End Date (YYYY-MM-DD): ");
        String endDate = scanner.nextLine();
    
        adminService.addRental(new Rental(rentalId, vehicleId, customerId, startDate, endDate));
        System.out.println("✅ Rental added successfully!");
    }
    
    private static void viewAllRentals() {
        System.out.println("\n══════ Rental List ══════");
        adminService.viewAllRentals();
        System.out.println("══════════════════════════════════════");
    }
    
    private static void findRentalByCustomerId() {
        System.out.print("Enter Customer ID: ");
        int customerId = Integer.parseInt(scanner.nextLine());
        adminService.findRentalByCustomerId(customerId);
    }
    
    private static void findRentalByVehicleId() {
        System.out.print("Enter Vehicle ID: ");
        int vehicleId = Integer.parseInt(scanner.nextLine());
        adminService.findRentalByVehicleId(vehicleId);
    }
}