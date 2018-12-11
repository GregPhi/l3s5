package lexical;

public class Or extends Operator{
	public Or(){
		super(TokenType.OR,"or",2);
	}

	@Override
	public boolean compute(boolean... operands) {
		return operands[0] || operands[1];
	}
	

}
