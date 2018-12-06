package quiz.answer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class NumericalAnswerTest{
	private MockNumrericalAnswer num;
	@Before
	public void init() {
		this.num = new MockNumrericalAnswer();
	}
	public class MockNumrericalAnswer extends NumericalAnswer{
		public MockNumrericalAnswer() {
			super(10);
		}
		@Override
		public boolean isAcceptable(String ans) {
			for(int i = 0; i < 10; i++){
				if(ans.contains(String.valueOf(i))) {
					return true;
				}
			}
			return false;
			//ou mettre un return true tout simplement
		}
		@Override
		public String getAnswerType() {
			return "numerical";
		}		
	}	
	@Test
	public void testGetAnswerAndType() {
		int a = this.num.getAnswer();
		assertEquals(a,10);
		assertEquals(this.num.getAnswerType(),"numerical");
	}
	@Test
	public void testIsAcceptable() {
		assertTrue(this.num.isAcceptable("0"));
	}
	@Test
	public void testIsCorrect() {
		assertTrue(this.num.isCorrect("10"));
	}
	@Test
	public void testIsNotCorrect() {
		assertFalse(this.num.isCorrect("0"));
	}
}
