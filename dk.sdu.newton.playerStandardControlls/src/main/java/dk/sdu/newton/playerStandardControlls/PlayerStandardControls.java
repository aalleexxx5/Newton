package dk.sdu.newton.playerStandardControlls;

import common.data.AvailableStates;
import common.data.InputActionMap;
import common.data.Registrator;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import java.awt.event.KeyEvent;

public class PlayerStandardControls implements BundleActivator {

    private InputActionMap inputs;

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        inputs = Registrator.getInstance().getState(AvailableStates.PLAY_STATE).getInputActionMap();
        inputs.registerKey("W", "up");
        inputs.registerKey("A", "left");
        inputs.registerKey("S", "down");
        inputs.registerKey("D", "right");
        inputs.registerKey("Space", "shoot"); // might have to be uppercase?

        //inputs.registerAction("display message", (st)-> System.out.println("Action called: "+st));
        inputs.registerKey("A", "display message");
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        inputs.removeKey("w","up");
        inputs.removeKey("a","left");
        inputs.removeKey("s","down");
        inputs.removeKey("d","right");
        inputs.removeKey("space","shoot");
    }
}
