/**
 * Orchestrates transport booking: distance -> fare -> driver -> payment (DIP).
 */
public class TransportBookingService {
    private final DistanceCalculator distanceCalculator;
    private final DriverAllocator driverAllocator;
    private final PaymentGateway paymentGateway;

    public TransportBookingService(DistanceCalculator distanceCalculator, DriverAllocator driverAllocator,
                                   PaymentGateway paymentGateway) {
        this.distanceCalculator = distanceCalculator;
        this.driverAllocator = driverAllocator;
        this.paymentGateway = paymentGateway;
    }

    public void book(TripRequest request) {
        double km = distanceCalculator.km(request.from, request.to);
        double fare = km * 12.0;
        String driverId = driverAllocator.allocate(request.studentId);
        String txnId = paymentGateway.charge(request.studentId, fare);
        System.out.println("Booked: " + driverId + " | " + String.format("%.1f", km) + " km | Rs " + String.format("%.2f", fare) + " | " + txnId);
    }
}
