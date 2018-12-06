/**
 * PROJECT QUESTIONNARY
 * @author : Hugot Jean-Michel and Philippot Gregoire
 */

package quiz.answer;

import java.util.List;
import java.util.Arrays;

public class OneCorrectAnswer extends Answer<List<String>>{
	//ATTRIBUTS
	/*separator*/
	private final static String ONE_ANSWER_SEPARATOR = "|";
	//ajouter un argument qui correspondrait à la bonne réponse
	
	//CONSTRUCTOR
	/**
	 * Create a one correct answer
	 * @param ans : (type-List) the list of answer
	 */
	public OneCorrectAnswer(List<String> ans) {
		super(ans);
	}
	
	/**
	 * Create a one correct answer
	 * @param ans : (type-String) the answer
	 * @throws BadValueException : bad value
	 */
	public OneCorrectAnswer(String ans) throws BadValueException {
		this(toList(ans));
	}
	
	//METHODS
	/**
	 * to list
	 * @param ans : (type-String) the answer
	 * @return : (type-List) list of answer
	 * @throws BadValueException : bad value
	 */
	public static List<String> toList(String ans) throws BadValueException{
		if(!ans.contains(ONE_ANSWER_SEPARATOR)) {
			throw new BadValueException();
		}
		return Arrays.asList(ans.split(" \\"+ONE_ANSWER_SEPARATOR+" "));
	}
	
	@Override
	public boolean isCorrect(String ans) {
		return this.getAnswer().get(0).toLowerCase().equals(ans.toLowerCase());
	}
	
	@Override
	public boolean isAcceptable(String ans) {
		return true;
	}
	
	@Override
	public String getAnswerType() {
		System.out.print("Your choice are :");
		for(String s : this.getAnswer()) {
			System.out.print(" "+s);
		}
		System.out.println();
		return "type : one correct answer.";
	}
}
