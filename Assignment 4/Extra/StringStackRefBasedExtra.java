/**
 * StringStackRefBasedExtra.java
 * Michael Reiter
 * V00831568
 * CSC 115 Assignment Four
 * This extra class implements a string stack using the library java.util.LinkedList
 * March 16, 2015
 */

import java.util.LinkedList;

public class StringStackRefBasedExtra implements StringStack {
    LinkedList<String> linkedList;

    public StringStackRefBasedExtra() {
        this.linkedList = new LinkedList<String>();
    }

    /**
     * Returns true if there are no strings in the stack; false otherwise.
     * @return true or false as described
     */
    public boolean isEmpty() {
        return (this.linkedList.size() == 0);
    }

    /**
     * If the stack is not empty, then the string at the top is removed, 
     * and the value contained is returned to the caller. If the stack was empty
     * then a StringStackException is thrown.
     * @return the value of the String at the top of the stack.
     */
    public String pop() throws StringStackException {
        if (this.isEmpty()) {
            throw new StringStackException("Error: cannot pop empty stack.");
        } else {
            return this.linkedList.remove(0);                
        }
    }

    /**
     * Removes all items from the stack
     */
    public void popAll() {
        this.linkedList.clear();
    }

    /**
     * Places the item onto the top of the stack.
     */
    public void push(String item) {
        this.linkedList.add(0, item);
    }

    /**
     * If the stack has minimum one string, the value of
     * the String at the top is returned to the caller.
     * If the stack was empty then a StringStackException is thrown.
     * @return the value of the String at the top of the stack.
     */
    public String peek() throws StringStackException {
        if (this.isEmpty()) {
            throw new StringStackException("Error: cannot pop empty stack.");
        } else {
            return this.linkedList.get(0);                
        }
    }
}
