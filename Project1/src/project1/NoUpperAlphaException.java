package project1;
/**
 * @author Wonjae Kim
 */
public class NoUpperAlphaException extends Exception {
	private static final long serialVersionUID = 1L;

	public NoUpperAlphaException(String message) {
		super(message);
	}

}
