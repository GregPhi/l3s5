package analyse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import lexical.LexicalException;
import lexical.TokenType;
import lexical.Yylex;
import lexical.Yytoken;

/**
 * Classe de Base pour Analyseur Récursif Descendant Les symboles à analyser
 * sont des tokens lu <strong>via l'analyseur lexical</strong> dans le Reader fourni à
 * l'instanciation. Cette classe doit être étendue afin d'implémenter des méthodes
 * d'analyse adaptées à la grammaire.
 * 
 * Le type générique T est le type du résultat renvoyé par l'analyseur.
 * 
 * @author Bruno.Bogaert (at) univ-lille1.fr
 *
 */

public abstract class AbstractParser<T> {
	private boolean invalid = false;
	protected Yylex lexer; // lexical parser

	protected Yytoken current; // current token

	/**
	 * Avance d'une position dans la lecture des tokens
	 * 
	 * @throws ParserException
	 *             si on est en fin de texte.
	 * @throws LexicalException
	 * @throws IOException
	 */
	protected void next() throws ParserException, IOException, LexicalException {
		if (current.getType() == TokenType.EOD) {
			throw new ParserException();
		}
		shift();
	}

	private void shift() throws IOException, LexicalException {
		current = lexer.yylex();
	}

	/**
	 * Vérifie que le token courant correspond au type de token attendu puis
	 * progresse d'une position dans la lecture.
	 * 
	 * @throws SyntaxException
	 *             si la vérification de correspondance échoue
	 * @throws LexicalException
	 * @throws IOException
	 */
	protected void eat(TokenType expected) throws SyntaxException, ParserException, IOException, LexicalException {
		if (current.getType() != expected)
			throw new SyntaxException(ErrorType.UNMATCHING_TOKEN, current, expected);
		if (expected == TokenType.EOD)
			current = null;
		else
			next();
	}

	/**
	 * Doit simplement invoquer la méthode implémentant l'axiome de la
	 * grammaire.
	 * 
	 * @throws LexicalException
	 * @throws IOException
	 */
	protected abstract T axiom() throws SyntaxException, ParserException, IOException, LexicalException;

	/**
	 * Analyse le texte. Déclenche une exception en cas d'erreur.
	 * 
	 * Ne peut être invoquée qu'une seule fois.
	 * 
	 * @throws SyntaxException
	 *             En cas d'erreur de syntaxe
	 * @throws ParserException
	 *             En cas d'erruer de utilisation du parser.
	 * @throws LexicalException
	 * @throws IOException
	 */
	public T parse() throws SyntaxException, ParserException, IOException, LexicalException {
		if (invalid)
			throw new ParserException();
		// Axiom :
		T result = axiom();
		// check end of data :
		eat(TokenType.EOD);
		this.invalid = true;
		return result;
	}

	protected AbstractParser(Reader in) throws IOException, LexicalException {
		lexer = new Yylex(new BufferedReader(in));
		shift();
	}

}
