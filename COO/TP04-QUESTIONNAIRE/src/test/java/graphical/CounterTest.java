package graphical;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CounterTest {
	private Counter test;
	@Before
	public void init() {
		this.test = new Counter();
	}
	@Test
	public void testReset() {
		assertEquals(this.test.getMaxPoints(),0);
		assertEquals(this.test.getNbPoints(),0);
		assertEquals(this.test.getNbQuest(),0);
		assertEquals(this.test.getNbRep(),0);
	}
	@Test
	public void testMethodIncrement() {
		this.test.incMaxPoints(1);
		this.test.incNbPoints(1);
		this.test.incNbQuest();
		this.test.incNbRep();
		assertEquals(this.test.getMaxPoints(),1);
		assertEquals(this.test.getNbPoints(),1);
		assertEquals(this.test.getNbQuest(),1);
		assertEquals(this.test.getNbRep(),1);
	}
	@Test
	public void testIsFilled() {
		assertTrue(this.test.isFilled());
	}
}
