package dk.sdu.newton.core.internal;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import common.data.AvailableStates;
import common.data.Entity;
import common.data.GameState;
import common.data.Registrator;
import common.services.Destructable;
import common.services.EntityPart;
import common.services.Updatable;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Game implements ApplicationListener {
	
	private static OrthographicCamera cam;
	private ShapeRenderer sr;
	private GameState playState;
	private final HashMap<String, Sprite> sprites = new HashMap<>();
	private SpriteBatch batch;

	public void create() {
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.translate(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		cam.update();
		playState = Registrator.getInstance().getState(AvailableStates.PLAY_STATE);
		playState.setWidth(Gdx.graphics.getWidth());
		playState.setHeight(Gdx.graphics.getHeight());
		batch = new SpriteBatch();
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
        	if (Input.Keys.valueOf(keyName) == -1) continue;
            boolean value = Gdx.input.isKeyPressed(Input.Keys.valueOf(keyName));
            if (value){
                String keyPressed = keyName;
                playState.getInputActionMap().onKeyPress(keyPressed);
            }
        }
		
		ArrayList<Updatable> updatables = playState.getEntitiesByInterface(Updatable.class);
		if (updatables.size() > 100) System.err.println("WARNING! there are above 100 updatables on screen. This seemes wrong!");
        for (Updatable updatable : updatables) {
			updatable.update(playState);
		}
		for (Entity entity : playState.getGameEntities()) {
			for (EntityPart entityPart : entity.getEntityParts()) {
				entityPart.update(entity,playState);
			}
		}
		for (Destructable destructable : playState.getEntitiesByInterface(Destructable.class)) {
			if (destructable.shouldDestruct()){
				playState.removeEntity((Entity) destructable);
			}
		}
		for (Updatable it : playState.getPostUpdateables()) {
			it.update(playState);
		}
	}
	
	private void draw() {
		
		ArrayList<common.data.Sprite> commonSprites = new ArrayList<>(32);
		for (Entity it : playState.getGameEntities()) {
			commonSprites.add(it.draw());
		}
		commonSprites.sort(new Comparator<common.data.Sprite>() {
			@Override
			public int compare(common.data.Sprite o1, common.data.Sprite o2) {
				return o1.getFilename().compareTo(o2.getFilename());
			}
		});
		
		String filename = "";
		Texture texture = null;
		batch.begin();
		for (common.data.Sprite commonSprite : commonSprites) {
			String spriteName = commonSprite.getFilename();
			if (!spriteName.equals(filename)){
				filename = spriteName;
				File spriteFile = new File("assets/"+filename);
				texture = new Texture(new FileHandle(spriteFile));
			}
			batch.draw(texture, commonSprite.getX(), commonSprite.getY(),commonSprite.getWidth(), commonSprite.getHeight());
		}
		batch.end();
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
