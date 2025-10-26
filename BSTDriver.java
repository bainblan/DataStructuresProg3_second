
public class BSTDriver {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        // Add test cases here to test the BinarySearchTree methods
        bst.insert(9);
        bst.insert(4);
        bst.insert(2);
        bst.insert(5);
        bst.insert(15);
        bst.insert(13);
        bst.inOrder();
        System.out.println();
        bst.getCousins(bst.getRoot().getRight().getLeft()); // Example node
    }
}