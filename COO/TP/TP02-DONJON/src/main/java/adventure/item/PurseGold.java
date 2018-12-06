/**
 * Project DUNGEON
 * 
 * Hugot Jean-Michel
 * Philippot Grégoire
 * 
 * Group 3
 */

package adventure.item;

import java.util.ArrayList;

import adventure.character.Player;

public class PurseGold extends Item{
	//ATTRIBUTS
	/*gold gain by this purse*/
	private int gold;
	/*description of gold purse*/
	private String desc;
	
	//CONSTRUCTOR
	/**
	 * Create a purse gold
	 * @param gold : (type-int) gold of purse
	 */
	public PurseGold(int gold){
		this.gold = gold;
		this.defineDesc();
	}
	
	/**
	 * Create a random strength potion
	 * @param choseGold : (type-ArrayList) a list to allow randomInt to choose an integer for gold's purse
	 */
	public PurseGold(ArrayList<Integer> choseGold) {
		assert(choseGold.size()==2);
		//choseGold.get(0) vaut la valeur minimale de gold
		//choseGold.get(1) vaut la valeur maximale de gold
		this.gold = randomInt(choseGold.get(0),choseGold.get(1));
		this.defineDesc();
	}
	
	//METHODS
	/**
	 * Define the description of this purse in relation to the golden pound it gives
	 */
	public void defineDesc() {
		if(this.getGold()<20){
			this.desc = "Small gold purse";
		}if((this.getGold()>=20)&&(gold<50)){
			this.desc = "Medium gold purse";
		}if(this.getGold()>=50){
			this.desc = "Large gold purse";
		}
	}
	
	/**
	 * Get gold of this purse
	 * @return : (type-int) the gold of purse
	 */
	public int getGold(){
		return this.gold;
	}
	
	@Override
	public String toString(){
		return this.desc;
	}
	
	@Override
	public void isUsedBy(Player player){
		System.out.println(player.getName()+" found a "+this.desc+" with "+this.gold+" gold");
		player.changeGold(this.gold);
		player.getRoom().removeItem(this);
	}
}
