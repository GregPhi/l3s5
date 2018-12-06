package pool.action;

import static org.junit.Assert.*;

import org.junit.Test;

import pool.actions.ForseeableAction;
import pool.actions.Action;
import pool.exception.ActionFinishedException;

public class ForseeableActionTest extends ActionTest{
	@Override
	protected Action createAction() {
		return new ForseeableAction(null, 3);
	}
	
	@Test
	public void aForseeableActionHasAFixedNumberOfSteps() throws ActionFinishedException {
		int n = (int)(Math.random()*100)+1;
		Action act = new ForseeableAction(null, n);
		for(int i = 0; i < n; i++) {
			act.doStep();
		}
		assertTrue(act.isFinished());
	}
}
