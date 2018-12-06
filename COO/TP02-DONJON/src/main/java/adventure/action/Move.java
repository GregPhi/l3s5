/**
 * Projet DUNGEON
 * 
 * Hugot Jean-Michel
 * Philippot Grégoire
 * 
 * Groupe 3
 */

package adventure.action;

import adventure.Direction;
import adventure.ListChoser;
import adventure.character.Player;

public class Move extends Action{
	//CONSTRUCTOR
	/**
	 * Create the move action
	 */
	public Move() {
		super("Move");
	}
	
	//METHODS
	@Override
	public void execute(Player player) {
		ListChoser lc = new ListChoser();
		Direction d = lc.chose("Choose the direction you want to go :",player.getRoom().getDirection());
		if(player.travel(d)) {
			if(player.getRoom().playerIsAlreadyKnow()) {
				player.act();
			}else {
				player.defaultAction();
			}
		}else {
			player.act();
		}
	}
}
