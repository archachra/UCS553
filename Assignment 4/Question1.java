import java.util.*;

class Course {
    private String courseCode;
    private String courseName;
    private int capacity;

    private HashSet<String> enrolledStudents;
    private ArrayList<String> prerequisites;
    private LinkedList<String> waitlist;

    public Course(String courseCode, String courseName, int capacity) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.capacity = capacity;
        this.enrolledStudents = new HashSet<>();
        this.prerequisites = new ArrayList<>();
        this.waitlist = new LinkedList<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCapacity() {
        return capacity;
    }

    public HashSet<String> getEnrolledStudents() {
        return enrolledStudents;
    }

    public ArrayList<String> getPrerequisites() {
        return prerequisites;
    }

    public LinkedList<String> getWaitlist() {
        return waitlist;
    }

    public void addPrerequisite(String prereqCode) {
        if (!prerequisites.contains(prereqCode)) {
            prerequisites.add(prereqCode);
        }
    }

    public boolean isFull() {
        return enrolledStudents.size() >= capacity;
    }

    public boolean enroll(String studentId) {
        if (isFull()) return false;
        return enrolledStudents.add(studentId);
    }

    public boolean unenroll(String studentId) {
        return enrolledStudents.remove(studentId);
    }

    public void addToWaitlist(String studentId) {
        if (!waitlist.contains(studentId) && !enrolledStudents.contains(studentId)) {
            waitlist.addLast(studentId);
        }
    }

    public String pollWaitlist() {
        return waitlist.pollFirst();
    }

    public boolean removeFromWaitlist(String studentId) {
        return waitlist.remove(studentId);
    }

    @Override
    public String toString() {
        return String.format("%s (%s) | Enrolled: %d/%d %s | Waitlist: %s",
                courseCode, courseName, enrolledStudents.size(), capacity, enrolledStudents, waitlist);
    }
}

class RegistrationPortal {
    private HashMap<String, Course> courses = new HashMap<>();
    private HashMap<String, Set<String>> studentCompletedCourses = new HashMap<>();

    public void addCourse(String courseCode, String courseName, int capacity, List<String> prereqs) {
        Course course = new Course(courseCode, courseName, capacity);
        if (prereqs != null) {
            for (String p : prereqs) {
                course.addPrerequisite(p);
            }
        }
        courses.put(courseCode, course);
    }

    public void addStudentCompletedCourse(String studentId, String courseCode) {
        studentCompletedCourses.computeIfAbsent(studentId, k -> new HashSet<>()).add(courseCode);
    }

    public Course getCourse(String courseCode) {
        return courses.get(courseCode);
    }

    public void register(String studentId, String courseCode) {
        Course course = courses.get(courseCode);
        if (course == null) {
            System.out.println("[FAILED] Course " + courseCode + " does not exist.");
            return;
        }

        if (course.getEnrolledStudents().contains(studentId)) {
            System.out.println("[FAILED] Student " + studentId + " is already enrolled in " + courseCode + ".");
            return;
        }

        if (course.getWaitlist().contains(studentId)) {
            System.out.println("[FAILED] Student " + studentId + " is already in waitlist for " + courseCode + ".");
            return;
        }

        Set<String> completed = studentCompletedCourses.getOrDefault(studentId, Collections.emptySet());
        for (String prereq : course.getPrerequisites()) {
            if (!completed.contains(prereq)) {
                System.out.println("[REJECTED] Student " + studentId + " missing prerequisite: " + prereq + " for course " + courseCode);
                return;
            }
        }

        if (!course.isFull()) {
            course.enroll(studentId);
            System.out.println("[SUCCESS] Enrolled " + studentId + " in " + courseCode + ".");
        } else {
            course.addToWaitlist(studentId);
            System.out.println("[WAITLISTED] " + courseCode + " is full! Added " + studentId + " to waitlist (Position: " + course.getWaitlist().size() + ").");
        }
    }

    public void dropCourse(String studentId, String courseCode) {
        Course course = courses.get(courseCode);
        if (course == null) return;

        if (course.getWaitlist().contains(studentId)) {
            course.removeFromWaitlist(studentId);
            System.out.println("[DROPPED] Removed " + studentId + " from waitlist of " + courseCode + ".");
            return;
        }

        if (!course.getEnrolledStudents().contains(studentId)) {
            System.out.println("[ERROR] Student " + studentId + " is not enrolled in " + courseCode + ".");
            return;
        }

        course.unenroll(studentId);
        System.out.println("[DROPPED] Student " + studentId + " dropped " + courseCode + ".");

        if (!course.getWaitlist().isEmpty()) {
            String promotedStudent = course.pollWaitlist();
            course.enroll(promotedStudent);
            System.out.println("[AUTOMATED PROMOTION] Promoted " + promotedStudent + " from waitlist into " + courseCode + "!");
        }
    }

    public void displayCourses() {
        System.out.println("\n--- Current Course Status ---");
        for (Course c : courses.values()) {
            System.out.println(c);
        }
        System.out.println("-----------------------------\n");
    }
}

public class Question1 {
    public static void main(String[] args) {
        RegistrationPortal portal = new RegistrationPortal();

        portal.addCourse("CS101", "Intro to Programming", 2, Collections.emptyList());
        portal.addCourse("CS201", "Data Structures", 2, List.of("CS101"));

        portal.addStudentCompletedCourse("S101", "CS101");
        portal.addStudentCompletedCourse("S102", "CS101");
        portal.addStudentCompletedCourse("S104", "CS101");

        System.out.println("1. Prerequisite check:");
        portal.register("S103", "CS201");

        System.out.println("\n2. Successful enrollments up to capacity:");
        portal.register("S101", "CS201");
        portal.register("S102", "CS201");

        System.out.println("\n3. Overflow handling (Waitlist LinkedList):");
        portal.register("S104", "CS201");

        portal.displayCourses();

        System.out.println("4. Dropping course & Automated Waitlist Promotion:");
        portal.dropCourse("S101", "CS201");

        portal.displayCourses();
    }
}
