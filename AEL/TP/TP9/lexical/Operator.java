package lexical;

public abstract class Operator extends AbstractToken {
	protected int arity;
	public abstract boolean compute(boolean... operands);
	protected Operator(TokenType type, String image,int arity){
		super(type,image);
		this.arity = arity;
	}
}
