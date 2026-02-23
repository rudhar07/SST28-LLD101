public class AirConditioner implements PowerControl, TemperatureControl {
    @Override
    public void powerOn() {
        System.out.println("AC ON");
    }

    @Override
    public void powerOff() {
        System.out.println("AC OFF");
    }

    @Override
    public void setTemperatureC(int celsius) {
        System.out.println("AC set to " + celsius + "C");
    }
}
