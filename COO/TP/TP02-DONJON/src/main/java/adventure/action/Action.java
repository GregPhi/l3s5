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

public abstract class Action {
	//ATTRIBUTS
	/*name of action*/
	protected String name;
	
	//CONSTRUCTOR
	/**
	 * Create an action
	 * @param name : (type-String) name of action
	 */
	public Action(String name) {
		this.name = name;
	}
	
	//METHODS
	@Override
	public String toString() {
		return this.name;
	}
	
	/**
	 * Execute this action for a player
	 * @param p : (type-Player) the player who performs an action
	 */
	public abstract void execute(Player p);
}
