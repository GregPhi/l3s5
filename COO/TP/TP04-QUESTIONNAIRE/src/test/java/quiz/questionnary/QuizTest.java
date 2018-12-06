package quiz.questionnary;
import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import quiz.answer.Answer;
import quiz.answer.NumericalAnswer;
import quiz.answer.TextAnswer;
import quiz.answer.YesNo;
import quiz.answer.YesNoAnswer;

public class QuizTest {
	private MockQuiz quiz;
	@Before
	public void init() {
		this.quiz = new MockQuiz();
	}
	public class MockQuiz extends Quiz{
		public MockQuiz() {
			super("test.txt");
		}
		@Override
		public void add(){
			this.add(new Question("Que suis je ?",1,new TextAnswer("un test")));
			this.add(new Question("Un test ?",1,new YesNoAnswer(YesNo.oui)));
			this.add(new Question("Combien de points ?",1,new NumericalAnswer(1)));
		}
		public int ask(Question q, String user) {
			Answer<?> ans = q.getAnswer();
			if(ans.isAcceptable(user)) {
				if(ans.isCorrect(user)) {
					return q.getNbOfPoints();
				}
			}else {
				return 0;
			}
			return 0;
		}
	}
	@Test
	public void testGetListOfQuestion() {
		assertEquals(this.quiz.getListOfQuestion(),new LinkedList<Question>());
	}
	@Test
	public void testAdd() {
		this.quiz.add();
		assertEquals(this.quiz.getListOfQuestion().size(),3);
		Question q = this.quiz.getListOfQuestion().get(0);
		Question q1 = new Question("test",1,new YesNoAnswer("oui"));
		assertTrue(this.quiz.getListOfQuestion().contains(q));
		assertFalse(this.quiz.getListOfQuestion().contains(q1));
	}
	@Test
	public void testAsk() {
		this.quiz.add();
		int res = 0;
		for(Question q : this.quiz.getListOfQuestion()) {
			assertEquals(this.quiz.ask(q,q.getAnswer().toString()),q.getNbOfPoints());
			res += q.getNbOfPoints();
		}
		assertEquals(res,3);
	}
}
