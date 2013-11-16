package com.deeep.duckhuntprototipe.entities;

import com.deeep.duckhuntprototipe.classes.DynamicGameObject;

public class Duck extends DynamicGameObject {

	public static final int DUCK_STATE_FLYING = 0;
	public static final int DUCK_STATE_HIT = 1;
	public static final int DUCK_STATE_FALLING = 2;
	public static final float DUCK_VELOCITY = 10;
	public static final float DUCK_WIDTH = 32;
	public static final float DUCK_HEIGHT = 32;

	int state;
	public float stateTime;

	public Duck(float x, float y) {
		super(x, y, DUCK_WIDTH, DUCK_HEIGHT);
		state = DUCK_STATE_FLYING;
		stateTime = 0;
	}

	public void update(float deltaTime) {
		stateTime += deltaTime;
		if(stateTime >= 0.3f)
			stateTime = 0;
	}

	public void hit() {
		/***/
	}

	public void dead() {
		/***/
	}
}
