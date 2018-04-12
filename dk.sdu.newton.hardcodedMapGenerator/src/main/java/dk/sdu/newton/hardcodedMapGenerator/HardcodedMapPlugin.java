package dk.sdu.newton.hardcodedMapGenerator;

import common.data.AvailableStates;
import common.data.Registrator;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class HardcodedMapPlugin implements BundleActivator {
    HardcodeMapGen hMapGen;

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        hMapGen = new HardcodeMapGen();
        Registrator.getInstance().getState(AvailableStates.PLAY_STATE).setMap(hMapGen.getMap());
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {

        Registrator.getInstance().getState(AvailableStates.PLAY_STATE).setMap(null);
    }
}
