package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.Text;

public class MyGdxGame extends ApplicationAdapter implements InputProcessor {
	private OrthographicCamera cam;
	private SpriteBatch batch;
	Texture img;
	Baba baba;
	List<Answer> answers = new ArrayList<Answer>();
	List<PositionedTexture> mTextures = new ArrayList<PositionedTexture>();
	DropBox dropBox;

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		cam.viewportWidth = 1200f;
		cam.viewportHeight = 800f;
		cam.update();
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		PositionedTexture.screenHeight = 800;
		mTextures.add(new PositionedTexture("pytanie_1_label.jpg", 639,65));
		mTextures.add(new PositionedTexture("multilac_logo.jpg", 39,28));
		mTextures.add(new PositionedTexture("bottom.jpg", 0,578));


		baba = new Baba(
				new PositionedTexture("baba_czeka.jpg", 48,230),
				new PositionedTexture("baba_zadowolona.jpg", 6,188),
				new PositionedTexture("baba_zla.jpg", 81,219)
		);

		answers.add(new Answer(0, 0, img, false));
		answers.add(new Answer(800, 0, img, false));
		answers.add(new Answer(582,202, new Texture("pyt_1_a.png"), true));
		dropBox = new DropBox(267,107,  new Texture("chmurka.jpg"));
		Gdx.input.setInputProcessor(this);

		cam = new OrthographicCamera(1200, 800);
		cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
		cam.update();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		cam.update();

		batch.setProjectionMatrix(cam.combined);
		dropBox.draw(batch);
		for(Answer answer : answers) {
			answer.draw(batch);
		}
		for(PositionedTexture positionedTexture : mTextures) {
			positionedTexture.draw(batch);
		}

		baba.draw(batch);

		batch.end();


		float deltaTime = Gdx.graphics.getDeltaTime();
		for(Answer answer : answers) {
			answer.animate(deltaTime);
		}
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Gdx.app.log("input", "touchDown");
		int x = (int) (((float)screenX/(float)Gdx.graphics.getWidth())* cam.viewportWidth);
		int y = (int) ((1- ((float)screenY/(float)Gdx.graphics.getHeight()))* cam.viewportHeight);
		for(Answer answer : answers) {
			if (answer.contains(x,y)) {
				answer.startDrag(x,y);
			}
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Gdx.app.log("input", "touchUp");
		int x = (int) (((float)screenX/(float)Gdx.graphics.getWidth())* cam.viewportWidth);
		int y = (int) ((1- ((float)screenY/(float)Gdx.graphics.getHeight()))* cam.viewportHeight);
		for(final Answer answer : answers) {
			answer.onEndDrag(x,y, new Answer.EndDragCallback() {
				@Override
				public void onEndDrag(int endX, int endY) {
					if (dropBox.contains(endX, endY)) {
						if (answer.isCorrect()) {
							answer.startAnimation(answer.getPosition(), dropBox.getPosition(), 5f);
							baba.state = Baba.State.ZADOWOLONA;
						} else {
							answer.startAnimation(answer.getPosition(), answer.getStartPosition(), 4f);
							baba.state = Baba.State.ZLA;
						}


					} else {
						answer.startAnimation(answer.getPosition(), answer.getStartPosition(), 1.2f);
						baba.state = Baba.State.CZEKA;
					}
				}
			});
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		int x = (int) (((float)screenX/(float)Gdx.graphics.getWidth())* cam.viewportWidth);
		int y = (int) ((1- ((float)screenY/(float)Gdx.graphics.getHeight()))* cam.viewportHeight);
		for(Answer answer : answers) {
			answer.onDrag(x,y);
		}
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	@Override
	public void dispose() {
		img.dispose();
		super.dispose();
	}
}
