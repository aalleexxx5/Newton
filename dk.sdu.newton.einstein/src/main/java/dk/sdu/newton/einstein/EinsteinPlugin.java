package dk.sdu.newton.einstein;

import common.data.GameState;
import common.data.Registrator;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class EinsteinPlugin implements BundleActivator {

	//TODO make reflections (register the class instead of the object)

	Einstein einstein = new Einstein(0, 0, 10, 10);

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		Registrator.registerEnemy(einstein,1000);
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		Registrator.unregisterEnemy(einstein);
	}
}
