/**
 * PROJECT QUESTIONNARY
 * @author : Hugot Jean-Michel and Philippot Gregoire
 */

package quiz.answer;

public abstract class Answer<T> {
	//ATTRIBUTS
	/*answer*/
	private T answer;
	
	//CONSTRUCTOR
	/**
	 * Create an answer
	 * @param ans : (type-T) answer
	 */
	public Answer(T ans) {
		this.answer = ans;
	}
	
	//METHODS
	/**
	 * Get the answer
	 * @return : (type-T) the answer
	 */
	public T getAnswer() {
		return this.answer;
	}
	
	/**
	 * <code>ans</code> is acceptable ?
	 * @param ans : (type-String) a possible answer
	 * @return : (type-boolean) <code>true</code> if <code>ans</code> is an acceptable answer
	 */
	public abstract boolean isAcceptable(String ans);
	
	/**
	 * Get the answer type : multi, numerical, one correct, textual or yes/no answer
	 * @return : (type-String) the type of answer
	 */
	public abstract String getAnswerType();
	
	/**
	 * <code>code</code> is correct ?
	 * @param ans : (type-String) a possible answer
	 * @return : (type-boolean) <code>true</code> if <code>ans</code> is the answer
	 */
	public boolean isCorrect(String ans) {
		if(this.answer instanceof Integer) {
			return Integer.parseInt(ans) == (Integer)this.answer; 
		}
		return ans.toLowerCase().equals(this.answer.toString().toLowerCase());
	}
	
	/**
	 * The answer
	 * @return : (type-String) the answer
	 */
	@Override
	public String toString() {
		if(this.answer instanceof Integer) {
			return Integer.toString((Integer)this.answer);
		}
		return this.answer.toString();
	}
}
