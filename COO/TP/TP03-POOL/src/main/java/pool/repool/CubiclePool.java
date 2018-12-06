/**
 * Project POOL
 * @authors : Hugot Jean-Michel and Philippot Grégoire 
 */

package pool.repool;

import pool.resource.Cubicle;

public class CubiclePool extends ResourcePool<Cubicle>{
	//METHODS
	@Override
	protected Cubicle createRes(){
		return new Cubicle();
	}

	//CONSTRUCTOR
	/**
	 * Create a cubicle pool
	 * @param nbR : (type-int) the number of cubicle pool
	 */
	public CubiclePool(int nbR){
		super(nbR);
	 }
}
