package com.mygdx.game;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.model.UserData;

public class AndroidLauncher extends AndroidApplication {
	UserData mUserData;
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if(getIntent()!=null) {
			mUserData = (UserData) getIntent().getSerializableExtra("user_data");
		}

		Log.d("user data", mUserData.toString());

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new MyGdxGame(new Runnable() {
			@Override
			public void run() {
				onQuizEnd();
			}
		}), config);
	}

	private void onQuizEnd() {
		Intent intent = new Intent(this, SendingDataActivity.class);
		intent.putExtra("user_data", mUserData);
		finish();
		startActivity(intent);
	}
}
