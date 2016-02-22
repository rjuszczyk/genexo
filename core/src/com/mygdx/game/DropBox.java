package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Radek on 22.02.2016.
 */
public class DropBox {
    int mX;
    int mY;
    Texture mSprite;

    public DropBox(int startX, int startY, Texture sprite) {
        mSprite = sprite;
        mX = startX;
        mY = PositionedTexture.screenHeight - startY - sprite.getHeight();
    }

    public boolean contains(int x, int y) {
        return x>=mX && x<mX+mSprite.getWidth() && y>=mY && y<mY+mSprite.getHeight();
    }

    public Vector2 getPosition() {
        return new Vector2(mX, mY);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(mSprite, mX, mY);
    }
}
