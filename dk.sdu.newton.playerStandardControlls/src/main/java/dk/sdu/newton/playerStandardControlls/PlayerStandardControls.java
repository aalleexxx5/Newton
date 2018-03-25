package dk.sdu.newton.playerStandardControlls;

import common.data.InputActionMap;

import java.awt.event.KeyEvent;

public class PlayerStandardControls implements IPlayerStandardControls {

    private InputActionMap iamap = new InputActionMap();
    private String moveUp;
    private String moveLeft;
    private String moveDown;
    private String moveRight;
    private String shoot;

    public void registerKey() {

    }

    /**
     * Invoked when a key has been pressed.
     *
     * @param e
     */
    @Override
    public String keyPressed(KeyEvent e) {
        String w, a, s, d;
        String keyPressed = String.valueOf(e);

        return null;
    }

    /**
     * Invoked when a key has been released.
     *
     * @param e
     */
    @Override
    public String keyReleased(KeyEvent e) {

        return null;
    }
}
