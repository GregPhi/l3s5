package graphical.answer;

import graphical.Counter;
import quiz.answer.OneCorrectAnswer;
import quiz.questionnary.Question;

public class GraphicalOneCorrectAnswer extends GraphicalAnswer<UniqueChoice<String>>{

	//ATTRIBUTS
	/**/
	private static final long serialVersionUID = -783715192164913291L;

	//CONSTRUCTOR
	/**
	 * Create a graphical one correct answer
	 * @param quest : (type-Question) question
	 * @param c : (type-Counter) counter
	 */
	public GraphicalOneCorrectAnswer(Question quest, Counter c) {
		super(quest, c);
	}

	//METHODS
	@Override
	public String retrieverUserAnswer() {
		return this.userInput.getUserInput();
	}

	@Override
	public UniqueChoice<String> createUserInput() {
		OneCorrectAnswer ans = (OneCorrectAnswer)this.quest.getAnswer();
		return new UniqueChoice<String>(ans.getAnswer());
	}

}
