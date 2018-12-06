package quiz.answer;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MultiAnswerTest{
	private MockMultiAnswer multi;
	private List<String> ans;
	@Before
	public void init() {
		this.ans = new ArrayList<String>();
		this.ans.add("test");
		this.ans.add(" ; ");
		this.ans.add("yes");
		this.multi = new MockMultiAnswer(this.ans);
	}
	public class MockMultiAnswer extends MultiAnswer{
		public MockMultiAnswer(List<String> ans) {
			super(ans);
		}
		@Override
		public boolean isAcceptable(String ans) {
			return true;
		}
		@Override
		public String getAnswerType() {
			return "multi";
		}		
	}	
	@Test
	public void testGetAnswerAndType() {
		assertEquals(this.multi.getAnswer(),this.ans);
		assertEquals(this.multi.getAnswerType(),"multi");
	}
	@Test
	public void testIsAcceptable() {
		assertTrue(this.multi.isAcceptable("t"));
	}
	@Test
	public void testIsCorrect() {
		assertTrue(this.multi.isCorrect(this.ans.get(0)));
		assertTrue(this.multi.isCorrect(this.ans.get(2)));
	}
	@Test
	public void testIsNotCorrect() {
		assertFalse(this.multi.isCorrect("no"));
	}
}