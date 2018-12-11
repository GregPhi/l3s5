package analyse;

import java.io.IOException;
import java.io.Reader;

import lexical.Constant;
import lexical.LexicalException;
import lexical.TokenType;
import lexical.Yytoken;
import reduction.BooleanConstant;
import reduction.BooleanExpression;
import reduction.BooleanIdent;

public class BooleanExpReductor extends AbstractParser<String> {

	public BooleanExpReductor(Reader in) throws IOException, LexicalException {
		super(in);
	}

	@Override
	protected String axiom() throws SyntaxException, ParserException, IOException, LexicalException {
		return E().toString();
	}

	private BooleanExpression E() throws SyntaxException, ParserException, IOException, LexicalException {
		switch (current.getType()) {
		case CONSTANT:
		case IDENT:
		case OPEN_BRACKET:
		case NOT:
			BooleanExpression e1 = T();
			BooleanExpression e2 = Ep();
			return e1.rightOr(e2);
		default:
			throw new SyntaxException(ErrorType.NO_RULE, current);
		}
	}

	private BooleanExpression Ep() throws ParserException, IOException, LexicalException, SyntaxException {
		switch (current.getType()) {
		case OR:
			next();
			BooleanExpression e1 = T();
			BooleanExpression e2 = Ep();
			return e1.rightOr(e2);
		case CLOSE_BRACKET:
		case EOD:
			return new BooleanConstant(false); // neutral element for OR
		default:
			throw new SyntaxException(ErrorType.NO_RULE, current);
		}
	}

	private BooleanExpression T() throws SyntaxException, ParserException, IOException, LexicalException {
		switch(current.getType()) {
		case CONSTANT:
		case IDENT:
		case OPEN_BRACKET:
		case NOT:
			BooleanExpression f = F();
			BooleanExpression tp = Tp();
			return f.rightAnd(tp);
		default:
			throw new SyntaxException(ErrorType.NO_RULE, current);
		}
	}

	private BooleanExpression Tp() throws ParserException, IOException, LexicalException, SyntaxException {
		switch(current.getType()) {
		case AND:
			eat(TokenType.AND);
			BooleanExpression f = F();
			BooleanExpression tp = Tp();
			return f.rightOr(tp);
		case OR:
		case CLOSE_BRACKET:
		case EOD:
			return new BooleanConstant(false);
		default:
			throw new SyntaxException(ErrorType.NO_RULE, current);
		}
	}

	private BooleanExpression F() throws SyntaxException, ParserException, IOException, LexicalException {
		switch (current.getType()) {
		case CONSTANT: {
			Constant c = (Constant) current;
			eat(TokenType.CONSTANT);
			BooleanExpression v = new BooleanConstant(c.getValue());
			return v;
		}
		case IDENT: {
			Yytoken t = current;
			eat(TokenType.IDENT);
			return new BooleanIdent(t.image());
		}
		case OPEN_BRACKET: {
			eat(TokenType.OPEN_BRACKET);
			BooleanExpression e = E();
			eat(TokenType.CLOSE_BRACKET);
			return e.wrap();
		}
		case NOT: {
			eat(TokenType.NOT);
			BooleanExpression f = F();
			return f.not();
		}
		default:
			throw new SyntaxException(ErrorType.NO_RULE, current);
		}
	}

}
