package lexical;

public class And extends Operator{
	public And(){
		super(TokenType.AND,"and",2);
	}

	@Override
	public boolean compute(boolean... operands) {
		return operands[0] && operands[1];
	}
	

}
