import java.util.Objects;
import java.util.Optional;

public class Q3StudentResult {
    static class Result<T extends Number> {
        private final T[] marks;
        private final double maximumMarks;
        private final double passPercentage;

        public Result(T[] marks, double maximumMarks, double passPercentage) {
            this.marks = Objects.requireNonNull(marks, "Marks array cannot be null");
            if (marks.length == 0) {
                throw new IllegalArgumentException("Marks array cannot be empty");
            }
            if (maximumMarks <= 0) {
                throw new IllegalArgumentException("Maximum marks must be positive");
            }
            if (passPercentage < 0 || passPercentage > 100) {
                throw new IllegalArgumentException("Pass percentage must be between 0 and 100");
            }

            for (T mark : marks) {
                Objects.requireNonNull(mark, "A mark cannot be null");
                if (mark.doubleValue() < 0 || mark.doubleValue() > maximumMarks) {
                    throw new IllegalArgumentException("Each mark must be within the valid range");
                }
            }

            this.maximumMarks = maximumMarks;
            this.passPercentage = passPercentage;
        }

        public Optional<T[]> getMarks() {
            return Optional.of(marks);
        }

        public double getAverage() {
            double total = 0.0;
            for (T mark : marks) {
                total += mark.doubleValue();
            }
            return total / marks.length;
        }

        public double getPercentage() {
            return (getAverage() / maximumMarks) * 100.0;
        }

        public boolean hasPassed() {
            return getPercentage() >= passPercentage;
        }
    }

    public static void main(String[] args) {
        Double[] studentMarks = {78.0, 85.0, 90.0};
        Result<Double> result = new Result<>(studentMarks, 100.0, 40.0);

        result.getMarks().ifPresent(marks -> {
            System.out.print("Marks: ");
            for (Double mark : marks) {
                System.out.print(mark + " ");
            }
            System.out.println();
        });

        System.out.printf("Average: %.2f%n", result.getAverage());
        System.out.printf("Percentage: %.2f%%%n", result.getPercentage());
        System.out.println("Result: " + (result.hasPassed() ? "Passed" : "Failed"));
    }
}
