// Generated code from Butter Knife. Do not modify!
package com.mygdx.game;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class FormActivity$$ViewBinder<T extends com.mygdx.game.FormActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427440, "field 'mPrzedstawicielMedyczny'");
    target.mPrzedstawicielMedyczny = finder.castView(view, 2131427440, "field 'mPrzedstawicielMedyczny'");
    view = finder.findRequiredView(source, 2131427441, "field 'mMiasto'");
    target.mMiasto = finder.castView(view, 2131427441, "field 'mMiasto'");
    view = finder.findRequiredView(source, 2131427442, "field 'mApteka'");
    target.mApteka = finder.castView(view, 2131427442, "field 'mApteka'");
    view = finder.findRequiredView(source, 2131427451, "field 'mQuiz' and method 'onDalej'");
    target.mQuiz = view;
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onDalej(p0);
        }
      });
    view = finder.findRequiredView(source, 2131427443, "field 'mImie'");
    target.mImie = finder.castView(view, 2131427443, "field 'mImie'");
    view = finder.findRequiredView(source, 2131427444, "field 'mNazwisko'");
    target.mNazwisko = finder.castView(view, 2131427444, "field 'mNazwisko'");
    view = finder.findRequiredView(source, 2131427445, "field 'mTelefon'");
    target.mTelefon = finder.castView(view, 2131427445, "field 'mTelefon'");
    view = finder.findRequiredView(source, 2131427446, "field 'mEmail'");
    target.mEmail = finder.castView(view, 2131427446, "field 'mEmail'");
    view = finder.findRequiredView(source, 2131427448, "field 'mCheck1'");
    target.mCheck1 = finder.castView(view, 2131427448, "field 'mCheck1'");
    view = finder.findRequiredView(source, 2131427449, "field 'mCheck2'");
    target.mCheck2 = finder.castView(view, 2131427449, "field 'mCheck2'");
    view = finder.findRequiredView(source, 2131427450, "field 'mCheck3'");
    target.mCheck3 = finder.castView(view, 2131427450, "field 'mCheck3'");
    view = finder.findRequiredView(source, 2131427447, "method 'onRegulamin'");
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
