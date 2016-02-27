// Generated code from Butter Knife. Do not modify!
package com.mygdx.game;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class FormActivity$$ViewBinder<T extends com.mygdx.game.FormActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427435, "field 'mPrzedstawicielMedyczny'");
    target.mPrzedstawicielMedyczny = finder.castView(view, 2131427435, "field 'mPrzedstawicielMedyczny'");
    view = finder.findRequiredView(source, 2131427436, "field 'mMiasto'");
    target.mMiasto = finder.castView(view, 2131427436, "field 'mMiasto'");
    view = finder.findRequiredView(source, 2131427437, "field 'mApteka'");
    target.mApteka = finder.castView(view, 2131427437, "field 'mApteka'");
    view = finder.findRequiredView(source, 2131427438, "method 'onDalej'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onDalej(p0);
        }
      });
  }

  @Override public void unbind(T target) {
    target.mPrzedstawicielMedyczny = null;
    target.mMiasto = null;
    target.mApteka = null;
  }
}
