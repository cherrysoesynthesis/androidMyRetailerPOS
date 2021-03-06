// Generated by data binding compiler. Do not edit!
package com.dcs.myretailer.app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.dcs.myretailer.app.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class FragmentReportProductBinding extends ViewDataBinding {
  @NonNull
  public final LinearLayout LayTotalSales;

  @NonNull
  public final ListView ReportProductListView;

  @NonNull
  public final CardView cardviewId;

  @NonNull
  public final LinearLayout productLinearLayoutId;

  @NonNull
  public final TextView txttotalPriceAmount;

  @NonNull
  public final TextView txttotalQty;

  protected FragmentReportProductBinding(Object _bindingComponent, View _root, int _localFieldCount,
      LinearLayout LayTotalSales, ListView ReportProductListView, CardView cardviewId,
      LinearLayout productLinearLayoutId, TextView txttotalPriceAmount, TextView txttotalQty) {
    super(_bindingComponent, _root, _localFieldCount);
    this.LayTotalSales = LayTotalSales;
    this.ReportProductListView = ReportProductListView;
    this.cardviewId = cardviewId;
    this.productLinearLayoutId = productLinearLayoutId;
    this.txttotalPriceAmount = txttotalPriceAmount;
    this.txttotalQty = txttotalQty;
  }

  @NonNull
  public static FragmentReportProductBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_report_product, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentReportProductBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentReportProductBinding>inflateInternal(inflater, R.layout.fragment_report_product, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentReportProductBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_report_product, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentReportProductBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentReportProductBinding>inflateInternal(inflater, R.layout.fragment_report_product, null, false, component);
  }

  public static FragmentReportProductBinding bind(@NonNull View view) {
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
  public static FragmentReportProductBinding bind(@NonNull View view, @Nullable Object component) {
    return (FragmentReportProductBinding)bind(component, view, R.layout.fragment_report_product);
  }
}
