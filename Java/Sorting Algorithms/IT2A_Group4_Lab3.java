/** *A Java program that lets the user perform bubble, selection, and insertion sorting operations on an integer array. 
 * Group 4
 * Authors: Vasquez, Daniel Victor
 *          Gerona, Lorence
 *          Delfin, Jiro
 * Laboratory Exercise 3
 * October 14, 2023
 */

import java.util.Scanner;
import java.util.InputMismatchException;

public class IT2A_Group4_Lab3
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);

        int arraySize = getArraySize(input);

        System.out.println("\nEnter " + arraySize + " array elements!");
        int[] userArray = inputArray(input, arraySize);

        boolean isTerminated = false;

        while (!isTerminated) 
        {
            System.out.println("\n====================================");
            System.out.println("|      SORTING ALGORITHM MENU      |");
            System.out.println("====================================");
            System.out.println("| Options:                         |");
            System.out.println("|        [1] Bubble Sort           |");
            System.out.println("|        [2] Selection Sort        |");
            System.out.println("|        [3] Insertion Sort        |");
            System.out.println("|        [4] Terminate             |");
            System.out.println("|                                  |");
            System.out.println("| Pick a choice: [_]               |");
            System.out.println("====================================");

            System.out.print("\n<USER> ");

            try 
            {
                int sortingChoice = input.nextInt();

                switch (sortingChoice) 
                {
                    case 1:
                        clearScreen();
                        bubbleSort(userArray, input);
                        break;
                    case 2:
                        clearScreen();
                        selectionSort(userArray, input);
                        break;

                    case 3:
                        clearScreen();
                        insertionSort(userArray, input);
                        break;

                    case 4:
                        clearScreen();
                        System.out.println("\nThanks for stopping by!");
                        
                        boolean validChoice = false;
                        
                        while (!validChoice) 
                        {
                            System.out.print("\nDo you want to try again (Y/y) or exit (N/n)? ");
                            char continueChoice = input.next().charAt(0);
                            
                            if (continueChoice == 'Y' || continueChoice == 'y') 
                            {
                                clearScreen();
                                arraySize = getArraySize(input);
                                System.out.println("\nEnter " + arraySize + " array elements!");
                                userArray = inputArray(input, arraySize);
                                validChoice = true;
                            } else if (continueChoice == 'N' || continueChoice == 'n') {
                                clearScreen();
                                System.out.println("\nProgram has been terminated.");
                                System.out.println("Goodbye.");
                                isTerminated = true;
                                validChoice = true;
                            } else {
                                clearScreen();
                                System.out.println("\nInvalid choice. Please enter 'Y/y' or 'N/n'.");
                            }
                        }
                        break;

                    default:
                        clearScreen();
                        System.out.println("\nInvalid choice. Please try again.");
                        break;
                }
            } 
            catch (InputMismatchException e) 
            {
                clearScreen();
                System.out.println("\nInvalid input. Please enter a valid integer.");
                input.nextLine();
            }
        }

        input.close();
    }

    private static int getArraySize(Scanner input) 
    {
        int arraySize = 0;

        System.out.println("\nWelcome to our Java Sorting Array Program!");
        
        while (true) 
        {
            try 
            {
                System.out.print("\nEnter the array size (between 5 and 15): ");
                arraySize = input.nextInt();

                if (arraySize >= 5 && arraySize <= 15) 
                {
                    break;
                } 
                else 
                {
                    System.out.println("\nInvalid size. Please enter a size between 5 and 15.");
                }
            } 
            catch (InputMismatchException e) 
            {
                System.out.println("\nInvalid input. Please enter a valid integer size.");
                input.nextLine();
            }
        }

        return arraySize;
    }

    private static int[] inputArray(Scanner input, int arraySize) 
    {
        int[] userArray = new int[arraySize];

        for (int i = 0; i < arraySize; i++) 
        {
            boolean validInput = false;
            while (!validInput) 
            {
                try 
                {
                    System.out.print("Enter element #" + (i + 1) + ": ");
                    int element = input.nextInt();

                    boolean isDuplicate = false;
                    for (int j = 0; j < i; j++) 
                    {
                        if (userArray[j] == element) 
                        {
                            isDuplicate = true;
                            break;
                        }
                    }

                    if (!isDuplicate) 
                    {
                        userArray[i] = element;
                        validInput = true;
                    } 
                    else 
                    {
                        System.out.println("\nDuplicate element. Please enter a unique element.");
                    }
                } 
                catch (InputMismatchException e) 
                {
                    System.out.println("\nInvalid input. Please enter a valid integer element.");
                    input.nextLine();
                }
            }
        }

        clearScreen();
        return userArray;
    }

    private static void displayArray(int[] arrayToDisplay) 
    {
        for (int element : arrayToDisplay) 
        {
            System.out.printf("%5d ", element);
        }
        System.out.println();
    }

    private static void bubbleSort(int[] array, Scanner input) 
    {
        int n = array.length;
        int[] copy = array.clone();
        System.out.println("\n\t\t\t::BUBBLE SORT::");
        System.out.print("\nGiven Array Elements: ");
        displayArray(copy);

        System.out.println();
        int iteration = 1;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) 
        {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) 
            {
                if (copy[j] > copy[j + 1]) 
                {
                    int temp = copy[j];
                    copy[j] = copy[j + 1];
                    copy[j + 1] = temp;

                    swapped = true;
                }
            }

            System.out.print(" " + iteration + ". ");
            displayArray(copy);

            iteration++;

            if (!swapped) 
            {
                break;
            }
        }

        System.out.print("\nThe Sorted Array Elements: ");
        displayArray(copy);
        pressAnyKey(input);
    }

    private static void selectionSort(int[] array, Scanner input) 
    {
        int n = array.length;
        int[] copy = array.clone();
        System.out.println("\n\t\t\t::SELECTION SORT::");
        System.out.print("\nGiven Array Elements: ");
        displayArray(copy);

        System.out.println();
        int iteration = 1;

        for (int i = 0; i < n - 1; i++) 
        {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) 
            {
                if (copy[j] < copy[minIndex]) 
                {
                    minIndex = j;
                }
            }

            int temp = copy[i];
            copy[i] = copy[minIndex];
            copy[minIndex] = temp;

            System.out.print(" " + iteration + ". ");
            displayArray(copy);

            iteration++;
        }

        System.out.print("\nThe Sorted Array Elements: ");
        displayArray(copy);
        pressAnyKey(input);
    }

    private static void insertionSort(int[] array, Scanner input) 
    {
        int n = array.length;
        int[] copy = array.clone();
        System.out.println("\n\t\t\t::INSERTION SORT::");
        System.out.print("\nGiven Array Elements: ");
        displayArray(copy);

        System.out.println();
        int iteration = 1;

        for (int i = 1; i < n; i++) 
        {
            int key = copy[i];
            int j = i - 1;
            while (j >= 0 && copy[j] > key) 
            {
                copy[j + 1] = copy[j];
                j--;
            }
            copy[j + 1] = key;

            System.out.print(" " + iteration + ". ");
            displayArray(copy);

            iteration++;
        }

        System.out.print("\nThe Sorted Array Elements: ");
        displayArray(copy);
        pressAnyKey(input);
    }

    private static void pressAnyKey(Scanner input) 
    {
        System.out.print("\nPress any key to continue...");
        input.nextLine();
        input.nextLine();
        clearScreen();
    }

    private static void clearScreen() 
    {
        System.out.print('\u000C');
    }
}
