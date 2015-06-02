/**
 * TaskListRefBased.java
 * Michael Reiter
 * V00831568
 * CSC 115 Assignment Two
 * February 3, 2015
 */

class TaskListRefBased implements TaskList {
	private TaskListNodeAlt mHead;

	public TaskListRefBased() { 
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
}