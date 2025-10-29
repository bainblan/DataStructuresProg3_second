
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BSTDriver {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter list type (i - int, d - double, s - string): ");
        char typeChar = input.next().toLowerCase().charAt(0);
        String type = (typeChar == 'i') ? "int" : (typeChar == 'd') ? "double" : (typeChar == 's') ? "string" : "";

        // Accept an optional filename argument. If none provided, use data.txt.
        // If multiple args are given, join them with spaces to support filenames that contain spaces.
        String filename = "data.txt";
        if (args.length >= 1) {
            filename = String.join(" ", args);
        }

        // We'll use a raw BinarySearchTree reference and treat values based on typeChar.
        BinarySearchTree bst = null;
        Scanner s = null;
        try {
            // construct Scanner inside try so FileNotFoundException is thrown from here
            s = new Scanner(new File(filename));
            bst = new BinarySearchTree();
            switch (typeChar) {
                case 'i':
                    while (s.hasNext()) {
                        if (s.hasNextInt()) {
                            try {
                                bst.insert(s.nextInt());
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            }
                        } else {
                            s.next();
                        }
                    }
                    break;
                case 'd':
                    while (s.hasNext()) {
                        if (s.hasNextDouble()) {
                            try {
                                bst.insert(s.nextDouble());
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            }
                        } else {
                            s.next();
                        }
                    }
                    break;
                case 's':
                    while (s.hasNext()) {
                        try {
                            bst.insert(s.next());
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                default:
                    System.out.println("Invalid type selected.");
                    input.close();
                    if (s != null) s.close();
                    return;
            }
        } catch (FileNotFoundException e) {
            // If the input file is not found, create an empty tree and continue
            System.out.println("Input file '" + filename + "' not found; starting with an empty tree.");
            bst = new BinarySearchTree();
        } finally {
            if (s != null) {
                s.close();
            }
        }

        char command = ' ';
        while (command != 'q') {
            System.out.println("Commands:");
            System.out.println("(i) - Insert Item");
            System.out.println("(d) - Delete Item");
            System.out.println("(p) - Print Tree (in-order)");
            System.out.println("(r) - Retrieve Item");
            System.out.println("(l) - Count Leaf Nodes");
            System.out.println("(s) - Find Single Parents");
            System.out.println("(c) - Find Cousins of a node");
            System.out.println("(o) - Is Proper?");
            System.out.println("(m) - Is Complete?");
            System.out.println("(q) - Quit program");

            String value = input.next().toLowerCase();
            if (value.isEmpty()) {
                continue;
            }
            command = value.charAt(0);
            switch (command) {
                case 'i':
                    iCalled(input, bst, typeChar);
                    break;
                case 'd':
                    dCalled(input, bst, typeChar);
                    break;
                case 'p':
                    pCalled(bst);
                    break;
                case 'r':
                    rCalled(input, bst, typeChar);
                    break;
                case 'l':
                    lCalled(bst);
                    break;
                case 's':
                    sCalled(bst);
                    break;
                case 'c':
                    cCalled(input, bst, typeChar);
                    break;
                case 'o':
                    oCalled(bst);
                    break;
                case 'm':
                    mCalled(bst);
                    break;
                case 'q':
                    System.out.println("Quitting.");
                    break;
                default:
                    System.out.println("Invalid command. Please enter a valid command.");
            }
        }
        input.close();
    }

    public static void iCalled(Scanner input, BinarySearchTree bst, char typeChar) {
        System.out.print("Enter value to insert: ");
        String value = input.next();
        try {
            switch (typeChar) {
                case 'i':
                    bst.insert(Integer.parseInt(value));
                    break;
                case 'd':
                    bst.insert(Double.parseDouble(value));
                    break;
                default:
                    bst.insert(value);
            }
            System.out.println("Inserted: " + value);
        } catch (Exception e) {
            System.out.println("Insert failed: " + e.getMessage());
        }
    }

    public static void dCalled(Scanner input, BinarySearchTree bst, char typeChar) {
        System.out.print("Enter value to delete: ");
        String value = input.next();
        try {
            switch (typeChar) {
                case 'i':
                    bst.delete(Integer.parseInt(value));
                    break;
                case 'd':
                    bst.delete(Double.parseDouble(value));
                    break;
                default:
                    bst.delete(value);
            }
            System.out.println("Deleted (if present): " + value);
        } catch (Exception e) {
            System.out.println("Delete failed: " + e.getMessage());
        }
    }

    public static void pCalled(BinarySearchTree bst) {
        System.out.println("In-order:");
        bst.inOrder();
    }

    public static boolean rCalled(Scanner input, BinarySearchTree bst, char typeChar) {
        System.out.print("Enter value to retrieve: ");
        String value = input.next();
        try {
            boolean found;
            switch (typeChar) {
                case 'i':
                    found = bst.retrieve(Integer.parseInt(value));
                    break;
                case 'd':
                    found = bst.retrieve(Double.parseDouble(value));
                    break;
                default:
                    found = bst.retrieve(value);
            }
            System.out.println("Retrieve " + value + ": " + (found ? "FOUND" : "NOT FOUND"));
            return found;
        } catch (IllegalArgumentException e) {
            System.out.println("Retrieve failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Retrieve failed: " + e.getMessage());
        }
        return false;
    }

    public static int lCalled(BinarySearchTree bst) {
        int leaves = bst.getNumLeafNodes();
        System.out.println("Number of leaf nodes: " + leaves);
        return leaves;
    }

    public static void sCalled(BinarySearchTree bst) {
        System.out.println("Single parent nodes (one child):");
        bst.getSingleParent();
    }

    public static boolean cCalled(Scanner input, BinarySearchTree bst, char typeChar) {
        System.out.print("Enter a number: ");
        String value = input.next();
        try {
            Object key;
            switch (typeChar) {
                case 'i':
                    key = Integer.parseInt(value);
                    break;
                case 'd':
                    key = Double.parseDouble(value);
                    break;
                default:
                    key = value;
                    break;
            }
            // find node by traversing
            NodeType node = findNode((NodeType) bst.getRoot(), (Comparable) key);
            if (node == null) {
                System.out.println("Node not found.");
                return false;
            }
            System.out.print(value + "cousins: ");
            bst.getCousins(node);
            System.out.println();
            return true;
        } catch (Exception e) {
            System.out.println("Cousins lookup failed: " + e.getMessage());
            return false;
        }
    }

    public static void oCalled(BinarySearchTree bst) {
        if (bst.isProper()) {
            System.out.println("The tree is proper.");
        } else {
            System.out.println("The tree is not proper.");
        }
    }

    public static void mCalled(BinarySearchTree bst) {
        if (bst.isComplete()) {
            System.out.println("The tree is complete.");
        } else {
            System.out.println("The tree is not complete.");
        }
    }

    // Generic node finder (returns NodeType<T>)
    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> NodeType<T> findNode(NodeType<T> root, T key) {
        if (root == null) {
            return null;
        }
        int cmp = key.compareTo(root.getInfo());
        if (cmp == 0) {
            return root;
        }
        if (cmp < 0) {
            return findNode(root.getLeft(), key);
        }
        return findNode(root.getRight(), key);
    }
}
