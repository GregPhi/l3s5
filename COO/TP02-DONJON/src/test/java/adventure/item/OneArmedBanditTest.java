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

import adventure.character.*;
import adventure.room.*;

public class OneArmedBanditTest {

	//ONEARMEDBANDITMOCK
	public class MockOneArmedBandit extends OneArmedBandit {
		public MockOneArmedBandit(int cost) {
			super(cost);
		}
		@Override
		public void isUsedBy(Player player) {
			//for have no check if the player has enough gold
			//we give what it costs to the player
			player.changeGold(this.getCost());
			this.addItemInOneArmed(new LifePotion(5));
			this.addItemInOneArmed(new StrengthPotion(5));
			this.addItemInOneArmed(new PurseGold(5));
			this.addItemInOneArmed(new OneArmedBandit(5));
			LifePotion lp = (LifePotion) this.getListOfItems().get(0);
			lp.isUsedBy(player);
			player.changeGold(-this.getCost());
		}
	}
	
	@Test
	public void testGet() {
		OneArmedBandit ob = new OneArmedBandit(50);
		assertEquals(ob.getCost(),50);
	}
	
	@Test
	public void testExecute() {
		Room room = new Room("testR");
		Player player = new Player(room,10,10,0,"testP");
		OneArmedBandit OaB = new MockOneArmedBandit(50);
		assertEquals(OaB.getCost(),50);
		OaB.isUsedBy(player);
		assertEquals(player.getGold(),0);
		assertEquals(player.getLife(),15);
	}
}
