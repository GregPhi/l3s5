/**
 * Project POOL
 * @authors : Hugot Jean-Michel and Philippot Grégoire 
 */

package pool.actions.resource;

import java.util.NoSuchElementException;

import pool.actions.ActionState;
import pool.exception.ActionFinishedException;
import pool.repool.ResourcePool;
import pool.resource.Resource;
import pool.resource.ResourceUser;

public class TakeResourceAction<R extends Resource> extends ResourceAction<R>{
	//CONSTRUCTOR
	/**
	 * Create a take resource action
	 * @param rP : (type-ResourcePool) the resource pool
	 * @param rU : (type-ResourceUser) the resource user
	 */
	public TakeResourceAction(ResourcePool<R> rP, ResourceUser<R> rU) {
		super(rP,rU);
	}

	@Override
	protected void reallyDoStep() throws ActionFinishedException, NoSuchElementException{
		/*if(this.isFinished()) {
			throw new ActionFinishedException();
		}if(this.rUser.getResource() != null) {
			throw new IllegalArgumentException();
		}*/
		try {
			this.rUser.setResource(this.rPool.provideResource());
			this.done = true;
			this.act = ActionState.FINISHED;
			System.out.println(" success\n");
		}catch(NoSuchElementException e) {
			System.out.println(" fail\n");
		}
	}

	@Override
	public boolean stopCondition() {
		return this.done;
	}

}
