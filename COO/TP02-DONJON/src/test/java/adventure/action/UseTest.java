/**
 * Project DUNGEON
 * 
 * Hugot Jean-Michel
 * Philippot Grégoire
 * 
 * Group 3
 */

package adventure.action;

import static org.junit.Assert.*;
import org.junit.Test;
import adventure.character.Player;
import adventure.item.LifePotion;
import adventure.room.Room;

public class UseTest {

	//MOCK FOR FIGHTACTION
	public class MockUse extends Use{
		public MockUse() {
			super();
		}
		@Override
		public void execute(Player player){
			LifePotion lp = new LifePotion(5);
			lp.isUsedBy(player);
		}
	}
	
	@Test
	public void testExecute() {
		Room room = new Room("testR");
		Player player = new Player(room,10,10,10,"testP");
		Action use = new MockUse(); 
		use.execute(player);
		assertEquals(player.getLife(),15);
	}
}
