package com.deeep.duckhuntprototipe.entities;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.deeep.duckhuntprototipe.classes.Assets;
import com.deeep.duckhuntprototipe.classes.DynamicGameObject;
import com.deeep.duckhuntprototipe.classes.World;
import com.deeep.duckhuntprototipe.screens.GameScreen;

public class Duck extends DynamicGameObject {

	public static final int DUCK_STATE_FLYING = 0;
	public static final int DUCK_STATE_HIT = 1;
	public static final int DUCK_STATE_FALLING = 2;
	public static final int DUCK_STATE_STANDBY = 3;
	public static final int DUCK_STATE_DEAD = 4;
	public static final int DUCK_STATE_FLY_AWAY = 6;
	public static final int DUCK_STATE_GONE = 7;
	public static final float DUCK_VELOCITY = 5;
	public static final float DUCK_GRAVITY = -0.5f;
	public static final float DUCK_WIDTH = 1.25f;
	public static final float DUCK_HEIGHT = 1.25f;

	public TextureRegion texture;
	public TextureRegion uiTexture;
	public float side;
	public int state;
	public float stateTime;
	public float uiStateTime;
	private Random rand;
	private int frames;

	public Duck(float x, float y) {
		super(x, y, DUCK_WIDTH, DUCK_HEIGHT);
		state = DUCK_STATE_FLYING;
		velocity.set(DUCK_VELOCITY, DUCK_VELOCITY);
		stateTime = 0;
		uiStateTime = 0;
		rand = new Random();
		uiTexture = Assets.uiWhiteDuck;
	}

	public void update(float deltaTime) {

		switch (state) {
		case DUCK_STATE_STANDBY:
			uiTexture = Assets.uiWhiteDuck;
			texture = null;
			break;
		case DUCK_STATE_FLYING:
			stateFlying(deltaTime);
			break;
		case DUCK_STATE_HIT:
			stateHit();
			break;
		case DUCK_STATE_FALLING:
			stateFalling(deltaTime);
			break;
		case DUCK_STATE_DEAD:
			// uiTexture = Assets.uiRedDuck;
			break;
		case DUCK_STATE_FLY_AWAY:
			stateFlyAway(deltaTime);
			break;
		case DUCK_STATE_GONE:
			stateGone();
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

	private void stateFalling(float deltaTime) {
		velocity.add(0, DUCK_GRAVITY);
		position.add(velocity.x * deltaTime, velocity.y * deltaTime);
		bounds.x = position.x - DUCK_WIDTH / 2;
		bounds.y = position.y - DUCK_HEIGHT / 2;

		if (position.y < 2.5f) {
			state = DUCK_STATE_DEAD;
			Assets.hitGround.play();
		}

		frames++;
		if (frames > 4) {
			Assets.duckFalling.flip(true, false);
			texture = Assets.duckFalling;
			frames = 0;
		} else
			texture = Assets.duckFalling;

	}

	private void stateHit() {
		if (stateTime > 1.0f) {
			state = DUCK_STATE_FALLING;
			velocity.set(0, DUCK_GRAVITY);
			Assets.playSound(Assets.duckFallingSnd);
		}
		texture = Assets.duckHit;
		uiTexture = Assets.uiRedDuck;
	}

	private void stateFlying(float deltaTime) {
		position.add(velocity.x * deltaTime, velocity.y * deltaTime);
		bounds.x = position.x - DUCK_WIDTH / 2;
		bounds.y = position.y - DUCK_HEIGHT / 2;

		if (GameScreen.shots == 0) {
			state = DUCK_STATE_FLY_AWAY;
			return;
		} else if (stateTime > 8f) {
			state = DUCK_STATE_FLY_AWAY;
			stateTime = 0;
			return;
		}

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

		frames++;
		if (frames > 7) {
			Assets.miss.play();
			frames = 0;
		}
		texture = Assets.duckFly.getKeyFrame(stateTime, true);

		if (uiStateTime < 1.5f)
			uiTexture = null;
		else if (uiStateTime < 3)
			uiTexture = Assets.uiWhiteDuck;
		else
			uiStateTime = 0;

		uiStateTime += deltaTime;
	}

	private void stateFlyAway(float deltaTime) {
		position.add(0, deltaTime * 5);

		frames++;
		if (frames > 7) {
			Assets.miss.play();
			frames = 0;
		}

		if (stateTime > 3) {
			state = DUCK_STATE_GONE;
		}

		uiTexture = Assets.uiWhiteDuck;
	}

	private void stateGone() {
		uiTexture = Assets.uiWhiteDuck;
	}
}
