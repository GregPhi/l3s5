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

import adventure.Direction;
import adventure.character.Player;
import adventure.room.Room;

public class MoveTest {

	//MOCK FOR FIGHTACTION
	public class MockMove extends Move{
		public MockMove() {
			super();
		}
		@Override
		public void execute(Player player){
			Room room = new Room("direct 1");
			player.getRoom().addNeighbours(Direction.NORTH, room);
			player.travel(Direction.NORTH);
		}
	}
	
	@Test
	public void testExecute() {
		Room room = new Room("testR");
		Player player = new Player(room,10,10,10,"testP");
		Action move = new MockMove(); 
		move.execute(player);
		assertTrue(player.travel(Direction.SOUTH));
	}
}
