package dk.sdu.newton.collision.internal;

import common.data.AvailableStates;
import common.data.Registrator;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class CollisionPlugin implements BundleActivator {
	CollisionChecker checker;
	@Override
	public void start(BundleContext bundleContext) throws Exception {
		checker = new CollisionChecker();
		Registrator.getInstance().getState(AvailableStates.PLAY_STATE).addPostUpdatable(checker);
	}
	
	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		Registrator.getInstance().getState(AvailableStates.PLAY_STATE).removePostUpdatable(checker);
	}
}
