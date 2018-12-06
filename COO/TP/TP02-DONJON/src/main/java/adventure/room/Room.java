/**
 * Project DUNGEON
 * 
 * Hugot Jean-Michel
 * Philippot Grégoire
 * 
 * Group 3
 */

package adventure.room;

import java.util.*;

import adventure.*;
import adventure.character.*;
import adventure.item.*;

public class Room {
	//ATTRIBUTS
	/*monster(s) in this room*/
	protected List<Monster> monsters;
	/*item(s) in this room*/
	protected List<Item> items;
	/*neighbours of this room*/
	protected Map<Direction, Room> neighbours;
	/*name of this room*/
	protected String name;
	/*player is already visit this room ?*/
	protected boolean playerIsAlreadyKnow = false;
	/*room is the exit ?*/
	protected boolean isExit = false;
	/*room is a trap ?*/
	protected boolean isTrap = false;
	
	//CONSTRUCTOR
	/**
	 * Create a room with a name
	 * @param name : (type-String) name of room
	 */
	public Room(String name){
		this.monsters = new ArrayList<Monster>();
		this.items = new ArrayList<Item>();
		this.neighbours = new HashMap<Direction, Room>();
		this.name = name;
	}
	
	//METHODS
	@Override
	public String toString(){
		String roomDescription = "This is room is name "+this.name+".\n";
		roomDescription+="This room are "+this.neighbours.size()+" door(s).\n";
		if(!this.monsters.isEmpty()){
			roomDescription+="This room are "+this.monsters.size()+" monster(s).\n";
			//roomDescription+="To continue your adventure, you must kill the monster/ all monsters.\n";
		}
		if(!this.items.isEmpty()) {
			roomDescription+="This room are "+this.items.size()+" item(s)."+"\n";
			//roomDescription+="You can continue your adventure without recovering the items.\n";
		}
		if(this.isExit()) {
			roomDescription+="This room is an exit.";
		}
		if(this.isTrap()) {
			roomDescription+="This room is very strange.";
		}
		return roomDescription;
	}
	
	/**
	 * Add neighbours to this room
	 * @param d : (type-Direction) direction to go 
	 * @param r : (type-Room) neighbour room
	 */
	public void addNeighbours(Direction d, Room r) {
		this.neighbours.put(d, r);
		r.neighbours.put(d.opposite(), this);
	}
	
	/**
	 * Get neighbours to this room
	 * @param d : (type-Direction) direction to go
	 * @return : (type-Room) neighbur room
	 * @throws NoRoomInThisDirectionException : no room in this direction
	 */
	public Room getNeighbour(Direction d) throws NoRoomInThisDirectionException{
		if(this.neighbours.containsKey(d)) {
			return this.neighbours.get(d);
		}else {
			throw new NoRoomInThisDirectionException();
		}
	}
	
	/**
	 * Get neighbours
	 * @return : (type-List) list of neighbour
	 */
	public List<Room> getNeighbours(){
		List<Room> list =new ArrayList<Room>(this.neighbours.values());
		return list;
	}
	
	/**
	 * Get directions
	 * @return : (type-List) list of direction
	 */
	public List<Direction> getDirection(){
		List<Direction> list = new ArrayList<Direction>(this.neighbours.keySet());
		return list;
	}
	
	/**
	 * Get monsters
	 * @return : (type-List) list of monsters
	 */
	public List<Monster> getMonsters(){
		List<Monster> list = new ArrayList<Monster>(this.monsters);
		return list;
	}
	
	/**
	 * Add monster in this room
	 * @param m : (type-Monster) monster add
	 */
	public void addMonster(Monster m) {
		this.monsters.add(m);
	}
	
	/**
	 * Remove monster of this room
	 * @param m : (type-Monster) monster remove
	 */
	public void removeMonster(Monster m) {
		this.monsters.remove(m);
	}
	
	/**
	 * Get items
	 * @return : (type-List) list of items
	 */
	public List<Item> getItems(){
		List<Item> list = new ArrayList<Item>(this.items);
		return list;
	}
	
	/**
	 * Add item in this room
	 * @param i : (type-Item) item add
	 */
	public void addItem(Item i) {
		this.items.add(i);
	}
	
	/**
	 * Remove item of this room
	 * @param i : (type-Item) item remove
	 */
	public void removeItem(Item i) {
		this.items.remove(i);
	}
	
	/**
	 * Player is already visit this room ?
	 * @return : (type-boolean) <code>true</code> if player know already this room
	 */
	public boolean playerIsAlreadyKnow() {
		return this.playerIsAlreadyKnow;
	}
	
	/**
	 * Visit :
	 * if player know already this room, show the description of room
	 * else player don't know this room, change the value of playerIsAlreadyKnow and show the description of room
	 */
	public void visit() {
		if(this.playerIsAlreadyKnow) {
			System.out.println("You already visit this room : ");
			System.out.println(this.toString());
		}else {
			this.playerIsAlreadyKnow = true;
			System.out.println("You take a look at the room : ");
			System.out.println(this.toString());
		}
	}
	
	/**
	 * Set a room to an exit
	 */
	public void becomesAnExit() {
		this.isExit = true;
	}
		
	/**
	 * Check if this room is an exit
	 * @return : (type-boolean) <code>true</code> if this room is the exit ?
	 */
	public boolean isExit() {
		return this.isExit;
	}
	
	/**
	 * Set a room to a trap
	 */
	public void becomesAnTrap() {
		this.isTrap = true;
	}
	
	/**
	 * Check if this room is a trap
	 * @return : (type-boolean) <code>true</code> if this room is a trap
	 */
	public boolean isTrap() {
		return this.isTrap;
	}
}
