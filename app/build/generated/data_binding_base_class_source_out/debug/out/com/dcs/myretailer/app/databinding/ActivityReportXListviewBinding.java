// Generated by data binding compiler. Do not edit!
package com.dcs.myretailer.app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.dcs.myretailer.app.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivityReportXListviewBinding extends ViewDataBinding {
  @NonNull
  public final TextView reportXHeader;

  @NonNull
  public final TextView reportXName;

  @NonNull
  public final TextView reportXPrice;

  protected ActivityReportXListviewBinding(Object _bindingComponent, View _root,
      int _localFieldCount, TextView reportXHeader, TextView reportXName, TextView reportXPrice) {
    super(_bindingComponent, _root, _localFieldCount);
    this.reportXHeader = reportXHeader;
    this.reportXName = reportXName;
    this.reportXPrice = reportXPrice;
  }

  @NonNull
  public static ActivityReportXListviewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_report_x_listview, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityReportXListviewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityReportXListviewBinding>inflateInternal(inflater, R.layout.activity_report_x_listview, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityReportXListviewBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_report_x_listview, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityReportXListviewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityReportXListviewBinding>inflateInternal(inflater, R.layout.activity_report_x_listview, null, false, component);
  }

  public static ActivityReportXListviewBinding bind(@NonNull View view) {
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
  public static ActivityReportXListviewBinding bind(@NonNull View view,
      @Nullable Object component) {
    return (ActivityReportXListviewBinding)bind(component, view, R.layout.activity_report_x_listview);
  }
}
