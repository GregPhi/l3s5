/**
 * Project DUNGEON
 * 
 * Hugot Jean-Michel
 * Philippot Grégoire
 * 
 * Group 3
 */

package adventure.item;

import java.util.*;

import adventure.ListChoser;
import adventure.character.Player;

public class Enigme extends Item{
	//ATTRIBUTS
	/*enigme*/
	private String enigme;
	/*list of answers*/
	private List<String> listAnswers;
	/*answer*/
	private String answer;
	
	//CONSTRUCTOR
	/**
	 * Create an enigme
	 * @param enigme : (type-String) the enigme 
	 * @param answer : (type-String) the answer of engigme
	 */
	public Enigme(String enigme, String answer) {
		this.enigme = enigme;
		this.listAnswers = new ArrayList<String>();
		this.answer = answer;
	}
	
	//METHODS
	@Override
	public String toString() {
		return this.enigme;
	}
	
	/**
	 * Set enigme
	 * @param enigme : (type-String) enigme
	 */
	public void setEnigme(String enigme) {
		this.enigme = enigme;
	}
	
	/**
	 * Get answer
	 * @return : (type-String) answer of enigme
	 */
	public String getAnswer() {
		return this.answer;
	}
	
	/**
	 * Set answer
	 * @param answer : (type-String) answer of enigme
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	/**
	 * Add answer in the chose list of answers
	 * @param answer : (type-String) add a possible answer of enigme
	 */
	public void addAnswer(String answer) {
		this.listAnswers.add(answer);
	}
	
	/**
	 * Reset the enigme
	 * @return : (type-Enigme) enigme reset
	 */
	public Enigme reset() {
		return new Enigme("","");
	}
		
	@Override
	public void isUsedBy(Player player) {
		System.out.println("You look the room, and 'votre attention est attiré par une épitaphe'");
		if(this.toString()!="") {
			ListChoser lc = new ListChoser();
			String answ = lc.chose(this.toString(), this.listAnswers);
			if(this.getAnswer().equals(answ)) {
				player.getRoom().addItem(new PurseGold(randomInt(5,20)));
				player.getRoom().removeItem(this);
				player.act();
			}else {
				player.die();
			}
			
		}else {
			System.out.println("Vous avez déja répondu à l'énigme");
			player.act();
		}
		
	}

}
