package com.deeep.duckhuntprototipe.classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Assets {
	public static TextureRegion backgroundRegion;
	public static TextureAtlas items;
	public static TextureRegion pause;
	public static TextureRegion ready;
	public static TextureRegion duckHit;
	public static TextureRegion duckFalling;
	public static TextureRegion title;
	public static TextureRegion gameMode1;
	public static TextureRegion gameMode2;
	public static TextureRegion menuCursor;
	public static TextureRegion dogFound;
	public static TextureRegion uiShot;
	public static TextureRegion uiDucks;
	public static TextureRegion uiScore;
	public static TextureRegion presentRound;

	public static Animation duckFly;
	public static Animation dogWalking;
	public static Animation dogJumping;
	public static Animation dogLaughing;

	public static BitmapFont font;

	public static Music duckHunt;
	public static Music startRound;

	public static Sound reloading;
	public static Sound shoot;
	public static Sound ducks;
	public static Sound bark;

	public static void load() {
		loadAtlas();

		loadTextures();

		loadFont();

		loadSounds();
	}

	private static void loadAtlas() {
		items = new TextureAtlas(Gdx.files.internal("data/items.pack"),
				Gdx.files.internal("data"));
	}

	private static void loadTextures() {
		backgroundRegion = items.findRegion("background");
		dogWalking = new Animation(0.15f,
				((TextureRegion) items.findRegion("dogWalking1")),
				((TextureRegion) items.findRegion("dogWalking2")),
				((TextureRegion) items.findRegion("dogWalking3")));
		dogFound = items.findRegion("dogFound");
		dogJumping = new Animation(0.8f,
				((TextureRegion) items.findRegion("dogJump1")),
				((TextureRegion) items.findRegion("dogJump2")));
		dogLaughing = new Animation(0.2f,
				((TextureRegion) items.findRegion("dogLaughing1")),
				((TextureRegion) items.findRegion("dogLaughing2")));
		duckFly = new Animation(0.2f,
				((TextureRegion) items.findRegion("duckFlyRight1")),
				((TextureRegion) items.findRegion("duckFlyRight2")));
		duckHit = items.findRegion("duckHit");
		duckFalling = items.findRegion("duckFalling");
		title = items.findRegion("title");
		gameMode1 = items.findRegion("gameMode1");
		gameMode2 = items.findRegion("gameMode2");
		menuCursor = items.findRegion("menuCursor");
		uiShot = items.findRegion("uiShot");
		uiDucks = items.findRegion("uiDucks");
		uiScore = items.findRegion("uiScore");
		presentRound = items.findRegion("presentRound");
	}

	private static void loadFont() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(
				Gdx.files.internal("data/wonder.ttf"));
		font = generator.generateFont(20);
		generator.dispose();

	}

	private static void loadSounds() {
		duckHunt = Gdx.audio.newMusic(Gdx.files.internal("data/DuckHunt.mp3"));
		duckHunt.setLooping(false);
		duckHunt.setVolume(0.5f);
		startRound = Gdx.audio.newMusic(Gdx.files
				.internal("data/sounds/start_round.mp3"));

		reloading = Gdx.audio
				.newSound(Gdx.files.internal("data/reloading.wav"));
		shoot = Gdx.audio.newSound(Gdx.files.internal("data/sounds/blast.mp3"));
		ducks = Gdx.audio.newSound(Gdx.files.internal("data/ducks.wav"));
		bark = Gdx.audio.newSound(Gdx.files.internal("data/sounds/bark.mp3"));
	}

	public static void playSound(Sound sound) {
		if (Settings.soundEnabled)
			sound.play(1);
	}
}
