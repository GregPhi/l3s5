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

import adventure.room.Room;

public class MonsterTest {

	@Test
	public void testGetter() {
		Room room = new Room("test");
		Monster monster = new Monster(room,10,10,10,"test");
		assertEquals(monster.getRoom(),room);
		assertEquals(monster.getLife(),10);
		assertEquals(monster.getStrength(),10);
		assertEquals(monster.getGold(),10);
		assertEquals(monster.getName(),"test");
	}
	
	@Test
	public void testDie() {
		Room room = new Room("test");
		Monster monster = new Monster(room,10,10,10,"test");
		assertEquals(monster.getLife(),10);
		monster.die();
		assertEquals(monster.getLife(),0);
		assertTrue(!(room.getMonsters().contains(monster)));
		assertEquals(room.getItems().size(),1);
	}
	
	@Test
	public void testPlayerAttackAMonster() {
		Room room = new Room("test");
		Player player = new Player(room,10,10,10,"test");
		Monster monster = new Monster(room,5,5,5,"testM");
		player.attack(monster);
		assertEquals(monster.getLife(),0);
		assertTrue(!(room.getMonsters().contains(monster)));
	}
	
}
