public class Main {
    public static void main(String[] args) {
        System.out.println("=== Classroom ===");
        DeviceRegistry reg = new DeviceRegistry();
        reg.add(new LightsPanel());
        reg.add(new Projector());
        reg.add(new AirConditioner());
        ClassroomController ctrl = new ClassroomController(reg);
        ctrl.startClass();
        ctrl.endClass();
    }
}
