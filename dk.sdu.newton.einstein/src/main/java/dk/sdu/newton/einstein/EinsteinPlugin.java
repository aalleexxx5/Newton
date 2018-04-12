package dk.sdu.newton.einstein;

import common.data.AvailableStates;
import common.data.GameState;
import common.data.Registrator;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class EinsteinPlugin implements BundleActivator {

	//TODO make reflections (register the class instead of the object)

	Einstein einstein = new Einstein(400, 400, 32, 32);

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		Registrator.registerEnemy(einstein,1000);
		Registrator.getInstance().getState(AvailableStates.PLAY_STATE).addEntity(einstein);
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		Registrator.unregisterEnemy(einstein);
	}
}
