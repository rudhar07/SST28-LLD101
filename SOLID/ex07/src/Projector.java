public class Projector implements PowerControl, InputConnection {
    private boolean on = false;

    @Override
    public void powerOn() {
        on = true;
        System.out.println("Projector ON");
    }

    @Override
    public void powerOff() {
        on = false;
        System.out.println("Projector OFF");
    }

    @Override
    public void connectInput(String source) {
        System.out.println("Projector input: " + source);
    }
}
