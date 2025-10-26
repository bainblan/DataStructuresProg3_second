
public class BinarySearchTree<T extends Comparable<T>> {

    private NodeType<T> root;

    /**
     * Get the root of the binary search tree.
     *
     * @return the root node
     */
    public NodeType<T> getRoot() {
        return root;
    }

    /**
     * Constructor for BinarySearchTree
     */
    public BinarySearchTree() {
    }

    /**
     * Insert a new item into the binary search tree.
     *
     * @param key the item to insert
     */
    public void insert(T key) {
        root = insertHelper(root, key);
    }

    /**
     * Helper method for insertion.
     * @param root
     * @param key
     * @return
     */
    private NodeType<T> insertHelper(NodeType<T> root, T key) {
        if (root == null) {
            root = new NodeType<T>(key);
            return root;
        } else if (key.compareTo(root.getInfo()) == 0) {
            throw new IllegalArgumentException("The item already exists in the tree.");
        } else if (key.compareTo(root.getInfo()) < 0) {
            root.setLeft(insertHelper(root.getLeft(), key));
        } else {
            root.setRight(insertHelper(root.getRight(), key));
        }
        return root;
    }

    /**
     * Delete a node from the binary search tree.
     * @param key the item to delete
     */
    public void delete(T key) {
        root = deleteHelper(root, key);
    }

    /**
     * Helper method for deletion. Throws an exception if the item to be deleted is not found.
     * @param root
     * @param key
     * @return
     */
    private NodeType<T> deleteHelper(NodeType<T> root, T key) {
        if (root == null) {
            throw new IllegalArgumentException("The number is not present in the tree");
        } else if (key.compareTo(root.getInfo()) < 0) {
            root.setLeft(deleteHelper(root.getLeft(), key));
        } else if (key.compareTo(root.getInfo()) > 0) {
            root.setRight(deleteHelper(root.getRight(), key));
        } else {
            if (root.getLeft() == null && root.getRight() == null) {
                root = null;
            } else if (root.getRight() != null) {
                root.setInfo(successor(root));
                root.setRight(deleteHelper(root.getRight(), root.getInfo()));
            } else {
                root.setInfo(predecessor(root));
                root.setLeft(deleteHelper(root.getLeft(), root.getInfo()));
            }
        }
        return root;
    }

    /**
     * Find the successor of a given node.
     * @param root
     * @return
     */
    private T successor(NodeType<T> root) {
        root = root.getRight();
        while (root.getLeft() != null) {
            root = root.getLeft();
        }
        return root.getInfo();
    }

    /**
     * Find the predecessor of a given node.
     * @param root
     * @return
     */
    private T predecessor(NodeType<T> root) {
        root = root.getLeft();
        while (root.getRight() != null) {
            root = root.getRight();
        }
        return root.getInfo();
    }

    /**
     * Retrieve an item from the binary search tree.
     *
     * @param item the item to retrieve
     * @return true if the item is found, false otherwise
     */
    public boolean retrieve(T item) {
        return retrieveHelper(root, item);
    }

    /**
     * Helper method for retrieval.
     * @param root
     * @param item
     * @return
     */
    private boolean retrieveHelper(NodeType<T> root, T item) {
        if (root == null) {
            throw new IllegalArgumentException("Item is not present in the tree");
        } else if (item.compareTo(root.getInfo()) == 0) {
            return true;
        } else if (item.compareTo(root.getInfo()) < 0) {
            return retrieveHelper(root.getLeft(), item);
        } else {
            return retrieveHelper(root.getRight(), item);
        }
    }

    /**
     * In-order traversal of the binary search tree.
     */
    public void inOrder() {
        inOrderHelper(root);
    }

    /**
     * Helper method for in-order traversal.
     * @param root
     */
    private void inOrderHelper(NodeType<T> root) {
        if (root != null) {
            inOrderHelper(root.getLeft());
            System.out.println(root.getInfo());
            inOrderHelper(root.getRight());
        }
    }

