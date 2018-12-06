package quiz.answer;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TextAnswerTest{
	private MockTextAnswer text;
	@Before
	public void init() {
		this.text = new MockTextAnswer();
	}
	public class MockTextAnswer extends TextAnswer{
		public MockTextAnswer() {
			super("test");
		}
		@Override
		public boolean isAcceptable(String ans) {
			return true;
		}
		@Override
		public String getAnswerType() {
			return "text";
		}		
	}	
	@Test
	public void testGetAnswerAndType() {
		assertEquals(this.text.getAnswer(),"test");
		assertEquals(this.text.getAnswerType(),"text");
	}
	@Test
	public void testIsAcceptable() {
		assertTrue(this.text.isAcceptable("t"));
	}
	@Test
	public void testIsCorrect() {
		assertTrue(this.text.isCorrect("test"));
	}
	@Test
	public void testIsNotCorrect() {
		assertFalse(this.text.isCorrect("t"));
	}
}