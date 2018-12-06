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

public class CharacterTest {

	public class CharacterMock extends Character{
		public CharacterMock(Room room, int life, int strength, int gold, String name){
			super(room,life,strength,gold,name);
		}

		@Override
		public void die() {
			this.setLife(0);
		}
		
	}
	
	@Test
	public void testGetter(){
		Room room = new Room("testR");
		Character charac = new CharacterMock(room,10,10,10,"testC");
		assertEquals(charac.getRoom(),room);
		assertEquals(charac.getName(),"testC");
		assertEquals(charac.getLife(),10);
		assertEquals(charac.getStrength(),10);
		assertEquals(charac.getGold(),10);
	}
	
	@Test
	public void testChange(){
		Room room = new Room("testR");
		Character charac = new CharacterMock(room,10,10,10,"testC");
		charac.changeGold(5);
		charac.addLife(5);
		charac.addStrength(5);
		assertEquals(charac.getLife(),15);
		assertEquals(charac.getStrength(),15);
		assertEquals(charac.getGold(),15);
		Room room2 = new Room("testR2");
		charac.setRoom(room2);
	}
	
	@Test
	public void testDead(){
		Room room = new Room("testR");
		Character charac = new CharacterMock(room,10,10,10,"testC");
		charac.die();
		assertTrue(charac.isDead());
	}
	
	@Test
	public void testAttack(){
		Room room = new Room("testR");
		Character charac = new CharacterMock(room,10,10,10,"testC");
		Character charac2 = new CharacterMock(room,5,5,5,"testCAttack");
		charac.attack(charac2);
		assertTrue(charac2.isDead());
	}

}
