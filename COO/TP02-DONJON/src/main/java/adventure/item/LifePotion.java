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

public class LifePotion extends Item{
	//ATTRIBUTS
	/*life gain by this potion*/
	private int life;
	/*description of life potion*/
	private String desc;
	
	//CONSTRUCTOR
	/**
	 * Create a life potion
	 * @param life : (type-int) life of potion
	 */
	public LifePotion(int life){
		this.life = life;
		this.defineDesc();
	}
	
	/**
	 * Create a random life potion
	 * @param choseLife : (type-ArrayList)
	 */
	public LifePotion(ArrayList<Integer> choseLife) {
		assert(choseLife.size()==2);
		//choseLife.get(0) vaut la valeur minimale de life
		//choseLife.get(1) vaut la valeur maximale de life
		this.life = randomInt(choseLife.get(0),choseLife.get(1));
		this.defineDesc();
	}
	
	//METHODS
	/**
	 * Get life of this potion
	 * @return : (type-int) a list to allow randomInt to choose an integer for life's potion
	 */
	public int getLife(){
		return this.life;
	}
	
	/**
	 * Defines the description of the potion with relative to the point of life which gives
	 */
	public void defineDesc() {
		if(this.getLife()<20){
			this.desc = "Small potion of life";
		}if((this.getLife()>=20)&&(life<50)){
			this.desc = "Medium potion of life";
		}if(this.getLife()>=50){
			this.desc = "Large potion of life";
		}
	}
	
	@Override
	public String toString(){
		return this.desc;
	}
	
	@Override
	public void isUsedBy(Player player){
		System.out.println(player.getName()+" found a "+this.desc+" with "+this.life+" life");
		player.addLife(this.life);
		player.getRoom().removeItem(this);
	}
}
