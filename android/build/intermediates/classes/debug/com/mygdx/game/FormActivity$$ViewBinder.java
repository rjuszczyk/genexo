// Generated code from Butter Knife. Do not modify!
package com.mygdx.game;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class FormActivity$$ViewBinder<T extends com.mygdx.game.FormActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492976, "field 'mPrzedstawicielMedyczny'");
    target.mPrzedstawicielMedyczny = finder.castView(view, 2131492976, "field 'mPrzedstawicielMedyczny'");
    view = finder.findRequiredView(source, 2131492977, "field 'mMiasto'");
    target.mMiasto = finder.castView(view, 2131492977, "field 'mMiasto'");
    view = finder.findRequiredView(source, 2131492978, "field 'mApteka'");
    target.mApteka = finder.castView(view, 2131492978, "field 'mApteka'");
    view = finder.findRequiredView(source, 2131492987, "field 'mQuiz' and method 'onDalej'");
    target.mQuiz = view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onDalej(p0);
        }
      });
    view = finder.findRequiredView(source, 2131492979, "field 'mImie'");
    target.mImie = finder.castView(view, 2131492979, "field 'mImie'");
    view = finder.findRequiredView(source, 2131492980, "field 'mNazwisko'");
    target.mNazwisko = finder.castView(view, 2131492980, "field 'mNazwisko'");
    view = finder.findRequiredView(source, 2131492981, "field 'mTelefon'");
    target.mTelefon = finder.castView(view, 2131492981, "field 'mTelefon'");
    view = finder.findRequiredView(source, 2131492982, "field 'mEmail'");
    target.mEmail = finder.castView(view, 2131492982, "field 'mEmail'");
    view = finder.findRequiredView(source, 2131492984, "field 'mCheck1'");
    target.mCheck1 = finder.castView(view, 2131492984, "field 'mCheck1'");
    view = finder.findRequiredView(source, 2131492985, "field 'mCheck2'");
    target.mCheck2 = finder.castView(view, 2131492985, "field 'mCheck2'");
    view = finder.findRequiredView(source, 2131492986, "field 'mCheck3'");
    target.mCheck3 = finder.castView(view, 2131492986, "field 'mCheck3'");
    view = finder.findRequiredView(source, 2131492983, "method 'onRegulamin'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onRegulamin(p0);
        }
      });
  }

  @Override public void unbind(T target) {
    target.mPrzedstawicielMedyczny = null;
    target.mMiasto = null;
    target.mApteka = null;
    target.mQuiz = null;
    target.mImie = null;
    target.mNazwisko = null;
    target.mTelefon = null;
    target.mEmail = null;
    target.mCheck1 = null;
    target.mCheck2 = null;
    target.mCheck3 = null;
  }
}
