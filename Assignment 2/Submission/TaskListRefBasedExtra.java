/**
 * TaskListRefBasedExtra.java
 * Michael Reiter
 * V00831568
 * CSC 115 Assignment Two
 * February 3, 2015
 */

/*
TaskListRefBasedExtra implements several extra methods described below as well as in javadoc comments.

removeAll(): removes all items from the list
dumpList(): prints the list the console
reverseList(): takes a list as input and returns the same list in reversed order
insertUnordered(): inserts items to the end of a list (inserted items are not sorted)
retrieveHead(): retrieves the first item in the list
*/

class TaskListRefBasedExtra implements TaskList {
	private TaskListNodeAlt mHead;

	public TaskListRefBasedExtra() { 
		mHead = null;
	}

	/** 
     * Examines the task list; if there are no tasks
     * returns true, otherwise false.
     * @return whether or not the list contains tasks
     */
    public boolean isEmpty() {
    	return (mHead == null);
    }

    /** 
     * Returns the number of tasks
     * currently stored in the task list.
     * @return number greater than or equal to 0 corresponding
     * to number of items in the list
     */
    public int getLength() {
    	TaskListNodeAlt curr = mHead;
        int counter = 0;
        while (curr != null) {
            curr = curr.next;
            counter++;
        }
        return counter;
    }

    /**
     * If the list has at least one task, then the task at
     * the head is removed from the list, and this task
     * is returned. If there are no items in the list,
     * then the value returned is null.
     * @return a Task object corresponding the the task at the
     * head of the list; possibly null.
     */
    public Task removeHead() {
    	if (!this.isEmpty()) {
            TaskListNodeAlt curr = mHead;
            mHead = curr.next;
            curr.next = null;
            return curr.task;
        } else {
            return null;
        }
    }

    /**
     * If there are no items in the list, the value of
     * of null is returned.
     *
     * If the list has at least one task, then the list
     * is searched for a task with the same priority and
     * number as t. When found, this task is removed from
     * the list, and t is returned; otherwise the value
     * of null is returned.
     * @param t Task to be removed from the list.
     * @return a Task object corresponding the the task at the
     * head of the list; possibly null.
     */
    public Task remove(Task t) {
    	TaskListNodeAlt curr = mHead;
        TaskListNodeAlt marker = null;
        while (curr != null) {
            if (curr.task.number != t.number || curr.task.priority != t.priority) {
                marker = curr;
                curr = curr.next;
            } else {
                break;
            }
        }
        if (curr == null) {
            return null;
        }
        if (marker != null) {
            marker.next = curr.next;
        } else {
            mHead = curr.next;
        }
        return curr.task;
    }

    /** 
     * Accepts a task to be inserted into the list. Assume
     * PREV is the task in the list after insertion, and
     * SUCC is the task in the list after insertion. The
     * following two conditions must hold.
     * (1) t.priority > SUCC.priority
     * (2) if PREV.priority == t.priority, then
     *     PREV must have been inserted at an earlier
     *     time than t.
     * (3) if PREV.priority > t.priority, then t is
     *     the only task in the list having t.priority.
     * Also: No two tasks in the list may have the same
     * task number.
     * @param t task to be placed into the task list
     */
    public void insert(Task t) {
        TaskListNodeAlt curr = mHead;
        for (int counter = 0; counter < this.getLength(); counter++) {
            if (curr.task.number == t.number && curr.task.priority == t.priority) {
                System.out.println("Insertion failed. Cannot insert a task identical to an existing task.");
                return;
            }
            curr = curr.next;
        }
    	TaskListNodeAlt newNode = new TaskListNodeAlt(t);
        if (mHead == null || newNode.task.priority > mHead.task.priority) {
            newNode.next = mHead;
            mHead = newNode;
        } else {
            TaskListNodeAlt before = mHead;
            TaskListNodeAlt after = mHead.next;
            while (after != null) {
                if (newNode.task.priority > after.task.priority) {
                    break;
                }
                before = after;
                after = after.next;
            }
            newNode.next = after;
            before.next = newNode;
        }
    }

    /**
     * Takes an integer value indicate that the ith task
     * in the list is to be returned. When i is 0, the first
     * task is returned, when i is 1, the second task is
     * returned, etc. If there is no such task i, then null
     * is returned. The list is not modified by this operation.
     * @param i indicates the number of task from the start of
     * of the list which will be the task returned
     * @return contents of the ith task in the list; possibly null
     */ 
    public Task retrieve(int i) {
        TaskListNodeAlt curr = mHead;
        if (i < this.getLength() && i >= 0) {
            for (int counter = 0; counter < i; counter++) {
                curr = curr.next;
            }
            return curr.task;
        } else {
            return null;
        }
    }

    /**
     * Prints the contents of the list
     */
    public void dumpList() {
        TaskListNodeAlt curr = mHead;
        System.out.println("The list contains:");
        while (curr != null) {
            System.out.println("(" + curr.task.priority + ", " + curr.task.number + ")");
            curr = curr.next;
        }
    }

    /** 
     * Removes all items from the list
     */
    public void removeAll() {
        mHead = null;
    }

    /** 
     * Accepts a task list and returns the same list, but in reverse order.
     * @return a reversed list created from the input list
     */
    public TaskListRefBasedExtra reverseList() {
        TaskListRefBasedExtra reversedList = new TaskListRefBasedExtra();
        for (int i = this.getLength() - 1; i >= 0; i--) {
            reversedList.insertUnordered(this.retrieve(i));
        }
        return reversedList;
    }

    /** 
     * Accepts a task to be inserted at the end of the list (the list is not sorted).
     * @param t task to be placed at the end of the task list
     */
    public void insertUnordered(Task t) {
        TaskListNodeAlt newNode = new TaskListNodeAlt(t);
        if (mHead == null) {
            mHead = newNode;
        } else {
            TaskListNodeAlt curr = mHead;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
        }
    }

    /**
     * Retrieves the task at the head of the list
     * @return the first task in the list; can be null
     */
    public Task retrieveHead() {
        return mHead.task;
    }
}