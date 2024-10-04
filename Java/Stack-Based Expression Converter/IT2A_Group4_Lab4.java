/** *A Java program that allows users to perform stack-based conversions through a menu-driven interface.
 * Group 4
 * Authors: Vasquez, Daniel Victor
 *          Gerona, Lorence
 *          Delfin, Jiro
 * Laboratory Exercise 4
 * November 20, 2023
 */

import java.util.Scanner;
import java.util.Stack;

public class IT2A_Group4_Lab4 {

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        int userChoice;

        do {
            displayMenu();

            System.out.print("\n<USER> ");

            if (inputScanner.hasNextInt()) {
                userChoice = inputScanner.nextInt();
                inputScanner.nextLine();

                switch (userChoice) {
                    case 1:
                        clearScreen();
                        convertInfixToPostfix(inputScanner);
                        break;
                    case 2:
                        clearScreen();
                        convertInfixToPrefix(inputScanner);
                        break;
                    case 3:
                        clearScreen();
                        convertPostfixToInfix(inputScanner);
                        break;
                    case 0:
                        clearScreen();
                        System.out.println("\nProgram has been terminated.");
                        System.out.println("Goodbye.");
                        break;
                    default:
                        clearScreen();
                        System.out.println("\nInvalid choice. Please read the choice carefully.");
                }
            } else {
                clearScreen();
                System.out.println("\nInvalid input. Please enter a valid number.");
                inputScanner.nextLine();
                userChoice = -1;
            }
        } while (userChoice != 0);

