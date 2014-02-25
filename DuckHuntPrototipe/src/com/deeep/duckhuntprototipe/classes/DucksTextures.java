package com.deeep.duckhuntprototipe.classes;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.deeep.duckhuntprototipe.entities.Duck;

public class DucksTextures {

	public static TextureRegion getTexture(float stateTime, int type,
			Vector2 velocity) {
		TextureRegion texture = null;

		switch (type) {
		case Duck.BLUE_DUCK:
			texture = getBlueDuckTexture(stateTime, velocity);
			break;
		case Duck.BLACK_DUCK:
			texture = getBlackDuckTexture(stateTime, velocity);
			break;
		case Duck.RED_DUCK:
			texture = getRedDuckTexture(stateTime, velocity);
			break;
		}

		return texture;
	}

	private static TextureRegion getRedDuckTexture(float stateTime,
			Vector2 velocity) {
		TextureRegion texture = null;

		if (velocity.x > velocity.y)
			texture = Assets.duckFlyRightRed.getKeyFrame(stateTime);
		else
			texture = Assets.duckFlyTopRed.getKeyFrame(stateTime);

		return texture;
	}

	private static TextureRegion getBlackDuckTexture(float stateTime,
			Vector2 velocity) {
		TextureRegion texture = null;

		if (velocity.x > velocity.y)
			texture = Assets.duckFlyRightBlack.getKeyFrame(stateTime);
		else
			texture = Assets.duckFlyTopBlack.getKeyFrame(stateTime);

		return texture;
	}

	private static TextureRegion getBlueDuckTexture(float stateTime,
			Vector2 velocity) {
		TextureRegion texture = null;

		if (velocity.x > velocity.y)
			texture = Assets.duckFlyRightBlue.getKeyFrame(stateTime);
		else
			texture = Assets.duckFlyTopBlue.getKeyFrame(stateTime);

		return texture;
	}
}
