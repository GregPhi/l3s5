/**
 * PROJECT QUESTIONNARY
 * @author : Hugot Jean-Michel and Philippot Gregoire
 */

package graphical.answer;

import java.awt.Dimension;

import javax.swing.JSpinner;

import graphical.Counter;
import quiz.questionnary.Question;

public class GraphicalNumericalAnswer extends GraphicalAnswer<JSpinner>{
	//ATTRIBUTS
	/**/
	private static final long serialVersionUID = 7397206047581131258L;

	//CONSTRUCTOR
	/**
	 * Create a graphical numerical answer
	 * @param quest : (type-Question) the question
	 * @param c : (type-Counter) count
	 */
	public GraphicalNumericalAnswer(Question quest, Counter c) {
		super(quest, c);
	}

	@Override
	public String retrieverUserAnswer() {
		return this.userInput.getValue().toString();
	}

	@Override
	public JSpinner createUserInput() {
		JSpinner num = new JSpinner();
		num.setPreferredSize(new Dimension(25,15));
		num.setAlignmentY(0.5F);
		return num;
	}

}
