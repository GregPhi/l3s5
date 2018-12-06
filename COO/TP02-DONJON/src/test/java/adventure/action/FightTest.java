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

import adventure.character.Monster;
import adventure.character.Player;
import adventure.room.Room;

public class FightTest {

	//MOCK FOR FIGHTACTION
	public class MockFight extends Fight{
		public MockFight() {
			super();
		}
		@Override
		public void execute(Player player) {
			Monster monster = player.getRoom().getMonsters().get(0);
			fight(player,monster);
		}
		@Override
		public void fight(Player player, Monster monster) {
			while(!player.isDead() && !monster.isDead()) {
				player.attack(monster);
				if(!monster.isDead()) {
					monster.attack(player);
				}
			}
		}
	}

	@Test
	public void testFight() {
		Room room = new Room("testR");
		Player player = new Player(room,10,10,10,"testP");
		Monster monster = new Monster(room,2,2,2,"testM");
		Fight f = new MockFight();
		f.fight(player,monster);
		assertTrue(monster.isDead());
		assertTrue(room.getMonsters().isEmpty());
		assertEquals(room.getItems().size(),1);
	}

	@Test
	public void testExecute() {
		Room room = new Room("testR");
		Player player = new Player(room,10,10,10,"testP");
		room.addMonster(new Monster(room,2,2,2,"testM"));
		Fight f = new MockFight();
		f.execute(player);
		assertTrue(room.getMonsters().isEmpty());
	}
}
