package vehicle_rental_system;

public class Rental {
    private int rentalId;
    private int vehicleId;
    private int customerId;
    private String startDate;
    private String endDate;

    public Rental(int rentalId, int vehicleId, int customerId, String startDate, String endDate) {
        this.rentalId = rentalId;
        this.vehicleId = vehicleId;
        this.customerId = customerId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getRentalId() { return rentalId; }
    public int getVehicleId() { return vehicleId; }
    public int getCustomerId() { return customerId; }
    public String getStartDate() { return startDate; }
    public String getEndDate() { return endDate; }

    public void setRentalId(int rentalId) { this.rentalId = rentalId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public void setStartDate(String startDate) { this.startDate = startDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }
    
    // Utility methods
    @Override
    public String toString() {
        return "Rental{" +
                "rentalId='" + rentalId + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
    public String getRentalDetails() {
        return "Rental ID: " + rentalId + "\n" +
                "Vehicle ID: " + vehicleId + "\n" +
                "Customer ID: " + customerId + "\n" +
                "Start Date: " + startDate + "\n" +
                "End Date: " + endDate;
    }

}
