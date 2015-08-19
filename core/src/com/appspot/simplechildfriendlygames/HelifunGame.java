package com.appspot.simplechildfriendlygames;

import com.appspot.simplechildfriendlygames.actors.Player;
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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import javax.xml.soap.Text;
import java.util.Date;


public class HelifunGame extends ApplicationAdapter {
	Texture img;
	Sprite player;
	int playerFrame=1;
	int playerMaxFrame=5;
	boolean movingLeft=false;
	boolean movingRight=false;
	FPSLogger fpsLogger;

	TextureAtlas textureAtlas;
	Sprite sprite;
	TextureRegion textureRegion;

	ParallaxBackground parallaxBackground;
	float delta = 2f;

	Stage stage;

	@Override
	public void create () {
		Gdx.app.log("Helifun ", "started");
		Player player1 = new Player();
		stage = new Stage(new ScreenViewport());
		stage.addActor(player1);
		stage.setKeyboardFocus(player1);
 		fpsLogger = new FPSLogger();
		Gdx.input.setInputProcessor(stage);

	}

	@Override
	public void render () {
		//fpsLogger.log();
		int framesPerSec = Gdx.graphics.getFramesPerSecond();
		//Gdx.app.log("FPS: ", "" + framesPerSec);
		playerFrame++;
		if (playerFrame>playerMaxFrame) playerFrame=1;

		//player.setRegion(textureAtlas.findRegion("h_fly_" + String.format("%04d", playerFrame)));
		/*if (movingLeft) {
			player.translateX(-1f);
		}
		else if (movingRight) {
			player.translateX(1f);
		}*/
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
			//parallaxBackground.render(delta);
	}

	@Override
	public void dispose() {
		//img.dispose();
	}

}
