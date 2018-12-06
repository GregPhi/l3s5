package pool;

import java.util.NoSuchElementException;
import pool.actions.*;
import pool.actions.resource.*;
import pool.exception.*;
import pool.resource.*;
import pool.repool.*;

public class Swimmer extends Action{
	//ATTRIBUTS
	/*name of swimmer*/
	private String name;
	private ResourcePool<Basket> basketPool;
	private ResourcePool<Cubicle> cubiclePool;
	private ResourceUser<Basket> basketU;
	private ResourceUser<Cubicle> cubicleU;
	private ForseeableAction undress;
	private ForseeableAction swimm;
	private ForseeableAction dress;
	private TakeResourceAction<Basket> basketT;
	private FreeResourceAction<Basket> basketF;
	private TakeResourceAction<Cubicle> cubicleT;
	private FreeResourceAction<Cubicle> cubicleF;

	//CONSTRUCTOR
	/**
	 * Create a swimmer
	 * @param name : (type-String) name of swimmer
	 * @param basket : (type-ResourcePool)
	 * @param cubicle : (type-ResourcePool)
	 * @param undressT : (type-int) undressing time
	 * @param swimmT : (type-int) swimming time
	 * @param dressT : (type-int) dreesing time
	 * @throws ActionFinishedException : action finished
	 * @throws SchedulerStartedException : scheduler started
	 */
	public Swimmer(String name, ResourcePool<Basket> basket, ResourcePool<Cubicle> cubicle, int undressT, int swimmT, int dressT) throws ActionFinishedException, SchedulerStartedException {
		this.name = name;
		this.basketPool = basket;
		this.cubiclePool = cubicle;
		this.basketU = new ResourceUser<Basket>();
		this.cubicleU = new ResourceUser<Cubicle>();
		this.undress = new ForseeableAction("undress",undressT);
		this.swimm = new ForseeableAction("swimm",swimmT);
		this.dress = new ForseeableAction("dress",dressT);
		this.basketT = new TakeResourceAction<Basket>(basket, this.basketU);
		this.basketF = new FreeResourceAction<Basket>(basket, this.basketU);
		this.cubicleT = new TakeResourceAction<Cubicle>(cubicle, this.cubicleU);
		this.cubicleF = new FreeResourceAction<Cubicle>(cubicle, this.cubicleU);
	}

	@Override
	public void reallyDoStep() throws ActionFinishedException, NoSuchElementException {
		System.out.println(this.name+"'s turn.");
		if(this.basketU.getResource() == null) {
			System.out.println(this.name+" trying to take resource froom pool basket...");
			this.basketT.doStep();
		}else if(!this.undress.isFinished()) {
			if(this.cubicleU.getResource() == null) {
				System.out.println(this.name+" trying to take resource froom pool cubicle...");
				this.cubicleT.doStep();
			}else {
				this.undress.doStep();
			}
		}else if(this.cubicleU.getResource() != null && !this.swimm.isFinished()) {
			this.cubicleF.doStep();
		}else if(!this.swimm.isFinished()) {
			this.swimm.doStep();
		}else if(this.cubicleU.getResource() != null && !this.dress.isFinished()) {
			System.out.println(this.name+" trying to take resource froom pool cubicle...");
			this.cubicleT.doStep();
		}else if(!this.dress.isFinished()) {
			this.dress.doStep();
		}else if(this.cubicleU.getResource() != null && this.dress.isFinished()) {
			this.cubicleF.doStep();
		}else {
			this.basketF.doStep();
		}
	}

	/**
	 * Get the basket resource pool
	 * @return : (type-ResourcePool) basket resource pool
	 */
	public ResourcePool<Basket> getBasketPool(){
		return this.basketPool;
	}
	
	/**
	 * Get the cubicle resource pool
	 * @return : (type-ResourcePool) cubicle resource pool
	 */
	public ResourcePool<Cubicle> getCubiclePool(){
		return this.cubiclePool;
	}
	
	@Override
	public boolean stopCondition() {
		return this.undress.isFinished() && this.swimm.isFinished() && this.dress.isFinished() && this.basketU.getResource() == null && this.cubicleU.getResource() == null;
	}
}
