
public class NodeType<T extends Comparable <T>> {
    private T info;
    private NodeType<T> left;
    private NodeType<T> right;

    public NodeType(T info) {
        this.info = info;
        left = null;
        right = null;
    }

    /**
     * Return the payload stored in this node.
     *
     * @return the info value (may be null)
     */
    public T getInfo() {
        return info;
    }

    /**
     * Set the payload for this node.
     *
     * @param info value to store in the node
     */
    public void setInfo(T info) {
        this.info = info;
    }

    /**
     * Get the next node in the list.
     *
     * @return reference to the next node, or null if none
     */
    public NodeType<T> getLeft() {
        return left;
    }

    /**
     * Set the next node reference.
     *
     * @param next node to set as next
     */
    public void setLeft(NodeType<T> left) {
        this.left = left;
    }

    /**
     * Get the previous (back) node in the list.
     *
     * @return reference to the previous node, or null if none
     */
    public NodeType<T> getRight() {
        return right;
    }

    /**
     * Set the right node reference.
     *
     * @param right node to set as right
     */
    public void setRight(NodeType<T> right) {
        this.right = right;
    }
} // NodeType

