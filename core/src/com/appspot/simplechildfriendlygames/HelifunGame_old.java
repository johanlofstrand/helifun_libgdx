package com.appspot.simplechildfriendlygames;

import com.appspot.simplechildfriendlygames.parallax.ParallaxBackground;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;


public class HelifunGame_old extends ApplicationAdapter implements GestureDetector.GestureListener {
	SpriteBatch batch;
	Texture img;

	Sprite player;
	int playerFrame=1;
	int playerMaxFrame=5;
	boolean movingLeft=false;
	boolean movingRight=false;
	FPSLogger fpsLogger;

	GestureDetector gestureDetector;

	TextureAtlas textureAtlas;
	Sprite sprite;
	TextureRegion textureRegion;

	ParallaxBackground parallaxBackground;
	float delta = 2f;

	@Override
	public void create () {
		fpsLogger = new FPSLogger();
		batch = new SpriteBatch();
		textureAtlas = new TextureAtlas(Gdx.files.internal("images/spritesheets/HeliSprites.atlas"));
		textureRegion = textureAtlas.findRegion("h_fly_0001");
		//img = new Texture("badlogic.jpg");
		player = new Sprite(textureRegion);
		player.setPosition(Gdx.graphics.getWidth()/2-player.getWidth()/2, Gdx.graphics.getHeight()/2-player.getHeight()/2);

		gestureDetector = new GestureDetector(this);
		Gdx.input.setInputProcessor(gestureDetector);

		/*parallaxBackground = new ParallaxBackground(new ParallaxLayer[]{
				new ParallaxLayer(atlas.findRegion("bg1"),new Vector2(),new Vector2(0, 0)),
				new ParallaxLayer(atlas.findRegion("bg2"),new Vector2(1.0f,1.0f),new Vector2(0, 500)),
				new ParallaxLayer(atlas.findRegion("bg3"),new Vector2(0.1f,0),new Vector2(0, Constants.HEIGHT-200),new Vector2(0, 0)),
		}, 800, 480,new Vector2(150,0));*/
	}

	@Override
	public void render () {
		fpsLogger.log();
		int framesPerSec = Gdx.graphics.getFramesPerSecond();
		//Gdx.app.log("FPS: ", "" + framesPerSec);
		playerFrame++;
		if (playerFrame>playerMaxFrame) playerFrame=1;
		if (Gdx.graphics.getFrameId() % 20 == 0) {
			player.setRegion(textureAtlas.findRegion("h_fly_" + String.format("%04d", playerFrame)));
		}
		if (movingLeft) {
			player.translateX(-1f);
		}
		else if (movingRight) {
			player.translateX(1f);
		}
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(player,player.getX(), player.getY());
		batch.end();
		//parallaxBackground.render(delta);
	}

	@Override
	public void dispose() {
		img.dispose();
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
		return false;
	}

}
