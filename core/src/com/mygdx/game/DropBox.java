package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Radek on 22.02.2016.
 */
public class DropBox {
    int mX;
    int mY;
    Sprite mSprite;
    Sprite mSpriteWrong;
    Sprite mSpriteGood;

    public DropBox(int startX, int startY, Texture sprite, Texture spriteWrong, Texture spriteGood) {
        mSprite = new Sprite(sprite);
        mSpriteWrong = new Sprite(spriteWrong);
        mSpriteGood = new Sprite(spriteGood);
        mX = startX;
        mY = PositionedTexture.screenHeight - startY - sprite.getHeight();
    }

    public boolean contains(int x, int y) {
        if(isWrong()) {
            return false;
        }

        return x>=mX && x<mX+mSprite.getWidth() && y>=mY && y<mY+mSprite.getHeight();
    }
    float counterGood = -1;
    float counter = -1; //ms

    public boolean isWrong(long deltaTime, Runnable onTimeElapsed) {
        if(counter<0) {
            return false;
        } else {
            counter -= deltaTime;
            if(counter<0) {
                onTimeElapsed.run();
            }
            return true;
        }
    }

    public void reset() {
        counter = -1;
        counterGood = -1;
        isGood = false;
    }

    public boolean isWrong() {
        return counter>=0;
    }

    public void setWrong() {
        counter += 2000;
    }
    boolean isGood = false;
    public void setGood() {
        isGood = true;
        counterGood = 2000;
    }

    public boolean isGood(long deltaTime, Runnable onTimeElapsed) {
        if(counterGood<0) {
            return false;
        } else {
            counterGood -= deltaTime;
            if(counterGood<0) {
                onTimeElapsed.run();
            }
            return true;
        }
    }

    public Vector2 getPosition() {
        return new Vector2(mX, mY);
    }

    public void draw(SpriteBatch batch) {
        if(isGood) {
            batch.draw(mSpriteGood, mX-13, mY-15);
            return;
        }

        if(!isWrong()) {
            batch.draw(mSprite, mX, mY);
        } else {
            float alpha = 1;
            if(counter < 500) {
                alpha = (counter)/500;
                if(alpha>1)alpha=1;
                if(alpha<0)alpha=0;
                Gdx.app.log("alpha", "a = " + alpha);
                mSpriteWrong.setAlpha(alpha);
            } else if(counter > 1500){
                alpha = (2000-counter)/200;
                if(alpha>1)alpha=1;
                if(alpha<0)alpha=0;
                mSpriteWrong.setAlpha(alpha);

                Gdx.app.log("alpha", "a = " + alpha);
            }
            //batch.setColor(1,0,0,0);
          // mSpriteWrong.draw(batch, alpha);
            float oldAlpha = mSpriteWrong.getColor().a;
            mSpriteWrong.setAlpha(oldAlpha * alpha);
            float[] v = mSpriteWrong.getVertices();
            //xy 123
            float[] mV = new float[v.length];
            for(int i = 0; i < v.length; i++) {
                mV[i] = v[i];
                if(i%5==0) {
                    mV[i] += mX;
                }
                if(i%5==1) {
                    mV[i] += mY;
                }
            }


            batch.draw(mSprite, mX, mY);
            batch.draw(mSpriteWrong.getTexture(), mV, 0, 20);
            mSpriteWrong.setAlpha(oldAlpha);

            /*
            batch.draw(mSprite, mX, mY);
            float oldAlpha = mSpriteWrong.getColor().a;
            mSpriteWrong.setAlpha(oldAlpha * alpha);
            batch.draw(mSpriteWrong.getTexture(), mX, mY);
            mSpriteWrong.setAlpha(oldAlpha);
             */
        }
    }
}
