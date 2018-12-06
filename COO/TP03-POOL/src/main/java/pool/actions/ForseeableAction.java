/**
 * Project POOL
 * @authors : Hugot Jean-Michel and Philippot Grégoire 
 */

package pool.actions;

import java.util.NoSuchElementException;

import pool.exception.ActionFinishedException;

public class ForseeableAction extends Action{
  //ATTRIBUTS
	/*a counter of the wait time*/
  protected int waitTime;
  /*wait time*/
  protected final int waitTimeMax;

  //CONSTRUCTOR
  /**
   * Create a forseeable action
   * @param msg : (type-String)
   * @param wT : (type-int) time to wait
   */
  public ForseeableAction(String msg, int wT){
    super(msg);
    this.waitTime = 0;
    this.waitTimeMax = wT;
  }

  //METHODS
  @Override
  protected void reallyDoStep() throws NoSuchElementException, ActionFinishedException{
	  /*this.act = ActionState.IN_PROGRESS;
	  this.waitTime++;
	  if(this.stopCondition()){ 
		  this.act = ActionState.FINISHED;
	  }*/
	  if(!this.isFinished()) {
		  this.waitTime++;
		  System.out.println("waiting : "+this.waitTime+" / "+this.waitTimeMax);
	  }
  }

  @Override
  public boolean stopCondition(){
    return this.waitTime == this.waitTimeMax;
  }
}
