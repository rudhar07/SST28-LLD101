import java.util.List;
import java.util.UUID;

/**
 * Orchestrates fee calculation using room and add-on pricing (OCP), then persists and prints receipt.
 */
public class HostelFeeCalculator {
    private final List<RoomTypePricing> roomPricings;
    private final List<AddOnPricing> addOnPricings;
    private final FakeBookingRepo repo;

    public HostelFeeCalculator(List<RoomTypePricing> roomPricings, List<AddOnPricing> addOnPricings,
                               FakeBookingRepo repo) {
        this.roomPricings = roomPricings;
        this.addOnPricings = addOnPricings;
        this.repo = repo;
    }

    public void process(BookingRequest req) {
        double monthly = 0.0;
        for (RoomTypePricing r : roomPricings) {
            if (r.matches(req.roomType)) {
                monthly += r.getMonthlyAmount();
                break;
            }
        }
        for (AddOn addOn : req.addOns) {
            for (AddOnPricing a : addOnPricings) {
                if (a.appliesTo(addOn)) {
                    monthly += a.getMonthlyAmount();
                    break;
                }
            }
        }
        Money monthlyMoney = new Money(monthly);
        Money deposit = new Money(monthly * 0.5);
        String id = "BKG-" + UUID.randomUUID().toString().substring(0, 8);
        repo.save(id, req, monthlyMoney, deposit);
        ReceiptPrinter.print(req, monthlyMoney, deposit);
    }
}
