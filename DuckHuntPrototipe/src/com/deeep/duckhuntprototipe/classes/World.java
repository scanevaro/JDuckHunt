package com.deeep.duckhuntprototipe.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.deeep.duckhuntprototipe.entities.Dog;
import com.deeep.duckhuntprototipe.entities.Duck;

public class World {

	public interface WorldListener {
		public void reload();

		public void shoot();

		public void ducks();
	}

	public static final float WORLD_WIDTH = 15;
	public static final float WORLD_HEIGHT = 10;
	public static final int WORLD_STATE_RUNNING = 0;
	public static final int WORLD_STATE_ROUND_START = 1;
	public static final int WORLD_STATE_ROUND_PAUSE = 2;
	public static final int GAME_MODE_1 = 0;
	public static final int GAME_MODE_2 = 1;

	public final List<Duck> ducks;
	public final Random rand;
	public final WorldListener listener;
	public final Dog dog;

	public int state;
	public int gameMode;
	public int duckCount;
	public int ducksHit;
	public boolean checkDucksRoundPause;

	Vector3 touchPoint;

	public World(WorldListener listener, int gameMode) {
		this.ducks = new ArrayList<Duck>(10);
		this.listener = listener;
		this.gameMode = gameMode;
		rand = new Random();
		this.touchPoint = new Vector3();
		dog = new Dog(0, 1.9f, this);
		generateLevel();

		duckCount = 0;
		this.state = WORLD_STATE_ROUND_START;
	}

	private void generateLevel() {
		for (int i = 0; i < 10; i++) {
			float random = rand.nextFloat() > 0.5f ? 6.5f : 8.5f;
			Duck duck = new Duck(random, 2f);
			ducks.add(duck);
		}
	}

	public void update(float deltaTime) {
		if (state == WORLD_STATE_ROUND_START) {
			updateDog(deltaTime, ducksHit);
			checkDogState();
		} else if (state == WORLD_STATE_RUNNING) {
			updateDog(deltaTime, ducksHit);
			updateDucks(deltaTime);
			checkCollisions();
			checkDucksRoundPause = true;
		} else if (state == WORLD_STATE_ROUND_PAUSE) {
			if (checkDucksRoundPause)
				checkDucksRoundPause();
			updateDog(deltaTime, ducksHit);
			checkDogState();
		}
	}

	private void updateDog(float deltaTime, int duckCount) {
		dog.update(deltaTime, duckCount);
	}

	private void checkDogState() {
		if (dog.state == Dog.DOG_STATE_HIDDEN)
			state = WORLD_STATE_RUNNING;
	}

	private void updateDucks(float deltaTime) {
		if (gameMode == GAME_MODE_1)
			ducks.get(duckCount).update(deltaTime);
		else {
			ducks.get(duckCount).update(deltaTime);
			ducks.get(duckCount + 1).update(deltaTime);
		}
	}

	private void checkCollisions() {
		checkDuckCollision();
		checkDuckStates();
	}

	private void checkDuckCollision() {
		if (gameMode == GAME_MODE_1) {
			Duck duck = ducks.get(duckCount);
			if (Gdx.input.justTouched()
					&& duck.bounds.contains(touchPoint.x, touchPoint.y)) {
				duck.hit();
			}
		} else {
			Duck duck = ducks.get(duckCount);
			if (Gdx.input.justTouched()
					&& duck.bounds.contains(touchPoint.x, touchPoint.y)) {
				duck.hit();
			}

			Duck duck2 = ducks.get(duckCount + 1);
			if (Gdx.input.justTouched()
					&& duck2.bounds.contains(touchPoint.x, touchPoint.y)) {
				duck2.hit();
			}
		}
	}

	private void checkDuckStates() {
		if (gameMode == GAME_MODE_1) {
			if (ducks.get(duckCount).state == Duck.DUCK_STATE_DEAD
					|| ducks.get(duckCount).state == Duck.DUCK_STATE_FLY_AWAY)
				state = WORLD_STATE_ROUND_PAUSE;
		} else {
			if (ducks.get(duckCount).state == Duck.DUCK_STATE_DEAD
					&& ducks.get(duckCount + 1).state == Duck.DUCK_STATE_DEAD)
				state = WORLD_STATE_ROUND_PAUSE;
		}
	}

	private void checkDucksRoundPause() {
		ducksHit = 0;
		if (gameMode == GAME_MODE_1) {
			if (ducks.get(duckCount).state == Duck.DUCK_STATE_DEAD) {
				ducksHit++;
				checkDucksRoundPause = false;
			} else
				checkDucksRoundPause = false;
		} else {
			if (ducks.get(duckCount).state == Duck.DUCK_STATE_DEAD)
				ducksHit++;

			if (ducks.get(duckCount + 1).state == Duck.DUCK_STATE_DEAD)
				ducksHit++;

			checkDucksRoundPause = false;
		}
	}
}
