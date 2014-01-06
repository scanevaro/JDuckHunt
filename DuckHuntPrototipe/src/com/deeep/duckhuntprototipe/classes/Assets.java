package com.deeep.duckhuntprototipe.classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
	public static TextureRegion uiDucksRound;
	public static TextureRegion uiScore;
	public static TextureRegion presentRound;
	public static TextureRegion ui3Shots;
	public static TextureRegion ui2Shots;
	public static TextureRegion ui1Shots;
	public static TextureRegion uiWhiteDuck;
	public static TextureRegion uiRedDuck;
	public static TextureRegion dogDuckFound;
	public static TextureRegion dogDucksFound;
	public static TextureRegion presentFlyAway;

	public static Animation duckFly;
	public static Animation dogWalking;
	public static Animation dogJumping;
	public static Animation dogLaughing;
	public static Animation ui0Shots;

	public static BitmapFont font;

	public static Music duckHunt;
	public static Music startRound;

	public static Sound shoot;
	public static Sound ducks;
	public static Sound dogBark;
	public static Sound miss;
	public static Sound dogLaughingSnd;
	public static Sound dogDuckFoundSnd;
	public static Sound hitGround;
	public static Sound duckFallingSnd;

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
		ui0Shots = new Animation(0.4f,
				((TextureRegion) items.findRegion("ui0Shots1")),
				((TextureRegion) items.findRegion("ui0Shots2")));

		duckHit = items.findRegion("duckHit");
		duckFalling = items.findRegion("duckFalling");
		title = items.findRegion("title");
		gameMode1 = items.findRegion("gameMode1");
		gameMode2 = items.findRegion("gameMode2");
		menuCursor = items.findRegion("menuCursor");
		uiShot = items.findRegion("uiShot");
		uiDucksRound = items.findRegion("uiDucksRound");
		uiScore = items.findRegion("uiScore");
		presentRound = items.findRegion("presentRound");
		presentFlyAway = items.findRegion("presentFlyAway");
		ui3Shots = items.findRegion("ui3Shots");
		ui2Shots = items.findRegion("ui2Shots");
		ui1Shots = items.findRegion("ui1Shots");
		uiWhiteDuck = items.findRegion("uiWhiteDuck");
		uiRedDuck = items.findRegion("uiRedDuck");
		dogDuckFound = items.findRegion("dog1Duck");
		dogDucksFound = items.findRegion("dog2Ducks");
	}

	private static void loadFont() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(
				Gdx.files.internal("data/wonder.ttf"));
		font = generator.generateFont(20);
		generator.dispose();

	}

	private static void loadSounds() {
		duckHunt = Gdx.audio.newMusic(Gdx.files
				.internal("data/sounds/DuckHunt.mp3"));
		duckHunt.setLooping(false);
		duckHunt.setVolume(0.5f);
		startRound = Gdx.audio.newMusic(Gdx.files
				.internal("data/sounds/start_round.mp3"));

		shoot = Gdx.audio.newSound(Gdx.files.internal("data/sounds/blast.mp3"));
		ducks = Gdx.audio.newSound(Gdx.files.internal("data/ducks.wav"));
		dogBark = Gdx.audio
				.newSound(Gdx.files.internal("data/sounds/bark.mp3"));
		miss = Gdx.audio.newSound(Gdx.files.internal("data/sounds/miss.mp3"));
		dogLaughingSnd = Gdx.audio.newSound(Gdx.files
				.internal("data/sounds/laugh.mp3"));
		dogDuckFoundSnd = Gdx.audio.newSound(Gdx.files
				.internal("data/sounds/end_duck_round.mp3"));
		hitGround = Gdx.audio.newSound(Gdx.files
				.internal("data/sounds/drop.mp3"));
		duckFallingSnd = Gdx.audio.newSound(Gdx.files
				.internal("data/sounds/duck_falling.mp3"));
	}

	public static void playSound(Sound sound) {
		if (Settings.soundEnabled)
			sound.play(1);
	}
}
