package plugins;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PluginRemoveConsonantsTest {
	@Test
	public void testRemoveConsonants() {
		PluginRemoveConsonants p = new PluginRemoveConsonants();
		String msg = "test";
		assertEquals("e",p.transform(msg));
		assertEquals(p.getLabel(),"plugin : remove consonants !");
		assertEquals(p.helpMessage(),"Remove all consonants of the text !");
	}
}
