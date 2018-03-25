package dk.sdu.newton.playerStandardControlls;

import java.awt.event.KeyEvent;

public interface IPlayerStandardControls {

    String keyPressed(KeyEvent e);

    String keyReleased(KeyEvent e);
}
