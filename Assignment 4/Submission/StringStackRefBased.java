/**
 * StringStackRefBased.java
 * Michael Reiter
 * V00831568
 * CSC 115 Assignment Four
 * This class implements methods for a string stack
 * March 7, 2015
 */

public class StringStackRefBased implements StringStack {

	private StringNode top;
	
	StringStackRefBased() {
		this.top = null;
	}

	/**
     * Returns true if there are no strings in the stack; false otherwise.
     * @return true or false as described
     */
    public boolean isEmpty() {
    	return (this.top == null);
    }

    /**
     * If the stack is not empty, then the string at the top is removed, 
     * and the value contained is returned to the caller. If the stack was empty
     * then a StringStackException is thrown.
     * @return the value of the String at the top of the stack.
     */
    public String pop() throws StringStackException {
    	if (!this.isEmpty()) {
    		StringNode temp = this.top;
            this.top = this.top.next;
            return temp.toString();
    	} else {
    		throw new StringStackException("Cannot pop because stack is empty");
    	}
    }

    /**
     * Removes all items from the stack
     */
    public void popAll() {
    	this.top = null;
    }

    /**
     * Places the item onto the top of the stack.
     */
    public void push(String item) throws StringStackException {
    	StringNode node = new StringNode(item);
        node.next = this.top;
        this.top = node;
    }

    /**
     * If the stack has minimum one string, the value of
     * the String at the top is returned to the caller.
     * If the stack was empty then a StringStackException is thrown.
     * @return the value of the String at the top of the stack.
     */
    public String peek() throws StringStackException {
    	if (!this.isEmpty()) {
    		return this.top.toString();
    	} else {
    		throw new StringStackException("Cannot peek because stack is empty");
    	}
    }

    public static void main(String[] args) {
        //Stack implementation tests below
        StringStackRefBased stack = new StringStackRefBased();

        try {
            //test 1: isEmpty()
            //stack should initially be empty
            if (stack.isEmpty()) {
                System.out.println("Test 1: Passed");
            } else {
                System.out.println("Test 1: Failed");
            }

            //test 2: push()
            //calling push() should put pushed item at the top of the stack
            stack.push("1");
            if (stack.top.item.equals("1")) {
                System.out.println("Test 2: Passed");
            } else {
                System.out.println("Test 2: Failed");
            }

            //test 3: isEmpty()
            //stack should not be empty after it has an item pushed onto it
            if (!stack.isEmpty()) {
                System.out.println("Test 3: Passed");
            } else {
                System.out.println("Test 3: Failed");
            }

            //test 4: pop()
            //calling pop should remove only the top item from the stack
            stack.push("first");
            stack.push("second");
            if (stack.pop().equals("second") && !stack.isEmpty()) {
                System.out.println("Test 4: Passed");
            } else {
                System.out.println("Test 4: Failed");
            }

            //test 5: popAll()
            //calling popAll() should remove all items from the stack
            stack.push("new1");
            stack.push("new2");
            stack.push("new3");
            stack.popAll();
            if (stack.isEmpty()) {
                System.out.println("Test 5: Passed");
            } else {
                System.out.println("Test 5: Failed");
            }

            //test 6: peek()
            //calling peek() should return the top item from the stack
            stack.push("peekTest");
            if (stack.peek().equals("peekTest")) {
                System.out.println("Test 6: Passed");
            } else {
                System.out.println("Test 6: Failed");
            }
        }
        catch (StringStackException e) {
            System.out.println("Stack exception caught");
        }
        
    }
}