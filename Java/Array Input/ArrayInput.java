
/**
 * Write a program in which you are going to create an array with variable input using methods.
 *
 * @author Daniel Vasquez
 * @version 09/12/2023
 */

import java.util.Scanner;

public class ArrayInput {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("Enter the size of the array: ");
            int size = input.nextInt();

            int[] array = new int[size];

            System.out.println("Enter array elements:");
            for (int i = 0; i < size; i++) {
                array[i] = input.nextInt();
            }

            System.out.println("Elements of array: ");
            printElements(array);

            int sum = getSum(array);
            System.out.println("Sum = " + sum);
        }

    }

    static void printElements(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    static int getSum(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return sum;
    }
}
