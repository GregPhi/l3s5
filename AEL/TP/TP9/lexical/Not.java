package lexical;

public class Not extends Operator{
	public Not(){
		super(TokenType.NOT,"not",1);
	}

	@Override
	public boolean compute(boolean... operands) {
		return ! operands[0];
	}
	

}
