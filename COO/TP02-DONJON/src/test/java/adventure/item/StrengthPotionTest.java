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

public class StrengthPotionTest {

	@Test
	public void testGetSrength() {
		StrengthPotion pg = new StrengthPotion(10);
		assertEquals(pg.getStrength(),10);
	}
	
	@Test
	public void testGetDesc() {
		StrengthPotion pg = new StrengthPotion(10);
		assertEquals(pg.toString(),"Small potion of strength");
	}
	
	@Test
	public void testIsUsedByPlayer() {
		StrengthPotion pg = new StrengthPotion(10);
		Room room = new Room("test");
		Player player = new Player(room,10,10,10,"test");
		room.addItem(pg);
		pg.isUsedBy(player);
		assertEquals(player.getStrength(),20);
		assertTrue(!(room.getItems().contains(pg)));
	}
}
