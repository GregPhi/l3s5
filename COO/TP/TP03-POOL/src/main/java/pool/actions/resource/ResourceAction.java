/**
 * Project POOL
 * @authors : Hugot Jean-Michel and Philippot Grégoire 
 */

package pool.actions.resource;

import pool.actions.Action;
import pool.repool.ResourcePool;
import pool.resource.Resource;
import pool.resource.ResourceUser;

public abstract class ResourceAction<R extends Resource> extends Action{
	//ATTRIBUTS
	/*the resource user*/
	protected ResourceUser<R> rUser;
	/*the resource pool*/
	protected ResourcePool<R> rPool;
	/*if action is done*/
	protected boolean done;
	
	//CONSTRUCTOR
	/**
	 * Create a resource action
	 * @param rP : (type-RessourcePool) the resource pool
	 * @param rU : (type-ResourceUser) the resource user
	 */
	public ResourceAction(ResourcePool<R> rP, ResourceUser<R> rU) {
		this.rPool = rP;
		this.rUser = rU;
		this.done = false;
	}
	
	//METHODS
	/**
	 * Get the resource pool
	 * @return : (type-RsourcePool) the resource pool
	 */
	public ResourcePool<R> getPool(){
		return this.rPool;
	}
	
	/**
	 * Get the resource user
	 * @return : (type-ResourceUser) : the resource user
	 */
	public ResourceUser<R> getUser(){
		return this.rUser;
	}

}
