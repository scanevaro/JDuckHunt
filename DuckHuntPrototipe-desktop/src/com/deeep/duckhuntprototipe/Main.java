package com.deeep.duckhuntprototipe;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "JDuckHunt";
		cfg.useGL20 = false;
		cfg.width = 480;
		cfg.height = 320;

		new LwjglApplication(new JDuckHunt(), cfg);
	}
}
