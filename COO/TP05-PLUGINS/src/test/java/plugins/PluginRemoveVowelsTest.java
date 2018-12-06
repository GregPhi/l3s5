package plugins;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PluginRemoveVowelsTest {
	@Test
	public void testRemoveVowels() {
		PluginRemoveVowels p = new PluginRemoveVowels();
		String msg = "test";
		assertEquals("tst",p.transform(msg));
		assertEquals(p.getLabel(),"plugin : remove vowels !");
		assertEquals(p.helpMessage(),"Remove all vowels of the text !");
	}
}
