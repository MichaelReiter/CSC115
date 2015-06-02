/**
 * BSTRefBasedExtra.java
 * Michael Reiter
 * V00831568
 * CSC 115 Assignment Five
 * This extra class implements a reference based binary search tree using integers rather than Strings.
 * March 21, 2015
 * Note: some methods implemented with the help of lab TA and the textbook.
 */

import java.util.Iterator;

public class BSTRefBasedExtra extends AbstractBinaryTree implements Iterable<WordRefsExtra> {
    
    private TreeNode root;

    public BSTRefBasedExtra() {
        root = null;
    }

    public BSTRefBasedExtra(WordRefsExtra item, AbstractBinaryTree left, AbstractBinaryTree right) {
        root = new TreeNode(item, null, null);
        if (left != null) {
            attachLeftSubtree(left);
        }

        if (right != null) {
            attachRightSubtree(right);
        }
    }

    /**
     * Determines if there are nodes in a tree by checking if the root is null.
     * @return true if there are no nodes in the tree, false otherwise.
     */
    public boolean isEmpty() {
        return (root == null);
    }

    /**
     * Removes all nodes from a tree by setting the root to null.
     */
    public void makeEmpty() {
        root = null;
    }

    /**
     * Returns the node at the root of the tree.
     * @return the root node
     */
    protected TreeNode getRoot() {
        return root;
    }

    /**
     * Sets the root node of the tree.
     * @param r The TreeNode to set the root to.    
     */
    protected void setRoot(TreeNode r) {
        root = r;
    }

    /**
     * Throws a TreeException if the tree is empty (root is null). Otherwise, returns the item of the root.
     * @return the item field stored in the root node if it exists.
     */
    public WordRefsExtra getRootItem() throws TreeException {
        if (root == null) {
            throw new TreeException("getRootItem() on empty tree");
        }
        return root.item;
    }

    /**
     * Creates a new TreeNode with the input item as the root if the tree is empty (root is null),
     * or just sets the root item if the tree isn't empty.
     * @param item The item reference to be stored in the tree's root node's item field.
     */
    public void setRootItem(WordRefsExtra item) {
        if (root == null) {
            root = new TreeNode(item);
        } else {
            root.item = item;
        }
    }

    /**
     * Attaches a new node to the left branch.
     * Throws a TreeException if the tree is empty or if trying to attach to a preoccupied branch.
     * @param item the item to be attached
     */
    public void attachLeft(WordRefsExtra item) throws TreeException {
        if (isEmpty()) {
            throw new TreeException("attachLeft to empty tree");
        }
        if (!isEmpty() && root.left != null) {
            throw new TreeException("attachLeft to occupied left child");
        }
        root.left = new TreeNode(item, null, null);
        return;
    }

    /**
     * Attaches a binary tree to the left branch by setting the main tree's left branch to input tree's root,
     * then sets the input tree's root to null.
     * Throws a TreeException if the tree is empty or if trying to attach to a preoccupied branch.
     * @param left the tree to the left
     */
    public void attachLeftSubtree(AbstractBinaryTree left) {
        if (isEmpty()) {
            throw new TreeException("attachLeftSubtree to empty tree");
        }
        if (!isEmpty() && root.left != null) {
            throw new 
                TreeException("attachLeftSubtree to occupied right child");
        }
        root.left = left.getRoot();
        left.makeEmpty();
        return;    
    }

    /**
     * Attaches a new node to the right branch.
     * Throws a TreeException if the tree is empty or if trying to attach to a preoccupied branch.
     * @param item the item to be attached
     */
    public void attachRight(WordRefsExtra item) throws TreeException {
        if (isEmpty()) {
            throw new TreeException("attachRight to empty tree");
        }
        if (!isEmpty() && root.right != null) {
            throw new TreeException("attachRight to occupied right child");
        }
        root.right = new TreeNode(item, null, null);
        return;
    }

    /**
     * Attaches a binary tree to the right branch by setting the main tree's left branch to input tree's root,
     * then sets the input tree's root to null.
     * Throws a TreeException if the tree is empty or if trying to attach to a preoccupied branch.
     * @param right the tree to the right
     */
    public void attachRightSubtree(AbstractBinaryTree right) {
        if (isEmpty()) {
            throw new TreeException("attachRightSubtree to empty tree");
        }
        if (!isEmpty() && root.right != null) {
            throw new 
                TreeException("attachRightSubtree to occupied right child");
        }
        root.right = right.getRoot();
        right.makeEmpty();
        return;
    }

