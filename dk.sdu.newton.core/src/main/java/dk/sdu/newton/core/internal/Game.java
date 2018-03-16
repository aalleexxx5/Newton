package dk.sdu.newton.core.internal;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Game implements ApplicationListener {
	
	private static OrthographicCamera cam;
	private ShapeRenderer sr;
	
	
	public void create() {
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.translate(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		cam.update();
		
		sr = new ShapeRenderer();
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
		
	}
	
	private void draw() {
	
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
