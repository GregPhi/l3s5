package reduction;

public class BooleanExpression {
	protected String image;

	public String getImage() {
		return image;
	}

	public BooleanExpression(String image) {
		this.image = image;
	}

	public String toString() {
		return image;
	}

	/**
	 * 
	 * @return right composition by AND
	 */
	public BooleanExpression rightAnd(BooleanExpression other) {
		if(this.toString().equals("false")) {
			return this;
		}
		if(other.toString().equals("false")) {
			return other;
		}
		if(this.toString().equals("true")) {
			return other;
		}
		if(other.toString().equals("true")) {
			return this;
		}
		return new BooleanExpression(this.toString()+" et "+other.toString());
	}

	/**
	 * 
	 * @return right composition by OR
	 */
	public BooleanExpression rightOr(BooleanExpression other) {
		if(this.toString().equals("false")) {
			return other;
		}
		if(other.toString().equals("false")) {
			return this;
		}
		if(this.toString().equals("true")) {
			return this;
		}
		if(other.toString().equals("true")) {
			return other;
		}
		return new BooleanExpression(this.toString()+" or "+other.toString());
	}

	/**
	 * 
	 * @return negation of this expression
	 */
	public BooleanExpression not() {
		if(this.toString().equals("false")) {
			return new BooleanConstant(true);
		}
		if(this.toString().equals("true")) {
			return new BooleanConstant(false);
		}
		return new BooleanExpression(" not "+this.toString());
	}

	/**
	 * 
	 * @return wrap expression with brackets
	 */
	public BooleanExpression wrap() {
		if(this.toString().equals("(false)")){
			return new BooleanConstant(false);
		}
		if(this.toString().equals("(true)")){
			return new BooleanConstant(true);
		}
		return this;
	}
}
