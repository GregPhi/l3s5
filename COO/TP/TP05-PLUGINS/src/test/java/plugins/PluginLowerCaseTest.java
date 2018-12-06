package plugins;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PluginLowerCaseTest {
	@Test
	public void testLowerCase() {
		PluginLowerCase p = new PluginLowerCase();
		String msg = "TEST";
		assertEquals("test",p.transform(msg));
		assertEquals(p.getLabel(),"plugin : lower case !");
		assertEquals(p.helpMessage(),"Transform all leters of text to lower case !");
	}
}
