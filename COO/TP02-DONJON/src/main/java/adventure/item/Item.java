/**
 * Project DUNGEON
 * 
 * Hugot Jean-Michel
 * Philippot Grégoire
 * 
 * Group 3
 */

package adventure.item;

import adventure.character.Player;

public abstract class Item {
	/**
	 * Create a random int between min and max
	 * @param min : (type-int)
	 * @param max : (type-int)
	 * @return : (type-int) random integer between min and max
	 */
	public int randomInt(int min, int max) {
		return (int)(Math.random()*(max-min))+min;
	}
	
	/**
	 * Player used this item
	 * @param player : (type-Player) player use an item
	 */
	public abstract void isUsedBy(Player player);
}
