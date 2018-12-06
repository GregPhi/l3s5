/**
 * Projet DUNGEON
 * 
 * Hugot Jean-Michel
 * Philippot Grégoire
 * 
 * Groupe 3
 */

package adventure.action;

import adventure.character.Player;

public class Look extends Action{
	//CONSTRUCTOR
	/**
	 * Create the look action
	 */
	public Look() {
		super("Look");
	}
	
	//METHODS
	@Override
	public void execute(Player player) {
		player.getRoom().visit();
		player.act();
	}
}
