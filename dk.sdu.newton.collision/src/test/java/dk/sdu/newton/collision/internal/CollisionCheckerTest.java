package dk.sdu.newton.collision.internal;


import common.data.GameState;
import common.services.Collidable;
import dk.sdu.newton.collision.internal.mocks.CollidingMock;
import dk.sdu.newton.collision.internal.mocks.Collision;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CollisionCheckerTest {
	CollisionChecker checker;
	GameState testState;
	
	@Before
	public void setup(){
		checker = new CollisionChecker();
		testState = new GameState();
		assertEquals(0,testState.getEntitiesByInterface(Collidable.class).size());
	}
	
	@Test
	public void testNoErrorsWhenEmpty() {
		checker.update(testState);
	}
	
	@Test public void testNoCollision(){
		testState.addEntity(new CollidingMock(new float[] {0f,0f,10f,10f}));
		testState.addEntity(new CollidingMock(new float[] {20f,20f,10f,10f}));
		checker.update(testState);
	}
	
	@Test
	public void testNoCollisionEdgeCase(){
		testState.addEntity(new CollidingMock(new float[]{0f,0f,10f,10f}));
		testState.addEntity(new CollidingMock(new float[]{10f,10f,10,10}));
		checker.update(testState);
	}
	
	
	@Test(expected = Collision.class)
	public void testCollision(){
		testState.addEntity(new CollidingMock(new float[]{0f,0f,10f,10f}));
		testState.addEntity(new CollidingMock(new float[]{9f,9f,10f,10f}));
		checker.update(testState);
	}
	
	@Test(expected = Collision.class)
	public void testContainedCollision(){
		testState.addEntity(new CollidingMock(new float[]{0f,0f,10f,10f}));
		testState.addEntity(new CollidingMock(new float[]{2f,2f,2f,2f}));
		checker.update(testState);
	}
	
	
}