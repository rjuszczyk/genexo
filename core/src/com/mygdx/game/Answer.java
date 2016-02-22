package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Radek on 22.02.2016.
 */
public class Answer {
    private final boolean mIsCorrect;
    int mX;
    int mY;
    Texture mSprite;
    private float mAnimationSpeed = 1;



    public Answer(int startX, int startY, Texture sprite, boolean isCorrect) {
        mSprite = sprite;
        mX = startX;
        mY = PositionedTexture.screenHeight - startY - sprite.getHeight();
        mIsCorrect = isCorrect;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(mSprite, mX, mY);
    }

    public boolean isCorrect() {
        return mIsCorrect;
    }

    int mDragX_start;
    int mDragY_start;
    int mDragX_offset;
    int mDragY_offset;
    boolean mIsDragging = false;

    public boolean contains(int x, int y) {
        return x>=mX && x<mX+mSprite.getWidth() && y>=mY && y<mY+mSprite.getHeight();
    }

    boolean isAnimating = false;

    public void animate(float deltaTime) {
        if(isAnimating) {
            animationProgress += deltaTime*mAnimationSpeed; //MathUtils.lerp(animationProgress, 1, deltaTime);
            if(animationProgress <0.99f) {
                mX = (int) (startAnimation.x + (endAnimation.x - startAnimation.x) * animationProgress);
                mY = (int) (startAnimation.y + (endAnimation.y - startAnimation.y) * animationProgress);
            } else {
                animationProgress = 1;
                isAnimating = false;
                mX = (int) endAnimation.x;
                mY = (int) endAnimation.y;
            }
        }
    }

    public void startAnimation(Vector2 from, Vector2 to, float speed ) {
        startAnimation.set(from);
        mAnimationSpeed = speed;
        endAnimation.set(to);
        animationProgress = 0;
        isAnimating = true;
    }

    private float animationProgress = 0;
    Vector2 startAnimation = new Vector2();
    Vector2 endAnimation = new Vector2();

    public void startDrag(int dragX, int dragY) {
        mDragX_offset = mX - dragX;
        mDragY_offset = mY - dragY;
        mDragX_start = mX;
        mDragY_start = mY;

        mIsDragging = true;
    }

    public void onDrag(int currentX, int currentY) {
        isAnimating = false;
        if(mIsDragging) {
            mX = currentX + mDragX_offset;
            mY = currentY + mDragY_offset;
        }
    }

    public void onEndDrag(int currentX, int currentY, EndDragCallback endDragCallback) {
        if(mIsDragging) {
            mIsDragging = false;
            endDragCallback.onEndDrag(currentX, currentY);
        }
    }

    public void setPosition(Vector2 position) {
        mX = (int) position.x;
        mY = (int) position.y;
    }

    public Vector2 getStartPosition() {
        return new Vector2(mDragX_start, mDragY_start);
    }

    public Vector2 getPosition() {
        return new Vector2(mX, mY);

    }

    public static interface EndDragCallback {
        void onEndDrag(int endX, int endY);
    }
}
