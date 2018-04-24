package dk.sdu.newton.weapons;

import common.data.AvailableStates;
import common.data.Registrator;
import dk.sdu.newton.weapons.crescentStrike.CrescentStrikeWeapon;
import dk.sdu.newton.weapons.homingRocket.RocketItem;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class WeaponPlugin implements BundleActivator{
    CrescentStrikeWeapon csweapon;

    @Override
    public void start(BundleContext bundleContext) throws Exception {
    	Registrator.getInstance().getState(AvailableStates.PLAY_STATE).addEntity(new RocketItem(new float[]{500,500}));
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {

    }
}
