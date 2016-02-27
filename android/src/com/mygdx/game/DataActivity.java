package com.mygdx.game;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mygdx.game.database.DatabaseHelper;
import com.mygdx.game.dialog.ChooseElementDialog;
import com.mygdx.game.model.Row;
import com.mygdx.game.model.RowResponse;
import com.mygdx.game.network.MyRetrofit;
import com.mygdx.game.util.SharedPreferencesUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by Radek on 2016-02-26.
 */
public class DataActivity extends AppCompatActivity {

    @OnClick(R.id.start)
    public void onStart(View view) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dane_activity);

        ButterKnife.bind(this);


        if(!SharedPreferencesUtils.isStoreIndexInserted(this)) {
            Call<RowResponse> rowCall = MyRetrofit.getApi().rows();
            rowCall.enqueue(new Callback<RowResponse>() {
                @Override
                public void onResponse(Response<RowResponse> response) {
                    final RowResponse rowResponse = response.body();
                    Log.d("response", "onResponse");
                    if (rowResponse != null) {
                        Log.d("response", "not null");
                        DatabaseHelper databaseHelper = new DatabaseHelper(DataActivity.this);

                        SQLiteDatabase db = databaseHelper.getWritableDatabase();
                        db.beginTransaction();
                        try {
                            for (Row row : rowResponse.getRows()) {
                                row.save(db);
                            }
                            db.setTransactionSuccessful();
                        } catch (Exception e) {

                            e.printStackTrace();
                        } finally {
                            db.endTransaction();
                        }
                        SharedPreferencesUtils.setStoreIndexInserted(DataActivity.this, true);
                    }

                    //Nawrot
                    //Aleksandra
                    long time = System.currentTimeMillis();
                    List<Row> r = DatabaseHelper.rowsForPrzedstawiciel(DataActivity.this);
                    time = System.currentTimeMillis() - time;
                    Log.d("result", "r = " + r.size());
                    Log.d("time", "time = " + time);
                    startNextActivity();
                }

                @Override
                public void onFailure(Throwable t) {
                    Log.d("response", "onFailure " + t.getMessage());
                }
            });
        } else {
            startNextActivity();
        }
    }

    private void startNextActivity() {

        Intent intent = new Intent(this, FormActivity.class);
        startActivity(intent);
    }
}