    /**
     * Check if the binary tree is a proper binary tree.
     * @return
     */
    public boolean isProper() {
        return isProperHelper(root);
    }

    /**
     * Helper method for isProper.
     * @param root
     * @return
     */
    private boolean isProperHelper(NodeType<T> root) {
        if (root == null) {
            return true;
        }
        if ((root.getLeft() == null && root.getRight() != null) ||
                (root.getRight() == null && root.getLeft() != null)) {
            return false;
        } else {
            return isProperHelper(root.getLeft()) && isProperHelper(root.getRight());
        }
    }

    /**
     * Check if the binary tree is a complete binary tree.
     * @return
     */
    public boolean isComplete() {
        int totalNodes = countNodes(root);
        return isCompleteHelper(root, 0, totalNodes);
    }

    /**
     * Helper method for isComplete.
     * @param root
     * @param index
     * @param totalNodes
     * @return
     */
    private boolean isCompleteHelper(NodeType<T> root, int index, int totalNodes) {
        if (root == null) {
            return true;
        }
        if (index >= totalNodes) {
            return false;
        }
        return isCompleteHelper(root.getLeft(), 2 * index + 1, totalNodes)
                && isCompleteHelper(root.getRight(), 2 * index + 2, totalNodes);
    }

    /**
     * Count the number of nodes in the binary tree.
     * @param root
     * @return
     */
    private int countNodes(NodeType<T> root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.getLeft()) + countNodes(root.getRight());
    }

    /**
     * Get and print all single parent nodes in the binary tree.
     */
    public void getSingleParent() {
        getSingleParentHelper(root);
    }

    /**
     * Helper method for getSingleParent.
     * @param root
     */
    private void getSingleParentHelper(NodeType<T> root) {
        if (root != null) {
            if ((root.getLeft() == null && root.getRight() != null) ||
                    (root.getRight() == null && root.getLeft() != null)) {
                System.out.println(root.getInfo());
            }
            getSingleParentHelper(root.getLeft());
            getSingleParentHelper(root.getRight());
        }
    }

    /**
     * Get the number of leaf nodes in the binary tree.
     * @return
     */
    public int getNumLeafNodes() {
        return getNumLeafNodesHelper(root);
    }

    /**
     * Helper method for getNumLeafNodes.
     * @param root
     * @return
     */
    private int getNumLeafNodesHelper(NodeType<T> root) {
        if (root == null) {
            return 0;
        }
        if (root.getLeft() == null && root.getRight() == null) {
            return 1;
        }
        return getNumLeafNodesHelper(root.getLeft()) + getNumLeafNodesHelper(root.getRight());
    }

    /**
     * Get and print all cousin nodes of a given node in the binary tree.
     * @param node
     */
    public void getCousins(NodeType<T> node) {
        getCousinsHelper(root, node, getLevel(root, node, 1));
    }

    /**
     * Helper method for getCousins.
     * @param root
     * @param node
     * @param level
     */
    private void getCousinsHelper(NodeType<T> root, NodeType<T> node, int level) {
        if (root == null || level < 2) {
            return;
        }
        if (level == 2) {
            NodeType<T> left = root.getLeft();
            NodeType<T> right = root.getRight();
            if (left == node || right == node) {
                return;
            }
            if (left != null)
                System.out.print(left.getInfo() + " ");
            if (right != null)
                System.out.print(right.getInfo() + " ");
        }
        else {
            getCousinsHelper(root.getLeft(), node, level - 1);
            getCousinsHelper(root.getRight(), node, level - 1);
        }
    }

    /**
     * Get the level of a given node in the binary tree.
     * @param root
     * @param node
     * @param level
     * @return
     */
    private int getLevel(NodeType<T> root, NodeType<T> node, int level) {
        if (root == null) {
            return 0;
        }
        if (root == node) {
            return level;
        }
        int downLevel = getLevel(root.getLeft(), node, level + 1);
        if (downLevel != 0) {
            return downLevel;
        }
        return getLevel(root.getRight(), node, level + 1);
    }

} // BinarySearchTree