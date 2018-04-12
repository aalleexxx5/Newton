package dk.sdu.newton.player;

import common.data.AvailableStates;
import common.data.Registrator;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class PlayerPlugin implements BundleActivator {
	private Player player;
	
	@Override
	public void start(BundleContext bundleContext) throws Exception {
		player = new Player(400,400);
		Registrator.getInstance().getState(AvailableStates.PLAY_STATE).addEntity(player);
	}
	
	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		Registrator.getInstance().getState(AvailableStates.PLAY_STATE).removeEntity(player);
		player = null;
	}
}
