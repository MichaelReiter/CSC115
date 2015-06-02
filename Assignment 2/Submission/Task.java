/**
 * Task.java
 * Michael Reiter
 * V00831568
 * CSC 115 Assignment Two
 * February 3, 2015
 */

/**
 * Task objects contain the information necessary to represent
 * an task in a scheduling simulator, i.e., task priority
 * and task number.
 *
 * This class is not meant to be used as a linked-list node!
 */
public class Task {
    int priority;
    int number;

    Task(int priority, int number) {
        this.priority = priority;
        this.number   = number;
    }

    @Override
    public String toString() {
    	return "(" + this.priority + ", " + this.number + ")";
    }
}
