package com.deeep.duckhuntprototipe.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.deeep.duckhuntprototipe.DuckHuntPrototipe;
import com.deeep.duckhuntprototipe.classes.Assets;
import com.deeep.duckhuntprototipe.classes.GameObject;

public class Dog extends GameObject {

	public static final int DOG_STATE_WALKING = 0;
	public static final int DOG_STATE_FOUND = 1;
	public static final int DOG_STATE_JUMPING = 2;
	public static final int DOG_STATE_FOUND_DUCK = 3;
	public static final int DOG_STATE_HIDDEN = 4;
	public static final int DOG_STATE_LAUGHING = 5;
	public static final float DOG_WIDTH = 2f;
	public static final float DOG_HEIGHT = 2f;

	public TextureRegion texture;
	private int bark;
	public int state;
	public float stateTime;
	private int frames;

	public Dog(float x, float y) {
		super(x, y, DOG_WIDTH, DOG_HEIGHT);
		state = DOG_STATE_WALKING;
		stateTime = 0;
		bark = 0;
		texture = Assets.dogWalking.getKeyFrame(stateTime, true);
	}

	public void update(float deltaTime) {

		switch (state) {
		case DOG_STATE_WALKING:
			if (!Assets.startRound.isPlaying()) {
				state = DOG_STATE_FOUND;
				stateTime = 0;
				break;
			}
			texture = Assets.dogWalking.getKeyFrame(stateTime, true);
			position.add(deltaTime, 0);
			break;
		case DOG_STATE_FOUND:
			if (stateTime > 1) {
				state = DOG_STATE_JUMPING;
				stateTime = 0;
			}
			texture = Assets.dogFound;
			break;
		case DOG_STATE_JUMPING:
			frames++;
			if (frames > 15) {
				if (bark < 3) {
					Assets.bark.play();
					bark++;
				}
				frames = 0;
			}
			texture = Assets.dogJumping.getKeyFrame(stateTime);
			break;
		case DOG_STATE_FOUND_DUCK:
			/***/
			break;
		case DOG_STATE_LAUGHING:
			texture = Assets.dogLaughing.getKeyFrame(stateTime, true);
			break;
		case DOG_STATE_HIDDEN:
			/***/
			break;
		}

		stateTime += deltaTime;
	}
}
