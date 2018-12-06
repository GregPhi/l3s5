package pool.repool;

import java.util.NoSuchElementException;
import org.junit.Test;
import pool.resource.Basket;
import pool.resource.Cubicle;

public class ResourcePoolTest {

	@Test(expected = NoSuchElementException.class)
	public void testProvde() {
		ResourcePool<Cubicle> c = new CubiclePool(2);
		for(int i = 0; i < 2; i++) {
			c.provideResource();
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFree() {
		ResourcePool<Basket> b = new BasketPool(4);
		Basket b1 = b.provideResource();
		b.recoverResource(b1);
		Basket b2 = new Basket();
		b.recoverResource(b2);
	}

}
