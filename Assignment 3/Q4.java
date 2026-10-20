import java.util.Scanner;

class InvalidMarksException extends Exception {

    public InvalidMarksException(String message) {
        super(message);
    }
}
public class Q4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter number of subjects: ");
            int n = Integer.parseInt(sc.nextLine());
            int total = 0;
            for (int i = 1; i <= n; i++) {
                System.out.print("Enter marks for subject " + i + ": ");
                int marks = Integer.parseInt(sc.nextLine());
                if (marks < 0 || marks > 100) {
                    throw new InvalidMarksException(
                        "Marks must be between 0 and 100"
                    );
                }
                total += marks;
            }
            double percentage = (double) total / n;
            String grade;
            if (percentage >= 90) {
                grade = "A";
            }
            else if (percentage >= 80) {
                grade = "B";
            }
            else if (percentage >= 70) {
                grade = "C";
            }
            else if (percentage >= 60) {
                grade = "D";
            }
            else {
                grade = "F";
            }
            System.out.println("\nTotal = " + total);
            System.out.println("Percentage = " + percentage);
            System.out.println("Grade = " + grade);
        }
        catch (NumberFormatException e) {
            System.out.println("Error: Enter valid numeric marks");
        }
           catch (InvalidMarksException e) {
            System.out.println("Error: " + e.getMessage());
        }
          finally {
            sc.close();
        }
    }
}