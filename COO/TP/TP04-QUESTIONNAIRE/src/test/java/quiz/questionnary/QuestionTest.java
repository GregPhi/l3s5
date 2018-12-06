package quiz.questionnary;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import quiz.answer.Answer;
import quiz.answer.TextAnswer;

public class QuestionTest {
	private String quest;
	private int points;
	private Answer<?> ans;
	private Question question;
	@Before
	public void init() {
		this.quest = "Qu'est ce qui est petit et marron ?";
		this.points = 42;
		this.ans = new TextAnswer("marron");
		this.question = new Question(this.quest, this.points, this.ans);
	}
	@Test
	public void testGetQuestionGetPointsAndGetAnswer() {
		assertEquals(this.question.getQuestion(),this.quest);
		assertEquals(this.question.getNbOfPoints(),this.points);
		assertEquals(this.question.getAnswer(),this.ans);
	}
	@Test
	public void testGetAnswerType() {
		assertEquals(this.question.getAnswerType(),this.ans.getAnswerType());
	}
	@Test
	public void testIsAcceptable() {
		assertTrue(this.question.isAcceptable("marron"));
	}
	@Test
	public void testIsCorrect() {
		assertTrue(this.question.isCorrect("marron"));
	}
	@Test
	public void testIsNotCorrect() {
		assertFalse(this.question.isCorrect("42"));
	}
}
