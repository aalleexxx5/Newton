package dk.sdu.newton.core.internal;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import common.data.AvailableStates;
import common.data.GameState;
import common.data.Registrator;

import java.util.ArrayList;
import java.util.HashMap;

public class Game implements ApplicationListener {
	
	private static OrthographicCamera cam;
	private ShapeRenderer sr;
	private GameState playState;
	private final HashMap<String, Sprite> sprites = new HashMap<>();

	public void create() {
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.translate(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		cam.update();
		playState = Registrator.getInstance().getState(AvailableStates.PLAY_STATE);
	}
	
	public void render() {
		// clear screen to black
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		update();
		
		draw();
	}
	
	private void update() {
		// Update

		playState.setDeltaTime(Gdx.graphics.getDeltaTime());
        ArrayList<String> keyNames = playState.getInputActionMap().getKeyNames();



        for (String keyName : keyNames) {
            boolean isKeyPressed = Gdx.input.isKeyPressed(Input.Keys.valueOf(keyName));
            if(isKeyPressed){
            playState.getInputActionMap().onKeyPress(keyName);
        }}

		/*
		playState.getEntitiesByInterface(Updatable.class).forEach(it->it.update(playState));
		playState.getUpdatables().forEach(it->it.update(playState));
		playState.getPostUpdateables().forEach(it->it.update(playState));
		*/
	}
	
	private void draw() {
		/*
		ArrayList<common.data.Sprite> commonSprites = new ArrayList<>(32);
		playState.getGameEntities().forEach(it-> commonSprites.add(it.draw()));
		*/
	}
	
	public void resize(int width, int height) {
	
	}
	
	public void pause() {
	}
	
	public void resume() {
	}
	
	public void dispose() {
	}
}
