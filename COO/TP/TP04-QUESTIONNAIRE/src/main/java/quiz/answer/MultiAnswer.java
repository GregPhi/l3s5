/**
 * PROJECT QUESTIONNARY
 * @author : Hugot Jean-Michel and Philippot Gregoire
 */

package quiz.answer;

import java.util.List;
import java.util.Arrays;

public class MultiAnswer extends Answer<List<String>>{
	
	//ATRIBUTS
	/*separator*/
	private final static String MULTI_ANSWER_SEPARATOR = ";";
	
	//CONSTRUCTOR
	/**
	 * Create a multiple answer
	 * @param ans : (type-List) the list of answer
	 */
	public MultiAnswer(List<String> ans) {
		super(ans);
	}
	
	/**
	 * Create a multiple answer
	 * @param ans : (type-String) the answer
	 * @throws BadValueException : bad value
	 */
	public MultiAnswer(String ans) throws BadValueException{
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
		if(!ans.contains(MULTI_ANSWER_SEPARATOR)) {
			throw new BadValueException();
		}
		return Arrays.asList(ans.split(" "+MULTI_ANSWER_SEPARATOR+" "));
	}
	
	@Override
	public boolean isCorrect(String ans) {
		return this.getAnswer().toString().toLowerCase().contains(ans.toLowerCase());
	}
	
	@Override
	public boolean isAcceptable(String ans) {
		//return true, because type of List is string
		return true;
	}
	
	@Override
	public String getAnswerType() {
		return "type : multi answer.";
	}
}
