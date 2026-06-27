import java.util.ArrayList;
import java.util.Scanner;

class Student {

    private String name;
    private int marks;

    public Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    // Grade Calculation
    public String getGrade() {

        if (marks >= 90)
            return "A";
        else if (marks >= 80)
            return "B";
        else if (marks >= 70)
            return "C";
        else if (marks >= 60)
            return "D";
        else
            return "F";
    }

    // Pass / Fail
    public String getResult() {

        if (marks >= 40)
            return "PASS";
        else
            return "FAIL";
    }
}

public class StudentGradeTracker {

    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    // Add Student
    public static void addStudent() {

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        int marks;

        while (true) {

            System.out.print("Enter Marks (0-100): ");
            marks = sc.nextInt();
            sc.nextLine();

            if (marks >= 0 && marks <= 100)
                break;

            System.out.println("Invalid Marks! Please Enter Between 0 and 100.");
        }

        students.add(new Student(name, marks));

        System.out.println("\nStudent Added Successfully!");
    }

    // Display Students
    public static void displayStudents() {

        if (students.isEmpty()) {

            System.out.println("\nNo Student Records Available.");
            return;
        }

        System.out.println("\n================ STUDENT RECORDS ================");

        System.out.printf("%-5s %-20s %-10s %-10s %-10s\n",
                "No", "Name", "Marks", "Grade", "Result");

        System.out.println("--------------------------------------------------------------");

        int i = 1;

        for (Student s : students) {

            System.out.printf("%-5d %-20s %-10d %-10s %-10s\n",
                    i++,
                    s.getName(),
                    s.getMarks(),
                    s.getGrade(),
                    s.getResult());
        }

        System.out.println("==============================================================");
    }

    // Summary Report
    public static void summaryReport() {

        if (students.isEmpty()) {

            System.out.println("\nNo Student Records Available.");
            return;
        }

        int total = 0;

        int highest = students.get(0).getMarks();
        int lowest = students.get(0).getMarks();

        String topper = students.get(0).getName();
        String lowStudent = students.get(0).getName();

        for (Student s : students) {

            total += s.getMarks();

            if (s.getMarks() > highest) {

                highest = s.getMarks();
                topper = s.getName();
            }

            if (s.getMarks() < lowest) {

                lowest = s.getMarks();
                lowStudent = s.getName();
            }
        }

        double average = (double) total / students.size();

        System.out.println("\n============== SUMMARY REPORT ==============");

        System.out.println("Total Students : " + students.size());
        System.out.printf("Average Marks  : %.2f\n", average);
        System.out.println("Highest Marks  : " + highest + " (" + topper + ")");
        System.out.println("Lowest Marks   : " + lowest + " (" + lowStudent + ")");

        System.out.println("============================================");
    }

    // Search Student
    public static void searchStudent() {

        if (students.isEmpty()) {

            System.out.println("\nNo Student Records Available.");
            return;
        }

        System.out.print("Enter Student Name to Search: ");
        String name = sc.nextLine();

        boolean found = false;

        for (Student s : students) {

            if (s.getName().equalsIgnoreCase(name)) {

                System.out.println("\nStudent Found");

                System.out.println("Name   : " + s.getName());
                System.out.println("Marks  : " + s.getMarks());
                System.out.println("Grade  : " + s.getGrade());
                System.out.println("Result : " + s.getResult());

                found = true;
                break;
            }
        }

        if (!found)
            System.out.println("Student Not Found.");
    }

    // Edit Student
    public static void editStudent() {

        if (students.isEmpty()) {

            System.out.println("\nNo Student Records Available.");
            return;
        }

        System.out.print("Enter Student Name to Edit: ");
        String name = sc.nextLine();

        boolean found = false;

        for (Student s : students) {

            if (s.getName().equalsIgnoreCase(name)) {

                System.out.println("Current Marks : " + s.getMarks());

                int marks;

                while (true) {

                    System.out.print("Enter New Marks (0-100): ");
                    marks = sc.nextInt();
                    sc.nextLine();

                    if (marks >= 0 && marks <= 100)
                        break;

                    System.out.println("Invalid Marks!");
                }

                s.setMarks(marks);

                System.out.println("Student Updated Successfully.");

                found = true;
                break;
            }
        }

        if (!found)
            System.out.println("Student Not Found.");
    }

    // Delete Student
    public static void deleteStudent() {

        if (students.isEmpty()) {

            System.out.println("\nNo Student Records Available.");
            return;
        }

        System.out.print("Enter Student Name to Delete: ");
        String name = sc.nextLine();

        boolean found = false;

        for (int i = 0; i < students.size(); i++) {

            if (students.get(i).getName().equalsIgnoreCase(name)) {

                students.remove(i);

                System.out.println("Student Deleted Successfully.");

                found = true;
                break;
            }
        }

        if (!found)
            System.out.println("Student Not Found.");
    }

    // Main Method
    public static void main(String[] args) {

        int choice;

        do {

            System.out.println("\n==========================================");
            System.out.println("        STUDENT GRADE TRACKER");
            System.out.println("==========================================");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Edit Student Marks");
            System.out.println("5. Delete Student");
            System.out.println("6. Summary Report");
            System.out.println("7. Exit");
            System.out.println("==========================================");

            System.out.print("Enter Your Choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    displayStudents();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    editStudent();
                    break;

                case 5:
                    deleteStudent();
                    break;

                case 6:
                    summaryReport();
                    break;

                case 7:
                    System.out.println("\nThank You for Using Student Grade Tracker!");
                    break;

                default:
                    System.out.println("\nInvalid Choice! Please Try Again.");
            }

        } while (choice != 7);

        sc.close();
    }
}
