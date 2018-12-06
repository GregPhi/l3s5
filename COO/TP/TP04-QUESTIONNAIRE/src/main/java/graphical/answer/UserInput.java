/**
 * PROJECT QUESTIONNARY
 * @author : Hugot Jean-Michel and Philippot Gregoire
 */

package graphical.answer;

import javax.swing.JPanel;

import graphical.Counter;
import quiz.answer.BadValueException;
import quiz.answer.OneCorrectAnswer;
import quiz.answer.YesNoAnswer;
import quiz.questionnary.Question;

public class UserInput {

	//ATTRIBUTS
	/*INSTANCE user input*/
	public static final UserInput uif = new UserInput();

	//METHODS
	/**
	 * Craft a text answer
	 * @param quest : (type-Question)
	 * @param c : (type-Counter) counter 
	 * @return : (type-GraphicalTextAnswer) a graphical text answer
	 */
	private GraphicalTextAnswer craftTextAnswer(Question quest, Counter c) {
		return new GraphicalTextAnswer(quest,c);
	}
	
	/**
	 * Craft a numerical answer
	 * @param quest : (type-Question)
	 * @param c : (type-Counter) counter 
	 * @return : (type-GraphicalNumericalAnswer) a graphical numerical answer
	 */
	private GraphicalNumericalAnswer craftNumericalAnswer(Question quest, Counter c) {
		return new GraphicalNumericalAnswer(quest,c);
	}

	/**
	 * Craft a multi answer
	 * @param quest : (type-Question)
	 * @param c : (type-Counter) counter 
	 * @return : (type-GraphicalTextAnswer) a graphical text answer
	 */
	private GraphicalTextAnswer craftMultiAnswer(Question quest, Counter c) {
		return new GraphicalTextAnswer(quest,c);
	}
	
	/**
	 * Craft a one correct answer
	 * @param quest : (type-Question)
	 * @param c : (type-Counter) counter 
	 * @return : (type-GrphicalOneCorrectAnswer) a graphical one correct answer
	 */
	private GraphicalOneCorrectAnswer craftOneCorrectAnswer(Question quest, Counter c) {
		return new GraphicalOneCorrectAnswer(quest,c);
	}
	
	/**
	 * Craft a yes no answer
	 * @param quest : (type-Question)
	 * @param c : (type-Counter) counter 
	 * @return : (type-GraphicalYesNoAnswer) a graphical yes no answer
	 * @throws BadValueException : bad value exception
	 */
	private GraphicalYesNoAnswer craftYesNoAnswer(Question quest, Counter c) throws BadValueException {
		YesNoAnswer a = new YesNoAnswer(quest.getAnswer().toString());
		String ans = "";
		ans+=a.getAnswer().toString()+" | "+a.getAnswer().opposate().toString();
		OneCorrectAnswer one = new OneCorrectAnswer(ans);
		Question q = new Question(quest.getQuestion(),quest.getNbOfPoints(),one);
		return new GraphicalYesNoAnswer(q,c);
	}
	
	/**
	 * Craft a user input 
	 * @param quest : (type-Question) question
	 * @param c : (type-Counter) counter
	 * @return : (type-JPanel) jpanel
	 * @throws BadValueException : bad value exception
	 */
	public JPanel craftUserInput(Question quest, Counter c) throws BadValueException {
		String answerType = quest.getAnswer().getAnswerType();
		if("type : multi answer.".equals(answerType)){
			return this.craftMultiAnswer(quest, c);
		}if("type : numerical answer.".equals(answerType)){
			return this.craftNumericalAnswer(quest, c);
		}if("type : one correct answer.".equals(answerType)){
			return this.craftOneCorrectAnswer(quest, c);
		}if("oui".equals(quest.getAnswer().toString().toLowerCase())||"non".equals(quest.getAnswer().toString().toLowerCase())) {
			return this.craftYesNoAnswer(quest, c);
		}if("type : text answer.".equals(answerType)){
			return this.craftTextAnswer(quest, c);
		}
		return null;
	}
}
