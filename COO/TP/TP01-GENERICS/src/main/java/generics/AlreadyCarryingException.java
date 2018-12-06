package generics;

public class AlreadyCarryingException extends IllegalStateException{
	//ATTRIBUTS
	private static final long serialVersionUID = 1L;

	//CONSTRUCTOR
	public AlreadyCarryingException() {
		super();
	}

	public AlreadyCarryingException(String msg) {
		super(msg);
	}

	public AlreadyCarryingException(Throwable why) {
		super(why);
	}

	public AlreadyCarryingException(String msg, Throwable why) {
		super(msg,why);
	}
}
