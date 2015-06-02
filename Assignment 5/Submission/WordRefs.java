import java.util.LinkedList;

/**
 * @author Mike Zastre, UVic
 *
 * Modified by Michael Reiter to support integers rather than Strings for extra work.
 *
 * Part of CSC 115, Spring 2015, Assignment #5
 */

public class WordRefs {
    private int number;
    private LinkedList<Integer> lineNumbers;

    public WordRefs(int number) {
        this.number = number;
        lineNumbers = new LinkedList<>();
    }

    public void addLine(int lineNumber) {
        lineNumbers.add(lineNumber);
    }

    public int getNumber() {
        return number;
    }

    public LinkedList<Integer> getLineNumbers() {
        return lineNumbers;
    }
   
    public String toString() {
        String result = number + ":";

        for (Integer ii : lineNumbers) {
            result += " " + ii;
        }

        return result;
    }
}
