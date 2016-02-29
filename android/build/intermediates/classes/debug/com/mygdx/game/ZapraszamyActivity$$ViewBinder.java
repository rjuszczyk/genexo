// Generated code from Butter Knife. Do not modify!
package com.mygdx.game;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ZapraszamyActivity$$ViewBinder<T extends com.mygdx.game.ZapraszamyActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493007, "method 'dalej'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.dalej(p0);
        }
      });
    view = finder.findRequiredView(source, 2131492983, "method 'regulamin'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.regulamin(p0);
        }
      });
  }

  @Override public void unbind(T target) {
  }
}
