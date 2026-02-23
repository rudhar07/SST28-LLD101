public class SampleData {
    public static String longBody() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) sb.append("Line ").append(i).append("\n");
        return sb.toString();
    }
}
