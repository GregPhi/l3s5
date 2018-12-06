package pool.action;

import static org.junit.Assert.*;

import org.junit.Test;

import pool.actions.Action;
import pool.actions.ActionState;
import pool.exception.ActionFinishedException;

public abstract class ActionTest {
	protected abstract Action createAction();
	
	@Test
	public void nextActionIsInReadyState() {
		Action act = this.createAction();
		assertEquals(act.getState(),ActionState.READY);
	}
	
	@Test(timeout=1000)
	public void finishedImpliesStopCondition() throws ActionFinishedException {
		Action act = this.createAction();
		while(!act.isFinished()) {
			act.doStep();
		}
		assertTrue(act.stopCondition());
	}
	
	@Test(expected = ActionFinishedException.class)
	public void finishedActionCannotContinue() throws ActionFinishedException {
		Action act = this.createAction();
		while(!act.isFinished()) {
			act.doStep();
		}
		assertTrue(act.stopCondition());
		act.doStep();
	}
}
