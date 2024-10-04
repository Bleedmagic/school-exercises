/** *A Java program that provides a menu-driven interface for performing various operations on an integer array. 
 * Group 4
 * Authors: Vasquez, Daniel Victor
 *          Gerona, Lorence
 *          Delfin, Jiro
 * Laboratory Exercise 2
 * September 26, 2023
 */

import java.util.Scanner;
import java.util.InputMismatchException;

public class IT2A_Group4_Lab2
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        int[] array = null;
        int arraySize = 0;

        while (true) 
        {
            // Main Menu
            System.out.println("\n=====================================");
            System.out.println("|       ARRAY OPERATIONS MENU       |");
            System.out.println("=====================================");
            System.out.println("| Options:                          |");
            System.out.println("|        [1] Create Array           |");
            System.out.println("|        [2] Insert Elements        |");
            System.out.println("|        [3] Search                 |");
            System.out.println("|        [4] Display                |");
            System.out.println("|        [5] Delete                 |");
            System.out.println("|        [6] Reset                  |");
            System.out.println("|        [0] Terminate              |");
            System.out.println("|                                   |");
            System.out.println("| Pick a choice: [_]                |");
            System.out.println("=====================================");
            
            System.out.print("\n<USER> ");
            
            try
            {
                int choice = input.nextInt();
    
                switch (choice) 
                {
                    case 1: // Create
                    {
                        clearScreen();
                        
                        if (array != null) 
                        {
                            System.out.println("An array has already been created.");
                            break;
                        }
                        
                        int newSize = 0;
                        boolean validSizeEntered = false;
                        
                        System.out.println(":: Create Array Operation ::");
                        
                        while (!validSizeEntered) 
                        {
                            System.out.print("Enter the size of the array (between 5 and 20): ");
                            try 
                            {
                                newSize = input.nextInt();
                                
                                if (newSize >= 5 && newSize <= 20) 
                                {
                                    validSizeEntered = true;
                                } else {
                                    System.out.println("\nInvalid size. Size must be between 5 and 20.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("\nInvalid input. Please enter a valid integer.");
                                input.nextLine();
                            }
                        }
                        
                        arraySize = newSize;
                        array = new int[arraySize];
                        System.out.println("Array created with size " + arraySize);
                        break;
                    }

                    case 2: // Insert
                    {
                        clearScreen();
                        
                        if (array == null) 
                        {
                            System.out.println("Create an array first.");
                            break;
                        }
                        
                        if (isFull(array)) 
                        {
                            System.out.println("Array is already full. Cannot insert more elements.");
                            break;
                        }
                        
                        System.out.println(":: Insert Element Operation ::");
                        System.out.print("Enter elements to insert (Exit with -99): ");
                        int insertPosition = 0;
                        
                        while (true) 
                        {
                            try
                            {
                                int elementToInsert = input.nextInt();
                                
                                if (elementToInsert == -99) 
                                {
                                    break;
                                }
                                
                                if (containsElement(array, elementToInsert)) 
                                {
                                    System.out.println("Element already exists. Enter a different one.");
                                } else {
                                    
                                    while (insertPosition < arraySize && array[insertPosition] != 0) 
                                    {
                                        insertPosition++;
                                    }
                                    
                                    if (insertPosition < arraySize) 
                                    {
                                        array[insertPosition] = elementToInsert;
                                        System.out.println("Element inserted into the array at index " + insertPosition);
                                    } else {
                                        System.out.println("\nNo empty slots available in the array. (Type -99 to Exit)");
                                    }
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a valid integer.");
                                input.nextLine();
                            }
                        }
                        break;
                    }
                        
                    case 3: // Search
                    {
                        clearScreen();

                        if (array == null) 
                        {
                            System.out.println("Create an array first.");
                            break;
                        }
                        
                        if (isEmpty(array)) 
                        {
                            System.out.println("The array is empty. Nothing to search.");
                            break;
                        }
                        
                        int searchKey = 0;
                        boolean validInput = false;
                        
                        System.out.println(":: Search Element Operation ::");
                        
                        while (!validInput) 
                        {
                            System.out.print("Enter the element to find: ");
                            try 
                            {
                                searchKey = input.nextInt();
                                validInput = true;
                            } catch (InputMismatchException e) {
                                System.out.println("\nInvalid input. Please enter a valid integer.");
                                input.nextLine();
                            }
                        }
                        
                        int index = findElementIndex(array, searchKey);
                        
                        if (index != -1) 
                        {
                            System.out.println("Element " + searchKey + " is found at index " + index + " in the array.");
                        } else {
                            System.out.println("Element " + searchKey + " is not found in the array.");
                        }
                        break;
                    }
    
                    case 4: // Display
                    {
                        clearScreen();
                        
                        if (array == null) 
                        {
                            System.out.println("Create an array first.");
                            break;
                        }
                        
                        if (isEmpty(array)) 
                        {
                            System.out.println("The array is empty. Nothing to display.");
                            break;
                        }
                        
                        System.out.println(":: Display Array Operation ::");
                        System.out.println("Array Contents:");
                        displayArray(array);
                        break;
                    }
    
                    case 5: // Delete
                    {
                        clearScreen();
                        
                        if (array == null) {
                            System.out.println("Cannot delete from an empty array.");
                            break;
                        }
                        
                        if (isEmpty(array)) {
                            System.out.println("The array is empty. Nothing to delete.");
                            break;
                        }
                        
                        int elementToRemove = 0;
                        boolean validInput = false;
                        
                        System.out.println(":: Delete Element Operation ::");
                        
                        while (!validInput) {
                            System.out.print("Enter the element to be removed: ");
                            try {
                                elementToRemove = input.nextInt();
                                validInput = true;
                            } catch (InputMismatchException e) {
                                System.out.println("\nInvalid input. Please enter a valid integer.");
                                input.nextLine();
                            }
                        }
                        
                        int removedIndex = deleteElement(array, elementToRemove);
                        
                        if (removedIndex != -1) {
                            System.out.println("Element " + elementToRemove + " removed from index " + removedIndex + " in the array.");
                        } else {
                            System.out.println("Element " + elementToRemove + " not found in the array.");
                        }
                        break;
                    }

                    case 6: // Reset
                    {
                        clearScreen();
                        
                        array = null;
                        arraySize = 0;
                        
                        System.out.println("Program has been reset.");
                        break;
                    }
    
                    case 0: // Terminate
                    {
                        clearScreen();
                        System.out.println("Program has been terminated.");
                        System.out.println("Goodbye.");
                        input.close();
                        return;
                    }
    
                    default:
                    {
                        clearScreen();
                        System.out.println("Invalid choice. Please try again.");
                    }
                }
            } catch(InputMismatchException e) {
                clearScreen();
                System.out.println("Invalid input. Please enter a valid integer.");
                input.nextLine();
            }
        }
    }

    private static boolean containsElement(int[] array, int element) 
    {
        for (int value : array) 
        {
            if (value == element) 
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isFull(int[] array) 
    {
        for (int element : array) 
        {
            if (element == 0) 
            {
                return false;
            }
        }
        return true;
    }
    
    private static boolean isEmpty(int[] array) 
    {
        for (int element : array) 
        {
            if (element != 0) 
            {
                return false;
            }
        }
        return true;
    }
    
    private static int findElementIndex(int[] array, int element) 
    {
        for (int i = 0; i < array.length; i++) 
        {
            if (array[i] == element) 
            {
                return i;
            }
        }
        return -1;
    }
    
    private static void displayArray(int[] array) 
    {
        int columnWidth = 6;
    
        for (int i = 0; i < array.length; i++) 
        {
            System.out.printf("%" + columnWidth + "d ", array[i]);
            if ((i + 1) % 5 == 0 || i == array.length - 1) 
            {
                System.out.println();
            }
        }
    }
    
    private static int deleteElement(int[] array, int element) 
    {
        int removedIndex = -1;
        for (int i = 0; i < array.length; i++) 
        {
            if (array[i] == element) 
            {
                removedIndex = i;
                break;
            }
        }

        if (removedIndex != -1) 
        {
            for (int i = removedIndex; i < array.length - 1; i++) 
            {
                array[i] = array[i + 1];
            }
            array[array.length - 1] = 0;
        }

        return removedIndex;
    }
    
    private static void clearScreen()
    {
        System.out.print('\u000C');
    }
}
