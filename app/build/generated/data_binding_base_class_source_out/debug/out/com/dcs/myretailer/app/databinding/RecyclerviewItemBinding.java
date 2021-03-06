// Generated by data binding compiler. Do not edit!
package com.dcs.myretailer.app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.dcs.myretailer.app.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class RecyclerviewItemBinding extends ViewDataBinding {
  @NonNull
  public final LinearLayout Lay1;

  @NonNull
  public final ImageView categoryImage;

  @NonNull
  public final TextView categoryText;

  @NonNull
  public final ImageView dropdownmark;

  @NonNull
  public final FrameLayout fragmentContainer;

  @NonNull
  public final CardView layoutCardView;

  protected RecyclerviewItemBinding(Object _bindingComponent, View _root, int _localFieldCount,
      LinearLayout Lay1, ImageView categoryImage, TextView categoryText, ImageView dropdownmark,
      FrameLayout fragmentContainer, CardView layoutCardView) {
    super(_bindingComponent, _root, _localFieldCount);
    this.Lay1 = Lay1;
    this.categoryImage = categoryImage;
    this.categoryText = categoryText;
    this.dropdownmark = dropdownmark;
    this.fragmentContainer = fragmentContainer;
    this.layoutCardView = layoutCardView;
  }

  @NonNull
  public static RecyclerviewItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.recyclerview_item, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static RecyclerviewItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<RecyclerviewItemBinding>inflateInternal(inflater, R.layout.recyclerview_item, root, attachToRoot, component);
  }

  @NonNull
  public static RecyclerviewItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.recyclerview_item, null, false, component)
   */
  @NonNull
  @Deprecated
  public static RecyclerviewItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<RecyclerviewItemBinding>inflateInternal(inflater, R.layout.recyclerview_item, null, false, component);
  }

  public static RecyclerviewItemBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static RecyclerviewItemBinding bind(@NonNull View view, @Nullable Object component) {
    return (RecyclerviewItemBinding)bind(component, view, R.layout.recyclerview_item);
  }
}
