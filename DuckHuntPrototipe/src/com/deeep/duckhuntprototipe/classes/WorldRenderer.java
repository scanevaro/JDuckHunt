package com.deeep.duckhuntprototipe.classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.deeep.duckhuntprototipe.entities.Dog;
import com.deeep.duckhuntprototipe.entities.Duck;

public class WorldRenderer {

	static final float FRUSTUM_WIDTH = 15;
	static final float FRUSTUM_HEIGHT = 10;
	World world;
	OrthographicCamera cam;
	SpriteBatch batch;
	TextureRegion background;

	public WorldRenderer(SpriteBatch batch, World world) {
		this.world = world;
		this.cam = new OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
		this.cam.position.set(FRUSTUM_WIDTH / 2, FRUSTUM_HEIGHT / 2, 0);
		this.batch = batch;
	}

	public void render() {
		cam.update();
		cam.unproject(world.touchPoint.set(Gdx.input.getX(), Gdx.input.getY(),
				0));
		batch.setProjectionMatrix(cam.combined);
		batch.enableBlending();

		if (world.dog.state == Dog.DOG_STATE_WALKING
				|| world.dog.state == Dog.DOG_STATE_FOUND
				|| world.dog.state == Dog.DOG_STATE_JUMPING) {
			renderBackground();
			renderObjects();
		} else {
			renderObjects();
			renderBackground();
		}
	}

	private void renderBackground() {
		batch.begin();
		batch.draw(Assets.backgroundRegion, cam.position.x - FRUSTUM_WIDTH / 2,
				cam.position.y - FRUSTUM_HEIGHT / 2, FRUSTUM_WIDTH,
				FRUSTUM_HEIGHT);
		batch.end();
	}

	private void renderObjects() {
		batch.begin();
		renderDog();
		renderDucks();
		batch.end();
	}

	private void renderDog() {
		if (world.dog.texture != null)
			batch.draw(world.dog.texture, world.dog.position.x,
					world.dog.position.y, Dog.DOG_WIDTH, Dog.DOG_HEIGHT);
	}

	private void renderDucks() {
		int len = world.ducks.size();
		for (int i = 0; i < len; i++) {
			Duck duck = world.ducks.get(i);

			TextureRegion texture = duck.texture;
			float x = duck.position.x;
			float y = duck.position.y;
			float width = duck.side * Duck.DUCK_WIDTH;
			float height = Duck.DUCK_HEIGHT;

			batch.draw(texture, x, y, width, height);

			switch (duck.state) {
			case Duck.DUCK_STATE_FLYING:
				float side = duck.velocity.x < 0 ? -1 : 1;
				if (side < 0)
					batch.draw(
							Assets.duckFly.getKeyFrame(duck.stateTime, true),
							duck.position.x + 0.5f + i, duck.position.y - 0.5f,
							side * Duck.DUCK_WIDTH, Duck.DUCK_HEIGHT);
				else
					batch.draw(
							Assets.duckFly.getKeyFrame(duck.stateTime, true),
							duck.position.x - 0.5f + i, duck.position.y - 0.5f,
							side * Duck.DUCK_WIDTH, Duck.DUCK_HEIGHT);
				break;
			case Duck.DUCK_STATE_HIT:
				batch.draw(Assets.duckHit, duck.position.x, duck.position.y,
						Duck.DUCK_WIDTH, Duck.DUCK_HEIGHT);
				break;
			case Duck.DUCK_STATE_FALLING:
				batch.draw(Assets.duckFalling, duck.position.x,
						duck.position.y, Duck.DUCK_WIDTH, Duck.DUCK_WIDTH);
				break;
			}
		}
	}
}
