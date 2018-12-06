/**
 * Project DUNGEON
 * 
 * Hugot Jean-Michel
 * Philippot Grégoire
 * 
 * Group 3
 */

package adventure.character;

import java.util.*;

import adventure.Direction;
import adventure.ListChoser;
import adventure.action.*;
import adventure.item.Enigme;
import adventure.room.*;

public class Player extends Character{
	//ATRIBUTS
	/*available action can make by player*/
	protected HashMap<String, Action> availableAction;
	
	//CONSTRUCTOR
	/**
	 * Create a player
	 * @param room : (type-Room) the room where the player is located
	 * @param life : (type-int) life of player
	 * @param strength : (type-int) strength of player
	 * @param gold : (type-int) gold of player
	 * @param name : (type-String) name of player
	 */
	public Player(Room room, int life, int strength, int gold, String name) {
		super(room, life, strength, gold, name);
		this.availableAction = new HashMap<String, Action>();
	}
	
	/**
	 * Create a random player with their room and name define
	 * and you choose the minimum and maximum to create the player life, strength and gold in random
	 * the first element of the lists is the minimum and the second element of the lists is the maximum 
	 * @param room : (type-Room) the room where the player is located
	 * @param choseLife : (type-ArrayList) a list to allow randomInt to choose an integer for life's player
	 * @param choseStrength : (type-ArrayList) a list to allow randomInt to choose an integer for strength's player
	 * @param choseGold : (type-ArrayList) a list to allow randomInt to choose an integer for gold's player
	 * @param name : (type-String) name of player
	 */
	public Player(Room room, ArrayList<Integer> choseLife, ArrayList<Integer> choseStrength, ArrayList<Integer> choseGold,  String name) {
		super(room, choseLife, choseStrength, choseGold, name);
		this.availableAction = new HashMap<String, Action>();
	}
	
	//METHODS
	/**
	 * Return the list of available action
	 * @return : (type-List) list of available action
	 */
	public List<Action> availableActions(){
		List<Action> list = new ArrayList<Action>(this.availableAction.values());
		return list;
	}
	
	/**
	 * Get action
	 * @param s : (type-String) name of action
	 * @return : (type-Action) an action
	 */
	public Action getAction(String s){
		return this.availableAction.get(s);
	}
	
	/**
	 * Add available action
	 * @param a : (type-Action) add an action
	 */
	public void addAction(Action a){
		this.availableAction.put(a.toString(), a);
	}
	
	/**
	 * Remove an available action
	 * @param s : (type-String) name of action
	 */
	public void removeAction(String s){
		Action a = this.getAction(s);
		System.out.println(a.toString());
		this.availableAction.remove((Object)a);
	}
	
	/**
	 * Make the default action
	 */
	public void defaultAction(){
		this.availableAction.clear();
		this.addAction(new Look());
		this.choseAction().execute(this);
	}
	
	/**
	 * Actualize the available action
	 */
	public void actualizeAction(){
		this.availableAction.clear();
		this.addAction(new Look());
		Room r = this.getRoom();
		if(!r.getItems().isEmpty()){
			this.addAction(new Use());
		}if(!r.getMonsters().isEmpty()){
			this.addAction(new Fight());
		}else{
			if(r.getItems().isEmpty()||!(r.getItems().get(0) instanceof Enigme)){
				this.addAction(new Move());
			}
		}
		if(r.isTrap()) {
			System.out.println(this.name+" enters a room, the door closes and walls come out of the peaks. "
					+ "The walls begin to advance. "
					+ "You can not do anything .... "
					+this.name+" says : It's a trap."
					+ "You're broken.");
			this.die();
		}
		if(r.isExit()){
			this.addAction(new Exit());
		}
	}
	
	/**
	 * Make an action
	 */
	public void act(){
		System.out.println();
		System.out.println(this.diplayCharacter());
		this.actualizeAction();
		this.choseAction().execute(this);
	}
	
	/**
	 * Chose an action
	 * @return : (type-Action) an action chooses
	 */
	public Action choseAction(){
		ListChoser lc = new ListChoser();
		Action chosenAction = lc.chose("What do you want to do ?",this.availableActions());
		return chosenAction;
	}
	
	@Override
	public void die() {
		this.setLife(0);
		System.out.println("I die... help me...");
		System.out.println(this.getName()+" is dead.");
		System.exit(0);
	}
	
	/**
	 * Move the player to a direction, and return if it's make
	 * @param direc : (type-Direction) direction to go
	 * @return : (type-boolean) <code>true</code> if the travel is possible
	 */
	public boolean travel(Direction direc){
		try{
			Room r = this.getRoom().getNeighbour(direc);
			this.setRoom(r);
			System.out.println("You successfully went to the on the "+direc.toString());
			return true;
		}catch(NoRoomInThisDirectionException e){
			System.out.println("There is no room in this direction "+direc+" ! You stay in this room");
			return false;
		}
	}

}
