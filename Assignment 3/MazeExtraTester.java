/**
 * MazeTester.java
 * Michael Reiter
 * V00831568
 * CSC 115 Assignment Three
 * This class is for testing the public methods of the Maze class
 * March 4, 2015
 */

public class MazeExtraTester {
	
	public static void main(String[] args) {
		//test 1: toString()
		//toString() should return an accurate string representation of the input Maze consisting of spaces and astrisks
		String[] complexMazeString = {
			"* ***********************",
			"* *     * *     *       *",
			"* ***** * * * *** ***** *",
			"*   *   *   * *   *     *",
			"*** * ********* *** *****",
			"* * *           * *     *",
			"* * ********* *** *******",
			"*   *       * *         *",
			"* *** ***** * ******* ***",
			"*     *     *         * *",
			"******* *************** *",
			"*     *             *   *",
			"* * ******* ******* * ***",
			"* *                   * *",
			"********************* ***"
		};
		MazeExtra complexMaze = new MazeExtra(complexMazeString, 0, 1, 14, 21);

		System.out.println(complexMaze.solve());
		
	}
}