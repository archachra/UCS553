import java.io.*;
import java.util.Scanner;

public class Question1 {
    public static void copyBinaryFile(String sourcePath, String destPath) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(sourcePath);
            fos = new FileOutputStream(destPath);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
            System.out.println("Binary file copied successfully.");
        } catch (IOException e) {
            System.out.println("Error copying binary file: " + e.getMessage());
        } finally {
            try {
                if (fis != null) fis.close();
            } catch (IOException e) {
                System.out.println("Error closing input stream: " + e.getMessage());
            }
            try {
                if (fos != null) fos.close();
            } catch (IOException e) {
                System.out.println("Error closing output stream: " + e.getMessage());
            }
        }
    }

    public static void writeStudentDetails(String filePath, String studentData) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(filePath, true);
            writer.write(studentData + System.lineSeparator());
            System.out.println("Student record written successfully.");
        } catch (IOException e) {
            System.out.println("Error writing student details: " + e.getMessage());
        } finally {
            try {
                if (writer != null) writer.close();
            } catch (IOException e) {
                System.out.println("Error closing file writer: " + e.getMessage());
            }
        }
    }

    public static void readStudentDetails(String filePath) {
        FileReader reader = null;
        try {
            reader = new FileReader(filePath);
            int ch;
            System.out.println("--- Student Details ---");
            while ((ch = reader.read()) != -1) {
                System.out.print((char) ch);
            }
            System.out.println("-----------------------");
        } catch (IOException e) {
            System.out.println("Error reading student details: " + e.getMessage());
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
                System.out.println("Error closing file reader: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        String studentFile = "students.txt";
        String binarySource = "source.bin";
        String binaryDest = "copy_source.bin";

        try (FileOutputStream dummy = new FileOutputStream(binarySource)) {
            dummy.write(new byte[]{1, 2, 3, 4, 5, 6, 7, 8});
        } catch (IOException e) {
            System.out.println("Error creating sample binary file: " + e.getMessage());
        }

        writeStudentDetails(studentFile, "ID: 101, Name: Alice, Course: Computer Science");
        writeStudentDetails(studentFile, "ID: 102, Name: Bob, Course: Electronics");

        readStudentDetails(studentFile);

        copyBinaryFile(binarySource, binaryDest);
    }
}
