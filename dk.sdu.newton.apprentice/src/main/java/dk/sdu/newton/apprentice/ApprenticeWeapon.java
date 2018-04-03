package dk.sdu.newton.apprentice;

import common.data.Weapon;

public class ApprenticeWeapon extends Weapon {
    private Apprentice apprentice;
    public ApprenticeWeapon(Apprentice apprentice){
        this.apprentice=apprentice;
    }

    @Override
    public void onEquip() {

    }

    private void fire(){
        float x=apprentice.getBounds()[0];
        float y=apprentice.getBounds()[1];
        Bullet bullet = new Bullet("filename", x, y, 10, 10);

    }
}
