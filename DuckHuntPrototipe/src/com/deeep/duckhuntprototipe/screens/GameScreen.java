package com.deeep.duckhuntprototipe.screens;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.deeep.duckhuntprototipe.classes.Assets;
import com.deeep.duckhuntprototipe.classes.World;
import com.deeep.duckhuntprototipe.classes.WorldRenderer;
import com.deeep.duckhuntprototipe.classes.World.WorldListener;

public class GameScreen implements Screen {

	static final int GAME_READY = 0;
	static final int GAME_RUNNING = 1;

	Game game;

	int state;
	OrthographicCamera guiCam;
	Vector3 touchPoint;
	SpriteBatch batcher;
	World world;
	WorldListener worldListener;
	WorldRenderer renderer;

	public GameScreen(Game game) {
		this.game = game;

		guiCam = new OrthographicCamera(480, 320);
		guiCam.position.set(480 / 2, 320 / 2, 0);
		touchPoint = new Vector3();
		batcher = new SpriteBatch();
		worldListener = new WorldListener() {
			@Override
			public void hit() {
				Assets.playSound(Assets.hitSound);
			}

			@Override
			public void miss() {
				Assets.playSound(Assets.missSound);
			}
		};
		world = new World(worldListener);
		renderer = new WorldRenderer(batcher, world);
	}

	public void update(float deltaTime) {
		if (deltaTime > 0.1f)
			deltaTime = 0.1f;

		switch (state) {
		case GAME_READY:
			updateReady();
			break;
		case GAME_RUNNING:
			updateRunning(deltaTime);
			break;
		}
	}

	private void updateRunning(float deltaTime) {
		if (Gdx.input.justTouched()) {
			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(),
					0));

			/***/
		}

		ApplicationType appType = Gdx.app.getType();

		/*
		 * Input code
		 */

		world.update(deltaTime);
	}

	private void updateReady() {
		if (Gdx.input.justTouched())
			state = GAME_RUNNING;
	}

	public void draw(float deltaTime) {
		GLCommon gl = Gdx.gl;
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		renderer.render();

		guiCam.update();
		batcher.setProjectionMatrix(guiCam.combined);
		batcher.enableBlending();
		batcher.begin();

		switch (state) {
		case GAME_READY:
			presentReady();
			break;
		case GAME_RUNNING:
			presentRunning();
			break;
		}

		batcher.end();
	}

	private void presentRunning() {
		// batcher.draw(Assets.pause, 480 - 32, 32, 32, 32);
		// Assets.font.draw(batcher, scoreString, 16, 480 - 20);
	}

	private void presentReady() {
		Assets.font.draw(batcher, "READY", 64,
				Gdx.graphics.getHeight() / 2 + 32);
	}

	@Override
	public void render(float delta) {
		update(delta);
		draw(delta);
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

}
