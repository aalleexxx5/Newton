package dk.sdu.newton.evil.genericEnemy;

import common.data.AvailableStates;
import common.data.Registrator;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class ThugPlugin implements BundleActivator {
	private Thug thug;
	
	@Override
	public void start(BundleContext bundleContext) throws Exception {
		thug = new Thug(200, 200);
		Registrator.registerEnemy(thug, 200);
		Registrator.getInstance().getState(AvailableStates.PLAY_STATE).addEntity(thug);
		
	}
	
	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		Registrator.unregisterEnemy(thug);
	}
}
