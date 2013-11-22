package com.deeep.duckhuntprototipe.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
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

	public final List<Duck> ducks;
	public final Random rand;
	public final WorldListener listener;

	public int state;

	Vector3 touchPoint;

	public World(WorldListener listener) {
		this.ducks = new ArrayList<Duck>();
		this.listener = listener;
		rand = new Random();
		this.touchPoint = new Vector3();
		generateLevel();

		this.state = WORLD_STATE_RUNNING;
	}

	private void generateLevel() {
		// for (int i = 0; i < 2; i++) {
		Duck duck = new Duck(7.5f, 5f);
		ducks.add(duck);
		// }
	}

	public void update(float deltaTime) {
		updateDucks(deltaTime);
		checkCollisions();
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
			Duck duck = new Duck(7.5f, 5f);
			ducks.add(duck);
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
