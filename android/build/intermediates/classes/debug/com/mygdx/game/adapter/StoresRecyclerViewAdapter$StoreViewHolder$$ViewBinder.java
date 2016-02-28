// Generated code from Butter Knife. Do not modify!
package com.mygdx.game.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class StoresRecyclerViewAdapter$StoreViewHolder$$ViewBinder<T extends com.mygdx.game.adapter.StoresRecyclerViewAdapter.StoreViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427452, "field 'mStoreName'");
    target.mStoreName = finder.castView(view, 2131427452, "field 'mStoreName'");
  }

  @Override public void unbind(T target) {
    target.mStoreName = null;
  }
}
