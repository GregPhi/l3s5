/**
 * Project POOL
 * @authors : Hugot Jean-Michel and Philippot Grégoire 
 */

package pool.actions.scheduler;

import pool.actions.Action;
import pool.exception.ActionFinishedException;

public class FairScheduler extends Scheduler{
	//CONSTRUCTOR
	/**
	 * Create a FairScheduler
	 */
	public FairScheduler() {
		this(null);
	}
	
	/**
	 * Create a FairScheduler
	 * @param msg : (type-String) the message
	 */
	public FairScheduler(String msg){
	  super(msg);
	}
	
	//METHODS
	@Override
	protected Action nextAction() {
		return this.actions.get(this.currentIdx++%this.actions.size());
	}
	
	@Override
	protected void removeAction(){
	  this.actions.remove(this.currentIdx);
	}

	@Override
	protected void reallyDoStep() throws ActionFinishedException {
		if(this.currentIdx >= this.actions.size()) {
			this.currentIdx = 0;
		}if(!this.actions.get(this.currentIdx).isFinished()) {
			this.actions.get(this.currentIdx).doStep();
			this.currentIdx++;
		}else {
			this.currentIdx++;
			this.reallyDoStep();
		}
	}
}
