import java.util.ArrayList;
import java.util.List;

/**
 * Registers devices by type; get first of type (ISP-friendly).
 */
public class DeviceRegistry {
    private final List<Object> devices = new ArrayList<>();

    public void add(Object device) {
        devices.add(device);
    }

    public Object getFirstOfType(String typeName) {
        for (Object d : devices) {
            if (d.getClass().getSimpleName().equals(typeName)) return d;
        }
        return null;
    }
}
