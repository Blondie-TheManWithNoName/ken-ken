package exceptions;

public class GroupDoesNotExistException extends Exception {
	public GroupDoesNotExistException() {
		super("The group does not exist, it might have been removed earlier.");
	}
}
