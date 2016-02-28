// Generated code from Butter Knife. Do not modify!
package com.mygdx.game.dialog;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class RegulaminDialog$$ViewBinder<T extends com.mygdx.game.dialog.RegulaminDialog> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427429, "field 'mWebView'");
    target.mWebView = finder.castView(view, 2131427429, "field 'mWebView'");
  }

  @Override public void unbind(T target) {
    target.mWebView = null;
  }
}
