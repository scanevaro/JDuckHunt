package com.deeep.duckhuntprototipe.classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Assets {
	public static TextureRegion backgroundRegion;
	public static TextureAtlas items;
	public static TextureRegion dog;
	public static TextureRegion pause;
	public static TextureRegion ready;
	public static TextureRegion duckHit;
	public static TextureRegion duckFall;

	public static Animation duckFly;

	public static BitmapFont font;

	public static Music duckHunt;

	public static Sound reloading;
	public static Sound shoot;
	public static Sound ducks;

	public static void load() {
		items = new TextureAtlas(Gdx.files.internal("data/items.pack"),
				Gdx.files.internal("data"));
		backgroundRegion = items.findRegion("background");
		dog = items.findRegion("dogSmelling1");
		duckFly = new Animation(0.2f,
				((TextureRegion) items.findRegion("duckFlyRight1")),
				((TextureRegion) items.findRegion("duckFlyRight2")));
		duckHit = items.findRegion("duckHit");
		duckFall = items.findRegion("duckFalling");

		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(
				Gdx.files.internal("data/font.ttf"));
		font = generator.generateFont(62);
		generator.dispose();

		duckHunt = Gdx.audio.newMusic(Gdx.files.internal("data/DuckHunt.mp3"));
		duckHunt.setLooping(false);
		duckHunt.setVolume(0.5f);
		if(Settings.soundEnabled)
			duckHunt.play();
		
		reloading = Gdx.audio.newSound(Gdx.files.internal("data/reloading.wav"));
		shoot = Gdx.audio.newSound(Gdx.files.internal("data/shoot.wav"));
		ducks = Gdx.audio.newSound(Gdx.files.internal("data/ducks.wav"));
	}

	public static void playSound(Sound sound) {
		if (Settings.soundEnabled)
			sound.play(1);
	}
}
