package analyse;

import lexical.TokenType;
import lexical.Yytoken;

/**
 * 
 * @author Bruno.Bogaert -at- univ-lille1.fr
 * 
 *         Représentation des erreurs détectées par l'analyse syntaxique LL(1) 2
 *         types d'erreurs sont définis par le type ErrorType : - NO_RULE :
 *         absence, dans la table LL(1), de règle applicable à une variable et
 *         un token donnés - UNMATCHING_TOKEN : se produit quand on trouve un
 *         token d'un type différent de celui attendu.
 * 
 */
public class SyntaxException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 833687096891542484L;
	private ErrorType error;
	private Yytoken current;
	private TokenType expected;

	static protected void appendStack(StringBuffer buff, StackTraceElement[] stack) {
		String lastMethod = stack[0].getMethodName();
		int i = 1;
		while (!lastMethod.equals("parse")) {
			lastMethod = stack[i].getMethodName();
			buff.append("variable " + lastMethod);
			buff.append(" line " + stack[i].getLineNumber());
			buff.append(System.getProperty("line.separator"));
			i++;
		}
	}

	static protected String prepareMessage(ErrorType error, Yytoken current, TokenType expected,
			StackTraceElement[] stack) {
		StringBuffer buff = new StringBuffer();
		switch (error) {
		case NO_RULE:
			buff.append("method " + stack[0].getMethodName() + ", token " + current + ": no rule");
			break;
		case UNMATCHING_TOKEN:
			buff.append(
					"method " + stack[0].getMethodName() + ", " + "found : " + current + ", expected : " + expected);
			break;
		default:
			return "";
		}
		buff.append(System.getProperty("line.separator"));
		appendStack(buff, stack);
		return buff.toString();
	}

	public String getMessage() {
		return prepareMessage(this.error, this.current, this.expected, this.getStackTrace());

	}

	/**
	 * @param error
	 *            : type d'erreur rencontrée
	 * @param current
	 *            : valeur du token courant au moment de la détection d'erreur
	 * @param expected
	 *            : type de token attendu (uniquement en cas d'erreur
	 *            UNMATCHING_TOKEN)
	 */
	public SyntaxException(ErrorType error, Yytoken current, TokenType expected) {
		this.error = error;
		this.current = current;
		this.expected = expected;
	}

	/**
	 * @param error
	 *            : type d'erreur rencontrée
	 * @param current
	 *            : valeur du token courant au moment de la détection d'erreur
	 */

	public SyntaxException(ErrorType error, Yytoken current) {
		this(error, current, null);
	}

}
