package analyse;

import java.io.IOException;
import java.io.Reader;

import lexical.Constant;
import lexical.LexicalException;
import lexical.TokenType;

public class BooleanExpTranslator extends AbstractParser<String> {

	public BooleanExpTranslator(Reader in) throws IOException, LexicalException {
		super(in);
	}

	@Override
	protected String axiom() throws SyntaxException, ParserException, IOException, LexicalException {
		return E();
	}

	private String E() throws SyntaxException, ParserException, IOException, LexicalException {
		switch (current.getType()) {
		case CONSTANT:
		case IDENT:
		case OPEN_BRACKET:
		case NOT:
			String s1 = T();
			String s2 = Ep();
			return s1 + s2;
		default:
			throw new SyntaxException(ErrorType.NO_RULE, current);
		}
	}

	private String Ep() throws ParserException, IOException, LexicalException, SyntaxException {
		switch (current.getType()) {
		case OR:
			String i = current.image();
			eat(TokenType.OR);
			String t = T();
			String ep = Ep();
			return " "+i+" or "+ t + ep;
		case CLOSE_BRACKET:
		case EOD:
			return "";
		default:
			throw new SyntaxException(ErrorType.NO_RULE, current);
		}
	}

	private String T() throws SyntaxException, ParserException, IOException, LexicalException {
		switch (current.getType()) {
		case CONSTANT:
		case IDENT:
		case OPEN_BRACKET:
		case NOT:
			String f = F();
			String tp = Tp();
			return f+tp;
		default:
			throw new SyntaxException(ErrorType.NO_RULE, current);
		}
	}

	private String Tp() throws ParserException, IOException, LexicalException, SyntaxException {
		switch (current.getType()) {
		case AND:
			String i = current.image();
			eat(TokenType.AND);
			String f = F();
			String tp = Tp();
			return " "+i+" and "+f+tp;
		case OR:
		case CLOSE_BRACKET:
		case EOD:
			return "";
		default:
			throw new SyntaxException(ErrorType.NO_RULE, current);
		}

	}

	private String F() throws SyntaxException, ParserException, IOException, LexicalException {
		switch (current.getType()) {
		case CONSTANT: {
			Constant c = (Constant) current;
			eat(TokenType.CONSTANT);
			return Boolean.toString(c.getValue());
		}
		case IDENT:
			String i = current.image();
			eat(TokenType.IDENT);
			return i;
		case OPEN_BRACKET:
			i = current.image();
			eat(TokenType.OPEN_BRACKET);
			String e = E();
			String i2 = current.image();
			eat(TokenType.CLOSE_BRACKET);
			return i+" "+e+" "+i2;
		case NOT:
			i = current.image();
			eat(TokenType.NOT);
			String f = F();
			return " not "+i+f;
		default:
			throw new SyntaxException(ErrorType.NO_RULE, current);
		}	}

}
