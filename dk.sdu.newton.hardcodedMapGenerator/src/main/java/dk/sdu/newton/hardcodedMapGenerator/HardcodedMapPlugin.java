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
        Registrator.getInstance().getState(AvailableStates.PLAY_STATE).setMap(hMapGen.getMap());
        hMapGen.getMap().getCurrentRoom().addAllEntity(Registrator.getInstance().getState(AvailableStates.PLAY_STATE).getSpawnList());



    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        Registrator.getInstance().getState(AvailableStates.PLAY_STATE).addToSpawnList();
        map = new Map();
        map.setCurrentRoomToEmptyRoom();
        Registrator.getInstance().getState(AvailableStates.PLAY_STATE).setMap(map);
    }
}
