import java.util.Scanner;

class TreeNode {
    int data;
    TreeNode left, right;

    TreeNode(int data) {
        this.data = data;
        left = right = null;
    }
}

class BinaryTree {
    TreeNode root;

    BinaryTree() {
        root = null;
    }
}

public class Main {

    public static int height(TreeNode root) {
        if (root == null)
            return 0;
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    public static int calculateColumns(int height) {
        if (height == 1)
            return 1;

        int leftSubtreeColumns = calculateColumns(height - 1);
        int rightSubtreeColumns = calculateColumns(height - 1);

        return leftSubtreeColumns + rightSubtreeColumns + 1;
    }

    public static void populateMatrix(int[][] matrix, TreeNode root, int col, int row, int height) {
        if (root == null)
            return;
        matrix[row][col] = root.data;
        populateMatrix(matrix, root.left, col - (int) Math.pow(2, height - 2), row + 1, height - 1);
        populateMatrix(matrix, root.right, col + (int) Math.pow(2, height - 2), row + 1, height - 1);
    }

    public static void printTreeStructure(BinaryTree tree) {
        int treeHeight = height(tree.root);
        int columns = calculateColumns(treeHeight);
        int[][] matrix = new int[treeHeight][2 * columns];
        populateMatrix(matrix, tree.root, columns / 2, 0, treeHeight);

        // Calculate the leading spaces to center the output
        int leadingSpaces = (75 - 2 * columns) / 2; // Assuming console width is 80 characters

        for (int i = 0; i < treeHeight; i++) {
            // Print leading spaces
            for (int k = 0; k < leadingSpaces; k++) {
                System.out.print(" ");
            }

            // Print the tree structure
            for (int j = 0; j < 2 * columns; j++) {
                if (matrix[i][j] == 0)
                    System.out.print("  ");
                else
                    System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void insertIntoTree(BinaryTree tree, int value) {
        tree.root = insert(tree.root, value);
    }

    private static TreeNode insert(TreeNode root, int value) {
        if (root == null) {
            return new TreeNode(value);
        }

        if (value < root.data) {
            root.left = insert(root.left, value);
        } else if (value > root.data) {
            root.right = insert(root.right, value);
        } else {
            System.out.println("Element " + value + " already exists. Please enter a different value.");
        }

        return root;
    }

    private static TreeNode findNode(TreeNode root, int value) {
        if (root == null || root.data == value) {
            return root;
        }

        if (value < root.data) {
            return findNode(root.left, value);
        } else {
            return findNode(root.right, value);
        }
    }

    public static void deleteFromTree(BinaryTree tree, int value) {
        TreeNode nodeToDelete = findNode(tree.root, value);
        if (nodeToDelete == null) {
            System.out.println("Element " + value + " does not exist in the tree.");
        } else {
            tree.root = delete(tree.root, value);
            System.out.println("Element " + value + " deleted successfully.");
        }
    }

    private static TreeNode delete(TreeNode root, int value) {
        if (root == null) {
            return null;
        }

        if (value < root.data) {
            root.left = delete(root.left, value);
        } else if (value > root.data) {
            root.right = delete(root.right, value);
        } else {

            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.data = findInorderSuccessor(root.right);
            root.right = delete(root.right, root.data);
        }

        return root;
    }

    private static int findInorderSuccessor(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int minValue = root.data;
        while (root.left != null) {
            minValue = root.left.data;
            root = root.left;
        }
        return minValue;
    }

    private static void handleTraversalMenu(Scanner scanner, BinaryTree tree) {
        int traversalChoice;

        do {
            printTraversalMenu();
            System.out.print("\n<USER> ");

            if (scanner.hasNextInt()) {
                traversalChoice = scanner.nextInt();

                switch (traversalChoice) {
                    case 1:
                        clearScreen();
                        System.out.println("\n::: Inorder Traversal :::\n");
                        inorderTraversal(tree.root);
                        System.out.println();
                        pressAnyKeyToContinue();
                        clearScreen();
                        break;
                    case 2:
                        clearScreen();
                        System.out.println("\n::: Preorder Traversal :::\n");
                        preorderTraversal(tree.root);
                        System.out.println();
                        pressAnyKeyToContinue();
                        clearScreen();
                        break;
                    case 3:
                        clearScreen();
                        System.out.println("\n::: Postorder Traversal :::\n");
                        postorderTraversal(tree.root);
                        System.out.println();
                        pressAnyKeyToContinue();
                        clearScreen();
                        break;
                    case 4:
                        clearScreen();
                        System.out.println("You are now at the main menu.");
                        break;
                    default:
                        clearScreen();
                        System.out.println("Invalid choice. Please enter a valid option.");
                }

            } else {
                clearScreen();
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next();
                traversalChoice = -1;
            }
        } while (traversalChoice != 4);
    }

    private static void printTraversalMenu() {
        System.out.println("\n===================================");
        System.out.println("|       TREE TRAVERSAL MENU       |");
        System.out.println("===================================");
        System.out.println("|  Options:                       |");
        System.out.println("|         [1] Inorder             |");
        System.out.println("|          [2] Preorder           |");
        System.out.println("|           [3] Postorder         |");
        System.out.println("|            [4] Exit             |");
        System.out.println("|                                 |");
        System.out.println("|  Pick a choice: [_]             |");
        System.out.println("===================================");
    }

    private static void inorderTraversal(TreeNode root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.print(root.data + " ");
            inorderTraversal(root.right);
        }
    }

    private static void preorderTraversal(TreeNode root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preorderTraversal(root.left);
            preorderTraversal(root.right);
        }
    }

    private static void postorderTraversal(TreeNode root) {
        if (root != null) {
            postorderTraversal(root.left);
            postorderTraversal(root.right);
            System.out.print(root.data + " ");
        }
    }

    private static void pressAnyKeyToContinue() {
        System.out.println("\nPress any key to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean tryAgain(Scanner scanner, String action, boolean isDuplicate) {
        while (true) {
            System.out.print("\nTry Again for " + action + " (Y/N): ");
            String response = scanner.next().trim().toUpperCase();

            if (response.equals("Y")) {
                return true;
            } else if (response.equals("N")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter Y or N.");
            }
        }
    }

    private static void clearScreen() {
        System.out.print("\u000C");
    }

    public static void printMenu() {
        System.out.println("\n====================================");
        System.out.println("|        BINARY SEARCH TREE        |");
        System.out.println("|           OPERATIONS MENU        |");
        System.out.println("====================================");
        System.out.println("|  Options:                        |");
        System.out.println("|         [S] Show                 |");
        System.out.println("|          [I] Insert              |");
        System.out.println("|           [D] Delete             |");
        System.out.println("|            [T] Traverse          |");
        System.out.println("|             [Q] Quit             |");
        System.out.println("|                                  |");
        System.out.println("|        Pick a choice: [_]        |");
        System.out.println("====================================");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BinaryTree myBinaryTree = new BinaryTree();
        boolean elementInserted = false;

        char userChoice;
        do {
            printMenu();
            System.out.print("\n<USER> ");
            userChoice = scanner.next().toUpperCase().charAt(0);

            switch (userChoice) {
                case 'S':
                    clearScreen();
                    if (elementInserted && myBinaryTree.root != null) {
                        System.out.println("\n::: Your Tree Structure :::\n");
                        printTreeStructure(myBinaryTree);
                        pressAnyKeyToContinue();
                        clearScreen();
                    } else {
                        System.out.println("The tree is empty. Please insert an element first.");
                    }
                    break;
                case 'I':
                    clearScreen();
                    System.out.println("\n::: Insert Operation :::");
                    boolean invalidInput = false;
                    do {
                        if (invalidInput) {
                            System.out.println("Invalid input. Please enter a valid integer.");
                            scanner.next();
                        }
                        System.out.print("\nEnter Value to Insert: ");
                        if (scanner.hasNextInt()) {
                            int userInput = scanner.nextInt();
                            insertIntoTree(myBinaryTree, userInput);
                            elementInserted = true;
                            invalidInput = false;
                        } else {
                            invalidInput = true;
                        }
                    } while (invalidInput || tryAgain(scanner, "Insert", false));
                    clearScreen();
                    break;
                case 'D':
                    clearScreen();
                    boolean invalidInputDelete = false;
                    if (elementInserted && myBinaryTree.root != null) {
                        System.out.println("\n::: Delete Operation :::");
                        do {
                            if (invalidInputDelete) {
                                System.out.println("Invalid input. Please enter a valid integer.");
                                scanner.next();
                            }
                            System.out.print("\nEnter Value to Delete: ");
                            if (scanner.hasNextInt()) {
                                int deleteInput = scanner.nextInt();
                                deleteFromTree(myBinaryTree, deleteInput);
                                invalidInputDelete = false;
                            } else {
                                invalidInputDelete = true;
                            }
                        } while (invalidInputDelete || tryAgain(scanner, "Delete", false));
                        clearScreen();
                    } else {
                        System.out.println("The tree is empty. Please insert an element first.");
                    }
                    break;
                case 'T':
                    clearScreen();
                    if (elementInserted && myBinaryTree.root != null) {
                        System.out.println("You are now in a sub-menu!");
                        handleTraversalMenu(scanner, myBinaryTree);
                    } else {
                        System.out.println("The tree is empty. Please insert an element first.");
                    }
                    break;
                case 'Q':
                    clearScreen();
                    System.out.println("\nProgram has been terminated.");
                    System.out.println("Goodbye.");
                    break;
                default:
                    clearScreen();
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (userChoice != 'Q');

        scanner.close();
    }
}
