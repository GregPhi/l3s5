package ard;

/**
 * TP 8 AEL : Exo 2
 * @author : Philippot Gregoire
 */

import java.io.Reader;
import condenses_lex.TokenType;

public class ArdExo2 extends Ard {

	public TokenType getToken(char param){
	  if(param == 'a' || param == 'b' || param == 'c'){
	    return TokenType.LETTRE;
	  }
	  if(param == '0' || param == '1' || param == '2' || param == '3' || param == '4' || param == '5' || param == '6'|| param == '7'|| param == '8'|| param == '9'){
	    return TokenType.ENTIER;
	  }
	  if(param == '('){
	    return TokenType.OUVRANTE;
	  }
	  if(param == ')'){
	    return TokenType.FERMANTE;
	  }
		if(pram == 'END_OF_MARKER'){
		return TokenType.EOD;
	  }
		return TokenType.UNKNOWN;
	}

	public ArdExo2(Reader in) {
		super(in);
	}

  private void S() throws SyntaxException, ParserException {
	TokenType token = this.getToken(current);
    switch (token) {
      case LETTRE:
        E();
        R();
        S();
        break;
      case ENTIER:
      case OUVRANTE:
        E();
        R();
        S();
        break;
      case FERMANTE:
        break;
      case EOD:
        break;
      default:
        throw new SyntaxException(ErrorType.NO_RULE,current);
    }
  }

  private void E() throws SyntaxException, ParserException {
		TokenType token = this.getToken(current);
			switch (token) {
      case LETTRE:
        eat(current);
        break;
      case ENTIER:
      case OUVRANTE:
        eat('(');
        R();
        eat(')');
        break;
      case FERMANTE:
      case EOD:
      default:
        throw new SyntaxException(ErrorType.NO_RULE,current);
    }
  }

  private void R() throws SyntaxException, ParserException {
		TokenType token = this.getToken(current);
			switch (token) {
      case LETTRE:
        break;
      case ENTIER:
		eat(current);
		break;
      case OUVRANTE:
        break;
      case FERMANTE:
        break;
      case EOD:
        break;
      default:
        throw new SyntaxException(ErrorType.NO_RULE,current);
    }
  }

  @Override
  protected void axiom() throws SyntaxException, ParserException {
    S();
  }

}
