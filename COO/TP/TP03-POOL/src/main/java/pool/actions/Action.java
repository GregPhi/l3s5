/**
 * Project POOL
 * @authors : Hugot Jean-Michel and Philippot Grégoire 
 */

package pool.actions;

import java.util.NoSuchElementException;

import pool.exception.ActionFinishedException;

public abstract class Action {
  //ATTRIBUTS
	/*message of action*/
	protected String msg;
	/*the action state*/
	protected ActionState act = ActionState.READY;

  //CONSTRUCTOR
	/**
	 * Create an action
	 */
	public Action() {
		this(null);
	}
	
	/**
	 * Create an action
	 * @param m : (type-String) the message of action
	 */
	public Action(String m) {
		this.msg = m;
	}

  //METHODS
	/**
	 * Make an action
	 * @throws ActionFinishedException : if the action is already finished
	 * @throws NoSuchElementException : if no more element
	 */
	public void doStep() throws ActionFinishedException, NoSuchElementException{
	    if(this.isFinished()){
	      throw new ActionFinishedException("action is finisehd");
	    }
	    if(this.msg != null){
	      System.out.println(this.msg);
	    }
	    this.reallyDoStep();
	    if(this.stopCondition()){
	      this.act = ActionState.FINISHED;
	    }
	}

	/**
	 * Get the message
	 * @return : (type-String) message
	 */
	public String getMsg() {
		return this.msg;
	}
	
	/**
	 * Check if this action is finished
	 * @return : (type-boolean) <code>true</code> if this action is finished
	 */
	public boolean isFinished() {
		return this.act == ActionState.FINISHED;
	}
	
	/**
	 * Get the state of this action
	 * @return : (type-ActionState) the state of this action
	 */
	public ActionState getState() {
		return this.act;
	}

	/**
	 * Make an action
	 * @throws ActionFinishedException : if this action is already finished
	 */
	protected abstract void reallyDoStep() throws ActionFinishedException;
  
  	/**
  	 * The stop condition of an action
  	 * @return : (type-boolean) <code>true</code> if the stop condition of this action is completed
  	 */
  	public abstract boolean stopCondition();
}
