package pool.action.scheduler;

import static org.junit.Assert.*;

import org.junit.Test;

import pool.actions.Action;
import pool.actions.scheduler.Scheduler;
import pool.exception.ActionFinishedException;
import pool.exception.SchedulerStartedException;

public abstract class SchedulerTest {
	protected abstract Scheduler createScheduler();
	
	protected class TwoStepAction extends Action{
		private int stp = 0;
		@Override
		public boolean stopCondition(){
			return this.stp == 2;
		}
		@Override
		protected void reallyDoStep() throws ActionFinishedException {
			this.stp++;			
		}
	}
	
	@Test
	public void addActionOK() throws ActionFinishedException, SchedulerStartedException {
		Scheduler sch = this.createScheduler();
		assertEquals(sch.getAct().size(),0);
		sch.addAction(new TwoStepAction());
		assertEquals(sch.getAct().size(),2);
	}

	@Test(expected = ActionFinishedException.class)
	public void addActionWhenActionIsFinished() throws ActionFinishedException, SchedulerStartedException {
		Scheduler sch = this.createScheduler();
		Action act = new TwoStepAction();
		act.doStep();
		act.doStep();
		sch.addAction(act);
	}
	
	@Test
	public void aFinishedSchedulerDoesNotContainAnyNonFinishedAction() throws ActionFinishedException, SchedulerStartedException {
		Scheduler sch = this.createScheduler();
		for(int i = 0; i < 3; i++) {
			sch.addAction(new TwoStepAction());
		}
		for(int i = 0; i < 3; i++) {
			sch.doStep();
			sch.doStep();
		}
		assertTrue(sch.isFinished());
		for(Action a : sch.getAct()) {
			assertFalse(a.isFinished());
		}
	}
}
