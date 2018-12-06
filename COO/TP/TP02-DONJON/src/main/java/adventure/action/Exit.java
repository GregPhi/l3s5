/**
 * Project DUNGEON
 * 
 * Hugot Jean-Michel
 * Philippot Grégoire
 * 
 * Group 3
 */

package adventure.action;

import adventure.character.Player;

public class Exit extends Action{
	//CONSTRUCTOR
	/**
	 * Create the exit action
	 */
	public Exit() {
		super("Exit");
	}
	
	//METHODS
	@Override
	public void execute(Player player) {
		System.out.println();
		System.out.println("You found the exit of dungeon, alive.... Congratulations.");
	}
}
