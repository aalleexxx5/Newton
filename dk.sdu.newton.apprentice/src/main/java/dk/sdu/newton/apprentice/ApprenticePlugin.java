package dk.sdu.newton.apprentice;

import common.data.AvailableStates;
import common.data.Registrator;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class ApprenticePlugin implements BundleActivator {
	//toDo make x and y random
	Apprentice apprentice;
	
	@Override
	public void start(BundleContext bundleContext) throws Exception {
		
		//toDo difficulties might need changes
		System.out.println("Adding apprentice!");
		Registrator.registerEnemy(new Apprentice(200,200), 100);
	}
	
	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		Registrator.unregisterEnemy(apprentice);
		Registrator.getInstance().getState(AvailableStates.PLAY_STATE).removeEntity(apprentice);
		apprentice =null;
	}
}
