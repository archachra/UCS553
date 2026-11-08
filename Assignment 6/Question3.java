import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    private int employeeId;
    private String name;
    private String department;
    private double salary;

    public Employee(int employeeId, String name, String department, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return String.format("Employee[ID=%d, Name=%s, Department=%s, Salary=%.2f]",
                employeeId, name, department, salary);
    }
}

public class Question3 {
    public static void serializeEmployees(String filePath, List<Employee> employees) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(employees);
            System.out.println("Employees serialized successfully.");
        } catch (IOException e) {
            System.out.println("Error during serialization: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Employee> deserializeEmployees(String filePath) {
        List<Employee> employees = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            employees = (List<Employee>) ois.readObject();
            System.out.println("Employees deserialized successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error during deserialization: " + e.getMessage());
        }
        return employees;
    }

    public static void main(String[] args) {
        String filePath = "employees.ser";

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(101, "Alice Smith", "Engineering", 85000.0));
        employeeList.add(new Employee(102, "Bob Jones", "Marketing", 62000.0));
        employeeList.add(new Employee(103, "Charlie Brown", "Finance", 73000.0));

        serializeEmployees(filePath, employeeList);

        List<Employee> retrievedEmployees = deserializeEmployees(filePath);

        if (retrievedEmployees != null) {
            System.out.println("--- Retrieved Employee Records ---");
            for (Employee emp : retrievedEmployees) {
                System.out.println(emp);
            }
        }
    }
}
