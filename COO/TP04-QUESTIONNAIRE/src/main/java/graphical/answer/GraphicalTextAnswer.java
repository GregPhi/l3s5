/**
 * PROJECT QUESTIONNARY
 * @author : Hugot Jean-Michel and Philippot Gregoire
 */

package graphical.answer;

import javax.swing.JTextField;

import graphical.Counter;
import quiz.questionnary.Question;

public class GraphicalTextAnswer extends GraphicalAnswer<JTextField>{

	//ATTRIBUTS
	/**/
	private static final long serialVersionUID = 2609335948419182605L;

	//CONSTRUCTOR
	/**
	 * Create a graphical text answer
	 * @param quest : (type-Question)
	 * @param c : (type-Counter) counter
	 */
	public GraphicalTextAnswer(Question quest, Counter c) {
		super(quest, c);
	}

	@Override
	public String retrieverUserAnswer() {
		return this.userInput.getText();
	}

	@Override
	public JTextField createUserInput() {
		return new JTextField("",20);
	}
	

}
