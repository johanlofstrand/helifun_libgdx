package com.appspot.simplechildfriendlygames;

import com.appspot.simplechildfriendlygames.actors.Player;
import com.appspot.simplechildfriendlygames.parallax.ParallaxBackground;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import javax.xml.soap.Text;
import java.util.Date;


public class HelifunGame extends ApplicationAdapter implements InputProcessor {
	Texture img;
	Sprite player;
	int playerFrame=1;
	int playerMaxFrame=5;
	boolean movingLeft=false;
	boolean movingRight=false;
	TextureAtlas textureAtlas;
	Sprite sprite;
	TextureRegion textureRegion;
	ParallaxBackground parallaxBackground;
	float delta = 2f;
	Actor balloon;
	FPSLogger fpsLogger;
	Texture bgTexture;
	World world;
	Body body;
	private Box2DDebugRenderer box2DDebugRenderer;
	private OrthographicCamera camera;
	Viewport viewport;
	private int WORLD_WIDTH = 1400;
	private int WORLD_HEIGHT = 980;
	Stage stage;

	@Override
	public void create () {
		Gdx.app.log("Helifun ", "started");

		float aspectRatio = (float)Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();
		camera = new OrthographicCamera(WORLD_HEIGHT * aspectRatio ,WORLD_HEIGHT);
		camera.translate(camera.viewportWidth/2,camera.viewportHeight/2);
		viewport = new ExtendViewport(WORLD_HEIGHT * aspectRatio,WORLD_HEIGHT);
		stage = new Stage(viewport);
		stage.getViewport().setCamera(camera);
		viewport.apply();

		Player player1 = new Player();
		stage.addActor(player1);
		stage.setKeyboardFocus(player1);
		fpsLogger = new FPSLogger();
		Gdx.input.setInputProcessor(this);


	/*	bgTexture = new Texture(Gdx.files.internal("images/drakstart_bakgrund.png"));
		Image bgImage = new Image(bgTexture);
		bgImage.setPosition(0, 0);
		bgImage.setSize(WORLD_WIDTH,WORLD_HEIGHT);
		stage.addActor(bgImage);*/

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
	//	world.dispose();
		//box2DDebugRenderer.dispose();
	}


	public void resize(int width, int height){
		viewport.update(width,height);
		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
	}

	private void setupBox2D(Sprite sprite) {
		world = new World(new Vector2(0, -9.8f), true);

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(sprite.getX(), sprite.getY());
		body = world.createBody(bodyDef);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(sprite.getWidth()/2, sprite.getHeight()/2);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;

		Fixture fixture = body.createFixture(fixtureDef);

		shape.dispose();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Gdx.app.log("Mouse Event","Click at " + screenX + "," + screenY);
		Vector3 worldCoordinates = camera.unproject(new Vector3(screenX,screenY,0));
		Gdx.app.log("Mouse Event","Projected at " + worldCoordinates.x + "," + worldCoordinates.y);
		stage.touchDown(screenX,screenY,pointer,button);  //have to call this to get the stage to call actors touch down.
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Input.Keys.LEFT) {
			camera.translate(-1, 0);
		} else if (keycode == Input.Keys.RIGHT) {
			camera.translate(1, 0);
		}
		else if (keycode == Input.Keys.UP) {
			camera.translate(0, -1);
		}
		else if (keycode == Input.Keys.DOWN) {
			camera.translate(0, 1);
		}
		return true;
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

}