// Generated by data binding compiler. Do not edit!
package com.dcs.myretailer.app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.dcs.myretailer.app.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class OnhandqtyLayoutBinding extends ViewDataBinding {
  @NonNull
  public final LinearLayout LayOnHandQty;

  @NonNull
  public final TextView txtOnHandQty1;

  protected OnhandqtyLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount,
      LinearLayout LayOnHandQty, TextView txtOnHandQty1) {
    super(_bindingComponent, _root, _localFieldCount);
    this.LayOnHandQty = LayOnHandQty;
    this.txtOnHandQty1 = txtOnHandQty1;
  }

  @NonNull
  public static OnhandqtyLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.onhandqty_layout, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static OnhandqtyLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<OnhandqtyLayoutBinding>inflateInternal(inflater, R.layout.onhandqty_layout, root, attachToRoot, component);
  }

  @NonNull
  public static OnhandqtyLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.onhandqty_layout, null, false, component)
   */
  @NonNull
  @Deprecated
  public static OnhandqtyLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<OnhandqtyLayoutBinding>inflateInternal(inflater, R.layout.onhandqty_layout, null, false, component);
  }

  public static OnhandqtyLayoutBinding bind(@NonNull View view) {
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
  public static OnhandqtyLayoutBinding bind(@NonNull View view, @Nullable Object component) {
    return (OnhandqtyLayoutBinding)bind(component, view, R.layout.onhandqty_layout);
  }
}
