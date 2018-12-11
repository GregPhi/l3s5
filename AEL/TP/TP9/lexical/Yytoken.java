package lexical;
public interface Yytoken {
 /**
  * token source text
  **/
 String image();
 /**
  * type of the token (enum) 
  */
 TokenType getType();

}