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

public abstract class ActivityCheckOutListviewBinding extends ViewDataBinding {
  @NonNull
  public final LinearLayout LayProductName;

  @NonNull
  public final LinearLayout LayProductPrice;

  @NonNull
  public final LinearLayout checkoutProductListview;

  @NonNull
  public final LinearLayout layCheckoutAdapter;

  @NonNull
  public final TextView reportProductName;

  @NonNull
  public final TextView reportProductName1;

  @NonNull
  public final TextView reportProductPrice;

  @NonNull
  public final TextView reportProductPriceOld;

  @NonNull
  public final TextView reportProductQty;

  protected ActivityCheckOutListviewBinding(Object _bindingComponent, View _root,
      int _localFieldCount, LinearLayout LayProductName, LinearLayout LayProductPrice,
      LinearLayout checkoutProductListview, LinearLayout layCheckoutAdapter,
      TextView reportProductName, TextView reportProductName1, TextView reportProductPrice,
      TextView reportProductPriceOld, TextView reportProductQty) {
    super(_bindingComponent, _root, _localFieldCount);
    this.LayProductName = LayProductName;
    this.LayProductPrice = LayProductPrice;
    this.checkoutProductListview = checkoutProductListview;
    this.layCheckoutAdapter = layCheckoutAdapter;
    this.reportProductName = reportProductName;
    this.reportProductName1 = reportProductName1;
    this.reportProductPrice = reportProductPrice;
    this.reportProductPriceOld = reportProductPriceOld;
    this.reportProductQty = reportProductQty;
  }

  @NonNull
  public static ActivityCheckOutListviewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_check_out_listview, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityCheckOutListviewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityCheckOutListviewBinding>inflateInternal(inflater, R.layout.activity_check_out_listview, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityCheckOutListviewBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_check_out_listview, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityCheckOutListviewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityCheckOutListviewBinding>inflateInternal(inflater, R.layout.activity_check_out_listview, null, false, component);
  }

  public static ActivityCheckOutListviewBinding bind(@NonNull View view) {
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
  public static ActivityCheckOutListviewBinding bind(@NonNull View view,
      @Nullable Object component) {
    return (ActivityCheckOutListviewBinding)bind(component, view, R.layout.activity_check_out_listview);
  }
}
