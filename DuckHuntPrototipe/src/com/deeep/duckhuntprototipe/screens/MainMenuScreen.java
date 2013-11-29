package com.deeep.duckhuntprototipe.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.deeep.duckhuntprototipe.classes.Assets;
import com.deeep.duckhuntprototipe.classes.Settings;

public class MainMenuScreen implements Screen {

	Game game;

	int state;
	OrthographicCamera guiCam;
	Rectangle playMode1Bounds;
	Rectangle playMode2Bounds;
	Rectangle soundBounds;
	Vector3 touchPoint;
	SpriteBatch batcher;
	int menuCursor;

	boolean play;

	public MainMenuScreen(Game game) {
		this.game = game;

		guiCam = new OrthographicCamera(480, 320);
		guiCam.position.set(480 / 2, 320 / 2, 0);
		playMode1Bounds = new Rectangle(480 / 2
				- Assets.gameMode1.getRegionWidth() / 2 - 60, 100,
				Assets.gameMode1.getRegionWidth() * 2,
				Assets.gameMode1.getRegionHeight() * 2);
		// playMode2Bounds = new Rectangle(,,,);
		// soundBounds = new Rectangle(,,,);
		touchPoint = new Vector3();
		batcher = new SpriteBatch();
		play = true;
		menuCursor = 100;
	}

	public void update(float deltaTime) {
		if (Gdx.input.justTouched()) {
			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(),
					0));

			if (playMode1Bounds.contains(touchPoint.x, touchPoint.y)) {
				Assets.playSound(Assets.shoot);
				if (menuCursor == 100)
					game.setScreen(new GameMode1Screen(game));
				else
					menuCursor = 100;
				return;
			}

			if (playMode2Bounds.contains(touchPoint.x, touchPoint.y)) {
				Assets.playSound(Assets.shoot);
				if (menuCursor == 80)
					game.setScreen(new GameMode2Screen(game));
				else
					menuCursor = 80;
				return;
				// }
				// if (soundBounds.contains(touchPoint.x, touchPoint.y)) {
				// Assets.playSound(Assets.clickSound);
				// Settings.soundEnabled = !Settings.soundEnabled;
			}
		}
	}

	public void draw(float deltaTime) {
		GLCommon gl = Gdx.gl;
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		guiCam.update();
		batcher.setProjectionMatrix(guiCam.combined);

		presentMenu();
		presentIntroTheme();
	}

	private void presentMenu() {
		batcher.enableBlending();
		batcher.begin();

		batcher.draw(Assets.title, 480 / 2 - Assets.title.getRegionWidth() / 2,
				320 / 2, Assets.title.getRegionWidth(),
				Assets.title.getRegionHeight());
		batcher.draw(Assets.gameMode1,
				480 / 2 - Assets.gameMode1.getRegionWidth() / 2 - 60, 100,
				Assets.gameMode1.getRegionWidth() * 2,
				Assets.gameMode1.getRegionHeight() * 2);
		batcher.draw(Assets.gameMode2,
				480 / 2 - Assets.gameMode2.getRegionWidth() / 2 + 4 - 60, 70,
				Assets.gameMode2.getRegionWidth() * 2,
				Assets.gameMode2.getRegionHeight() * 2);
		batcher.draw(Assets.menuCursor,
				480 / 2 - Assets.gameMode2.getRegionWidth() / 2 - 80,
				menuCursor, Assets.menuCursor.getRegionWidth() * 2,
				Assets.menuCursor.getRegionHeight() * 2);

		batcher.end();
	}

	private void presentIntroTheme() {
		if (play) {
			Assets.duckHunt.play();
			play = false;
		}
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
