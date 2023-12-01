public class Commands {
        public static void log_view(String status_log, String request) {
        if (status_log.equals("ON")) {
            System.out.println("\n" + request);
        }
    }
}
