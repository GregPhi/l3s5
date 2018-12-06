package generics;

/** define collectors able to collect (and carry) one specific type T of objects
 * only one T object can be carried at a time
 */

public class Collector<T>{

	//ATTRIBUTS
	/* Name of Collector */
	private String name;
	/* object of collector */
	private T carriedObject;


	//CONSTRUCTOR
	/**
	 * Create a collector
	 * @param name (type-String) name of collector
	 */
    public Collector(String name) {
    	this.name = name;
    	this.carriedObject = null;
    }

    //METHODS

    /**
     * Return the name of collector
     * @return : (type-String)
     */
    public String toString() {
    	return this.name;
    }

    /**
     * Description of collector
     * @return : (type-String)
     */
    public String description() {
    	return this.name + " carries " + this.carriedObject;
    }

    // METHODES a DEFINIR
    // take : pour prendre un objet de type T (si aucun de "tenu")
    /**
     * Take a object to a collector
     * @param object : (type-T) object to give for a collector
     * @throws AlreadyCarryingException : Collector have already a object
     */
    public void take(T object) throws AlreadyCarryingException{
    	if(this.carriedObject == null) {
    		this.carriedObject = object;
    	}else {
    		throw new AlreadyCarryingException();
    	}
    }

    // getCarriedObject : pour connaitre l'objet "porte" (null si saucun)
    /**
     * Get the object of this collector
     * @return : (type-T)
     */
    public T getCarriedObject() {
    	return this.carriedObject;
    }

    // giveTo : donne l'objet porte a un autre ramasseur compatible
    /**
     * Give the object of a collector to an another collector
     * @param collect : (type-Collector)
     * @throws AlreadyCarryingException : Collector have already a object
     */
    public void giveTo(Collector<? super T> collect) throws AlreadyCarryingException{
    	if(collect.getCarriedObject() == null) {
    		collect.take(this.drop());
    		//this.carriedObject = null;
    	}else {
    		throw new AlreadyCarryingException();
    	}
    }

    // drop : depose l'objet "tenu"
    /**
     * Drop the item of collector
     * @return : (type-T)
     */
    public T drop() {
    	final T item = this.getCarriedObject();
    	this.carriedObject = null;
    	return item;
    }

    //MAIN
    public static void main(String[] args) throws AlreadyCarryingException{

		Carrot c1 = new Carrot(1);
		Carrot c2 = new Carrot(2);
		Carrot c3 = new Carrot(3);
		Apple p1 = new Apple(1);
		Apple p2 = new Apple(2);

		Collector<Carrot> carrotCollector1 = new Collector<Carrot>("carrot-collector-1");
		Collector<Carrot> carrotCollector2 = new Collector<Carrot>("carrot-collector-2");
		Collector<Apple> appleCollector1 = new Collector<Apple>("apple-collector-1");

		// attention ici le type d'objets ramasses est Legume :
		Collector<Vegetable> vegetableCollector = new Collector<Vegetable>("vegetable-collector");

		carrotCollector1.take(c3);
		System.out.println(carrotCollector1.description());
		// NE COMPILE PAS
		// carrotCollector2.take(p1);

		// NE COMPILE PAS
		// carrotCollector1.giveTo(appleCollector1);

		// COMPILE :
		carrotCollector1.giveTo(vegetableCollector);

		// NE COMPILE PAS
		// vegetableCollector.giveTo(carrotCollector1);
		// NE COMPILE PAS
		// appleCollector1.giveTo(vegetableCollector);

		carrotCollector1.take(c1);
		carrotCollector1.giveTo(carrotCollector2);
		System.out.println(carrotCollector1.description());
		System.out.println(carrotCollector2.description());
		carrotCollector1.take(c2);


		try {
			carrotCollector1.giveTo(carrotCollector2);
		} catch (AlreadyCarryingException e) {
			//System.out.println("*** exception : " + carrotCollector2 + " porte deja qque chose");
			System.out.println(" * " + e.getMessage());
		}

		appleCollector1.take(p2);
		System.out.println(appleCollector1.description());
		try {
			appleCollector1.take(p1);
		} catch (AlreadyCarryingException e) {
			//System.out.println("*** exception : " + appleCollector1 + " porte deja qque chose");
			System.out.println(" * " + e.getMessage());
		}
		appleCollector1.drop();
		System.out.println(appleCollector1.description());
		appleCollector1.take(p1);

     }
}
