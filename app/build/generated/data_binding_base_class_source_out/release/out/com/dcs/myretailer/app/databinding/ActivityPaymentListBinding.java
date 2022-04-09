// Generated by data binding compiler. Do not edit!
package com.dcs.myretailer.app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.dcs.myretailer.app.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivityPaymentListBinding extends ViewDataBinding {
  @NonNull
  public final RecyclerView rvPaymentType;

  protected ActivityPaymentListBinding(Object _bindingComponent, View _root, int _localFieldCount,
      RecyclerView rvPaymentType) {
    super(_bindingComponent, _root, _localFieldCount);
    this.rvPaymentType = rvPaymentType;
  }

  @NonNull
  public static ActivityPaymentListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_payment_list, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityPaymentListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityPaymentListBinding>inflateInternal(inflater, R.layout.activity_payment_list, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityPaymentListBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_payment_list, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityPaymentListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityPaymentListBinding>inflateInternal(inflater, R.layout.activity_payment_list, null, false, component);
  }

  public static ActivityPaymentListBinding bind(@NonNull View view) {
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
  public static ActivityPaymentListBinding bind(@NonNull View view, @Nullable Object component) {
    return (ActivityPaymentListBinding)bind(component, view, R.layout.activity_payment_list);
  }
}
