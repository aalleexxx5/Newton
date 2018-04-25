package dk.sdu.newton.einstein;

import common.data.AvailableStates;
import common.data.GameState;
import common.data.Registrator;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class EinsteinPlugin implements BundleActivator {

	private Einstein einstein = new Einstein(600, 200);

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("" +
				"Added: Einstein");
		Registrator.registerEnemy(einstein,1000);
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		Registrator.getInstance().getState(AvailableStates.PLAY_STATE).removeEntity(einstein);
		Registrator.unregisterEnemy(einstein);
	}
}
