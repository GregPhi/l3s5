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
import adventure.item.Item;
import adventure.ListChoser;

public class Use extends Action{
	//CONSTRUCTOR
	/**
	 * Create the use action
	 */
	public Use() {
		super("Use");
	}
	
	//METHODS
	@Override
	public void execute(Player player) {
		ListChoser lc = new ListChoser();
		Item item = lc.chose("Choose the item you want to use : ",player.getRoom().getItems());
		item.isUsedBy(player);
		player.act();
	}
}
