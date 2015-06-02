/**
 * MazeTester.java
 * Michael Reiter
 * V00831568
 * CSC 115 Assignment Three
 * This class is for testing the public methods of the Maze class
 * February 28, 2015
 */

public class MazeTester {
	
	public static void main(String[] args) {
		//test 1: toString()
		//toString() should return an accurate string representation of the input Maze consisting of spaces and astrisks
		String[] inputMaze = {
			"* *********",
			"*     *   *",
			"*** * *** *",
			"*   *     *",
			"********* *",
		};
		Maze maze = new Maze(inputMaze, 0, 1, 4, 9);
		String expectedStringOutput =  "* *********\n"
									 + "*     *   *\n"
									 + "*** * *** *\n"
									 + "*   *     *\n"
									 + "********* *\n";
		if (maze.toString().equals(expectedStringOutput)) {
			System.out.println("Test 1: Passed");
		} else {
			System.out.println("Test 1: Failed");
		}

		//test 2: solve()
		//if there is no path through the maze, solve() should return null
		String[] noSolutionMaze = {
			"* *********",
			"* *********",
			"*     *   *",
			"********* *",
		};
		Maze unsolvableMaze = new Maze(noSolutionMaze, 0, 1, 3, 9);
		if (unsolvableMaze.solve() == null) {
			System.out.println("Test 2: Passed");
		} else {
			System.out.println("Test 2: Failed");
		}

		//test 3: solve()
		//solve() should be able to determine a path straight down from entrance to exit
		String[] downMazeString = {
			"* *",
			"* *",
			"* *",
		};
		Maze downMaze = new Maze(downMazeString, 0, 1, 2, 1);
		MazeLocationList downMazeSolution = downMaze.solve();
		if (downMazeSolution.retrieve(0).equals(new MazeLocation(0, 1))
			&& downMazeSolution.retrieve(1).equals(new MazeLocation(1, 1))
			&& downMazeSolution.retrieve(2).equals(new MazeLocation(2, 1))
			) {
			System.out.println("Test 3: Passed");
		} else {
			System.out.println("Test 3: Failed");
		}

		//test 4: solve()
		//solve() should be able to check if there is a path in any direction
		String[] downRightMazeString = {
			"* **",
			"*  *",
			"** *",
		};
		Maze downRightMaze = new Maze(downRightMazeString, 0, 1, 2, 2);
		MazeLocationList downRightMazeSolution = downRightMaze.solve();
		if (downRightMazeSolution.retrieve(0).equals(new MazeLocation(0, 1))
			&& downRightMazeSolution.retrieve(1).equals(new MazeLocation(1, 1))
			&& downRightMazeSolution.retrieve(2).equals(new MazeLocation(1, 2))
			&& downRightMazeSolution.retrieve(3).equals(new MazeLocation(2, 2))
			) {
			System.out.println("Test 4: Passed");
		} else {
			System.out.println("Test 4: Failed");
		}

		//test 5: 
		//solve() should be able to solve a maze with dead ends by backtracking
		String[] mazeWithDeadEndString = {
			"* ****",
			"*    *",
			"* ** *",
			"* ** *",
			"**** *"
		};
		Maze mazeWithDeadEnd = new Maze(mazeWithDeadEndString, 0, 1, 4, 4);
		MazeLocationList mazeWithDeadEndSolution = mazeWithDeadEnd.solve();
		if (mazeWithDeadEndSolution.retrieve(0).equals(new MazeLocation(0, 1))
			&& mazeWithDeadEndSolution.retrieve(1).equals(new MazeLocation(1, 1))
			&& mazeWithDeadEndSolution.retrieve(2).equals(new MazeLocation(1, 2))
			&& mazeWithDeadEndSolution.retrieve(3).equals(new MazeLocation(1, 3))
			&& mazeWithDeadEndSolution.retrieve(4).equals(new MazeLocation(1, 4))
			&& mazeWithDeadEndSolution.retrieve(5).equals(new MazeLocation(2, 4))
			&& mazeWithDeadEndSolution.retrieve(6).equals(new MazeLocation(3, 4))
			&& mazeWithDeadEndSolution.retrieve(7).equals(new MazeLocation(4, 4))
			) {
			System.out.println("Test 5: Passed");
		} else {
			System.out.println("Test 5: Failed");
		}

		//test 6: 
		//solve() should be able to solve a long complex maze with many dead ends
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
		Maze complexMaze = new Maze(complexMazeString, 0, 1, 14, 21);
		MazeLocationList complexMazeSolution = complexMaze.solve();
		if (mazeWithDeadEndSolution.retrieve(0).equals(new MazeLocation(0, 1))
			&& complexMazeSolution.retrieve(1).equals(new MazeLocation(1, 1))
			&& complexMazeSolution.retrieve(2).equals(new MazeLocation(2, 1))
			&& complexMazeSolution.retrieve(3).equals(new MazeLocation(3, 1))
			&& complexMazeSolution.retrieve(4).equals(new MazeLocation(3, 2))
			&& complexMazeSolution.retrieve(5).equals(new MazeLocation(3, 3))
			&& complexMazeSolution.retrieve(6).equals(new MazeLocation(4, 3))
			&& complexMazeSolution.retrieve(7).equals(new MazeLocation(5, 3))
			&& complexMazeSolution.retrieve(8).equals(new MazeLocation(6, 3))
			&& complexMazeSolution.retrieve(9).equals(new MazeLocation(7, 3))
			&& complexMazeSolution.retrieve(10).equals(new MazeLocation(7, 2))
			&& complexMazeSolution.retrieve(11).equals(new MazeLocation(7, 1))
			&& complexMazeSolution.retrieve(12).equals(new MazeLocation(8, 1))
			&& complexMazeSolution.retrieve(13).equals(new MazeLocation(9, 1))
			&& complexMazeSolution.retrieve(14).equals(new MazeLocation(9, 2))
			&& complexMazeSolution.retrieve(15).equals(new MazeLocation(9, 3))
			&& complexMazeSolution.retrieve(16).equals(new MazeLocation(9, 4))
			&& complexMazeSolution.retrieve(17).equals(new MazeLocation(9, 5))
			&& complexMazeSolution.retrieve(18).equals(new MazeLocation(8, 5))
			&& complexMazeSolution.retrieve(19).equals(new MazeLocation(7, 5))
			&& complexMazeSolution.retrieve(20).equals(new MazeLocation(7, 6))
			&& complexMazeSolution.retrieve(21).equals(new MazeLocation(7, 7))
			&& complexMazeSolution.retrieve(22).equals(new MazeLocation(7, 8))
			&& complexMazeSolution.retrieve(23).equals(new MazeLocation(7, 9))
			&& complexMazeSolution.retrieve(24).equals(new MazeLocation(7, 10))
			&& complexMazeSolution.retrieve(25).equals(new MazeLocation(7, 11))
			&& complexMazeSolution.retrieve(26).equals(new MazeLocation(8, 11))
			&& complexMazeSolution.retrieve(27).equals(new MazeLocation(9, 11))
			&& complexMazeSolution.retrieve(28).equals(new MazeLocation(9, 10))
			&& complexMazeSolution.retrieve(29).equals(new MazeLocation(9, 9))
			&& complexMazeSolution.retrieve(30).equals(new MazeLocation(9, 8))
			&& complexMazeSolution.retrieve(31).equals(new MazeLocation(9, 7))
			&& complexMazeSolution.retrieve(32).equals(new MazeLocation(10, 7))
			&& complexMazeSolution.retrieve(33).equals(new MazeLocation(11, 7))
			&& complexMazeSolution.retrieve(34).equals(new MazeLocation(11, 8))
			&& complexMazeSolution.retrieve(35).equals(new MazeLocation(11, 9))
			&& complexMazeSolution.retrieve(36).equals(new MazeLocation(11, 10))
			&& complexMazeSolution.retrieve(37).equals(new MazeLocation(11, 11))
			&& complexMazeSolution.retrieve(38).equals(new MazeLocation(12, 11))
			&& complexMazeSolution.retrieve(39).equals(new MazeLocation(13, 11))
			&& complexMazeSolution.retrieve(40).equals(new MazeLocation(13, 12))
			&& complexMazeSolution.retrieve(41).equals(new MazeLocation(13, 13))
			&& complexMazeSolution.retrieve(42).equals(new MazeLocation(13, 14))
			&& complexMazeSolution.retrieve(43).equals(new MazeLocation(13, 15))
			&& complexMazeSolution.retrieve(44).equals(new MazeLocation(13, 16))
			&& complexMazeSolution.retrieve(45).equals(new MazeLocation(13, 17))
			&& complexMazeSolution.retrieve(46).equals(new MazeLocation(13, 18))
			&& complexMazeSolution.retrieve(47).equals(new MazeLocation(13, 19))
			&& complexMazeSolution.retrieve(48).equals(new MazeLocation(13, 20))
			&& complexMazeSolution.retrieve(49).equals(new MazeLocation(13, 21))
			&& complexMazeSolution.retrieve(50).equals(new MazeLocation(14, 21))
			) {
			System.out.println("Test 6: Passed");
		} else {
			System.out.println("Test 6: Failed");
		}
	}
}