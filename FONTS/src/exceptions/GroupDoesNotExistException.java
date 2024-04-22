package exceptions;

/**
 * Exception for when a group does not exist.
 */
public class GroupDoesNotExistException extends Exception {
	/**
	 * Constructor for the exception with the default message.
	 */
	public GroupDoesNotExistException() {
		super("The group does not exist, it might have been removed earlier.");
	}
}
