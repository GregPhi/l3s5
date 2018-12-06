package quiz.answer;

public enum YesNo {
	//ENUM
	oui,non;
	
	/**
	 * Opposite <code>oui</code> is <code>non</code> and opposite <code>non</code> is <code>oui</code>
	 * @return : (type-YesNo)
	 */
	public YesNo opposate(){
		if(this.equals(YesNo.oui)){
			return YesNo.non;
		}else{
			return YesNo.oui;
		}
	}
}
