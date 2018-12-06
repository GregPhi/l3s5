/**
 * PROJECT QUESTIONNARY
 * @author : Hugot Jean-Michel and Philippot Gregoire
 */

package graphical.answer;

import graphical.Counter;
import quiz.answer.OneCorrectAnswer;
import quiz.questionnary.Question;

public class GraphicalYesNoAnswer extends GraphicalAnswer<UniqueChoice<String>>{

	//ATTRIBUTS
	/**/
	private static final long serialVersionUID = 8788743200560343683L;

	//CONSTRUCTOR
	/**
	 * Create a graphical yes no answer
	 * @param quest : (type-Question) question
	 * @param c : (type-Counter) counter
	 */
	public GraphicalYesNoAnswer(Question quest, Counter c) {
		super(quest, c);
	}

	//METHODS
	@Override
	public String retrieverUserAnswer() {
		return this.userInput.getUserInput();
	}

	@Override
	public UniqueChoice<String> createUserInput() {
		OneCorrectAnswer answerCorrect = (OneCorrectAnswer)this.quest.getAnswer();
		return new UniqueChoice<String>(answerCorrect.getAnswer());
	}

}
