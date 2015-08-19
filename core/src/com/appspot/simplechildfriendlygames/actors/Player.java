package com.appspot.simplechildfriendlygames.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;

/**
 * Created by johanlofstrand on 15-08-04.
 */
public class Player extends Actor {

    TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("images/spritesheets/HeliSprites.atlas"));
    TextureRegion textureRegion = textureAtlas.findRegion("h_fly_0001");
    Sprite sprite = new Sprite(textureRegion);
    //sprite.setPosition(Gdx.graphics.getWidth()/2-player.getWidth()/2, Gdx.graphics.getHeight()/2-player.getHeight()/2);

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //batch.draw(sprite,0,0);
        sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    protected void positionChanged() {
        sprite.setPosition(this.getX(),this.getY());
        super.positionChanged();
    }

    public Player() {
        setBounds(sprite.getX(),sprite.getY(),sprite.getWidth(),sprite.getHeight());
        setTouchable(Touchable.enabled);

        addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
              ///  setVisible(false);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                if (keycode == Input.Keys.LEFT) {
                    MoveByAction moveByAction = new MoveByAction();
                    moveByAction.setAmount(-10f,0);
                    //moveByAction.setDuration(1f);
                    Player.this.addAction(moveByAction);
                }
                else if (keycode == Input.Keys.RIGHT) {
                    //sprite.translateX(1f);
                    MoveByAction moveByAction = new MoveByAction();
                    moveByAction.setAmount(10f,0);
                    //moveByAction.setDuration(1f);
                    Player.this.addAction(moveByAction);
                }
                return true;
            }

            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                return super.keyDown(event, keycode);
            }
        });
    }


}
