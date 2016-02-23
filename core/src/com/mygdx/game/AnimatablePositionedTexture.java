package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Radek on 23.02.2016.
 */
public class AnimatablePositionedTexture extends PositionedTexture {
    int mX_initial;
    int mY_initial;
    private float mAnimationSpeed = 1;

    public AnimatablePositionedTexture(String file, int x, int y) {
        super(file, x, y);
        mX_initial = mX;
        mY_initial = mY;
    }

    boolean isAnimating = false;

    public void animate(float deltaTime) {
        Gdx.app.log("yyy" , "y = " + mY);
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

    public void startAnimationIn() {
        //startAnimation(getPosition(), getPosition().add(0,-screenHeight), 0.1f);
        startAnimation(getPosition().add(0, screenHeight), getPosition(), MyGdxGame.ANIMATION_SPEED);
    }

    public void startAnimationOut() {
        startAnimation(getPosition(), getPosition().add(0,-screenHeight), MyGdxGame.ANIMATION_SPEED);
    }

    public Vector2 getPosition() {
        return new Vector2(mX, mY);
    }

    public void startAnimation(Vector2 from, Vector2 to, float speed ) {
        Gdx.app.log("animation", "" +from.y+" -> "+ to.y);

        startAnimation.set(from);
        mAnimationSpeed = speed;
        endAnimation.set(to);
        animationProgress = 0;
        isAnimating = true;
    }

    private float animationProgress = 0;
    Vector2 startAnimation = new Vector2();
    Vector2 endAnimation = new Vector2();

    public void reset() {
        mX = mX_initial;
        mY = mY_initial;
    }

    public void setPosition(Vector2 position) {
        mX = (int) position.x;
        mY = (int) position.y;
    }
}
