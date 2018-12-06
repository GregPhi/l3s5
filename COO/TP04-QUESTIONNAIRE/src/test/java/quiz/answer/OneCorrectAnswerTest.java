package quiz.answer;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class OneCorrectAnswerTest{
	private MockOneCorrectAnswer one;
	private List<String> ans;
	@Before
	public void init() {
		this.ans = new ArrayList<String>();
		this.ans.add("test");
		this.ans.add(" | ");
		this.ans.add("yes");
		this.one = new MockOneCorrectAnswer(this.ans);
	}
	public class MockOneCorrectAnswer extends OneCorrectAnswer{
		public MockOneCorrectAnswer(List<String> ans) {
			super(ans);
		}
		@Override
		public boolean isAcceptable(String ans) {
			return true;
		}
		@Override
		public String getAnswerType() {
			return "one";
		}		
	}	
	@Test
	public void testGetAnswerAndType() {
		assertEquals(this.one.getAnswer(),this.ans);
		assertEquals(this.one.getAnswerType(),"one");
	}
	@Test
	public void testIsAcceptable() {
		assertTrue(this.one.isAcceptable("t"));
	}
	@Test
	public void testIsCorrect() {
		assertTrue(this.one.isCorrect(this.ans.get(0)));
		//assertTrue(this.one.isCorrect("test");
	}
	@Test
	public void testIsNotCorrect() {
		//assertFalse(this.one.isCorrect(this.ans.get(2)));
		assertFalse(this.one.isCorrect("yes"));
	}
}