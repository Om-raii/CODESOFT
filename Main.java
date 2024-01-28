import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Class to represent a course
class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private int enrolledStudents;

    public Course(String code, String title, String description, int capacity) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolledStudents = 0;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getEnrolledStudents() {
        return enrolledStudents;
    }

    public boolean enrollStudent() {
        if (enrolledStudents < capacity) {
            enrolledStudents++;
            return true;
        }
        return false;
    }

    public void removeStudent() {
        if (enrolledStudents > 0) {
            enrolledStudents--;
        }
    }
}

// Class to represent a student
class Student {
    private int id;
    private String name;
    private List<Course> registeredCourses;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(Course course) {
        if (course.enrollStudent()) {
            registeredCourses.add(course);
            System.out.println(name + " registered for course: " + course.getCode());
        } else {
            System.out.println("Sorry, the course " + course.getCode() + " is full.");
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.remove(course)) {
            course.removeStudent();
            System.out.println(name + " dropped course: " + course.getCode());
        } else {
            System.out.println(name + " is not registered for course: " + course.getCode());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Create sample courses
        Course course1 = new Course("OOPS101", "Object oriented language", "An introduction to Java programming.", 30);
        Course course2 = new Course("MTH201", "Probability", "A course on Statatics.", 25);
        Course course3 = new Course("ENG102", "English Composition", "A course on academic writing and composition.", 20);

        // Create sample students
        Student student1 = new Student(1, "Om");
        Student student2 = new Student(2, "Rai");

        // Student registration
        student1.registerCourse(course1);
        student1.registerCourse(course2);
        student2.registerCourse(course2);
        student2.registerCourse(course3);

        // Displaying registered courses for each student
        displayRegisteredCourses(student1);
        displayRegisteredCourses(student2);

        // Student dropping course
        student1.dropCourse(course1);
        student2.dropCourse(course3);

        // Displaying registered courses after dropping
        displayRegisteredCourses(student1);
        displayRegisteredCourses(student2);
    }

    // Helper method to display registered courses for a student
    private static void displayRegisteredCourses(Student student) {
        System.out.println(student.getName() + "'s registered courses:");
        for (Course course : student.getRegisteredCourses()) {
            System.out.println("Course: " + course.getCode() + ", Title: " + course.getTitle());
        }
        System.out.println();
    }
}
