package dk.sdu.newton.weapons;

import common.data.AvailableStates;
import common.data.Registrator;
import dk.sdu.newton.weapons.Rake.RakeBullet;
import dk.sdu.newton.weapons.Rake.RakeItem;
import dk.sdu.newton.weapons.crescentStrike.CrescentStrikeItem;
import dk.sdu.newton.weapons.crescentStrike.CrescentStrikeWeapon;
import dk.sdu.newton.weapons.emergency.EmergencyTpItem;
import dk.sdu.newton.weapons.flyingf.FlyingFItem;
import dk.sdu.newton.weapons.homingRocket.RocketItem;
import dk.sdu.newton.weapons.homingRocket.RocketWeapon;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class WeaponPlugin implements BundleActivator {
    CrescentStrikeWeapon csweapon;
    private RocketWeapon rocket;

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        rocket = new RocketWeapon();
        Registrator.getInstance().getState(AvailableStates.PLAY_STATE).addEntity(new EmergencyTpItem(new float[]{500, 500}));
        Registrator.getInstance().registerWeapon(rocket, 2);
        Registrator.getInstance().getState(AvailableStates.PLAY_STATE).addEntity(new CrescentStrikeItem(new float[]{350, 350}));
        Registrator.getInstance().getState(AvailableStates.PLAY_STATE).addEntity(new RocketItem(new float[]{500, 500}));
        Registrator.getInstance().getState(AvailableStates.PLAY_STATE).addEntity(new RakeItem(new float[]{450, 350}));
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        Registrator.getInstance().unregisterWeapon(rocket);
    }
}
