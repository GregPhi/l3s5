/**
 * Project POOL
 * @authors : Hugot Jean-Michel and Philippot Grégoire 
 */

package pool.actions.scheduler;

import pool.actions.Action;
import pool.exception.ActionFinishedException;

public class SequentialScheduler extends Scheduler{
	//CONSTRUCTOR	
	/**
	 * Create a SequentialScheduler 
	 */
	public SequentialScheduler() {
		this(null);
	}
	
	/**
	 * Create a SequentialScheduler
	 * @param msg : type-String) the message
	 */
	public SequentialScheduler(String msg){
		super(msg);
	}

	//METHODS
	@Override
	protected Action nextAction() {
		return this.actions.get(this.currentIdx);
	}

	@Override
	protected void removeAction(){
		this.actions.remove(this.currentIdx);
	}

	@Override
	public void reallyDoStep() throws ActionFinishedException {
		this.actions.get(this.currentIdx).doStep();
		if(this.actions.get(this.currentIdx).isFinished()) {
			if(this.currentIdx < this.actions.size()-1) {
				this.currentIdx++;
			}
		}
	}
}
