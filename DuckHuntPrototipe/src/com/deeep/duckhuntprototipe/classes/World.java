package com.deeep.duckhuntprototipe.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.deeep.duckhuntprototipe.entities.Duck;

public class World {

	public interface WorldListener {
		public void hit();

		public void miss();
	}

	public static final float WORLD_WIDTH = 15;
	public static final float WORLD_HEIGHT = 10;
	public static final int WORLD_STATE_RUNNING = 0;

	public final List<Duck> ducks;
	public final Random rand;
	public final WorldListener listener;

	public int state;

	public World(WorldListener listener) {
		this.ducks = new ArrayList<Duck>();
		this.listener = listener;
		rand = new Random();
		generateLevel();

		this.state = WORLD_STATE_RUNNING;
	}

	private void generateLevel() {
		//for (int i = 0; i < 2; i++) {
			Duck duck = new Duck(7, 5);
			ducks.add(duck);
		//}
	}

	public void update(float deltaTime) {
		updateDucks(deltaTime);
	}

	private void updateDucks(float deltaTime) {
		int len = ducks.size();
		for (int i = 0; i < len; i++) {
			Duck duck = ducks.get(i);
			duck.update(deltaTime);
		}
	}

	private void checkCollisions() {
		checkDuckCollision();
	}

	private void checkDuckCollision() {
		/****/
	}
}
