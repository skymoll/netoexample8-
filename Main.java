import java.time.LocalDateTime;

public interface Logger {
    void log(String msg);
}

class SimpleLogger implements Logger {
    @Override
    public void log(String msg) {
        System.out.println("[" + LocalDateTime.now() + "] " + msg);
    }
}

class SmartLogger implements Logger {
    private int count = 0;

    @Override
    public void log(String msg) {
        count++;
        String level = msg.toLowerCase().contains("error") ? "ERROR" : "INFO";
        System.out.println(level + "#" + count + " [" + LocalDateTime.now() + "] " + msg);
    }
}