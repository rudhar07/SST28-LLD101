/**
 * Orchestrates class start/end using registered devices (DIP).
 */
public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) {
        this.reg = reg;
    }

    public void startClass() {
        Object lights = reg.getFirstOfType("LightsPanel");
        if (lights instanceof PowerControl) ((PowerControl) lights).powerOn();
        Object proj = reg.getFirstOfType("Projector");
        if (proj instanceof PowerControl) ((PowerControl) proj).powerOn();
        if (proj instanceof InputConnection) ((InputConnection) proj).connectInput("HDMI-1");
        Object ac = reg.getFirstOfType("AirConditioner");
        if (ac instanceof PowerControl) ((PowerControl) ac).powerOn();
        if (ac instanceof TemperatureControl) ((TemperatureControl) ac).setTemperatureC(24);
    }

    public void endClass() {
        Object lights = reg.getFirstOfType("LightsPanel");
        if (lights instanceof PowerControl) ((PowerControl) lights).powerOff();
        Object proj = reg.getFirstOfType("Projector");
        if (proj instanceof PowerControl) ((PowerControl) proj).powerOff();
        Object ac = reg.getFirstOfType("AirConditioner");
        if (ac instanceof PowerControl) ((PowerControl) ac).powerOff();
    }
}
