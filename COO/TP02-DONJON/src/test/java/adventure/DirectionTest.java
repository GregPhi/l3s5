/**
 * Project DUNGEON
 * 
 * Hugot Jean-Michel
 * Philippot Grégoire
 * 
 * Group 3
 */

package adventure;

import static org.junit.Assert.*;

import org.junit.Test;

public class DirectionTest{
	@Test
	public void testOpposite() {
		assertSame(Direction.NORTH.opposite(),Direction.SOUTH);
		assertSame(Direction.SOUTH.opposite(),Direction.NORTH);
		assertSame(Direction.EAST.opposite(),Direction.WEST);
		assertSame(Direction.WEST.opposite(),Direction.EAST);
	}
}
