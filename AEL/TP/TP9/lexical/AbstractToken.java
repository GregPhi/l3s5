package lexical;
public abstract class AbstractToken implements Yytoken {
	private static String camelToUpper(String s){
		if (s.length()==0)
			return s;
		StringBuffer res = 
				new StringBuffer(Character.toUpperCase(s.charAt(0))+"");
		for (int i=1; i<s.length(); i++){
			if (Character.isUpperCase(s.charAt(i)))
				res.append("_");
			res.append(Character.toUpperCase(s.charAt(i)));
		}
		return res.toString();
	}

    public final String image;
    public final TokenType type;
    public String image(){
        return image;
    }
    
    public TokenType getType(){
        return type;
    }
    protected AbstractToken(TokenType type, String image){
        this.image = image;
        this.type= type;
        //this.type=TokenType.valueOf(camelToUpper(this.getClass().getSimpleName()));
    }
    
    public String toString(){
    	return type+"["+image+"]";
    }
}
