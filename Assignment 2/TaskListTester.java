/**
 * TaskListTester.java
 * Michael Reiter
 * V00831568
 * CSC 115 Assignment Two
 * February 3, 2015
 */

class TaskListTester {
	public static void main(String[] args) {
		TaskListRefBased linkedList = new TaskListRefBased();

		//test 1: isEmpty()
		//list should initially be empty
		if (linkedList.isEmpty()) {
			System.out.println("Test 1: Passed");
		} else {
			System.out.println("Test 1: Failed");
		}

		//test 2: getLength()
		//getLength should should return same as isEmpty if the list is empty
		int expectedLength = 0;
		if (linkedList.getLength() == expectedLength) {
			System.out.println("Test 2: Passed");
		} else {
			System.out.println("Test 2: Failed");
		}

		//test 3: insert()
		//inserting one node into the list should increment the length by 1
		int insertExpectedLength = 1;
		Task firstItem = new Task(10, 212);
		linkedList.insert(firstItem);
		if (linkedList.getLength() == insertExpectedLength) {
			System.out.println("Test 3: Passed");
		} else {
			System.out.println("Test 3: Failed");
		}

		//test 4: retrieve()
		//calling retrieve(0) should return the first item in the list
		if (linkedList.retrieve(0) == firstItem) {
			System.out.println("Test 4: Passed");
		} else {
			System.out.println("Test 4: Failed");
		}

		//test 5: retrieve()
		//attempting to retrieve an index that does not exist should return null
		if (linkedList.retrieve(1) == null) {
			System.out.println("Test 5: Passed");
		} else {
			System.out.println("Test 5: Failed");
		}

		//test 6: insert()
		//tasks with the same priority should be sorted in order of arrival
		Task secondItem = new Task(10, 198);
		linkedList.insert(secondItem);
		if (linkedList.retrieve(1) == secondItem) {
			System.out.println("Test 6: Passed");
		} else {
			System.out.println("Test 6: Failed");
		}
		
		//test 7: insert()
		//tasks inserted with higher priority than the task at head are put first
		Task higherPriorityItem = new Task(12, 100);
		linkedList.insert(higherPriorityItem);
		if (linkedList.retrieve(0) == higherPriorityItem) {
			System.out.println("Test 7: Passed");
		} else {
			System.out.println("Test 7: Failed");
		}

		//test 8: insert()
		//tasks are inserted into correct position in list based on priority (11 goes between 12 and 10)
		Task elevenPriorityItem = new Task(11, 96);
		linkedList.insert(elevenPriorityItem);
		if (linkedList.retrieve(1) == elevenPriorityItem) {
			System.out.println("Test 8: Passed");
		} else {
			System.out.println("Test 8: Failed");
		}

		//test 9: remove()
		//attempting to remove a task which is not in the list should return null
		Task notInList = new Task(1, 1);
		if (linkedList.remove(notInList) == null) {
			System.out.println("Test 9: Passed");
		} else {
			System.out.println("Test 9: Failed");
		}

		//test 10: remove()
		//attempting to remove from an empty list should return null
		TaskListRefBased emptyList = new TaskListRefBased();
		Task someTask = new Task(2, 2);
		if (emptyList.remove(someTask) == null) {
			System.out.println("Test 10: Passed");
		} else {
			System.out.println("Test 10: Failed");
		}

		//test 11: remove()
		//removing a task should return the removed task
		Task taskToRemove = new Task(3, 3);
		linkedList.insert(taskToRemove);
		if (linkedList.remove(taskToRemove) == taskToRemove) {
			System.out.println("Test 11: Passed");
		} else {
			System.out.println("Test 11: Failed");
		}

		//test 12: remove()
		//when a task is removed from the list, the tasks on either side of the removed task are properly linked
		//list is initially (12, 100), (11, 96), (10, 212); after removing elevenPriorityItem, list should be (12, 100), (10, 212)
		linkedList.remove(elevenPriorityItem);
		if (linkedList.retrieve(0) == higherPriorityItem && linkedList.retrieve(1) == firstItem) {
			System.out.println("Test 12: Passed");
		} else {
			System.out.println("Test 12: Failed");
		}
		
		//test 13: removeHead()
		//attempting to removeHead on an empty list should return null
		if (emptyList.removeHead() == null) {
			System.out.println("Test 13: Passed");
		} else {
			System.out.println("Test 13: Failed");
		}

		//test 14: removeHead()
		//removeHead returns the removed task, which has the head prior to removal
		if (linkedList.removeHead() == higherPriorityItem) {
			System.out.println("Test 14: Passed");
		} else {
			System.out.println("Test 14: Failed");
		}
		
		//test 15: removeHead()
		//removeHead removes the first task (the head) from the list
		//list is initially (10, 212), (10, 198); after calling removeHead, it should be just (10, 198) a.k.a. secondItem
		linkedList.removeHead();
		if (linkedList.retrieve(0) == secondItem) {
			System.out.println("Test 15: Passed");
		} else {
			System.out.println("Test 15: Failed");
		}
	}	
}