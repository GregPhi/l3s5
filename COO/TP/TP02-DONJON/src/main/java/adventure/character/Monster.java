/**
 * Project DUNGEON
 * 
 * Hugot Jean-Michel
 * Philippot Grégoire
 * 
 * Group 3
 */

package adventure.character;

import java.util.ArrayList;

import adventure.item.PurseGold;
import adventure.room.Room;

public class Monster extends Character{
	//CONSTRUCTOR
	/**
	 * Create a monster
	 * @param room : (type-Room) the room where the monster is located
	 * @param life : (type-int) life of monster
	 * @param strength : (type-int) strength of monster
	 * @param gold : (type-int) gold of monster
	 * @param name : (type-String) name of monster
	 */
	public Monster(Room room, int life, int strength, int gold, String name) {
		super(room, life, strength, gold, name);
	}
	
	/**
	 * Create a random monster with their room and name define
	 * and you choose the minimum and maximum to create the monster's life, strength and gold in random
	 * the first element of the lists is the minimum and the second element of the lists is the maximum 
	 * @param room : (type-Room) the room where the monster is located
	 * @param choseLife : (type-ArrayList) a list to allow randomInt to choose an integer for life's monster
	 * @param choseStrength : (type-ArrayList) a list to allow randomInt to choose an integer for strength's monster
	 * @param choseGold : (type-ArrayList) a list to allow randomInt to choose an integer for gold's monster
	 * @param name : (type-String) name of monster
	 */
	public Monster(Room room, ArrayList<Integer> choseLife, ArrayList<Integer> choseStrength, ArrayList<Integer> choseGold,  String name) {
		super(room, choseLife, choseStrength, choseGold, name);
	}

	//METHODS
	@Override
	public void die() {
		this.setLife(0);
		System.out.println(this.getName()+" is dead.");
		if(this.getGold()>0) {
			System.out.println(this.getName()+" drops a purse of gold.");
			PurseGold purse = new PurseGold(this.getGold());
			this.getRoom().addItem(purse);
		}
		this.getRoom().removeMonster(this);
	}

}
