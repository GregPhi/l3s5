/**
 * Project DUNGEON
 * 
 * Hugot Jean-Michel
 * Philippot Grégoire
 * 
 * Group 3
 */


package adventure;

public enum Direction {
	//ENUM
	NORTH,EAST,SOUTH,WEST;
	
	//METHODS
	/**
	 * Return a random Direction
	 * @return : (type-Direction) random Direction
	 */
	public Direction alea(){
		Direction[] d = Direction.values();
		int lower = 0;
		int higher = 3;
		int random = (int)(Math.random()*(higher-lower)+lower);
		return d[random];
	}
	
	/**
	 * Return the opposite direction
	 * @return : (type-Direction) the opposite of a Direction, 
	 * for example Direction.NORTH.ooposite() return Direction.SOUTH 
	 */
	public Direction opposite(){
		Direction[] d = Direction.values();
		return d[(this.ordinal()+2)%4];
	}
}
