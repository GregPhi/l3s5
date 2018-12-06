/**
 * PROJECT QUESTIONNARY
 * @author : Hugot Jean-Michel and Philippot Gregoire
 */

package quiz.answer;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class AnswerFactory {
	/*factory answer*/
	public final static AnswerFactory FACTORY = new AnswerFactory();
	
	/**
	 * Create an answer
	 * @param answerClass : (type-String)
	 * @param answerText : (type-String)
	 * @return : (type-Answer) an answer
	 * @throws ClassNotFoundException : class not found
	 * @throws SecurityException : security
	 * @throws NoSuchMethodException : no such method
	 * @throws InvocationTargetException : invocation target
	 * @throws IllegalArgumentException : illegal argument
	 * @throws IllegalAccessException : illegal access
	 * @throws InstantiationException : instantiation
	 */
	public Answer<?> creatAnswer(String answerClass, String answerText) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class<?> c = Class.forName(answerClass);
		Constructor<?> ctr = c.getConstructor(String.class);
		Answer<?> a = (Answer<?>)ctr.newInstance(answerText);
		return a;
	}
}
