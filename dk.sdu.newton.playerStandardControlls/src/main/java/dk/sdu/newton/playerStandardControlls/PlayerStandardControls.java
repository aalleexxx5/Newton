package dk.sdu.newton.playerStandardControlls;

import common.data.AvailableStates;
import common.data.InputActionMap;
import common.data.Registrator;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class PlayerStandardControls implements BundleActivator {

    private InputActionMap inputs;

    /**
     * register keys to actionmap
     *
     * @param bundleContext loads and unloads controls, here loads
     */
    @Override
    public void start(BundleContext bundleContext) {
        inputs = Registrator.getInstance().getState(AvailableStates.PLAY_STATE).getInputActionMap();
        inputs.registerKey("W", "up");
        inputs.registerKey("A", "left");
        inputs.registerKey("S", "down");
        inputs.registerKey("D", "right");
        inputs.registerKey("Space", "shoot"); // might have to be uppercase?
        inputs.registerKey("A", "display message");
    }

    /**
     * unregister keys to actionmap
     *
     * @param bundleContext loads and unloads controls, here unloads
     */
    @Override
    public void stop(BundleContext bundleContext) {
        inputs.removeKey("W", "up");
        inputs.removeKey("A", "left");
        inputs.removeKey("S", "down");
        inputs.removeKey("D", "right");
        inputs.removeKey("Space", "shoot");
    }
}
