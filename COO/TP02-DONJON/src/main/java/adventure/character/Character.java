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

import adventure.room.Room;

public abstract class Character {
	//ATTRIBUTS
	/*room of character*/
	protected Room currentRoom;
	/*life of character*/
	protected int life;
	/*strength of character*/
	protected int strength;
	/*gold of character*/
	protected int gold;
	/*name of character*/
	protected String name;
	
	//COSTRUCTOR
	/**
	 * Create a character
	 * @param room : (type-Room) the room where the character is located
	 * @param life : (type-int) life of character
	 * @param strength : (type-int) strength of character
	 * @param gold : (type-int) gold of character
	 * @param name : (type-String) name of character
	 */
	public Character(Room room, int life, int strength, int gold, String name) {
		this.currentRoom = room;
		this.life = life;
		this.strength = strength;
		this.gold = gold;
		this.name = name;
	}
	
	/**
	 * Create a random character with their room and name define
	 * and you choose the minimum and maximum to create the character life, strength and gold in random
	 * the first element of the lists is the minimum and the second element of the lists is the maximum 
	 * @param room : (type-Room) the room where the character is located
	 * @param choseLife : (type-ArrayList) a list to allow randomInt to choose an integer for life's character
	 * @param choseStrength : (type-ArrayList) a list to allow randomInt to choose an integer for strength's character
	 * @param choseGold : (type-ArrayList) a list to allow randomInt to choose an integer for gold's character
	 * @param name : (type-String) name of character
	 */
	public Character(Room room, ArrayList<Integer> choseLife, ArrayList<Integer> choseStrength, ArrayList<Integer> choseGold,  String name) {
		assert((choseLife.size()==2)&&(choseStrength.size()==2)&&(choseGold.size()==2));
		this.currentRoom = room;
		//choseLife.get(0) vaut la valeur minimale de life
		//choseLife.get(1) vaut la valeur maximale de life
		this.life = randomInt(choseLife.get(0),choseLife.get(1));
		//choseStrength.get(0) vaut la valeur minimale de strength
		//choseStrength.get(1) vaut la valeur maximale de strength
		this.strength= randomInt(choseStrength.get(0),choseStrength.get(1));
		//choseGold.get(0) vaut la valeur minimale de gold
		//choseGold.get(1) vaut la valeur maximale de gold
		this.gold = randomInt(choseGold.get(0),choseGold.get(1));
		this.name = name;
	}
	
	//METHODS
	/**
	 * Create a random integer between min and max
	 * @param min : (type-int)
	 * @param max : (type-int)
	 * @return : (type-int) integer between min and max
	 */
	public int randomInt(int min, int max) {
		return (int)(Math.random()*(max-min))+min;
	}
	
	@Override
	public String toString() {
		return this.getName();
	}
	
	/**
	 * Display the character
	 * @return : (type-String) description of character
	 */
	public String diplayCharacter() {
		if(this.isDead()) {
			return this.name+" is dead.";
		}
		return this.name+" have : "+"\n"
			+"- "+this.life+" life"+"\n"
			+"- "+this.strength+" strength"+"\n"
			+"- "+this.gold+" gold"+"\n";
	}
	
	/**
	 * Character is dead ?
	 * @return : (type-boolean) <code>true</code> if character is dead
	 */
	public boolean isDead() {
		return this.life <= 0;
	}
	
	/**
	 * Return character's life
	 * @return : (type-int) life of character
	 */
	public int getLife() {
		return this.life;
	}
	
	/**
	 * Set character's life
	 * @param l : (type-int) set life of character
	 */
	public void setLife(int l) {
		this.life = l;
	}
	
	/**
	 * Add character's life
	 * @param l : (type-int) life to add (or remove) to character
	 */
	public void addLife(int l) {
		this.life += l;
	}
	
	/**
	 * Return character's strength
	 * @return : (type-int) strength of character
	 */
	public int getStrength() {
		return this.strength;
	}
	
	/**
	 * Set the character's strength
	 * @param s : (type-int) set the strength of character
	 */
	public void setStrength(int s) {
		this.strength = s;
	}
	
	/**
	 * Add strength to this character
	 * @param s : (type-int) add (or remove) strength to character
	 */
	public void addStrength(int s) {
		this.strength += s;
	}
	
	/**
	 * Return the character's gold
	 * @return : (type-int) gold of character
	 */
	public int getGold() {
		return this.gold;
	}
	
	/**
	 * Set gold of character
	 * @param g : (type-in) set the gold charcter
	 */
	public void setGold(int g) {
		this.gold = g;
	}
	
	/**
	 * Change gold of character
	 * @param g : (type-int) add (or remove) gold of character
	 */
	public void changeGold(int g) {
		this.gold += g;
	}
	
	/**
	 * Get name of character
	 * @return : (type-String) name of character
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Get the life after damages take by character
	 * @param damage : (type-int) damage taken by another character 
	 * @return : (type-int) the new life of character
	 */
	public int beHit(int damage) {
		if(damage >= this.life){
			this.die();
			return 0;
		}else {
			this.addLife(-(damage));
			System.out.println(this.getName()+" is hit and loose "+damage+" and now this points life are "+this.getLife());
			return this.getLife();
		}
	}
	
	/**
	 * Set the death player
	 */
	public abstract void die();
	
	/**
	 * A character attack an other character
	 * @param character : (type-Character) character attacked
	 */
	public void attack(Character character) {
		System.out.println(this.getName()+" attacks "+character.getName());
		character.beHit(this.strength);
	}
	
	/**
	 * Get current room
	 * @return : (type-Room) the current room
	 */
	public Room getRoom() {
		return this.currentRoom;
	}
	
	/**
	 * Set the current room
	 * @param newCurrentRoom : (type-Room) the new current room of character
	 */
	public void setRoom(Room newCurrentRoom) {
		this.currentRoom = newCurrentRoom;
	}
}
