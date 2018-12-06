package pool.resource;

import static org.junit.Assert.*;

import org.junit.Test;

public class ResourceUserTest {

	@Test
	public void testResourceSetterAndGetter() {
		Basket b= new Basket();
		Cubicle c = new Cubicle();
		ResourceUser<Resource> u = new ResourceUser<Resource>();
		assertEquals(b.description(),"[Ressource : Basket, used to store clothes.]");
		assertEquals(c.description(),"[Ressource : Cubicle, swimmer can dress/undress inside.]");
		u.setResource(b);
		assertEquals(u.getResource(),b);
		u.resetResource();
		assertNull(u.getResource());
	}

}
