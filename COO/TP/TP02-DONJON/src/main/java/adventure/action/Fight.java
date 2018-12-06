/**
 * Project DUNGEON
 * 
 * Hugot Jean-Michel
 * Philippot Grégoire
 * 
 * Group 3
 */

package adventure.action;

import adventure.ListChoser;
import adventure.character.Monster;
import adventure.character.Player;

public class Fight extends Action{
	//CONSTRUCTOR
	/**
	 * Create the fight action
	 */
	public Fight() {
		super("Fight");
	}
	
	//METHODS
	@Override
	public void execute(Player player) {
		ListChoser lc = new ListChoser();
		Monster m = lc.chose("Choose you target :",player.getRoom().getMonsters());
		fight(player,m);
		if(player.isDead()) {
			System.out.println(m.getName()+" killed you...."+"\n");
			player.die();
		}else {
			player.act();
		}
	}
	
	/**
	 * Fight between the player and a monster
	 * @param player : (type-Player) the attacking player
	 * @param monster : (type-Monster) the monster attacked
	 */
	public void fight(Player player,Monster monster) {
		while(!player.isDead() && !monster.isDead()) {
			player.attack(monster);
			if(!monster.isDead()) {
				monster.attack(player);
			}
		}
	}
}
