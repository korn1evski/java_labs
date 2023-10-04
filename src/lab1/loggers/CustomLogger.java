package lab1.loggers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomLogger {
    private String logFileName;

    public CustomLogger(String logFileName) {
        this.logFileName = logFileName;
    }

    public void log(String message) {
        try (FileWriter writer = new FileWriter("src" + File.separator + "lab1" + File.separator + "files" + File.separator + "log.txt", true)) {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            writer.write("[" + timestamp + "] " + message + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
