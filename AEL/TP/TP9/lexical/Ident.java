package lexical;

public class Ident extends AbstractToken{
	public Ident(String ident){
		super(TokenType.IDENT,ident);
	}
}
