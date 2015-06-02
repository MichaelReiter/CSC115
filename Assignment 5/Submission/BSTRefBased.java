/**
 * BSTRefBased.java
 * Michael Reiter
 * V00831568
 * CSC 115 Assignment Five
 * This class implements a reference based binary search tree.
 * March 21, 2015
 * Note: some methods implemented with the help of lab TA and the textbook.
 */

import java.util.Iterator;

public class BSTRefBased extends AbstractBinaryTree implements Iterable<WordRefs> {
    
    private TreeNode root;

    public BSTRefBased() {
        root = null;
    }

    public BSTRefBased(WordRefs item, AbstractBinaryTree left, AbstractBinaryTree right) {
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
    public WordRefs getRootItem() throws TreeException {
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
    public void setRootItem(WordRefs item) {
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
    public void attachLeft(WordRefs item) throws TreeException {
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
    public void attachRight(WordRefs item) throws TreeException {
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
    public void insert(String word) {
        root = insertItem(root, word);
    }

    /**
     * Recursively traverses the tree until it reaches the correct position, then adds the item to the tree.
     * @param r the TreeNode at which to start recursing
     * @param word the word for the inserted item
     * @return a TreeNode object of the inserted item
     */
    protected TreeNode insertItem(TreeNode r, String word) {
        if (r == null) {
            return new TreeNode(new WordRefs(word));
        }
        if (word.equals(r.item.getWord())) {
            return r;
        }
        if (word.compareTo(r.item.getWord()) < 0) {
            r.left = insertItem(r.left, word);
        } else {
            r.right = insertItem(r.right, word);
        }
        return r;
    }

    /**
     * Retrieves word reference information from the tree for an input word.
     * @param word the word for the inserted item
     * @return a WordRefs object for the input word if the word is found, null otherwise
     */
    public WordRefs retrieve(String word) throws TreeException {
        try {
            return retrieveItem(root, word).item;
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
    protected TreeNode retrieveItem(TreeNode r, String word) {
        if (r == null) {
            return null;
        }
        if (word.equals(r.item.getWord())) {
            return r;
        }
        if (word.compareTo(r.item.getWord()) < 0) {
            return retrieveItem(r.left, word);
        } else {
            return retrieveItem(r.right, word);
        }
    }

    /**
     * Deletes a node from the tree with an input word.
     * @param word the word for the item to delete
     */
    public void delete(String word) {
        root = deleteItem(root, word);
    }

    /**
     * Recursively traverses a tree and deletes a specified item if it is found. Throws a TreeException if the item is not found.
     * @param r the TreeNode at which the algorithm begins searching
     * @param word the word for the item to be deleted
     * @return a TreeNode object of the deleted node
     */
    protected TreeNode deleteItem(TreeNode r, String word) {
        if (r == null) {
            return null;
        } else {
            if (word.equals(r.item.getWord())) {
                r = deleteNode(r);
            } else if (word.compareTo(r.item.getWord()) < 0) {
                r.left = deleteItem(r.left, word);
            } else {
                r.right = deleteItem(r.right, word);
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

    public Iterator<WordRefs> iterator() {
        return new BSTIterator(this);
    }

    //tests below
    public static void main(String args[]) {
        BSTRefBased t;
        AbstractBinaryTree tt;
        int i;
        boolean result;
        String message;

        message = "Test 1: ";
        t = new BSTRefBased();
        try {
            t.insert("humpty");
            result = t.getRootItem().getWord().equals("humpty");
        } catch (Exception e) {
            result = false;
        }
        System.out.println(message + (result ? "Passed" : "FAILED"));

        message = "Test 2: ";
        t = new BSTRefBased();
        try {
            t.insert("humpty");
            t.insert("dumpty");
            t.insert("sat");
            result = t.getRootItem().getWord().equals("humpty");
            tt = t.detachLeftSubtree();
            result &= tt.getRootItem().getWord().equals("dumpty");
            tt = t.detachRightSubtree();
            result &= tt.getRootItem().getWord().equals("sat");
        } catch (Exception e) {
            result = false;
        }
        System.out.println(message + (result ? "Passed" : "FAILED"));

        //test 3: retrieve()
        //retrieve() should return a WordRefs object
        BSTRefBased bst = new BSTRefBased();
        bst.insert("test3");
        if (bst.retrieve("test3").getWord().equals("test3")) {
            System.out.println("Test 3: Passed");
        } else {
            System.out.println("Test 3: Failed");
        }

        //test 4: retrieve()
        //retrieve() should work the same for trees with any amount of nodes.
        bst.insert("one");
        bst.insert("two");
        bst.insert("three");
        if (bst.retrieve("two").getWord().equals("two")) {
            System.out.println("Test 4: Passed");
        } else {
            System.out.println("Test 4: Failed");
        }

        //test 5: delete()
        //delete() should remove the object from the tree. Once it is deleted, retrieve() cannot find it and returns null.
        bst.insert("4");
        bst.delete("4");
        if (bst.retrieve("4") == null) {
            System.out.println("Test 5: Passed");
        } else {
            System.out.println("Test 5: Failed");
        }

        //test 6: delete()
        //delete() should not affect the tree in any way if the deleted item does not exist.
        bst.delete("not in tree");
        if (bst.retrieve("one").getWord().equals("one") && bst.retrieve("two").getWord().equals("two") && bst.retrieve("three").getWord().equals("three")) {
            System.out.println("Test 6: Passed");
        } else {
            System.out.println("Test 6: Failed");
        }
    }
} 
