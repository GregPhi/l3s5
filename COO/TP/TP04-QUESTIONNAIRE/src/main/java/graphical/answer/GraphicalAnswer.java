/**
 * PROJECT QUESTIONNARY
 * @author : Hugot Jean-Michel and Philippot Gregoire
 */

package graphical.answer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import graphical.Counter;
import quiz.questionnary.Question;

public abstract class GraphicalAnswer<J extends JComponent> extends JPanel {

	//ATTRIBUTS
	/**/
	private static final long serialVersionUID = -5955160835873009910L;
	/*label*/
	public JLabel label;
	/*button validate*/
	public JButton validateB;
	/*question*/
	public Question quest;
	/*user input*/
	public J userInput;
	/*counter*/
	public final Counter c;
	
	//CONSTRUCTOR
	/**
	 * Create the graphical answer
	 * @param quest : (type-Question) question
	 * @param c : (type-Counter) counteur
	 */
	public GraphicalAnswer(final Question quest, final Counter c) {
		super();
		this.quest = quest;
		this.c = c;
		this.label = new JLabel(this.quest.getQuestion());
		this.validateB = new JButton("VALIDATE");
		this.userInput = this.createUserInput();
		this.setLayout(new BorderLayout());
		this.add(this.label, BorderLayout.NORTH);
		this.add(this.validateB, BorderLayout.EAST);
		this.add(this.userInput);
		this.validateB.addActionListener(new ButtonValidate());
	}
	
	//CLASS INTERNE
	/**
	 * Create the button validate 
	 */
	private class ButtonValidate implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			if(!(quest.isAcceptable(retrieverUserAnswer()))) {
				label.setText("Entre non valide");
				return;
			}
			c.incNbRep();
			if(quest.isCorrect(retrieverUserAnswer())) {
				GraphicalAnswer.this.remove(validateB);
				label.setText("Correct");
				c.incNbPoints(quest.getNbOfPoints());
				userInput.setEnabled(false);
				GraphicalAnswer.this.revalidate();
				GraphicalAnswer.this.repaint();
				JOptionPane.showMessageDialog(null, "Good answer, you win "+quest.getNbOfPoints()+" points !");
			}else {
				GraphicalAnswer.this.remove(validateB);
				label.setText("Incorrect : the good answer is "+quest.getAnswer().toString());
				userInput.setEnabled(false);
				GraphicalAnswer.this.revalidate();
				GraphicalAnswer.this.repaint();
				JOptionPane.showMessageDialog(null, "Wrong answer, the good answer was "+quest.getAnswer().toString());
			}
			if(c.isFilled()) {
				JOptionPane.showMessageDialog(null, "Fini ! Vous avez marqué "+((Integer)c.getNbPoints()).toString()+" points sur "+((Integer)c.getMaxPoints()).toString());
			}
		}
	}
	
	//METHODS
	/**
	 * Retriver user answer
	 * @return : (type-String)
	 */
	public abstract String retrieverUserAnswer();
	
	/**
	 * Create user input
	 * @return : (type-J) input
	 */
	public abstract J createUserInput();
	
	/**
	 * Get the user input
	 * @return : (type-J) user input
	 */
	public J getUserInput() {
		return this.userInput;
	}
}
