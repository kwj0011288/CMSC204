/**
 * @author Wonjae Kim
 */
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NotationStudentTest {
	public String complexInfix = "(4+(7-3))*((6+2)/4)";
	public String complexPostfix = "473-+62+4/*";
	public String easyInfix = "5%4";
	public String easyPostfix = "54%";
	public String intermediateInfix = "(7-3)*(4+2)";
	public String intermediatePostfix = "73-42+*";

	public String invalidPostfixExpression = "354+*-";
	public String invalidInfixExpression = "(3+5)*4)-2";

	public double evalComplexPostfix = 16.0;
	public double evalIntermediatePostfix = 24.0;
	public double evalEasyPostfix = 1.0;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void infixToPostFixTest() throws InvalidNotationFormatException {
		assertEquals(easyPostfix, Notation.convertInfixToPostfix(easyInfix));
		assertEquals(intermediatePostfix, Notation.convertInfixToPostfix(intermediateInfix));
		assertEquals(complexPostfix, Notation.convertInfixToPostfix(complexInfix));
	}

	@Test
	public void postFixtoInfixTest() throws InvalidNotationFormatException {
		assertEquals(easyInfix, Notation.convertPostfixToInfix(easyPostfix));
		assertEquals(intermediateInfix, Notation.convertPostfixToInfix(intermediatePostfix));
		assertEquals(complexInfix, Notation.convertPostfixToInfix(complexPostfix));
	}

	@Test
	public void evaluatePostfixExpressionTest() throws InvalidNotationFormatException {
		double result1 = Notation.evaluatePostfixExpression(easyPostfix);
		double result2 = Notation.evaluatePostfixExpression(intermediatePostfix);
		double result3 = Notation.evaluatePostfixExpression(complexPostfix);
		assertEquals(evalEasyPostfix, result1, .001);
		assertEquals(evalIntermediatePostfix, result2, .001);
		assertEquals(evalComplexPostfix, result3, .001);
	}

	@Test
	public void invalidInfixExpressionTest() {
		try {
			Notation.convertInfixToPostfix(invalidInfixExpression);
			assertTrue("Exception", false);
		} catch (InvalidNotationFormatException e) {
			assertTrue("This should be thrown", true);
		}
	}

	@Test
	public void invalidPostfixExpressionTest() {
		try {
			Notation.convertPostfixToInfix(invalidPostfixExpression);
			assertTrue("Exception", false);
		} catch (InvalidNotationFormatException e) {
			assertTrue("This should be thrown", true);
		}
	}

}
