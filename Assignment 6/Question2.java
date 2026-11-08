import java.io.*;

public class Question2 {
    public static void analyzeLogFile(String inputLogPath, String filteredLogPath) {
        int totalEntries = 0;
        int errorCount = 0;
        int warningCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputLogPath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(filteredLogPath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                totalEntries++;
                String upperLine = line.toUpperCase();
                if (upperLine.contains("ERROR") || upperLine.contains("WARNING")) {
                    writer.write(line);
                    writer.newLine();
                    if (upperLine.contains("ERROR")) errorCount++;
                    if (upperLine.contains("WARNING")) warningCount++;
                }
            }

            System.out.println("Log Analysis Complete:");
            System.out.println("Total log entries: " + totalEntries);
            System.out.println("ERROR entries: " + errorCount);
            System.out.println("WARNING entries: " + warningCount);
            System.out.println("Filtered entries saved to: " + filteredLogPath);

        } catch (IOException e) {
            System.out.println("Error processing log file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String logFile = "app.log";
        String filteredFile = "filtered_logs.txt";

        try (BufferedWriter sampleWriter = new BufferedWriter(new FileWriter(logFile))) {
            sampleWriter.write("2026-11-09 10:00:01 INFO Application started");
            sampleWriter.newLine();
            sampleWriter.write("2026-11-09 10:00:15 WARNING Disk space low");
            sampleWriter.newLine();
            sampleWriter.write("2026-11-09 10:01:23 INFO User login successful");
            sampleWriter.newLine();
            sampleWriter.write("2026-11-09 10:02:45 ERROR Database connection failed");
            sampleWriter.newLine();
            sampleWriter.write("2026-11-09 10:03:10 WARNING Memory consumption above 80%");
            sampleWriter.newLine();
            sampleWriter.write("2026-11-09 10:04:00 INFO User logged out");
            sampleWriter.newLine();
        } catch (IOException e) {
            System.out.println("Error creating sample log: " + e.getMessage());
        }

        analyzeLogFile(logFile, filteredFile);
    }
}
