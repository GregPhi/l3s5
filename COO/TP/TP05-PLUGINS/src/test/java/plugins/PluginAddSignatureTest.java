package plugins;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PluginAddSignatureTest {
	@Test
	public void testAddSignature() {
		PluginAddSignature p = new PluginAddSignature();
		String msg = "test";
		assertEquals(msg+"\n\n\n\n\n"+"Create by :"+"\n"+"Hugot Jean-Michel   "+"\n"+"Philippot Grégoire",p.transform(msg));
		assertEquals(p.getLabel(),"plugin : add signature !");
		assertEquals(p.helpMessage(),"Add a signature to the end of the text !");
	}
}
