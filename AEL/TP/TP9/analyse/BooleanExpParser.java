package analyse;

import java.io.IOException;
import java.io.Reader;

import lexical.LexicalException;
import lexical.TokenType;

public class BooleanExpParser extends AbstractParser<Void> {

	public BooleanExpParser(Reader in) throws IOException, LexicalException {
		super(in);
	}

	@Override
	protected Void axiom() throws SyntaxException, ParserException, IOException, LexicalException {
		E();
		return null;
	}

	private void E() throws SyntaxException, ParserException, IOException, LexicalException {
		switch (current.getType()) {
		case CONSTANT:
		case IDENT:
		case OPEN_BRACKET:
		case NOT:
			T();
			Ep();
			break;
		default:
			throw new SyntaxException(ErrorType.NO_RULE, current);
		}
	}

	private void Ep() throws ParserException, IOException, LexicalException, SyntaxException {
		switch (current.getType()) {
		case OR:
			eat(TokenType.OR);
			T();
			Ep();
			break;
		case CLOSE_BRACKET:
		case EOD:
			break;
		default:
			throw new SyntaxException(ErrorType.NO_RULE, current);
		}
	}

	private void T() throws SyntaxException, ParserException, IOException, LexicalException {
		switch (current.getType()) {
		case CONSTANT:
		case IDENT:
		case OPEN_BRACKET:
		case NOT:
			F();
			Tp();
			break;
		default:
			throw new SyntaxException(ErrorType.NO_RULE, current);
		}
	}

	private void Tp() throws ParserException, IOException, LexicalException, SyntaxException {
		switch (current.getType()) {
		case AND:
			eat(TokenType.AND);
			F();
			Tp();
			break;
		case OR:
		case CLOSE_BRACKET:
		case EOD:
			break;
		default:
			throw new SyntaxException(ErrorType.NO_RULE, current);
		}
	}

	private void F() throws SyntaxException, ParserException, IOException, LexicalException {
		switch (current.getType()) {
		case CONSTANT:
			eat(TokenType.CONSTANT);
			break;
		case IDENT:
			eat(TokenType.IDENT);
			break;
		case OPEN_BRACKET:
			eat(TokenType.OPEN_BRACKET);
			E();
			eat(TokenType.CLOSE_BRACKET);
			break;
		case NOT:
			eat(TokenType.NOT);
			F();
			break;
		default:
			throw new SyntaxException(ErrorType.NO_RULE, current);
		}

	}

}
