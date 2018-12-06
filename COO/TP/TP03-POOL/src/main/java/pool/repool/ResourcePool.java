/**
 * Project POOL
 * @authors : Hugot Jean-Michel and Philippot Grégoire 
 */

package pool.repool;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import pool.resource.Resource;

public abstract class ResourcePool<R extends Resource>{
	//ATTRIBUTS
	/*number of state*/
	protected int nbRes;
	/*the available resource*/
	protected List<R> availableR;
	/*the used resource*/
	protected List<R> busyR;

	//CONSTRUCTOR
	/**
	 * Create a ResourcePool
	 * @param nbR : (type-int) number of state
	 */
	public ResourcePool(int nbR) {
		this.nbRes = nbR;
		this.availableR = new ArrayList<R>();
		for(int i =0; i < nbRes; i++) {
			this.availableR.add(this.createRes());
		}
		this.busyR = new ArrayList<R>();
	}

	//METHODS
	/**
	 * Create a resource pool specific
	 * @return : (type-R) a resource
	 */
	protected abstract R createRes();

	/**
	 * Use a resource to the available
	 * @return : (type-R) the resource used
	 * @throws NoSuchElementException : if available is empty
	 */
	public R provideResource() throws NoSuchElementException{
		if(this.availableR.isEmpty()) {
			throw new NoSuchElementException();
		}else {
			R res = this.availableR.get(0);
			this.busyR.add(res);
			System.out.println(res.description());
			this.availableR.remove(res);
			return res;
		}
	}

	/**
	 * Remove a resource to the busy
	 * @param res : (type-R) the resource remove
	 * @throws IllegalArgumentException : if <code>res</code> is not contains in busy
	 */
	public void recoverResource(R res) throws IllegalArgumentException{
		if(!this.busyR.contains(res)) {
			throw new IllegalArgumentException();
		}else {
			this.busyR.remove(res);
			this.availableR.add(res);
		}
	}
	
	/**
	 * Get the available resources
	 * @return : (type-List) available resources
	 */
	public List<R> getAvailableR(){
		return this.availableR;
	}
	
	/**
	 * Get the busy resources
	 * @return : (type-List) busy resources
	 */
	public List<R> getBusyR(){
		return this.busyR;
	}
	
	/**
	 * Set the available resources
	 * @param av : (type-List) new available resources
	 */
	public void setAvailableR(List<R> av) {
		this.availableR = av;
	}
	
	/**
	 * Set the busy resources
	 * @param bu : (type-List) new busy resources
	 */
	public void setBusyR(List<R> bu) {
		this.busyR = bu;
	}
}
