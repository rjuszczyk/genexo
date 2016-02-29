// Generated code from Butter Knife. Do not modify!
package com.mygdx.game;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class DataActivity$$ViewBinder<T extends com.mygdx.game.DataActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492968, "method 'tryAgain'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.tryAgain(p0);
        }
      });
  }

  @Override public void unbind(T target) {
  }
}
