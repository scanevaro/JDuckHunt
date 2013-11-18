package com.deeep.duckhuntprototipe.entities;

import java.util.Random;

import com.deeep.duckhuntprototipe.classes.DynamicGameObject;
import com.deeep.duckhuntprototipe.classes.World;

public class Duck extends DynamicGameObject {

	public static final int DUCK_STATE_FLYING = 0;
	public static final int DUCK_STATE_HIT = 1;
	public static final int DUCK_STATE_FALLING = 2;
	public static final float DUCK_VELOCITY = 2;
	public static final float DUCK_WIDTH = 1;
	public static final float DUCK_HEIGHT = 1;

	int state;
	public float stateTime;
	private Random rand;

	public Duck(float x, float y) {
		super(x, y, DUCK_WIDTH, DUCK_HEIGHT);
		state = DUCK_STATE_FLYING;
		velocity.set(DUCK_VELOCITY, 0);
		stateTime = 0;
		rand = new Random();
	}

	public void update(float deltaTime) {
		position.add(velocity.x * deltaTime, velocity.y * deltaTime);
		bounds.x = position.x - DUCK_WIDTH / 2;
		bounds.y = position.y - DUCK_HEIGHT / 2;

		if (position.x < DUCK_WIDTH / 2) {
			position.x = DUCK_WIDTH / 2;
			velocity.x = DUCK_VELOCITY;
			velocity.y = rand.nextFloat();

		}
		if (position.x > World.WORLD_WIDTH - DUCK_WIDTH / 2) {
			position.x = World.WORLD_WIDTH - DUCK_WIDTH / 2;
			velocity.x = -DUCK_VELOCITY;
			velocity.y = rand.nextFloat();
		}

		if (position.y < DUCK_WIDTH / 2) {
			position.y = DUCK_HEIGHT / 2;
			velocity.x = DUCK_VELOCITY;
			velocity.y = rand.nextFloat();
		}

		if (position.y > World.WORLD_HEIGHT - DUCK_HEIGHT / 2) {
			position.y = World.WORLD_HEIGHT - DUCK_HEIGHT / 2;
			velocity.x = -DUCK_VELOCITY;
			float topBot = rand.nextFloat() > 0.5f ? 1 : -1;
			velocity.y = rand.nextFloat() * topBot;
		}

		stateTime += deltaTime;
	}

	public void hit() {
		/***/
	}

	public void dead() {
		/***/
	}
}
