// Generated by data binding compiler. Do not edit!
package com.dcs.myretailer.app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.dcs.myretailer.app.R;
import com.github.mikephil.charting.charts.BarChart;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class FragmentReportOverallBinding extends ViewDataBinding {
  @NonNull
  public final BarChart BarChart;

  @NonNull
  public final LinearLayout LayTotalSales;

  @NonNull
  public final ListView ReportOverallSaleSummaryListView;

  @NonNull
  public final ListView ReportOverallTransactionListView;

  @NonNull
  public final ScrollView ScrollView01;

  @NonNull
  public final TextView totalNetSales;

  @NonNull
  public final TextView txttotalPriceAmount;

  @NonNull
  public final TextView txttotalQty;

  protected FragmentReportOverallBinding(Object _bindingComponent, View _root, int _localFieldCount,
      BarChart BarChart, LinearLayout LayTotalSales, ListView ReportOverallSaleSummaryListView,
      ListView ReportOverallTransactionListView, ScrollView ScrollView01, TextView totalNetSales,
      TextView txttotalPriceAmount, TextView txttotalQty) {
    super(_bindingComponent, _root, _localFieldCount);
    this.BarChart = BarChart;
    this.LayTotalSales = LayTotalSales;
    this.ReportOverallSaleSummaryListView = ReportOverallSaleSummaryListView;
    this.ReportOverallTransactionListView = ReportOverallTransactionListView;
    this.ScrollView01 = ScrollView01;
    this.totalNetSales = totalNetSales;
    this.txttotalPriceAmount = txttotalPriceAmount;
    this.txttotalQty = txttotalQty;
  }

  @NonNull
  public static FragmentReportOverallBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_report_overall, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentReportOverallBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentReportOverallBinding>inflateInternal(inflater, R.layout.fragment_report_overall, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentReportOverallBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_report_overall, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentReportOverallBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentReportOverallBinding>inflateInternal(inflater, R.layout.fragment_report_overall, null, false, component);
  }

  public static FragmentReportOverallBinding bind(@NonNull View view) {
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
  public static FragmentReportOverallBinding bind(@NonNull View view, @Nullable Object component) {
    return (FragmentReportOverallBinding)bind(component, view, R.layout.fragment_report_overall);
  }
}
