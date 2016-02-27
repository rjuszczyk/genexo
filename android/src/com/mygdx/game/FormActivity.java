package com.mygdx.game;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mygdx.game.database.DatabaseHelper;
import com.mygdx.game.dialog.ChooseElementDialog;
import com.mygdx.game.model.Row;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Radek on 2016-02-27.
 */
public class FormActivity extends AppCompatActivity {
    @Bind(R.id.przedst)
    TextView mPrzedstawicielMedyczny;

    @Bind(R.id.miasto)
    TextView mMiasto;

    @Bind(R.id.apteka)
    TextView mApteka;

    @OnClick(R.id.dalej)
    void onDalej(View v) {
        startActivity(new Intent(this, AndroidLauncher.class));
    }

    String imie_przedstawiciela;
    String nazwisko_przedstawiciela;
    String miasto;
    String nazwa_apteki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_activity);

        ButterKnife.bind(this);

        mMiasto.setEnabled(false);
        mApteka.setEnabled(false);
        mPrzedstawicielMedyczny.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new ChooseElementDialog() {

                    @Override
                    public String getRowText(Row row) {
                        return row.imie_przedstawiciela + " " + row.nazwisko_przedstawiciela;
                    }

                    @Override
                    public List<Row> getRows(Context context) {
                        return DatabaseHelper.rowsForPrzedstawiciel(context);
                    }

                    @Override
                    public void onRowSelected(Row row) {
                        imie_przedstawiciela = row.imie_przedstawiciela;
                        nazwisko_przedstawiciela = row.nazwisko_przedstawiciela;
                        mPrzedstawicielMedyczny.setText(getRowText(row));
                        mMiasto.setEnabled(true);
                    }
                }.show(getSupportFragmentManager(), "tag");
            }
        });


        mMiasto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new ChooseElementDialog() {

                    @Override
                    public String getRowText(Row row) {
                        return row.miasto;
                    }

                    @Override
                    public List<Row> getRows(Context context) {
                        return DatabaseHelper.rowsForPrzedstawiciel(imie_przedstawiciela, nazwisko_przedstawiciela, context);
                    }

                    @Override
                    public void onRowSelected(Row row) {
                        miasto = row.miasto;
                        mMiasto.setText(miasto);
                        mApteka.setEnabled(true);
                    }
                }.show(getSupportFragmentManager(), "tag");
            }
        });

        mApteka.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new ChooseElementDialog() {

                    @Override
                    public String getRowText(Row row) {
                        return row.nazwa_apteki;
                    }

                    @Override
                    public List<Row> getRows(Context context) {
                        return DatabaseHelper.rowsForPrzedstawiciel(imie_przedstawiciela, nazwisko_przedstawiciela, miasto, context);
                    }

                    @Override
                    public void onRowSelected(Row row) {
                        nazwa_apteki = row.nazwa_apteki;
                        mApteka.setText(nazwa_apteki);
                        mApteka.setEnabled(true);
                    }
                }.show(getSupportFragmentManager(), "tag");
            }
        });
    }
}
