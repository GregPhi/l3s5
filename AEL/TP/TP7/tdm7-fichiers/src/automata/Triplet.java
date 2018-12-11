package automata;

public class Triplet {
	private String word;
	private int index;
	private State current;
	
	public Triplet(String w, int i, State c) {
		this.word = w;
		this.index = i;
		this.current = c;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public State getCurrent() {
		return current;
	}

	public void setCurrent(State current) {
		this.current = current;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
}
