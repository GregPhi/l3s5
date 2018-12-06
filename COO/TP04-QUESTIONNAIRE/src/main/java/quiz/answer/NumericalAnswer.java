/**
 * PROJECT QUESTIONNARY
 * @author : Hugot Jean-Michel and Philippot Gregoire
 */

package quiz.answer;

public class NumericalAnswer extends Answer<Integer>{
	
	//CONSTRUCTOR
	/**
	 * Create a numerical answer
	 * @param ans : (type-Integer) the answer
	 */
	public NumericalAnswer(Integer ans) {
		super(ans);
	}
	
	/**
	 * Create a numerical answer
	 * @param ans : (type-String) the answer
	 */
	public NumericalAnswer(String ans) {
		this(new Integer(ans));
	}
	
	//METHODS
	@Override
	public boolean isAcceptable(String ans) {
		try {
			Integer.valueOf(ans);
		}catch(Exception e) {
			return false;
		}
		return true;
	}
	
	@Override
	public String getAnswerType() {
		return "type : numerical answer.";
	}
}
