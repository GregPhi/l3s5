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

public class OneArmedBandit extends Item{
	//ATTRIBUTS
	/*cost to buy an item*/
	private int cost;
	/*items of one armed bandit*/
	private List<Item> OneArmed;
	
	//CONSTRUCTOR
	/**
	 * Create a one armed bandit
	 * @param cost : (type-int) cost to buy an item
	 */
	public OneArmedBandit(int cost) {
		this.cost = cost;
		this.OneArmed = new ArrayList<Item>();
	}
	
	/**
	 * Crate a one armed bandit with a random cost
	 * @param choseCost : (type-ArrayList)
	 */
	public OneArmedBandit(ArrayList<Integer> choseCost) {
		assert(choseCost.size() == 2);
		this.cost = randomInt(choseCost.get(0),choseCost.get(1));
		this.OneArmed = new ArrayList<Item>();
	}
	
	//METHODS
	@Override
	public String toString() {
		return "I'm the one armed bandit. I can sell an item to you.";
	}
	
	/**
	 * Get cost to buy an item
	 * @return : (type-int) cost to buy an item
	 */
	public int getCost() {
		return this.cost;
	}
	
	/**
	 * Add an item to on armed bandit
	 * @param item : (type-Item) add item to the list of item
	 */
	public void addItemInOneArmed(Item item) {
		this.OneArmed.add(item);
	}
	
	/**
	 * Remove an item to one armed bandit
	 * @param item : (type-Item) remove an item to the list
	 */
	public void removeItemInOneArmed(Item item) {
		this.OneArmed.remove(item);
	}
	
	/**
	 * Get the list
	 * @return : (type-ArrayList) the list of item buy
	 */
	public ArrayList<Item> getListOfItems(){
		ArrayList<Item> its = new ArrayList<Item>(this.OneArmed);
		return its;
	}
	
	/**
	 * Initialize one armed bandit
	 */
	public void initializeOneBandit() {
		this.addItemInOneArmed(new LifePotion(randomInt(10,50)));
		this.addItemInOneArmed(new StrengthPotion(randomInt(10,25)));
		this.addItemInOneArmed(new PurseGold(randomInt(10,50)));
		this.addItemInOneArmed(new OneArmedBandit(randomInt(50,150)));
	}
	
	@Override
	public void isUsedBy(Player player) {
		System.out.println("Welcome in my store.");
		if(player.getGold() >= this.cost) {
			this.initializeOneBandit();
			ListChoser lc = new ListChoser();
			Item item = lc.chose("For "+this.cost+" gold, choose an item : ",this.OneArmed);
			item.isUsedBy(player);
			player.changeGold(-this.cost);
			this.OneArmed.clear();
			player.act();
		}
		else {
			System.out.println("You do not have enough money, come back when you get more."+"\n"+"TIGHTFISTED");
			player.act();
		}
	}
}
