package lexical;

public class Constant extends AbstractToken{
	protected boolean value;
	public Constant(boolean value){
		super(TokenType.CONSTANT, Boolean.toString(value));
		this.value = value;
	}
	public boolean getValue(){
		return value;
	}
}