    /**
     * Creates and returns a new binary tree taken from the left branch of a tree,
     * which is subsequently set to null.
     * Throws a TreeException if the tree is empty (root is null).
     * @return the binary tree created by detaching the left branch of the root tree
     */
    public AbstractBinaryTree detachLeftSubtree() throws TreeException {
        if (root == null) {
            throw new TreeException("detachLeftSubtree on empty tree");
        }
        BSTRefBased result = new BSTRefBased();
        result.setRoot(root.left);
        root.left = null;
        return result;
    }

    /**
     * Creates and returns a new binary tree taken from the right branch of a tree,
     * which is subsequently set to null.
     * Throws a TreeException if the tree is empty (root is null).
     * @return the binary tree created by detaching the right branch of the root tree
     */
    public AbstractBinaryTree detachRightSubtree() throws TreeException {
        if (root == null) {
            throw new TreeException("detachRightSubtree on empty tree");
        }
        BSTRefBased result = new BSTRefBased();
        result.setRoot(root.right);
        root.right = null;
        return result;
    }

    /**
     * Adds a node to the correct position in the tree.
     * @param word the word to insert into the tree
     */
    public void insert(int number) {
        root = insertItem(root, number);
    }

    /**
     * Recursively traverses the tree until it reaches the correct position, then adds the item to the tree.
     * @param r the TreeNode at which to start recursing
     * @param word the word for the inserted item
     * @return a TreeNode object of the inserted item
     */
    protected TreeNode insertItem(TreeNode r, int number) {
        if (r == null) {
            return new TreeNode(new WordRefsExtra(number));
        }
        if (number == r.item.getNumber()) {
            return r;
        }
        if (number < r.item.getNumber()) {
            r.left = insertItem(r.left, number);
        } else {
            r.right = insertItem(r.right, number);
        }
        return r;
    }

    /**
     * Retrieves word reference information from the tree for an input word.
     * @param word the word for the inserted item
     * @return a WordRefs object for the input word if the word is found, null otherwise
     */
    public WordRefsExtra retrieve(int number) throws TreeException {
        try {
            return retrieveItem(root, number).item;
        }
        catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * Recursively traverses the tree until it reaches the correct position, then adds the item to the tree.
     * @param r the node at which to start
     * @param word the word to retrieve
     * @return a TreeNode object of the retrieved item
     */
    protected TreeNode retrieveItem(TreeNode r, int number) {
        if (r == null) {
            return null;
        }
        if (number == r.item.getNumber()) {
            return r;
        }
        if (number < r.item.getNumber()) {
            return retrieveItem(r.left, number);
        } else {
            return retrieveItem(r.right, number);
        }
    }

    /**
     * Deletes a node from the tree with an input word.
     * @param word the word for the item to delete
     */
    public void delete(int number) {
        root = deleteItem(root, number);
    }

    /**
     * Recursively traverses a tree and deletes a specified item if it is found. Throws a TreeException if the item is not found.
     * @param r the TreeNode at which the algorithm begins searching
     * @param word the word for the item to be deleted
     * @return a TreeNode object of the deleted node
     */
    protected TreeNode deleteItem(TreeNode r, int number) {
        if (r == null) {
            return null;
        } else {
            if (number == r.item.getNumber()) {
                r = deleteNode(r);
            } else if (number < r.item.getNumber()) {
                r.left = deleteItem(r.left, number);
            } else {
                r.right = deleteItem(r.right, number);
            }
        }
        return r;
    }

    /**
     * Deletes a node in a tree.
     * @param node the node to be deleted
     * @return a TreeNode object of the deleted node
     */
    protected TreeNode deleteNode(TreeNode node) {
        if (node.left == null && node.right == null) {
            return null;
        } else if (node.left == null) {
            return node.right;
        } else if (node.right == null) {
            return node.left;
        } else {
            node.item = findLeftMost(node.right).item;
            node.right = deleteLeftMost(node.right);
            return node;
        }
    }

    /**
     * Determines the leftmost node in the tree.
     * @param node the node at which to start searching
     * @return the leftmost node in the tree.
     */
    protected TreeNode findLeftMost(TreeNode node) {
        if (node.left == null) {
            return node;
        } else {
            return findLeftMost(node.left);
        }
    }

    /**
     * Removes the leftmost node from the tree.
     * @param node the node at which to start searching
     * @return the removed node
     */
    protected TreeNode deleteLeftMost(TreeNode node) {
        if (node.left == null) {
            return node.right;
        } else {
            node.left = deleteLeftMost(node.left);
            return node;
        }
    }

    public Iterator<WordRefsExtra> iterator() {
        return new BSTIterator(this);
    }
} 