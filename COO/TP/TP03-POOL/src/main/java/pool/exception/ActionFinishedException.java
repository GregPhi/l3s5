/**
 * Project POOL
 * @authors : Hugot Jean-Michel and Philippot Grégoire 
 */

package pool.exception;

public class ActionFinishedException extends Exception{
	//ATTRIBUTS
	private static final long serialVersionUID = 1L;

	//CONSTRUCTOR
	/**
	 * Create a action finished exception
	 */
	public ActionFinishedException(){
		super();
	}
	
	/**
	 * Create a action finished exception
	 * @param msg : (type-String) the message of exception
	 */
	public ActionFinishedException(String msg){
		super(msg);
	}
}
