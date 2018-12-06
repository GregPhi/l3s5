/**
 * Project POOL
 * @authors : Hugot Jean-Michel and Philippot Grégoire 
 */

package pool.repool;

import pool.resource.Basket;

public class BasketPool extends ResourcePool<Basket>{
	//METHODS
	@Override
	protected Basket createRes(){
		return new Basket();
	}

	//CONSTRUCTOR
	/**
	 * Create a basket pool
	 * @param nbR ; (type-int) the number of basket pool
	 */
	public BasketPool(int nbR){
		super(nbR);
	}
}
