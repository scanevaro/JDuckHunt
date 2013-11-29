package com.deeep.duckhuntprototipe;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;
import com.deeep.duckhuntprototipe.classes.Assets;
import com.deeep.duckhuntprototipe.classes.Settings;
import com.deeep.duckhuntprototipe.screens.MainMenuScreen;

public class DuckHuntPrototipe extends Game {
	FPSLogger fps;

	@Override
	public void create() {
		Settings.load();
		Assets.load();
		setScreen(new MainMenuScreen(this));
		fps = new FPSLogger();
	}

	@Override
	public void dispose() {
		super.dispose();
		getScreen().dispose();
	}

	@Override
	public void render() {
		super.render();
		fps.log();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}