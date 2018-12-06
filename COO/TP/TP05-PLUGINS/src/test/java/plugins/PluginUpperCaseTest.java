package plugins;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PluginUpperCaseTest {
	@Test
	public void testUpperCase() {
		PluginUpperCase p = new PluginUpperCase();
		String msg = "test";
		assertEquals("TEST",p.transform(msg));
		assertEquals(p.getLabel(),"plugin : upper case !");
		assertEquals(p.helpMessage(),"Transform all leters of text to upper case !");
	}
}
