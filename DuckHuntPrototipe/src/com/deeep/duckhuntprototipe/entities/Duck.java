package com.deeep.duckhuntprototipe.entities;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.deeep.duckhuntprototipe.classes.Assets;
import com.deeep.duckhuntprototipe.classes.DynamicGameObject;
import com.deeep.duckhuntprototipe.classes.World;

public class Duck extends DynamicGameObject {

	public static final int DUCK_STATE_FLYING = 0;
	public static final int DUCK_STATE_HIT = 1;
	public static final int DUCK_STATE_FALLING = 2;
	public static final float DUCK_VELOCITY = 5;
	public static final float DUCK_GRAVITY = -0.5f;
	public static final float DUCK_WIDTH = 1.25f;
	public static final float DUCK_HEIGHT = 1.25f;

	public TextureRegion texture;
	public float side;
	public int state;
	public float stateTime;
	private Random rand;

	public Duck(float x, float y) {
		super(x, y, DUCK_WIDTH, DUCK_HEIGHT);
		state = DUCK_STATE_FLYING;
		velocity.set(DUCK_VELOCITY, DUCK_VELOCITY);
		stateTime = 0;
		rand = new Random();
		texture = Assets.duckFly.getKeyFrame(stateTime);
	}

	public void update(float deltaTime) {

		switch (state) {
		case DUCK_STATE_FLYING:
			position.add(velocity.x * deltaTime, velocity.y * deltaTime);
			bounds.x = position.x - DUCK_WIDTH / 2;
			bounds.y = position.y - DUCK_HEIGHT / 2;

			if (rand.nextFloat() > 0.999f) {
				velocity.x = -velocity.x;
			}
			if (rand.nextFloat() > 0.999f) {
				velocity.y = -velocity.y;
			}

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

			texture = Assets.duckFly.getKeyFrame(stateTime, true);
			break;
		case DUCK_STATE_HIT:
			if (stateTime > 1.0f) {
				state = DUCK_STATE_FALLING;
				velocity.set(0, DUCK_GRAVITY);
			}
			texture = Assets.duckHit;
			break;
		case DUCK_STATE_FALLING:
			velocity.add(0, DUCK_GRAVITY);
			position.add(velocity.x * deltaTime, velocity.y * deltaTime);
			bounds.x = position.x - DUCK_WIDTH / 2;
			bounds.y = position.y - DUCK_HEIGHT / 2;

			texture = Assets.duckFalling;
			break;
		}

		stateTime += deltaTime;
	}

	public void hit() {
		velocity.set(0, 0);
		state = Duck.DUCK_STATE_HIT;
		stateTime = 0;
	}

	public void dead() {
		/***/
	}
}
