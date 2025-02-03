import java.util.*;

class Student {
    private String studentID;
    private String name;
    private String dob;
    private String contactInfo;
    private HashMap<String, String> grades; // CourseID -> Grade

    public Student(String studentID, String name, String dob, String contactInfo) {
        this.studentID = studentID;
        this.name = name;
        this.dob = dob;
        this.contactInfo = contactInfo;
        this.grades = new HashMap<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public void addGrade(String courseID, String grade) {
        grades.put(courseID, grade);
    }

    public HashMap<String, String> getGrades() {
        return grades;
    }

    public void displayStudentInfo() {
        System.out.println("Student ID: " + studentID);
        System.out.println("Name: " + name);
        System.out.println("DOB: " + dob);
        System.out.println("Contact: " + contactInfo);
        System.out.println("Grades: " + grades);
    }
}

class Course {
    private String courseID;
    private String courseName;
    private int credits;

    public Course(String courseID, String courseName, int credits) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.credits = credits;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void displayCourseInfo() {
        System.out.println("Course ID: " + courseID + " | Name: " + courseName + " | Credits: " + credits);
    }
}

class StudentManagementSystem {
    private static HashMap<String, Student> students = new HashMap<>();
    private static HashMap<String, Course> courses = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Add Course");
            System.out.println("4. View Courses");
            System.out.println("5. Assign Grade");
            System.out.println("6. Generate Report Card");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    addCourse();
                    break;
                case 4:
                    viewCourses();
                    break;
                case 5:
                    assignGrade();
                    break;
                case 6:
                    generateReportCard();
                    break;
                case 7:
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void addStudent() {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter DOB (YYYY-MM-DD): ");
        String dob = scanner.nextLine();
        System.out.print("Enter Contact Info: ");
        String contact = scanner.nextLine();

        students.put(id, new Student(id, name, dob, contact));
        System.out.println("Student added successfully!");
    }

    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("\n--- Student List ---");
        for (Student student : students.values()) {
            student.displayStudentInfo();
            System.out.println("----------------------");
        }
    }

    private static void addCourse() {
        System.out.print("Enter Course ID: ");
        String courseID = scanner.nextLine();
        System.out.print("Enter Course Name: ");
        String courseName = scanner.nextLine();
        System.out.print("Enter Credits: ");
        int credits = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        courses.put(courseID, new Course(courseID, courseName, credits));
        System.out.println("Course added successfully!");
    }

    private static void viewCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
            return;
        }
        System.out.println("\n--- Available Courses ---");
        for (Course course : courses.values()) {
            course.displayCourseInfo();
            System.out.println("----------------------");
        }
    }

    private static void assignGrade() {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.nextLine();
        if (!students.containsKey(studentID)) {
            System.out.println("Student not found!");
            return;
        }

        System.out.print("Enter Course ID: ");
        String courseID = scanner.nextLine();
        if (!courses.containsKey(courseID)) {
            System.out.println("Course not found!");
            return;
        }

        System.out.print("Enter Grade (A/B/C/D/F): ");
        String grade = scanner.nextLine();

        students.get(studentID).addGrade(courseID, grade);
        System.out.println("Grade assigned successfully!");
    }

    private static void generateReportCard() {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.nextLine();
        if (!students.containsKey(studentID)) {
            System.out.println("Student not found!");
            return;
        }

        Student student = students.get(studentID);
        System.out.println("\n--- Report Card for " + student.getName() + " ---");
        for (Map.Entry<String, String> entry : student.getGrades().entrySet()) {
            String courseID = entry.getKey();
            String grade = entry.getValue();
            String courseName = courses.get(courseID).getCourseName();
            System.out.println(courseName + " (" + courseID + "): " + grade);
        }
    }
}
