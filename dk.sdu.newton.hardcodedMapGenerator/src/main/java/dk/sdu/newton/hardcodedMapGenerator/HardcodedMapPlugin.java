package dk.sdu.newton.hardcodedMapGenerator;

import common.data.AvailableStates;
import common.data.Registrator;
import common.data.mapParts.Map;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class HardcodedMapPlugin implements BundleActivator {

    Map map = Registrator.getInstance().getState(AvailableStates.PLAY_STATE).getMap();

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("adding map");
        HardcodeMapGen hMapGen = new HardcodeMapGen();
        map.setCurrentRoomOnStart(hMapGen.getMap().getCurrentRoom());
        Registrator.getInstance().getState(AvailableStates.PLAY_STATE).setMap(hMapGen.getMap());
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        Registrator.getInstance().getState(AvailableStates.PLAY_STATE).addToSpawnList();

    }
}
