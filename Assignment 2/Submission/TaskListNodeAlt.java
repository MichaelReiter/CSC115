/**
 * TaskListNodeAlt.java
 * Michael Reiter
 * V00831568
 * CSC 115 Assignment Two
 * February 3, 2015
 */

class TaskListNodeAlt {
    //tasks contain priority and number integers used to identify correct insertion order
    Task task;
    TaskListNodeAlt next;

    TaskListNodeAlt(Task task) {
        this.task = task;
        this.next = null;
    }
}