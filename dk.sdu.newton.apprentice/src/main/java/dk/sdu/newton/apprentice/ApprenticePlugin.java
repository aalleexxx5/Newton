package dk.sdu.newton.apprentice;

import common.data.AvailableStates;
import common.data.Registrator;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class ApprenticePlugin implements BundleActivator{
    //toDo make x and y random
    Apprentice apprentice = new Apprentice(200,200,32,32);
    @Override
    public void start(BundleContext bundleContext) throws Exception {

        //toDo difficulties might need changes
        Registrator.registerEnemy(apprentice,200);
        Registrator.getInstance().getState(AvailableStates.PLAY_STATE).addEntity(apprentice);

    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
Registrator.unregisterEnemy(apprentice);
    }
}
