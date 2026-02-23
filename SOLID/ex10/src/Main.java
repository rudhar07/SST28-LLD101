public class Main {
    public static void main(String[] args) {
        System.out.println("=== Transport Booking ===");
        TransportBookingService svc = new TransportBookingService(
                new DistanceCalculatorImpl(),
                new DriverAllocatorImpl(),
                new PaymentGatewayImpl()
        );
        TripRequest req = new TripRequest("S1", new GeoPoint(12.9, 77.6), new GeoPoint(13.0, 77.7));
        svc.book(req);
    }
}
