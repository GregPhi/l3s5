/**
 * PROJECT QUESTIONNARY
 * @author : Hugot Jean-Michel and Philippot Gregoire
 */

package quiz.answer;

public class BadValueException extends Exception{
	//ATRIBUTS
	/**/
	private static final long serialVersionUID = 1L;
	
	//CONSTRUCTOR
	/**
	 * Bad value exception
	 */
	public BadValueException() {
		super();
	}
	
	/**
	 * Bad value exception
	 * @param msg : (type-String) message
	 */
	public BadValueException(String msg) {
		super(msg);
	}
}
