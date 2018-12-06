/**
 * Project POOL
 * @authors : Hugot Jean-Michel and Philippot Grégoire 
 */

package pool.actions.scheduler;

import java.util.ArrayList;
import java.util.List;
//import java.util.NoSuchElementException;
import pool.actions.Action;
import pool.actions.ActionState;
import pool.exception.ActionFinishedException;
import pool.exception.SchedulerStartedException;

public abstract class Scheduler extends Action{
	//ATTRIBUTS
	/*list of action*/
	protected List<Action> actions;
	/*index of current action*/
	protected int currentIdx = 0;
	
	//CONSTRUCTOR
	/**
	 * Create a Scheduler
	 */
	public Scheduler() {
		this(null);
	}
	 
	/**
	 * Create a Scheduler
	 * @param msg : (type-String) the message
	 */
	public Scheduler(String msg){
	    super(msg);
	    this.actions = new ArrayList<Action>();
	}
	
	//METHODS
	/**
	 * Get the list of actions
	 * @return : (type-List) the list of actions
	 */
	public List<Action> getAct(){
		return this.actions;
	}
	
	/*@Override
	public void reallyDoStep() throws ActionFinishedException, NoSuchElementException{
		if(!this.actions.isEmpty()) {
			Action a = this.nextAction(); 
			a.doStep();
			if(a.isFinished()){
				// TOREMOVE
				System.out.println("Finish");
				this.removeAction();
			}
	    } else {
	    	System.out.println("No more action");
	    }
	}*/
	
	@Override
	public boolean stopCondition(){
		//return this.actions.isEmpty();
		for(Action a : this.actions) {
			if((!a.isFinished())) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Add an action
	 * @param a : (type-Action) an action
	 * @throws SchedulerStartedException scheduler started
	 * @throws ActionFinishedException : action is finish
	 */	
	public void addAction(Action a) throws SchedulerStartedException, ActionFinishedException{
		if(this.getState() != ActionState.READY) {
			throw new SchedulerStartedException();
		}if(this.isFinished()) {
			throw new ActionFinishedException();
		}
		this.actions.add(a);
	}
		
	/**
	 * Next action
	 * @return : (type-Action) the next action
	 */
	protected abstract Action nextAction();
	
	/**
	 * Remove an action
	 */
	protected abstract void removeAction();
}
