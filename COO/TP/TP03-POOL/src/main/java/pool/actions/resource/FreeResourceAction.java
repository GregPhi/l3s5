/**
 * Project POOL
 * @authors : Hugot Jean-Michel and Philippot Grégoire 
 */

package pool.actions.resource;

import pool.actions.ActionState;
import pool.exception.ActionFinishedException;
import pool.repool.ResourcePool;
import pool.resource.Resource;
import pool.resource.ResourceUser;

public class FreeResourceAction<R extends Resource> extends ResourceAction<R>{
	//CONSTRUCTOR
	/**
	 * Create a free resource action
	 * @param rP : (type-ResourcePool) the resource pool
	 * @param rU : (type-ResourceUser) the resource user
	 */
	public FreeResourceAction(ResourcePool<R> rP, ResourceUser<R> rU) {
		super(rP,rU);
	}

	@Override
	protected void reallyDoStep() throws ActionFinishedException {
		/*if(this.isFinished()) {
			throw new ActionFinishedException();
		}if(this.rUser.getResource() == null) {
			throw new IllegalArgumentException();
		}*/
		try {
			this.rPool.recoverResource(this.rUser.getResource());
			this.rUser.resetResource();
			this.done = true;
			this.act = ActionState.FINISHED;
			System.out.println("freesing resource from " + this.rPool);
			System.out.println(" success\n");
		}catch(IllegalArgumentException e) {
			System.out.println(" fail\n");
		}
	}

	@Override
	public boolean stopCondition() {
		return this.done;
	}

}
