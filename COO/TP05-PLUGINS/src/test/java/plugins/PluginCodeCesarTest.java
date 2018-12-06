package plugins;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PluginCodeCesarTest {
	@Test
	public void testCodeCesar() {
		PluginCodeCesar p = new PluginCodeCesar(3);
		String msg = "test";
		assertEquals(p.transform(msg),"whvw");
		assertEquals(p.getLabel(),"plugin : code of ceasear with a shift of "+p.getShift());
		assertEquals(p.helpMessage(),"Transform all character of a text with the code of ceasear."+"\n"+"The shift is "+p.getShift());
	}
}
