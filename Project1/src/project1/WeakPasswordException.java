package project1;
/**
 * @author Wonjae Kim
 */

public class WeakPasswordException extends Exception {
	private static final long serialVersionUID = 1L;

	public WeakPasswordException(String message) {
		super(message);
	}
}
