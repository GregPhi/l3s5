/**
 * PROJECT QUESTIONNARY
 * @author : Hugot Jean-Michel and Philippot Gregoire
 */

package quiz.answer;

public class YesNoAnswer extends Answer<YesNo>{
	
	//CONSTRUCTOR
	/**
	 * Create a yes no answer
	 * @param ans : (type-YesNo) the answer
	 */
	public YesNoAnswer(YesNo ans) {
		super(ans);
	}
	
	/**
	 * Create a yes no answer
	 * @param ans : (type-String) the answer
	 */
	public YesNoAnswer(String ans) {
		this(createAnswer(ans.toLowerCase()));
	}
	
	//METHODS
	/**
	 * Create an Yes No answer 
	 * @param ans : (type-String) the answer to transform
	 * @return : (type-YesNo) answer Yes No
	 */
	public static YesNo createAnswer(String ans) {
		if("oui".equals(ans)){
			return YesNo.oui;
		}else {
			return YesNo.non;
		}
	}
	
	@Override
	public boolean isAcceptable(String ans) {
		if(ans.equals("oui") || ans.equals("non")) {
			return true;
		}
		return false;
	}
	
	@Override
	public String getAnswerType() {
		return "type : oui non answer.";
	}
	
	/**
	 * Opposite of answer
	 * @return : (type-YesNo) <code>YesNo.oui</code> if <code>this</code> equals to <code>YesNo.non</code> or <code>YesNo.non</code> if <code>this</code> equals to <code>YesNo.oui</code>
	 */
	public YesNo opposate() {
		return this.opposate();
	}
}
