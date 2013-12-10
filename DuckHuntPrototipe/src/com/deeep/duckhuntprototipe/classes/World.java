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
	public static final int GAME_MODE_1 = 0;
	public static final int GAME_MODE_2 = 1;

	public final List<Duck> ducks;
	public final Random rand;
	public final WorldListener listener;
	public final Dog dog;

	public int state;
	public int gameMode;

	Vector3 touchPoint;

	public World(WorldListener listener, int gameMode) {
		this.ducks = new ArrayList<Duck>(10);
		this.listener = listener;
		this.gameMode = gameMode;
		rand = new Random();
		this.touchPoint = new Vector3();
		dog = new Dog(0, 1.9f);
		generateLevel();

		this.state = WORLD_STATE_ROUND_START;
	}

	private void generateLevel() {
		if (gameMode == GAME_MODE_1) {
			for (int i = 0; i < 1; i++) {
				float random = rand.nextFloat() > 0.5f ? 6.5f : 8.5f;
				Duck duck = new Duck(random, 2f);
				ducks.add(duck);
			}
		} else {
			float random = rand.nextFloat() > 0.5f ? 5.5f : 6.5f;
			Duck duck = new Duck(random, 2f);
			ducks.add(duck);

			float random2 = rand.nextFloat() > 0.5f ? 8.5f : 9.5f;
			Duck duck2 = new Duck(random2, 2f);
			ducks.add(duck2);
		}
	}

	public void update(float deltaTime) {
		if (state == WORLD_STATE_ROUND_START) {
			updateDog(deltaTime);
			checkDogState();
		} else {
			updateDog(deltaTime);
			updateDucks(deltaTime);
			checkCollisions();
		}
	}

	private void updateDog(float deltaTime) {
		dog.update(deltaTime);
	}

	private void checkDogState() {
		if (dog.state == Dog.DOG_STATE_HIDDEN)
			state = WORLD_STATE_RUNNING;
	}

	private void updateDucks(float deltaTime) {
		int len = ducks.size();
		for (int i = 0; i < len; i++) {
			Duck duck = ducks.get(i);
			duck.update(deltaTime);

			if (duck.position.y < 0) {
				ducks.remove(i);
			}
		}

		if (ducks.size() == 0) {
			if (gameMode == GAME_MODE_1) {
				Duck duck = new Duck(8.5f, 5f);
				ducks.add(duck);
			} else {
				Duck duck = new Duck(8.5f, 5f);
				ducks.add(duck);

				Duck duck2 = new Duck(6.5f, 5f);
			}
		}
	}

	private void checkCollisions() {
		checkDuckCollision();
	}

	private void checkDuckCollision() {
		for (int i = 0; i < ducks.size(); i++) {
			Duck duck = ducks.get(i);
			if (Gdx.input.justTouched()) {
				if (duck.bounds.contains(touchPoint.x, touchPoint.y)) {
					duck.hit();
				}
			}
		}
	}
}
