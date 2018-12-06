/**
 * Project DUNGEON
 * 
 * Hugot Jean-Michel
 * Philippot Grégoire
 * 
 * Group 3
 */

package adventure.room;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import adventure.Direction;
import adventure.character.Monster;
import adventure.character.Player;
import adventure.item.LifePotion;

public class RoomTest {
	
	@Test
	public void testNeighbourWhenRoomInThisDirection() throws NoRoomInThisDirectionException{
		Room room1 = new Room("test1");
		Room room2 = new Room("test2");
		room1.addNeighbours(Direction.NORTH, room2);
		ArrayList<Room> lR = new ArrayList<Room>(room1.getNeighbours());
		assertEquals(lR,room1.getNeighbours());
		ArrayList<Direction> lD = new ArrayList<Direction>(room1.getDirection());
		assertEquals(lD,room1.getDirection());
		assertEquals(room1.getNeighbour(Direction.NORTH),room2);
	}
	
	@Test (expected=NoRoomInThisDirectionException.class)
	public void testNieghbourWhenNoRoomInThisDirection() throws NoRoomInThisDirectionException{
		Room room1 = new Room("test1");
		Room room2 = new Room("test2");
		room1.addNeighbours(Direction.NORTH, room2);
		room1.getNeighbour(Direction.SOUTH);
	}
	
	@Test
	public void testMonsters(){
		Room room1 = new Room("test1");
		Monster m = new Monster(room1,10,10,10,"testM");
		room1.addMonster(m);
		assertEquals(room1.getMonsters().size(),1);
		room1.removeMonster(m);
		assertEquals(room1.getMonsters().size(),0);
	}
	
	@Test
	public void testItems(){
		Room room1 = new Room("test1");
		LifePotion i = new LifePotion(10);
		room1.addItem(i);
		assertEquals(room1.getItems().size(),1);
		room1.removeItem(i);
		assertEquals(room1.getItems().size(),0);
	}
	
	@Test
	public void testPlayerKnowThisRoom(){
		Room r = new Room("t");
		Room r2 = new Room("t2");
		Player p = new Player(r,10,10,10,"testP");
		r.visit();
		assertTrue(r.playerIsAlreadyKnow());
		assertFalse(r2.playerIsAlreadyKnow());
		r.addNeighbours(Direction.NORTH, r2);
		p.travel(Direction.NORTH);
		r2.visit();
		assertTrue(r.playerIsAlreadyKnow());
		assertTrue(r2.playerIsAlreadyKnow());
		
	}
	
}
