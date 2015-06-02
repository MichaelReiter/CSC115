/**
 * MazeExtra.java
 * Michael Reiter
 * V00831568
 * CSC 115 Assignment Three
 * This class implements extra work for the Maze class.
 * It requires makes use of Random and Linked List classes in an algorithm that randomly determines
 * which direction (up, down, left, or right) to try first when finding a path through the maze.
 * This file specifically expands on findPath() and creates a new method, randomInt().
 * March 4, 2015
 */

import java.util.Random;
import java.util.LinkedList;

public class MazeExtra {

	private String[] textmaze;
	private int startRow, startCol, finishRow, finishCol;
	private boolean[][] mazeOfBools;
	private MazeLocationListRefBased mazeLocationList = new MazeLocationListRefBased();

	public MazeExtra(String[] textmaze, int startRow, int startCol, int finishRow, int finishCol) {
		this.textmaze = textmaze;
		this.startRow = startRow;
		this.startCol = startCol;
		this.finishRow = finishRow;
		this.finishCol = finishCol;
		this.mazeOfBools = stringArrayToBooleanArray(textmaze);
	}

    /**
     * Solves a maze.
     * @return a MazeLocationList of the path through a maze if there is one, otherwise null
     */
	public MazeLocationList solve() {
		if (findPath(this.startRow, this.startCol, this.finishRow, this.finishCol) == true) {
			//System.out.println("solved");
			return this.mazeLocationList;
		} else {
			return null;
		}
	}

	/**
     * Taskes to and from rows and columns and finds and recursively path retween them.
     * @param fromRow the row from which it begins
     * @param fromCol the column from which it begins
     * @param toRow the row at which it ends
     * @param toCol the column at which it ends
     * @return true once a path is found recursively
     */
	private boolean findPath(int fromRow, int fromCol, int toRow, int toCol) {
		//if to position is out of bounds
		if (fromRow > this.textmaze.length - 1 || fromRow < 0 || fromCol > this.textmaze[0].length() - 1 || fromCol < 0) {
			return false;
		}

		//if to position is not open
		if (this.mazeOfBools[fromRow][fromCol] == true) {
			return false;
		}

		MazeLocation mazeLocation = new MazeLocation(fromRow, fromCol);
		this.mazeLocationList.insertTail(mazeLocation);

		//mark visited path
		this.mazeOfBools[fromRow][fromCol] = true;

		//base case: if to position is the exit
		if (fromRow == toRow && fromCol == toCol) {
			return true;
		}

		LinkedList<String> listOfDirections = new LinkedList<String>();
		listOfDirections.add("up");
		listOfDirections.add("down");
		listOfDirections.add("left");
		listOfDirections.add("right");

		LinkedList<String> randomOrderList = new LinkedList<String>();

		for (int i = 0; i < 4; i++) {
			int rand = randomInt(listOfDirections.size());
			randomOrderList.add(listOfDirections.get(rand));
			listOfDirections.remove(rand);
		}

		for (int i = 0; i < 4; i++) {
			switch (randomOrderList.get(i)) {
				case "up":
					if (findPath(fromRow - 1, fromCol, toRow, toCol) == true) {
						return true;
					}
					break;
				case "down":
					if (findPath(fromRow + 1, fromCol, toRow, toCol) == true) {
						return true;
					}
					break;
				case "left":
					if (findPath(fromRow, fromCol - 1, toRow, toCol) == true) {
						return true;
					}
					break;
				case "right":
					if (findPath(fromRow, fromCol + 1, toRow, toCol) == true) {
						return true;
					}
					break;
			}
		}

		//remove dead end paths from location list
		this.mazeLocationList.removeTail();

		return false;
	}

	/**
     * Returns a textual representation of Maze.
     * @return a string representing Maze
     */
	public String toString() {
		StringBuilder mazeString = new StringBuilder();
		for (int j = 0; j < mazeOfBools.length; j++) {
			for (int k = 0; k < mazeOfBools[j].length; k++) {
				if (mazeOfBools[j][k]) {
					mazeString.append("*");
				} else {
					mazeString.append(" ");
				}
			}
			mazeString.append("\n");
		}
		return mazeString.toString();
	}

	/**
     * Converts an array of strings into a 2D array of booleans
     * @return the input string array converted to a 2D array of booleans
     */ 
	//written with the assistance of my lab TA
	private boolean[][] stringArrayToBooleanArray(String[] stringArray) {
		int stringArrayLength = stringArray.length;	//rows
		int stringLength = stringArray[0].length();	//columns
		if (stringArray == null || stringArray[0] == null || stringArrayLength == 0 || stringLength == 0) {
			return null;
		}
		boolean[][] booleanArray = new boolean[stringArrayLength][stringLength];
		for (int j = 0; j < stringArrayLength; j++) {
			for (int k = 0; k < stringLength; k++) {
				booleanArray[j][k] = (stringArray[j].charAt(k) == '*' ? true : false);
			}
		}
		return booleanArray;
	}

	/**
     * Generates a pseudorandom integer between 0 and max - 1
     * @param max the upper bound for random int generation
     * @return a pseudorandom integer between 0 and max - 1
     */ 
	private int randomInt(int max) {
		Random random = new Random();
		return random.nextInt(max);
	}
}