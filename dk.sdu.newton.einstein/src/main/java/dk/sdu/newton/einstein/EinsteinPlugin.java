package dk.sdu.newton.einstein;

import common.data.AvailableStates;
import common.data.GameState;
import common.data.Registrator;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class EinsteinPlugin implements BundleActivator {

	private Einstein einstein = new Einstein(400, 200);

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		Registrator.getInstance().getState(AvailableStates.PLAY_STATE).addEntity(einstein);
		Registrator.registerEnemy(einstein,1000);
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		Registrator.unregisterEnemy(einstein);
	}
}
