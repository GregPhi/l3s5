/**
 * PROJECT QUESTIONNARY
 * @author : Hugot Jean-Michel and Philippot Gregoire
 */

package graphical;

public class Counter{
	//ATRIBUTS
	/*number of question*/
	private int nbQuest;
	/*number of points*/
	private int nbPoints;
	/*number of answer*/
	private int nbRep;
	/*number of max points*/
	private int maxPoints;
	
	//CONSTRUCTOR
	/**
	 * Create a counter
	 */
	public Counter() {
		this.reset();
	}
	
	//METHODS
	/**
	 * Reset <code>nbQuest</code>, <code>nbPoints</code>, <code>nbRep</code>, <code>maxPoints</code>
	 */
	public void reset() {
		this.nbQuest = 0;
		this.nbPoints = 0;
		this.nbRep = 0;
		this.maxPoints = 0;
	}
	
	/**
	 * Increment <code>nbQuest</code>
	 */
	public void incNbQuest() {
		this.nbQuest++;
	}
	
	/**
	 * Add <code>nbPoints</code>
	 * @param p : (type-int) add
	 */
	public void incNbPoints(int p) {
		this.nbPoints += p;
	}
	
	/**
	 * Increment <code>nbRep</code>
	 */
	public void incNbRep() {
		this.nbRep++;
	}
	
	/**
	 * Add <code>maxPoints</code>
	 * @param p : (type-int) add
	 */
	public void incMaxPoints(int p) {
		this.maxPoints += p;
	}
	
	/**
	 * Check if <code>nbRep</code> and <code>nbQuest</code> are equals
	 * @return : (type-boolean) <code>true</code> if <code>nbRep</code> and <code>nbQuest</code> are equals
	 */
	public boolean isFilled() {
		return this.nbRep == this.nbQuest;
	}
	
	/**
	 * Get number of points
	 * @return : (type-int) points
	 */
	public int getNbPoints() {
		return this.nbPoints;
	}
	
	/**
	 * Get max points
	 * @return : (type-int) max points
	 */
	public int getMaxPoints() {
		return this.maxPoints;
	}
	
	/**
	 * Get number of rep
	 * @return : (type-int) rep
	 */
	public int getNbRep() {
		return this.nbRep;
	}
	
	/**
	 * Get number of question
	 * @return : (type-int) question
	 */
	public int getNbQuest() {
		return this.nbQuest;
	}
}