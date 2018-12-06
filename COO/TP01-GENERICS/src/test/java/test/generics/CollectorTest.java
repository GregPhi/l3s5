/**
 * Class Test CollectorTest : test the collector's methods : take(), getCarried(), drop() and giveTo()
 *
 * @version : 19 septembre 2017
 * @author : Hugot Jean-Michel and Philippot Gregoire
 */

package test.generics;

import static org.junit.Assert.*;
import org.junit.Test;
import generics.*;

public class CollectorTest {
	
	/**
	 * Test take and get when ok
	 */
	@Test
	public void testTakeAndGetCarriedObjectWhenOk() {
		Carrot c1 = new Carrot(1);
		Collector<Carrot> carrotCollector1 = new Collector<Carrot>("carrot-collector-1");
		carrotCollector1.take(c1);
		assertEquals(c1,carrotCollector1.getCarriedObject());
	}

	/**
	 * Test take when a collector have already a object
	 */
	@Test(expected = AlreadyCarryingException.class)
	public void testTakeWhenAlreadyCarrying() {
		Carrot c1 = new Carrot(1);
		Carrot c2 = new Carrot(2);
		Collector<Carrot> carrotCollector1 = new Collector<Carrot>("carrot-collector-1");
		carrotCollector1.take(c1);
		carrotCollector1.take(c2);
	}

	/**
	 * Test drop when ok
	 */
	@Test
	public void testDrop() {
		Carrot c1 = new Carrot(1);
		Collector<Carrot> carrotCollecteur1 = new Collector<Carrot>("carrot-collecteur-1-vide");
		Collector<Carrot> carrotCollecteur2 = new Collector<Carrot>("carrot-collecteur-2");
		carrotCollecteur2.take(c1);
		assertEquals(carrotCollecteur1.drop(),null);
		assertEquals(carrotCollecteur2.getCarriedObject(),c1);
		assertEquals(carrotCollecteur2.drop(),c1);
		assertEquals(carrotCollecteur2.getCarriedObject(),null);
	}
	

	/**
	 * Test give to when a collector have already a object
	 */
	@Test (expected = AlreadyCarryingException.class)
	public void testGiveTOWhenAlreadyCarrying() {
		Carrot c1 = new Carrot(1);
		Carrot c2 = new Carrot(2);
		Collector<Carrot> carrotCollecteur1 = new Collector<Carrot>("carrot-collecteur-1");
		Collector<Carrot> carrotCollecteur2 = new Collector<Carrot>("carrot-collecteur-2");
		carrotCollecteur1.take(c1);
		carrotCollecteur2.take(c2);
		carrotCollecteur1.giveTo(carrotCollecteur2);
	}

	/**
	 * Give to when ok
	 */
	@Test
	public void testGiveToWhenOk() {
		Carrot c1 = new Carrot(1);
		Collector<Carrot> carrotCollecteur1 = new Collector<Carrot>("carrot-collecteur-1");
		Collector<Carrot> carrotCollecteur2 = new Collector<Carrot>("carrot-collecteur-2");
		carrotCollecteur1.take(c1);
		assertEquals(carrotCollecteur1.getCarriedObject(),c1);
		assertEquals(carrotCollecteur2.getCarriedObject(),null);
		carrotCollecteur1.giveTo(carrotCollecteur2);
		assertEquals(carrotCollecteur1.getCarriedObject(),null);
		assertEquals(carrotCollecteur2.getCarriedObject(),c1);
	}
}
