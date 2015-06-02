/**
 * TaskListTesterExtra.java
 * Michael Reiter
 * V00831568
 * CSC 115 Assignment Two
 * February 3, 2015
 */

class TaskListTesterExtra {
	public static void main(String[] args) {
		TaskListRefBasedExtra linkedList = new TaskListRefBasedExtra();

		Task first = new Task(1, 1);
		Task fourth = new Task(1, 1);

		linkedList.insert(first);
		linkedList.insert(fourth);

		linkedList.dumpList();
	}
}