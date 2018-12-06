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

public class PurseGoldTest {

	@Test
	public void testGetGold() {
		PurseGold pg = new PurseGold(10);
		assertEquals(pg.getGold(),10);
	}
	
	@Test
	public void testGetDesc() {
		PurseGold pg = new PurseGold(10);
		assertEquals(pg.toString(),"Small gold purse");
	}
	
	@Test
	public void testIsUsedByPlayer() {
		PurseGold pg = new PurseGold(10);
		Room room = new Room("test");
		Player player = new Player(room,10,10,10,"test");
		room.addItem(pg);
		pg.isUsedBy(player);
		assertEquals(player.getGold(),20);
		assertTrue(!(room.getItems().contains(pg)));
	}
}
