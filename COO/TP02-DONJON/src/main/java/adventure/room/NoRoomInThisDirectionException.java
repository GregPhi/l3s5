/**
 * Project DUNGEON
 * 
 * Hugot Jean-Michel
 * Philippot Grégoire
 * 
 * Group 3
 */

package adventure.room;

public class NoRoomInThisDirectionException extends Exception{
	private static final long serialVersionUID = 1L;
	
	//CONSTRUCTOR
	/**
	 * Exception : no room in this direction
	 */
	public NoRoomInThisDirectionException() {
		super();
	}
	
	/**
	 * Exception : no room in this directionS
	 * @param msg : (type-String) message to explain the exception
	 */
	public NoRoomInThisDirectionException(String msg) {
		super(msg);
	}
}
