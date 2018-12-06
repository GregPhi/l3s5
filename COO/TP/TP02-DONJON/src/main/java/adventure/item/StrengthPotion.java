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

public class StrengthPotion extends Item{
	//ATTRIBUTS
	/*strength gain by this potion*/
	private int strength;
	/*description of strength potion*/
	private String desc;
	
	//CONSTRUCTOR
	/**
	 * Create a strength potion
	 * @param strength : (type-int) strength of potion
	 */
	public StrengthPotion(int strength){
		this.strength = strength;
		this.defineDesc();
	}
	
	/**
	 * Create a random strength potion
	 * @param choseStrength : (type-ArrayList) a list to allow randomInt to choose an integer for strength's potion
	 */
	public StrengthPotion(ArrayList<Integer> choseStrength) {
		assert(choseStrength.size()==2);
		//choseStrength.get(0) vaut la valeur minimale de strength
		//choseStrength.get(1) vaut la valeur maximale de strength
		this.strength = randomInt(choseStrength.get(0),choseStrength.get(1));
		this.defineDesc();
	}
	
	//METHODS
	/**
	 * Defines the description of the potion with relative to the point of strength which gives 
	 */
	public void defineDesc() {
		if(this.getStrength()<20){
			this.desc = "Small potion of strength";
		}if((this.getStrength()>=20)&&(strength<50)){
			this.desc = "Medium potion of strength";
		}if(this.getStrength()>=50){
			this.desc = "Large potion of strength";
		}
	}
	
	
	/**
	 * Get strength of this potion
	 * @return : (type-int) strength of potion
	 */
	public int getStrength(){
		return this.strength;
	}
	
	@Override
	public String toString(){
		return this.desc;
	}
	
	@Override
	public void isUsedBy(Player player){
		System.out.println(player.getName()+" found a "+this.desc+" with "+this.strength+" strength");
		player.addStrength(this.strength);
		player.getRoom().removeItem(this);
	}
}
