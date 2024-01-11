package project1;

/**
 * @author Wonjae Kim
 */

public class InvalidSequenceException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidSequenceException(String message) {
		super(message);
	}
}
