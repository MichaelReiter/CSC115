import java.util.LinkedList;

/**
 * @author Mike Zastre, UVic
 *
 * Part of CSC 115, Spring 2015, Assignment #5
 */

/**
 * BSTIterator.java
 * Michael Reiter
 * V00831568
 * CSC 115 Assignment Five
 * This class implements methods to iterate through a binary search tree.
 * March 21, 2015
 * Note: some methods implemented with the help of lab TA and the textbook.
 */

public class BSTIterator implements java.util.Iterator<WordRefs> {
    private BSTRefBased t;
    private WordRefs currentItem;
    private LinkedList<WordRefs> list;

    public BSTIterator(BSTRefBased t) {
        this.t = t;
        currentItem = null;
        list = new LinkedList<>();
        setInorder();
    }

    /**
     * Determines if the iteration has more elements.
     * @return true if the iteration has more elements; false, otherwise
     */
    public boolean hasNext() {
        return (list.isEmpty() ? false : true);
    }

    /**
     * Returns the following element.
     * @return the next element in the iteration
     */
    public WordRefs next() throws java.util.NoSuchElementException {
        currentItem = list.remove();
        return currentItem;
    }

    /**
     * Should remove the last element returned by the iterator. (Required by interface)
     * Instead throws an UnsupportedOperationException because remove() is not supported.
     */
    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    /**
     * A public method to call the private preorder method.
     */
    public void setPreorder() {
        list.clear();
        preorder(t.getRoot());
    }

    /**
     * A public method to call the private preorder method.
     */
    public void setInorder() {
        list.clear();
        inorder(t.getRoot());
    }

    /**
     * A public method to call the private preorder method.
     */
    public void setPostorder() {
        list.clear();
        postorder(t.getRoot());
    }

    /**
     * Recursively iterates through a tree in preorder adding the nodes to a list.
     * @param node the node at which to start iterating
     */
    private void preorder(TreeNode node) {
        if (node != null) {
            list.add(node.item);
            preorder(node.left);
            preorder(node.right);
        }
    }

    /**
     * Recursively iterates through a tree in inorder adding the nodes to a list.
     * @param node the node at which to start iterating
     */
    private void inorder(TreeNode node) {
        if (node != null) {
            inorder(node.left);
            list.add(node.item);
            inorder(node.right);
        }
    }

    /**
     * Recursively iterates through a tree in postorder adding the nodes to a list.
     * @param node the node at which to start iterating
     */
    private void postorder(TreeNode node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            list.add(node.item);
        }
    }
}
