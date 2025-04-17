import javax.swing.*;

public class StudentGradeCalculator {

    public static void main(String[] args) {
        try {
            int numberOfSubjects = 0;

            // Ask for number of subjects
            while (true) {
                String input = JOptionPane.showInputDialog("Enter the number of subjects:");
                if (input == null) return; // User cancelled
                try {
                    numberOfSubjects = Integer.parseInt(input);
                    if (numberOfSubjects <= 0) {
                        JOptionPane.showMessageDialog(null, "Please enter a positive number.");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid number.");
                }
            }

            int[] marks = new int[numberOfSubjects];
            int totalMarks = 0;

            // Input marks
            for (int i = 0; i < numberOfSubjects; i++) {
                while (true) {
                    String input = JOptionPane.showInputDialog("Enter marks for subject " + (i + 1) + " (out of 100):");
                    if (input == null) return;
                    try {
                        marks[i] = Integer.parseInt(input);
                        if (marks[i] < 0 || marks[i] > 100) {
                            JOptionPane.showMessageDialog(null, "Marks must be between 0 and 100.");
                        } else {
                            totalMarks += marks[i];
                            break;
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Invalid input! Please enter a number.");
                    }
                }
            }

            double average = (double) totalMarks / numberOfSubjects;
            String grade;

            // Grade Calculation
            if (average >= 90) grade = "A+";
            else if (average >= 80) grade = "A";
            else if (average >= 70) grade = "B";
            else if (average >= 60) grade = "C";
            else if (average >= 50) grade = "D";
            else grade = "F";

            // Display result
            String message = "Total Marks: " + totalMarks +
                    "\nAverage Percentage: " + String.format("%.2f", average) + "%" +
                    "\nGrade: " + grade;

            JOptionPane.showMessageDialog(null, message, "Student Grade Result", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
