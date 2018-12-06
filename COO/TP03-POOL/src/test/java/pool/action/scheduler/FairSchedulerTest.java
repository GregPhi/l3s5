package pool.action.scheduler;

import static org.junit.Assert.*;

import org.junit.Test;

import pool.actions.scheduler.FairScheduler;
import pool.actions.scheduler.Scheduler;
import pool.exception.ActionFinishedException;
import pool.exception.SchedulerStartedException;

public class FairSchedulerTest extends SchedulerTest{

	@Override
	protected Scheduler createScheduler() {
		return new FairScheduler();
	}
	
	@Test
	public void fairSchedulerOK() throws ActionFinishedException, SchedulerStartedException{
		Scheduler sch = this.createScheduler();
		for(int i = 0; i < 2; i++) {
			sch.addAction(new TwoStepAction());
		}
		for(int i = 0; i < 2; i++) {
			sch.doStep();
		}
		assertEquals(sch.getAct().size(), 2);
	}

}
