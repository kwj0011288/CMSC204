package project1;
/**
 * @author Wonjae Kim
 */
public class NoLowerAlphaException extends Exception {
	private static final long serialVersionUID = 1L;

	public NoLowerAlphaException(String message) {
		super(message);
	}
}
