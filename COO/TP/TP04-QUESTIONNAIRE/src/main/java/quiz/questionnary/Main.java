/**
 * PROJECT QUESTIONNARY
 * @author : Hugot Jean-Michel and Philippot Gregoire
 */

package quiz.questionnary;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import quiz.answer.BadValueException;

public class Main {
	public static void instructions(String file) {
		System.out.println("Vous allez répondre à un questionnaire sur : "+file);
		System.out.println("Afin de répondre au mieux aux questions voici une suite d'instructions :");
		System.out.println("-> lors des text answer : vous pouvez répondre avec des réponses comportant des majuscules.");
		System.out.println("-> lors des numerical answer : tant que vous donnerez pas une réponse numérique, vous ne pourrez pas continuer le questionnaire.");
		System.out.println("-> lors des multi et one correct answer : vous pouvez répondre avec des réponses comportant des majuscules.");
	}
	
	//MAIN
	/**
	 * Create the questionnary
	 * @param args : (type-String) the file of questionnary
	 * @throws ClassNotFoundException : class not found exception
	 * @throws InstantiationException : instantiation exception
	 * @throws IllegalArgumentException : illegal argument exception
	 * @throws InvocationTargetException : invocation target exception
	 * @throws NoSuchMethodException : no such method exception
	 * @throws SecurityException : security exception
	 * @throws IllegalAccessException : illegal access exception
	 * @throws IOException : io exception
	 * @throws BadValueException : bad value exception
	 */
	public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IllegalAccessException, IOException, BadValueException {
		if(args.length == 0) {
			instructions("question_tolkien_2.txt");
			Quiz q = new Quiz("question_tolkien_2.txt");
			q.add();
			q.askAll();
		}else {
			instructions(args[0]);
			Quiz q = new Quiz(args[0]);
			q.add();
			q.askAll();
		}
	}
}
