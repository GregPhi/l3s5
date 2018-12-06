/**
 * PROJECT QUESTIONNARY
 * @author : Hugot Jean-Michel and Philippot Gregoire
 */

package main;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import graphical.GraphicalQuiz;
import quiz.answer.BadValueException;
import quiz.questionnary.Quiz;

public class Main{
	/**
	 * Main of project
	 * @param args : (type-String) argument [option] and [file]
	 * @throws IOException : io exception
	 * @throws BadValueException : bad value exception
	 */
	public static void main(String args[]) throws IOException, BadValueException{
		if(args.length == 2) {
			if("-t".equals(args[0])){
				JOptionPane.showMessageDialog(null, "You choose the textual option and "+args[1]+" file.");
				Quiz quiz = new Quiz(args[1]);
				quiz.add();
				quiz.askAll();
			}
		}
		if("-g".equals(args[0])){
			JOptionPane.showMessageDialog(null, "You choose the graphical option.");
			JFrame window = new GraphicalQuiz("Questionnary");
			window.setVisible(true);
		}
	}
}
