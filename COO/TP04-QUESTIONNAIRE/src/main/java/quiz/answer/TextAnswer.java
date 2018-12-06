/**
 * PROJECT QUESTIONNARY
 * @author : Hugot Jean-Michel and Philippot Gregoire
 */

package quiz.answer;

public class TextAnswer extends Answer<String>{

	//CONSTRUCTOR
	/**
	 * Create a textual answer
	 * @param ans : (type-String) the answer
	 */
	public TextAnswer(String ans) {
		super(ans);
	}
	
	//METHODS
	@Override
	public boolean isAcceptable(String ans) {
		return true;
	}
	
	@Override
	public String getAnswerType() {
		return "type : text answer.";
	}
}
