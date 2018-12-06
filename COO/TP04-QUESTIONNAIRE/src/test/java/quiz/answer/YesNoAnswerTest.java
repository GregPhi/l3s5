package quiz.answer;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class YesNoAnswerTest{
	private MockYesNoAnswer ouinon;
	@Before
	public void init() {
		this.ouinon = new MockYesNoAnswer();
	}
	public class MockYesNoAnswer extends YesNoAnswer{
		public MockYesNoAnswer() {
			super(YesNo.oui);
		}
		@Override
		public boolean isAcceptable(String ans) {
			return (ans.equals("oui")||ans.equals("non"));
		}
		@Override
		public String getAnswerType() {
			return "yes-no";
		}		
	}	
	@Test
	public void testGetAnswerAndType() {
		assertEquals(this.ouinon.getAnswer(),YesNo.oui);
		assertEquals(this.ouinon.getAnswerType(),"yes-no");
	}
	@Test
	public void testIsAcceptable() {
		assertTrue(this.ouinon.isAcceptable("oui"));
	}
	@Test
	public void testIsCorrect() {
		assertTrue(this.ouinon.isCorrect("oui"));
	}
	@Test
	public void testIsNotCorrect() {
		assertFalse(this.ouinon.isCorrect("non"));
	}
}