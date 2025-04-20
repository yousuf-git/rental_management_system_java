package vehicle_rental_system;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import vehicle_rental_system.repos.CustomerRepository;
import vehicle_rental_system.repos.RentalRepository;
import vehicle_rental_system.repos.VehicleRepository;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


public class AdminServiceTest {

    private AdminService adminService;
    private VehicleRepository mockVehicleRepo;
    private CustomerRepository mockCustomerRepo;
    private RentalRepository mockRentalRepo;
    private Admin mockAdmin;

    @BeforeEach
    public void setUp() {
        mockVehicleRepo = mock(VehicleRepository.class);
        mockCustomerRepo = mock(CustomerRepository.class);
        mockRentalRepo = mock(RentalRepository.class);
        mockAdmin = mock(Admin.class);

        adminService = AdminService.getInstance();

        // Injecting mocks into AdminService
        adminService.setVehicleRepo(mockVehicleRepo);
        adminService.setCustomerRepo(mockCustomerRepo);
        adminService.setRentalRepo(mockRentalRepo);
        adminService.setAdmin(mockAdmin);
    }

    @Test
    public void testAuthenticate_Success() {
        when(mockAdmin.authenticate("admin", "password123")).thenReturn(true);
        assertTrue(adminService.authenticate("admin", "password123"));
        verify(mockAdmin, times(1)).authenticate("admin", "password123");
    }

    @Test
    public void testAuthenticate_Failure() {
        when(mockAdmin.authenticate("admin", "wrongPassword")).thenReturn(false);
        assertFalse(adminService.authenticate("admin", "wrongPassword"));
        verify(mockAdmin, times(1)).authenticate("admin", "wrongPassword");
    }

    @Test
    public void testAddVehicle_Success() {
        // createVehicle(String type, int id, String brand, double rentalRate, Boolean isRented, String specialArg)
        Vehicle vehicle = VehicleFactory.createVehicle("car", 1, "Toyota", 50.0, false, "4");
        when(mockVehicleRepo.addVehicle(vehicle)).thenReturn(true);

        adminService.addVehicle(vehicle);

        verify(mockVehicleRepo, times(1)).addVehicle(vehicle);
    }

    @Test
    public void testAddVehicle_Failure() {
        Vehicle vehicle = VehicleFactory.createVehicle("car", 1, "Toyota", 50.0, false, "4");
        when(mockVehicleRepo.addVehicle(vehicle)).thenReturn(false);

        adminService.addVehicle(vehicle);

        verify(mockVehicleRepo, times(1)).addVehicle(vehicle);
    }

    @Test
    public void testRemoveVehicle_Success() {
        when(mockVehicleRepo.removeVehicle(1)).thenReturn(true);

        adminService.removeVehicle(1);

        verify(mockVehicleRepo, times(1)).removeVehicle(1);
    }

    @Test
    public void testRemoveVehicle_Failure() {
        when(mockVehicleRepo.removeVehicle(1)).thenReturn(false);

        adminService.removeVehicle(1);

        verify(mockVehicleRepo, times(1)).removeVehicle(1);
    }

    @Test
    public void testChangePassword_Success() {
        when(mockAdmin.authenticate("oldPassword")).thenReturn(true);

        assertTrue(adminService.changePassword("oldPassword", "newPassword"));
        verify(mockAdmin, times(1)).authenticate("oldPassword");
        verify(mockAdmin, times(1)).setPassword("newPassword");
    }

    @Test
    public void testChangePassword_Failure() {
        when(mockAdmin.authenticate("wrongPassword")).thenReturn(false);

        assertFalse(adminService.changePassword("wrongPassword", "newPassword"));
        verify(mockAdmin, times(1)).authenticate("wrongPassword");
        verify(mockAdmin, never()).setPassword(anyString());
    }

    @Test
    public void testGetVehicleById() {
        Vehicle vehicle = VehicleFactory.createVehicle("car", 1, "Toyota", 50.0, false, "4");
        when(mockVehicleRepo.findVehicleById(1)).thenReturn(vehicle);

        Vehicle result = adminService.getVehicleById(1);

        assertEquals(vehicle, result);
        verify(mockVehicleRepo, times(1)).findVehicleById(1);
    }

    @Test
    public void testAddCustomer_Success() {
        // public Customer(int customerId, String name, String password, String gender) {
        Customer customer = new Customer(1, "John Doe", "password123", "male");
        when(mockCustomerRepo.addCustomer(customer)).thenReturn(true);

        adminService.addCustomer(customer);

        verify(mockCustomerRepo, times(1)).addCustomer(customer);
    }

    @Test
    public void testAddCustomer_Failure() {
        Customer customer = new Customer(1, "John Doe", "password", ",male");
        when(mockCustomerRepo.addCustomer(customer)).thenReturn(false);

        adminService.addCustomer(customer);

        verify(mockCustomerRepo, times(1)).addCustomer(customer);
    }

    // @Test
    // public void testAddRental_Success() {
    //     // public Rental(int rentalId, int vehicleId, int customerId, String startDate, String endDate)
    //     Rental rental = new Rental(1, 1, 1, "2023-10-01", "2023-10-05");
    //     when(mockRentalRepo.addRentalRecord(rental)).thenReturn(true);

    //     adminService.addRental(rental);

    //     verify(mockRentalRepo, times(1)).addRentalRecord(rental);
    // }

    // @Test
    // public void testAddRental_Failure() {
    //     Rental rental = new Rental();
    //     when(mockRentalRepo.addRentalRecord(rental)).thenReturn(false);

    //     adminService.addRental(rental);

    //     verify(mockRentalRepo, times(1)).addRentalRecord(rental);
    // }
}