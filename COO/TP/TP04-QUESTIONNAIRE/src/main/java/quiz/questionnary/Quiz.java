/**
 * PROJECT QUESTIONNARY
 * @author : http://www.fil.univ-lille1.fr/~yroos/coo/td/td7/td7.html
 */

package quiz.questionnary;

//import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
//import java.io.InputStreamReader;
import java.io.PrintStream;
//import java.io.Reader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import quiz.answer.*;

public class Quiz {
	//ATTRIBUTS
	/*list of question*/
	private List<Question> questions ;
	/*the file of questionnary*/
	private String questionnary;
	/*scanner in*/
	private Scanner in = new Scanner(System.in);
	/*scanner ou*/
	private PrintStream out = System.out;
	
	//CONSTRUCTOR
	/**
	 * Create the quiz
	 * @param f : (type-String) file of questionnary
	 */
	public Quiz(String f) {
		this.questions = new LinkedList<Question>() ;
		this.questionnary = f;
	}

	//METHODS
	/**
	 * Add a question to the list of question
	 * @param question : (type-Question) question
	 */
	public void add(Question question) {
		questions.add(question) ;
	}
	
	/**
	 * Remove <code>question</code>
	 * @param question : (type-Question) question to remove
	 */
	public void remove(Question question) {
		questions.remove(question);
	}
	
	/**
	 * Add all question from the file
	 * @throws IOException :  : io exception
	 * @throws BadValueException : bad value exception
	 */
	public void add() throws IOException, BadValueException {
		File f = new File(this.questionnary);
		Scanner scanner = new Scanner(f);
		while(scanner.hasNext()) {
			String question = "";
			String answer = "";
			String points = "";
			if(scanner.hasNext()) {
				question = scanner.nextLine();
			}if(scanner.hasNext()) {
				answer = scanner.nextLine();
			}if(scanner.hasNext()) {
				points = scanner.nextLine();
			}
			//utiliser AnswerFactory
			Answer<?> a1 = createAnswer(answer);
			this.questions.add(new Question(question,Integer.parseInt(points),a1));
		}
		scanner.close();
	}
	
	/**
	 * Create a answer from a string <code>a</code>
	 * @param a : (type-String) : the answer to create
	 * @return : (type-Answer) an answer
	 * @throws BadValueException : bad value exception
	 */
	public Answer<?> createAnswer(String a) throws BadValueException{
		if(a == "oui" || a == "non") {
			return new YesNoAnswer(YesNo.valueOf(a));
		}if(a.contains("|")) {
			return new OneCorrectAnswer(a);
		}if(a.contains(";")) {
			return new MultiAnswer(a);
		}for(int i = 0; i < 10; i++) {
			if(a.contains(String.valueOf(i))) {
				return new NumericalAnswer(a);
			}
		}
		return new TextAnswer(a);
	}
	
	/**
	 * Ask a question
	 * @param question : (type-Question) the question
	 * @return : (type-int) points
	 */
	private int ask(Question question) {
		Answer<?> answer = question.getAnswer() ;	
		out.println(question.getQuestion()) ;
		String userAnswer = null;
		do {
			out.print("[" + answer.getAnswerType() + "]") ;
			userAnswer = in .nextLine();
		} while (! answer.isAcceptable(userAnswer)) ;
		if (answer.isCorrect(userAnswer)) {
			int points = question.getNbOfPoints() ;
			out.println("correct (" + format(points) + ")") ;
			return points ;
		} else {
			if(answer instanceof OneCorrectAnswer) {
				out.println("incorrect, la bonne réponse était "+answer.getAnswer().toString().substring(1,5));
			}
			else {
				out.println("incorrect, la bonne réponse était " + answer.getAnswer().toString()) ;
			}
			return 0 ;
		}
	}
	
	/**
	 * Print number of points
	 * @param points : (type-int) points
	 * @return : (type-String) points
	 */
	private static String format(int points) {
		return "" + points + " point" + (points > 1?"s":"") ;
	}
	
	/**
	 * Ask all questions
	 */
	public void askAll() {
		int result = 0 ;
		for (Question question : this.questions) {
			result += this.ask(question) ;
		}
		out.println("Vous avez obtenu " + format(result)) ;
	}
	
	/**
	 * Get the list of question
	 * @return : (type-List) the questions
	 */
	public List<Question> getListOfQuestion(){
		List<Question> q = new LinkedList<Question>(this.questions);
		return q;
	}
}