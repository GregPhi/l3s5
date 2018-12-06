/**
 * Project DUNGEON
 * 
 * Hugot Jean-Michel
 * Philippot Grégoire
 * 
 * Group 3
 */

package adventure.character;

import static org.junit.Assert.*;
import org.junit.Test;
import adventure.Direction;
import adventure.action.*;
import adventure.room.*;

public class PlayerTest {

	@Test
	public void testGetter() {
		Room room = new Room("test");
		Player player = new Player(room,10,10,10,"test");
		assertEquals(player.getRoom().toString(),room.toString());
		assertEquals(player.getLife(),10);
		assertEquals(player.getStrength(),10);
		assertEquals(player.getGold(),10);
		assertEquals(player.getName(),"test");
	}
	
	@Test
	public void testDie() {
		Room room = new Room("test");
		Player player = new Player(room,10,10,10,"test");
		assertEquals(player.getLife(),10);
		player.die();
		assertEquals(player.getLife(),0);
	}
	
	@Test
	public void testAction() {
		Room room = new Room("test");
		Player player = new Player(room,10,10,10,"test");
		assertTrue(player.availableActions().isEmpty());
		Look look = new Look();
		player.addAction(look);
		assertEquals(player.availableActions().get(0),look);
	}
	
	@Test
	public void testTravelWhenAGoodDirection() {
		Room room = new Room("test1");
		Room room2 = new Room("test2");
		room.addNeighbours(Direction.NORTH, room2);
		Player player = new Player(room,10,10,10,"test");
		assertEquals(player.getRoom(),room);
		player.travel(Direction.NORTH);
		assertEquals(player.getRoom(), room2);
	}
	
	@Test (expected = NoRoomInThisDirectionException.class)
	public void testTravelWhenNotAGoodDirection() {
		Room room = new Room("test1");
		Room room2 = new Room("test2");
		room.addNeighbours(Direction.NORTH, room2);
		Player player = new Player(room,10,10,10,"test");
		assertEquals(player.getRoom(),room);
		player.travel(Direction.WEST);
	}
	
	@Test
	public void testMonsterAttackAPlayer() {
		Room room = new Room("test");
		Player player = new Player(room,10,10,10,"test");
		int life = player.getLife();
		Monster monster = new Monster(room,5,5,5,"testM");
		monster.attack(player);
		assertEquals(player.getLife(),life-monster.getStrength());
		monster.attack(player);
		assertEquals(player.getLife(),0);
	}
}
