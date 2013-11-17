package com.deeep.duckhuntprototipe.entities;

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

	public Duck(float x, float y) {
		super(x, y, DUCK_WIDTH, DUCK_HEIGHT);
		state = DUCK_STATE_FLYING;
		velocity.set(DUCK_VELOCITY, 0);
		stateTime = 0;
	}

	public void update(float deltaTime) {
		position.add(velocity.x * deltaTime, velocity.y * deltaTime);
		bounds.x = position.x - DUCK_WIDTH / 2;
		bounds.y = position.y - DUCK_HEIGHT / 2;

		if (position.x < DUCK_WIDTH / 2) {
			position.x = DUCK_WIDTH / 2;
			velocity.x = DUCK_VELOCITY;
		}
		if (position.x > World.WORLD_WIDTH - DUCK_WIDTH / 2) {
			position.x = World.WORLD_WIDTH - DUCK_WIDTH / 2;
			velocity.x = -DUCK_VELOCITY;
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
