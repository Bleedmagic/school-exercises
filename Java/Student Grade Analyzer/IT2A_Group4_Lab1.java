/** *This program is a grade calculator. Enter your name and scores! It'll give you an average, grade, remark.
 * Group 4
 *
 * Authors: Vasquez, Daniel Victor
 *          Gerona, Lorence
 *          Delfin, Jiro
 * Laboratory Exercise 1
 * September 19, 2023
 */

import java.util.Scanner;
import java.text.DecimalFormat;

public class IT2A_Group4_Lab1 {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            DecimalFormat resultFormat = new DecimalFormat("#.##");
            char choice;

            do {
                int count = 0;
                System.out.print("Enter Student Name: ");
                String name = input.nextLine();

                System.out.print("Number of Test Scores: ");
                int size = input.nextInt();
                int[] array = new int[size];

                System.out.println("Enter the " + size + " Test Scores: ");
                for (int i = 0; i < size; i++) {
                    array[i] = input.nextInt();
                }

                System.out.println("");

                // Displays name and inputted scores
                System.out.println("Student Name: " + name);
                System.out.println("The Test Scores are: ");
                for (int score : array) {
                    if (count == 0){
                        System.out.print("\t\t\t");
                    }
                    System.out.printf("%4d ", score);

                    count++;
                    if (count == 5){
                        System.out.println(); //move next line
                        count = 0; //reset counter
                    }
                }

                System.out.println("");

                // Gets Average
                double average = calculateAverage(array);
                System.out.println("Average: "+ resultFormat.format(average));

                // Gets Grade
                char grade = calculateGrade(average);
                System.out.println("Grade: " + grade);

                // Gets Remarks based on Grades
                String remarks = calculateRemarks(grade);
                System.out.println("Remarks: " + remarks);

                System.out.println("");

                do {
                    System.out.print("Try Again? (Y/N): ");
                    choice = input.next().charAt(0);
                    input.nextLine();

                    if (!(choice == 'Y' || choice == 'y' || choice == 'N' || choice == 'n')) {
                     System.out.println("\nInvalid input. Please enter 'Y' or 'N'.");
                     System.out.println();
                    }
                } while (!(choice == 'Y' || choice == 'y' || choice == 'N' || choice == 'n'));
                clearScreen(); // calls the method that clears the screen
            } while (choice == 'Y' || choice == 'y'); // resets if user inputs y
            if (choice == 'N' || choice == 'n') {
                System.out.println("PROGRAM TERMINATED");
            }
        }
    }

    // Computes the average
    public static double calculateAverage(int[] arr)
    {
        double sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum / arr.length;
    }

    // Analyzes grades and return letters to remarks
    public static char calculateGrade(double average) {
        if (average >= 90 && average <= 100)
        {
            return 'A';
        } else if (average >= 80 && average <= 89) {
            return 'B';
        } else if (average >= 70 && average <= 79) {
            return 'C';
        } else if (average >= 60 && average <= 69) {
            return 'D';
        } else if (average >= 0 && average <= 59) {
            return 'F';
        } else {
            return 0;
        }
    }

    // Gives corresponding remarks to grades
    public static String calculateRemarks(char grade) {
        switch (grade) {
            case 'A':
                return "Outstanding";
            case 'B':
                return "Very Satisfactory";
            case 'C':
                return "Satisfactory";
            case 'D':
                return "Fair";
            default:
                return "Needs Improvement";
        }
    }

    // Method to clear screen
    public static void clearScreen()
    {
        System.out.print('\u000C');
        System.out.flush();
    }
}
