package pool.exception;

public class SchedulerStartedException extends Exception {
	//ATTRIBUTS
		private static final long serialVersionUID = 1L;

		//CONSTRUCTOR
		/**
		 * Create a scheduler started exception
		 */
		public SchedulerStartedException(){
			super();
		}
		
		/**
		 * Create a scheduler started exception
		 * @param msg : (type-String) the message of exception
		 */
		public SchedulerStartedException(String msg){
			super(msg);
		}

}
