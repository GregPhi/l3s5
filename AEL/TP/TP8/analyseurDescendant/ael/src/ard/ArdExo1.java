package ard;

/**
 * TP 8 AEL : Exo 1 
 * @author : Philippot Gregoire
 */

import java.io.Reader;

public class ArdExo1 extends Ard {

	public ArdExo1(Reader in) {
		super(in);
	}

  private void S() throws SyntaxException, ParserException {
    switch (current) {
      case 'a':
        E();
        R();
        S();
        break;
      case 'b':
          E();
          R();
          S();
          break;
      case 'c':
          E();
          R();
          S();
          break;
      case '0':
      case '1':
      case '2':
      case '3':
      case '4':
      case '5':
      case '6':
      case '7':
      case '8':
      case '9':
      case '(':
        E();
        R();
        S();
        break;
      case ')':
        break;
      case END_MARKER:
        break;
      default:
        throw new SyntaxException(ErrorType.NO_RULE,current);
    }
  }

  private void E() throws SyntaxException, ParserException {
    switch (current) {
      case 'a':
        L();
        break;
      case 'b':
          L();
          break;
      case 'c':
          L();
          break;
      case '0':
      case '1':
      case '2':
      case '3':
      case '4':
      case '5':
      case '6':
      case '7':
      case '8':
      case '9':
      case '(':
        eat('(');
        S();
        eat(')');
        break;
      case ')':
      case END_MARKER:
      default:
        throw new SyntaxException(ErrorType.NO_RULE,current);
    }
  }

  private void R() throws SyntaxException, ParserException {
    switch (current) {
	  case 'a':
		  break;
	  case 'b':
		  break;
	  case 'c':
		  break;
      case '0':
    	  C();
          break;
      case '1':
    	  C();
          break;
      case '2':
    	  C();
          break;
      case '3':
    	  C();
          break;
      case '4':
    	  C();
          break;
      case '5':
    	  C();
          break;
      case '6':
    	  C();
          break;
      case '7':
    	  C();
          break;
      case '8':
    	  C();
          break;
      case '9':
        C();
        break;
      case '(':
        break;
      case ')':
        break;
      case END_MARKER:
        break;
      default:
        throw new SyntaxException(ErrorType.NO_RULE,current);
    }
  }

  private void L() throws SyntaxException, ParserException {
    switch (current) {
      case 'a':
        eat(current);
        break;
      case 'b':
          eat(current);
          break;
      case 'c':
          eat(current);
          break;
      case '0':
      case '1':
      case '2':
      case '3':
      case '4':
      case '5':
      case '6':
      case '7':
      case '8':
      case '9':
      case '(':
      case ')':
      case END_MARKER:
      default:
        throw new SyntaxException(ErrorType.NO_RULE,current);
    }
  }

  private void C() throws SyntaxException, ParserException {
    switch (current) {
      case 'a':
      case 'b':
      case 'c':
      case '0':
    	  eat(current);
          break;
      case '1':
    	  eat(current);
          break;
      case '2':
    	  eat(current);
          break;
      case '3':
    	  eat(current);
          break;
      case '4':
    	  eat(current);
          break;
      case '5':
    	  eat(current);
          break;
      case '6':
    	  eat(current);
          break;
      case '7':
    	  eat(current);
          break;
      case '8':
    	  eat(current);
          break;
      case '9':
        eat(current);
        break;
      case '(':
      case ')':
      case END_MARKER:
      default:
        throw new SyntaxException(ErrorType.NO_RULE,current);
    }
  }

  @Override
  protected void axiom() throws SyntaxException, ParserException {
    S();
  }

}
