package vehicle_rental_system.repos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import vehicle_rental_system.Customer;

/**
 * Saves data related to customers in a text file that is in resource folder
 * customers.txt
 * This class will handle customer data storage and retrieval
 * For simplicity, we can use a list to store customers in text file
 * In a real-world application, this would likely involve database operations
 */
public class CustomerRepository {
    private static final String FILE_PATH = "app/src/main/resources/customers.txt";
    
    public boolean addCustomer(Customer customer) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(customer.getCustomerId() + "," + customer.getName() + "," + customer.getPhone() + "," +
                    customer.getGender());
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.err.println("Error writing to customers file: " + e.getMessage());
            return false;
        }
        
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                customers.add(new Customer(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3]));
            }
        } catch (IOException e) {
            System.err.println("Error reading customers file: " + e.getMessage());
        }
        return customers;
    }

    // Example method to find a customer by ID
    public Customer findCustomerById(int id) {
        return getAllCustomers().stream()
                .filter(customer -> customer.getCustomerId() == id)
                .findFirst()
                .orElse(null);
    }

    // Returns the old value of the customer - if the customer is not found, returns null
    public Customer updateCustomer(int customerId, Customer updatedCustomer) {
        Customer customerToUpdate = findCustomerById(customerId);
        if (customerToUpdate == null) {
            System.out.println("Customer not found");
            return null;
        }
        List<Customer> customers = getAllCustomers();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Customer customer : customers) {
                if (customer.getCustomerId() == customerId) {
                    writer.write(updatedCustomer.getCustomerId() + "," + updatedCustomer.getName() + "," +
                            updatedCustomer.getPhone() + "," + updatedCustomer.getGender());
                } else {
                    writer.write(customer.getCustomerId() + "," + customer.getName() + "," +
                            customer.getPhone() + "," + customer.getGender());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error updating rentals file: " + e.getMessage());
        }
        return customerToUpdate;
    }
    // Example method to delete a customer
    public void deleteCustomer(int id) {
        List<Customer> customers = getAllCustomers();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Customer customer : customers) {
                if (customer.getCustomerId() != id) {
                    writer.write(customer.getCustomerId() + "," + customer.getName() + "," +
                            customer.getPhone() + "," + customer.getGender());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Error deleting customer: " + e.getMessage());
        }
    }
    
}
