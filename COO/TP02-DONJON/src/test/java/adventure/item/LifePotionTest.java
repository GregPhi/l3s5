/**
 * Project DUNGEON
 * 
 * Hugot Jean-Michel
 * Philippot Grégoire
 * 
 * Group 3
 */

package adventure.item;

import static org.junit.Assert.*;

import org.junit.Test;

import adventure.character.Player;
import adventure.room.Room;

public class LifePotionTest {

	@Test
	public void testGetLife() {
		LifePotion lp = new LifePotion(10);
		assertEquals(lp.getLife(),10);
	}
	
	@Test
	public void testGetDesc() {
		LifePotion lp = new LifePotion(10);
		assertEquals(lp.toString(),"Small potion of life");
	}
	
	@Test
	public void testIsUsedByPlayer() {
		LifePotion lp = new LifePotion(10);
		Room room = new Room("test");
		Player player = new Player(room,10,10,10,"test");
		room.addItem(lp);
		lp.isUsedBy(player);
		assertEquals(player.getLife(),20);
		assertTrue(!(room.getItems().contains(lp)));
	}
}
