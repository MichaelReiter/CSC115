/**
 * Prepared for UVic CSC 115, Spring 2015
 * Assignment #4
 *
 * Michael Zastre
 * and
 * Michael Reiter
 * V00831568
 * March 7, 2015
 * Note: Some code was written with lab instructor assistance and/or modified from Infix2Postfix.java that Michael Zastre posted on conneX
 */

public class EvalInfix {
    /**
     * First ensure the arithmetic operations plus parentheses
     * are surrounded by spaces. Then go ahead and split up the
     * whole expression using spaces (i.e, the operands will be
     * nicely separated from everything else by at least a single
     * space). This will not work for negative numbers.
     * @param s a string to tokenize
     * @return a string array of tokens
     */
    public static String[] tokenize(String s) {
        // Note the missing minus character...
        String symbols[] = {"\\(", "\\)", "\\+", "\\-", "\\*", "\\/"};

        // First eliminate any quotation marks
        s = s.replaceAll("'", " ");
        s = s.replaceAll("\"", " ");

        // Now all operators or parentheses, surround the character
        // with a single space on either side.
        for (int i = 0; i < symbols.length; i++) {
            String regex = symbols[i];
            String replace = " " + regex + " ";
            s = s.replaceAll(regex, replace);
        }

        // Special case: If there is a unary minus, then then
        // what appears to the right of the symbol is whitespace
        // and a digit; what appears to the left whitespace
        // and a non-digit symbol.
        s = s.replaceAll("(^|([\\+\\-\\*\\/]))\\s+\\-\\s+(\\d+)", "$1 -$3");

        // Eliminate extra whitespaces at start and end of the
        // transformed string. 
        s = s.trim();

        // Finally, take advantage of the whitespace to create an
        // array of strings where each item in the array is one
        // of the elements in the original string passed in to this
        // method.
        return s.split("\\s+");
    }

    /**
     * Checks if a string is an operator or not 
     * @param input a string to check
     * @return true if it is an operator (+, -, *, or /), false otherwise
     */
    public static boolean isOperator(String input) {
        return (input.equals("+") || input.equals("-") || input.equals("*") || input.equals("/"));
    }

    /**
     * Determines operation precedence
     * @param input a string to check
     * @return 4 if / or *, 3 if + or -, 0 otherwise
     */
    public static int precedence(String input) {
        switch (input) {
            case "/":
                return 4;
            case "*":
                return 4;
            case "-":
                return 3;
            case "+":
                return 3;
            default:
                return 0;
        }
    }

    /**
     * Converts an infix string array of tokens to a string list of tokens in postfix
     * @param tokens an array of strings where each item is a token
     * @return a list consisting of each token as a separate node in postfix
     */
    public static StringListRefBased toPostfix(String[] infix) throws StringStackException {
        StringListRefBased postfix = new StringListRefBased();
        StringStackRefBased operatorStack = new StringStackRefBased();
        for (int k = 0; k < infix.length; k++) {
            String infixChar = infix[k];
            if (infixChar.equals("(")) {
                operatorStack.push(infixChar);
            } else if (infixChar.equals(")")) {
                for (;;) {
                    String operator = operatorStack.pop();
                    if (operator.equals("(")) {
                        break;
                    }
                    postfix.insertTail(operator);
                }
            } else if (isOperator(infixChar)) {
                for (;;) {
                    if (operatorStack.isEmpty() || operatorStack.peek().equals("(") || precedence(operatorStack.peek()) < precedence(infixChar)) {
                        operatorStack.push(infixChar);
                        break;
                    }
                    String operator = operatorStack.pop();
                    postfix.insertTail(operator);
                }
            } else {
                postfix.insertTail(infixChar);
            }
        }
        while (!operatorStack.isEmpty()) {
            postfix.insertTail(operatorStack.pop());
        }
        return postfix;
    }

    /**
     * Takes an infix expression as a String and evaluates it
     * @param expr an expression to be evaluated
     * @return the result of the expression provided it is valid
     */
    public static String evaluateExpression(String expr) {
        if (!isBalanced(expr)) {
            return "<unbalanced ()>";
        }
        String[] tokenizedExpression = tokenize(expr);
        StringListRefBased postfixExpression = null;
        String result = null;
        try {
            postfixExpression = toPostfix(tokenizedExpression);
            result = evaluatePostfix(postfixExpression);
        }
        catch (StringStackException stringStackException) {
            System.out.println("Stack exception caught in evaluateExpression()");
            System.exit(1);
        }
        return result;
    }

    /**
     * Takes an expression and checks if it has balanced parantheses
     * @param expr an expression to determine whether or not it's balanced
     * @return whether or not the expression is balanced
     */
    public static boolean isBalanced(String expr) {
        StringStack stringStack = new StringStackRefBased();
        boolean balanced;
        try {
            for (int i = 0; i < expr.length(); i++) {
                if (expr.charAt(i) == '(') {
                    stringStack.push("(");
                } else if (expr.charAt(i) == ')') {
                    stringStack.pop();
                }
            }
            if (stringStack.isEmpty()) {
                balanced = true;
            } else {
                balanced = false;
            }
        } catch (StringStackException stringStackException) {
            balanced = false;
        }
        return balanced;
    }

    /**
     * Takes a postfix expression as a string list and evaluates the expression
     * @param postfix a string list expression to be evaluated
     * @return the result of the expression provided
     */
    public static String evaluatePostfix(StringList postfix) throws StringStackException {
        StringStackRefBased stack = new StringStackRefBased();
        int result = -1;
        String value = null;
        try {
            for (int k = 0; k < postfix.getLength(); k++) {
                String ch = postfix.retrieve(k);
                if (!isOperator(ch)) {
                    stack.push(postfix.retrieve(k));
                } else {
                    int operand2 = Integer.parseInt(stack.pop());
                    int operand1 = Integer.parseInt(stack.pop());

                    switch (postfix.retrieve(k)) {
                        case "+":
                            result = operand1 + operand2;
                            break;
                        case "-":
                            result = operand1 - operand2;
                            break;
                        case "*":
                            result = operand1 * operand2;
                            break;
                        case "/":
                            result = operand1 / operand2;
                            break;
                        default:
                            System.out.println("error");
                    }
                stack.push("" + result);
                }
            }
            value = stack.pop();
            if (!stack.isEmpty()) {
                throw new StringStackException("<invalid syntax>");
            }
        }
        catch (ArithmeticException arithmeticException) {
            System.out.println("<division by zero>");
            System.exit(1);
        }
        catch (StringStackException stringStackException) {
            System.out.println("<syntax error>");
            System.exit(1);
        }
        catch (NumberFormatException numberFormatException) {
            System.out.println("<noninteger>");
            System.exit(1);
        }
        return value;
    }

    public static void main(String args[]) {

        if (args.length < 1) {
            System.err.println("usage: java EvalInfix '<expression>'");
            System.exit(1);
        }

        System.out.println(evaluateExpression(args[0]));
    }
}
