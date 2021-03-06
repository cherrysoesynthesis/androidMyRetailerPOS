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

public abstract class ActivityProductManagementBinding extends ViewDataBinding {
  @NonNull
  public final RecyclerView productManagementRecyclerviewId;

  protected ActivityProductManagementBinding(Object _bindingComponent, View _root,
      int _localFieldCount, RecyclerView productManagementRecyclerviewId) {
    super(_bindingComponent, _root, _localFieldCount);
    this.productManagementRecyclerviewId = productManagementRecyclerviewId;
  }

  @NonNull
  public static ActivityProductManagementBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_product_management, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityProductManagementBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityProductManagementBinding>inflateInternal(inflater, R.layout.activity_product_management, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityProductManagementBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_product_management, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityProductManagementBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityProductManagementBinding>inflateInternal(inflater, R.layout.activity_product_management, null, false, component);
  }

  public static ActivityProductManagementBinding bind(@NonNull View view) {
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
  public static ActivityProductManagementBinding bind(@NonNull View view,
      @Nullable Object component) {
    return (ActivityProductManagementBinding)bind(component, view, R.layout.activity_product_management);
  }
}
