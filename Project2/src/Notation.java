import java.util.*;
import java.io.*;

/**
 * @author Wonjae Kim
 */

/*
 * A utility class that converts an infix expression to a postfix expression, a
 * postfix expression to an infix expression and evaluates a postfix expression.
 */
public class Notation {

	/**
	 * Evaluate a postfix and returns the result
	 *
	 * @param postfixExpr the postfix expression in String format
	 * @return the evaluation of the postfix expression as a double
	 * @throws InvalidNotationFormatException - if the postfix expression format is
	 *                                     p   invalid
	 */
	public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException {
		Stack<Double> operatorStack = new Stack<>();
		for (int i = 0; i < postfixExpr.length(); i++) {
			char nextChar = postfixExpr.charAt(i);
			// check nextChar is empty
			if (nextChar == ' ') {
				continue;
				// check nextChar is number
			} else if (Character.isDigit(nextChar)) {
				double num = Character.getNumericValue(nextChar);
				operatorStack.push(num);
				// check nextChar is + or - or or * or /
			} else if (checkOperator(nextChar)) {
				if (operatorStack.size() < 2) {
					throw new InvalidNotationFormatException("Invalid");
				}
				// pop the top two items
				double num2 = operatorStack.pop();
				double num1 = operatorStack.pop();
				double result = calculator(num1, num2, nextChar);
				operatorStack.push(result);
			} else {
				throw new InvalidNotationFormatException("Invalid");
			}
		}
		// at the end only one element left
		if (operatorStack.size() != 1) {
			throw new InvalidNotationFormatException("Invalid");
		}
		return operatorStack.pop();
	}

	/**
	 * Convert the Postfix expression to the Infix expression
	 *
	 * @param the postfix expression in string format
	 * @return the infix expression in string format
	 * @throws InvalidNotationFormatException - if the postfix expression format is
	 *                                        invalid
	 */
	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException {
		Stack<String> operatorStack = new Stack<>();
		for (int i = 0; i < postfix.length(); i++) {
			char nextChar = postfix.charAt(i);
			// check if nextChar is a letter
			if (Character.isLetter(nextChar)) {
				operatorStack.push(String.valueOf(nextChar));

			}
			// check if nextchar is empty
			if (nextChar == ' ') {
				continue;
				// check if nextChar is letter or number
			} else if (Character.isLetterOrDigit(nextChar)) {
				operatorStack.push(String.valueOf(nextChar));
				// check nextChar is + or - or or * or /
			} else if (checkOperator(nextChar)) {
				if (operatorStack.size() < 2) {
					throw new InvalidNotationFormatException("Invalid");
				}
				// pop the top two items
				String letter2 = operatorStack.pop();
				String letter1 = operatorStack.pop();
				String result = "";
				// set up the parentheses
				if (checkParentheses(letter1)) {
					result += "(" + letter1 + ")";
				} else {
					result += letter1;
				}

				result += nextChar;

				if (checkParentheses(letter2)) {
					result += "(" + letter2 + ")";
				} else {
					result += letter2;
				}

				operatorStack.push(result);
			} else {
				throw new InvalidNotationFormatException("Invalid");
			}
		}
		if (operatorStack.size() != 1) {
			throw new InvalidNotationFormatException("Invalid");
		}
		return operatorStack.pop();
	}

	/**
	 * Convert an infix expression into a postfix expression
	 *
	 * @param infix the infix expression in string format
	 * @return the postfix expression in string format
	 * @throws InvalidNotationFormatException - if the infix expression format is
	 *                                        invalid
	 */
	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {

		Stack<Character> operatorStack = new Stack<>();
		// set queue get the result
		Queue<Character> postfixQueue = new LinkedList<>();
		String postfix = "";

		for (int i = 0; i < infix.length(); i++) {
			char nextChar = infix.charAt(i);
			// check if nextChar is letter or number
			if (Character.isLetterOrDigit(nextChar)) {
				postfixQueue.add(nextChar);
			} else {
				// use switch case to find the operator
				switch (nextChar) {
				case '^':
					operatorStack.push(nextChar);
					break;
				case '+':
				case '-':
				case '*':
				case '/':
				case '%':
					// check the if the operator is +- or */
					while (!operatorStack.isEmpty() && getPrecedence(nextChar) <= getPrecedence(operatorStack.peek())) {
						postfixQueue.add(operatorStack.pop());
					}
					operatorStack.push(nextChar);
					break;
				case '(':
					operatorStack.push(nextChar);
					break;
				case ')':
					char topOperator = operatorStack.pop();
					while (topOperator != '(') {
						postfixQueue.add(topOperator);
						if (operatorStack.isEmpty()) {
							throw new InvalidNotationFormatException("Invalid");
						}
						topOperator = operatorStack.pop();
					}
					break;
				}
			}
		}
		// check if operatorStack is not empty or not
		while (!operatorStack.isEmpty()) {
			char topOperator = operatorStack.pop();
			postfixQueue.add(topOperator);
		}
		// check if queue is empty or not
		while (!postfixQueue.isEmpty()) {
			postfix += postfixQueue.poll();
		}
		return postfix;
	}

	// method to check the operator
	public static boolean checkOperator(char operator) {
		if (operator == '+' || operator == '-' || operator == '*' || operator == '/' || operator == '%') {
			return true;
		} else {
			return false;
		}
	}

	// set up the way to calculate letters or digits with the operators
	public static double calculator(double a, double b, char ch) throws InvalidNotationFormatException {
		if (ch == '+') {
			return a + b;
		} else if (ch == '-') {
			return a - b;
		} else if (ch == '*') {
			return a * b;
		} else if (ch == '/') {
			return a / b;
		} else if (ch == '%') {
			return a % b;
		} else {
			throw new InvalidNotationFormatException("Invalid");
		}
	}

	// return number which will let writer to compare the operator
	public static int getPrecedence(char ch) {
		switch (ch) {
		case '+':
		case '-':
			return 1;
		case '*':
		case '/':
		case '%':
			return 2;
		}
		return -1;
	}

	// check when parentheses is needed
	public static boolean checkParentheses(String str) {
		return str.contains("+") || str.contains("-") || str.contains("*") || str.contains("/") || str.contains("%");

	}
}
