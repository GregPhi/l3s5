/**
 * Project POOL
 * @authors : Hugot Jean-Michel and Philippot Grégoire 
 */

package pool.resource;

public class ResourceUser<R extends Resource>{
	//ATTRIBUTS
	/*resource*/
	protected R res;
	
	//METHODS
	/**
	 * Get the resource
	 * @return : (type-R) the resource
	 */
	public R getResource(){
	  return res;
	}
	
	/**
	 * Set the resource
	 * @param res : (type-R) the resource
	 */
	public void setResource(R res){
	  this.res = res;
	}
	
	/**
	 * Reset the resource
	 */
	public void resetResource(){
	  this.res = null;
	}
}
