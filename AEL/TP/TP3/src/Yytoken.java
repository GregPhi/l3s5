/**
 * AEL 2018
 * @author : bruno.bogaert (at) univ-lille.fr
 * Exemple du sujet JFLEX, version 0
 * Modifié par Philippot Grégoire
 */
public class Yytoken {
  private TokenType type;
  private String source;

  public Yytoken(TokenType type, String source){
    this.type = type ;
    this.source = source;
  }

  public TokenType getType() {
    return this.type;
  }

  public String getSource(){
    return this.source;
  }
}
