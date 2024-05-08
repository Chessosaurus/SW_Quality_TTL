package logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class CustomLogger {
    private static final String logFilePath = new File(CustomLogger.class.getProtectionDomain()
            .getCodeSource().getLocation().getPath()).getParent() + File.separator + "logFile.txt";

    /**
     * Adds the prefix LOG to the String to log
     *
     * @param message Text to log
     */
    public static void log(String message) {
        write("LOG: " + message);
    }

    /**
     * Adds the prefix WARNING to the String to log
     *
     * @param message Text to log
     */
    public static void warning(String message) {
        write("WARNING: " + message);
    }

    /**
     * Adds the prefix ERROR to the String to log
     *
     * @param message Text to log
     */
    public static void error(String message) {
        write("ERROR: " + message);
    }

    /**
     * Writes a message with a timestamp to the log File
     * <p>
     * This Method always returns a Value and never null
     *
     * @param message Text to write to the log file
     */
    private static void write(String message) {
        // Write some content to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath, true))) {
            writer.write(getFormattedDate() + " " + message + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to log file");
        }
    }

    private static String getFormattedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        return sdf.format(new Date());
    }
}
