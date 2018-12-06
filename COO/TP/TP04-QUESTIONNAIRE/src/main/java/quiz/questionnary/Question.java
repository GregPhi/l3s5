/**
 * PROJECT QUESTIONNARY
 * @author : Hugot Jean-Michel and Philippot Gregoire
 */

package quiz.questionnary;

import quiz.answer.*;

public class Question {
	//ATTRIBUTS
	/*question*/
	private String question;
	/*points of question*/
	private int points;
	/*answer of question*/
	private Answer<?> answer;
	
	//CONSTRUCTOR
	/**
	 * Create a question
	 * @param q : (type-Sring) question
	 * @param nb_p : (type-int) points of question
	 * @param ans : (type-Answer) answer of question
	 */
	public Question(String q, int nb_p, Answer<?> ans) {
		this.question = q;
		this.points = nb_p;
		this.answer = ans;
	}
	
	//METHODS
	/**
	 * Get the question
	 * @return : (type-String) question
	 */
	public String getQuestion() {
		return this.question;
	}
	
	/**
	 * Get number's points of this question
	 * @return : (type-int) points
	 */
	public int getNbOfPoints() {
		return this.points;
	}
	
	/**
	 * Get answer of this question
	 * @return : (type-Answer) answer
	 */
	public Answer<?> getAnswer(){
		return this.answer;
	}
	
	/**
	 * Get the type of answer's question
	 * @return : (type-String) type of answer
	 */
	public String getAnswerType() {
		return this.answer.getAnswerType();
	}
	
	/**
	 * <code>ans</code> is acceptable ?
	 * @param ans : (type-String) a possible answer
	 * @return : (type-boolean) <code>true</code> if <code>ans</code> is acceptable
	 */
	public boolean isAcceptable(String ans) {
		return this.answer.isAcceptable(ans);
	}
	
	/**
	 * <code>ans</code> is correct ?
	 * @param ans : (type-String) a possible answer
	 * @return : (type-boolean) <code>true</code> if <code>ans</code> is correct
	 */
	public boolean isCorrect(String ans) {
		return this.answer.isCorrect(ans);
	}
}