        inputScanner.close();
    }

    private static void convertInfixToPostfix(Scanner inputScanner) {
        String conversionHeader = "\n\t\t\t::: Infix to Postfix Conversion :::";
        System.out.println(conversionHeader);

        do {
            boolean validExpression = false;

            do {
                System.out.print("\nEnter an infix expression: ");
                String infixExpression = inputScanner.nextLine();

                if (!isValidInfixExpression(infixExpression) || infixExpression.length() < 3) {
                    System.out.println("\nInvalid infix expression. \nPlease enter a valid expression with at least two operands and one operator.");
                    toContinue(inputScanner, conversionHeader);
                } else {
                    validExpression = true;

                    Stack<Character> operatorStack = new Stack<>();
                    StringBuilder postfixExpression = new StringBuilder();

                    for (int i = 0; i < infixExpression.length(); i++) {
                        char currentChar = infixExpression.charAt(i);

                        if (Character.isDigit(currentChar) || Character.isLetter(currentChar)) {
                            postfixExpression.append(currentChar);
                        } else if (currentChar == '(') {
                            operatorStack.push(currentChar);
                        } else if (currentChar == ')') {
                            while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                                postfixExpression.append(operatorStack.pop());
                            }

                            if (!operatorStack.isEmpty()) {
                                operatorStack.pop();
                            }
                        } else if (isOperator(currentChar)) {
                            while (!operatorStack.isEmpty() && hasHigherPrecedence(operatorStack.peek(), currentChar)) {
                                postfixExpression.append(operatorStack.pop());
                            }

                            operatorStack.push(currentChar);
                        }
                    }

                    while (!operatorStack.isEmpty()) {
                        postfixExpression.append(operatorStack.pop());
                    }

                    System.out.println("\nPostfix expression: " + postfixExpression.toString());
                }
            } while (!validExpression);

        } while (tryAgain(inputScanner, conversionHeader));
    }

    private static void convertInfixToPrefix(Scanner inputScanner) {
        String conversionHeader = "\n\t\t\t::: Infix to Prefix Conversion :::";
        System.out.println(conversionHeader);

        do {
            boolean validExpression = false;

            do {
                System.out.print("\nEnter an infix expression: ");
                String infixExpression = inputScanner.nextLine();

                if (!isValidInfixExpression(infixExpression) || infixExpression.length() < 3) {
                    System.out.println("\nInvalid infix expression. \nPlease enter a valid expression with at least two operands and one operator.");
                    toContinue(inputScanner, conversionHeader);
                } else {
                    validExpression = true;

                    Stack<Character> operatorStack = new Stack<>();
                    StringBuilder prefixExpression = new StringBuilder();

                    infixExpression = reverse(infixExpression);

                    for (int i = 0; i < infixExpression.length(); i++) {
                        char currentChar = infixExpression.charAt(i);

                        if (Character.isDigit(currentChar) || Character.isLetter(currentChar)) {
                            prefixExpression.append(currentChar);
                        } else if (currentChar == ')') {
                            operatorStack.push(currentChar);
                        } else if (currentChar == '(') {
                            while (!operatorStack.isEmpty() && operatorStack.peek() != ')') {
                                prefixExpression.append(operatorStack.pop());
                            }

                            if (!operatorStack.isEmpty()) {
                                operatorStack.pop();
                            }
                        } else if (isOperator(currentChar)) {
                            while (!operatorStack.isEmpty() && hasHigherPrecedence(operatorStack.peek(), currentChar)) {
                                prefixExpression.append(operatorStack.pop());
                            }

                            operatorStack.push(currentChar);
                        }
                    }

                    while (!operatorStack.isEmpty()) {
                        prefixExpression.append(operatorStack.pop());
                    }

                    prefixExpression = new StringBuilder(reverse(prefixExpression.toString()));

                    System.out.println("\nPrefix expression: " + prefixExpression.toString());
                }
            } while (!validExpression);

        } while (tryAgain(inputScanner, conversionHeader));
    }

    private static void convertPostfixToInfix(Scanner inputScanner) {
        String conversionHeader = "\n\t\t\t::: Postfix to Infix Conversion :::";
        System.out.println(conversionHeader);
    
        boolean repeatConversion;
    
        do {
            repeatConversion = false;
    
            System.out.print("\nEnter a postfix expression: ");
            String postfixExpression = inputScanner.nextLine().replaceAll("\\s", "");
    
            if (isValidPostfixExpression(postfixExpression) && postfixExpression.length() > 1) {
                Stack<String> operandStack = new Stack<>();
    
                for (int i = 0; i < postfixExpression.length(); i++) {
                    char currentChar = postfixExpression.charAt(i);
    
                    if (Character.isDigit(currentChar) || Character.isLetter(currentChar)) {
                        operandStack.push(String.valueOf(currentChar));
                    } else {
                        if (operandStack.size() < 2) {
                            System.out.println("\nInvalid postfix expression. \nPlease enter a valid expression with the 'operand-operand-operator' pattern.");
                            toContinue(inputScanner, conversionHeader);
                            repeatConversion = true;
                            break;
                        }
    
                        String operand2 = operandStack.pop();
                        String operand1 = operandStack.pop();
                        String expression = "(" + operand1 + " " + currentChar + " " + operand2 + ")";
                        operandStack.push(expression);
                    }
                }
    
                if (!repeatConversion) {
                    if (operandStack.size() == 1) {
                        System.out.println("\nInfix expression: " + operandStack.pop());
                    } else {
                        System.out.println("\nInvalid postfix expression. \nPlease enter a valid expression with the 'operand-operand-operator' pattern.");
                        toContinue(inputScanner, conversionHeader);
                        repeatConversion = true;
                    }
    
                    if (tryAgain(inputScanner, conversionHeader)) {
                        repeatConversion = true;
                    }
                }
            } else {
                System.out.println("\nInvalid postfix expression. \nPlease enter a valid expression with at least two operands and one operator.");
                toContinue(inputScanner, conversionHeader);
                repeatConversion = true;
            }
    
        } while (repeatConversion);
    }

    private static boolean isValidInfixExpression(String expression) {
        Stack<Character> stack = new Stack<>();
        boolean expectingOperand = true;

        for (char c : expression.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                if (!expectingOperand) {
                    return false;
                }
                expectingOperand = !expectingOperand;
            } else if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
                expectingOperand = true;
            } else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty() || !isMatchingPair(stack.pop(), c)) {
                    return false;
                }
                expectingOperand = false;
            } else if (isOperator(c)) {
                if (expectingOperand) {
                    return false;
                }
                expectingOperand = true;
            }
        }

        return !expectingOperand && stack.isEmpty();
    }

    private static boolean isValidPostfixExpression(String postfixExpression) {
        Stack<String> operandStack = new Stack<>();

        for (char currentChar : postfixExpression.toCharArray()) {
            if (Character.isDigit(currentChar) || Character.isLetter(currentChar)) {
                operandStack.push(String.valueOf(currentChar));
            } else if (isOperator(currentChar)) {
                if (operandStack.size() < 2) {
                    return false;
                }
                operandStack.pop();
                operandStack.pop();
                operandStack.push("temp");
            }
        }

        return operandStack.size() == 1;
    }

    private static boolean isMatchingPair(char left, char right) {
        return (left == '(' && right == ')') ||
                (left == '[' && right == ']') ||
                (left == '{' && right == '}');
    }

    private static String reverse(String str) {
        StringBuilder reversedStringBuilder = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            reversedStringBuilder.append(str.charAt(i));
        }
        return reversedStringBuilder.toString();
    }

    private static boolean isOperator(char currentChar) {
        return currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/' || currentChar == '^';
    }

    private static boolean hasHigherPrecedence(char op1, char op2) {
        switch (op1) {
            case '^':
                return true;
            case '*':
            case '/':
                return op2 != '+' && op2 != '-';
            default:
                return false;
        }
    }

    private static void clearScreen() {
        System.out.print("\u000C");
    }

    private static void toContinue(Scanner input, String conversionHeader) {
        System.out.print("\nPress Enter to continue...");
        input.nextLine();
        clearScreen();
        System.out.println(conversionHeader);
    }

    private static boolean tryAgain(Scanner inputScanner, String conversionHeader) {
        System.out.print("\nTry Again (Y/N): ");
        String choice = inputScanner.nextLine().trim().toUpperCase();

        if (choice.equals("Y")) {
            clearScreen();
            System.out.println(conversionHeader);
            return true;
        } else if (choice.equals("N")) {
            clearScreen();
            return false;
        } else {
            clearScreen();
            System.out.println(conversionHeader);
            System.out.println("\nInvalid choice. Please enter 'Y' or 'N'.");
            return tryAgain(inputScanner, conversionHeader);
        }
    }

    private static void displayMenu() {
        System.out.println("\n======================================");
        System.out.println("|--------STACK APPLICATION-----------|");
        System.out.println("|--------CONVERSION MENU-------------|");
        System.out.println("======================================");
        System.out.println("| Options:                           |");
        System.out.println("|        [1] Infix to Postfix        |");
        System.out.println("|        [2] Infix to Prefix         |");
        System.out.println("|        [3] Postfix to Infix        |");
        System.out.println("|        [0] Exit                    |");
        System.out.println("|                                    |");
        System.out.println("| Pick a choice: [_]                 |");
        System.out.println("======================================");
    }
}
