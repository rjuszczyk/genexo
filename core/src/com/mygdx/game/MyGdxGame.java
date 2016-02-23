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

	public static final float ANIMATION_SPEED = 3;

	int currentQuestion = 0;
	Question[] mQuestions;
	Baba baba;
	List<Answer> answers;
	private Question mLastQuestion;

	Question getCurrentQuestion() {
		return mQuestions[currentQuestion%mQuestions.length];
	}
	
	List<PositionedTexture> mTextures = new ArrayList<PositionedTexture>();
	DropBox dropBox;

	void loadQuestion() {
		mQuestions = new Question[] {
				new Question("odp_1_a.png", "odp_1_b.png", "odp_1_c.png", "pytanie_1.png"),
				new Question("odp_2_a.png", "odp_2_b.png", "odp_2_c.png", "pytanie_2.png"),
				new Question("odp_3_a.png", "odp_3_b.png", "odp_3_c.png", "pytanie_3.png")
		};


	}

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

		PositionedTexture.screenHeight = 800;
		mTextures.add(new PositionedTexture("pytanie_bg.jpg", 0, 0));
		loadQuestion();

		baba = new Baba(
				new PositionedTexture("baba_czeka.png", 76,235),
				new PositionedTexture("baba_dobra.png", 12,200),
				new PositionedTexture("baba_zla.png", 87,225)
		);


		dropBox = new DropBox(263,107,  new Texture("chmurka_shape.png"), new Texture("zla_odp.png"), new Texture("dobra_odp.png"));
		Gdx.input.setInputProcessor(this);

		cam = new OrthographicCamera(1200, 800);
		cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
		cam.update();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		answers = getCurrentQuestion().getAnswers();
		AnimatablePositionedTexture question = getCurrentQuestion().getQuestion();

		batch.begin();
		cam.update();

		batch.setProjectionMatrix(cam.combined);

		for(PositionedTexture positionedTexture : mTextures) {
			positionedTexture.draw(batch);
		}

		dropBox.draw(batch);
		question.draw(batch);

		float deltaTime = Gdx.graphics.getDeltaTime();
		if(mLastQuestion!=null) {
			if(mLastQuestion.isAnimating()) {
				mLastQuestion.animate(deltaTime);
				mLastQuestion.draw(batch);
				if(!mLastQuestion.isAnimating()) {
					mLastQuestion.reset();
					mLastQuestion = null;
				}
			} else {
			}
		}
		for(Answer answer : answers) {
			answer.draw(batch);
		}


		baba.draw(batch);

		batch.end();



		dropBox.isWrong((int) (deltaTime * 1000), new Runnable() {
			@Override
			public void run() {
				baba.state = Baba.State.CZEKA;
			}
		});
		dropBox.isGood((int) (deltaTime * 1000), new Runnable() {
			@Override
			public void run() {
				getCurrentQuestion().startAnimationOut();
				mLastQuestion = getCurrentQuestion();
				currentQuestion++;
				getCurrentQuestion().startAnimationIn();
				dropBox.reset();
				baba.state = Baba.State.CZEKA;
			}
		});
		for(Answer answer : answers) {
			answer.animate(deltaTime);
		}
		question.animate(deltaTime);
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
		if(dropBox.isWrong()) {
			return false;
		}

		int x = (int) (((float)screenX/(float)Gdx.graphics.getWidth())* cam.viewportWidth);
		int y = (int) ((1- ((float)screenY/(float)Gdx.graphics.getHeight()))* cam.viewportHeight);



		for(Answer answer : answers) {
			if (answer.contains(x,y)) {
				answer.startDrag(x,y);
				return false;
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
							dropBox.setGood();
						} else {
							answer.startAnimation(answer.getPosition(), answer.getStartPosition(), 4f);
							baba.state = Baba.State.ZLA;
							dropBox.setWrong();
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
		super.dispose();
	}
}
