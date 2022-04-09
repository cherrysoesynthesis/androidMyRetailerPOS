// Generated by data binding compiler. Do not edit!
package com.dcs.myretailer.app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.dcs.myretailer.app.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivityCashLayoutBinding extends ViewDataBinding {
  @NonNull
  public final LinearLayout Laybtnaccept;

  @NonNull
  public final LinearLayout Layshowamt;

  @NonNull
  public final Button btn10;

  @NonNull
  public final Button btn100;

  @NonNull
  public final Button btn15;

  @NonNull
  public final Button btn20;

  @NonNull
  public final Button btn25;

  @NonNull
  public final Button btn30;

  @NonNull
  public final Button btn50;

  @NonNull
  public final Button btn500;

  @NonNull
  public final Button btnAccept;

  @NonNull
  public final Button btnFixed;

  @NonNull
  public final EditText cashCollected;

  @NonNull
  public final TextView changeDue;

  @NonNull
  public final LinearLayout layLine;

  @NonNull
  public final LinearLayout layOverAll;

  @NonNull
  public final LinearLayout laycollected;

  @NonNull
  public final LinearLayout layshowall;

  @NonNull
  public final LinearLayout layshowcollected;

  @NonNull
  public final TextView total;

  protected ActivityCashLayoutBinding(Object _bindingComponent, View _root, int _localFieldCount,
      LinearLayout Laybtnaccept, LinearLayout Layshowamt, Button btn10, Button btn100, Button btn15,
      Button btn20, Button btn25, Button btn30, Button btn50, Button btn500, Button btnAccept,
      Button btnFixed, EditText cashCollected, TextView changeDue, LinearLayout layLine,
      LinearLayout layOverAll, LinearLayout laycollected, LinearLayout layshowall,
      LinearLayout layshowcollected, TextView total) {
    super(_bindingComponent, _root, _localFieldCount);
    this.Laybtnaccept = Laybtnaccept;
    this.Layshowamt = Layshowamt;
    this.btn10 = btn10;
    this.btn100 = btn100;
    this.btn15 = btn15;
    this.btn20 = btn20;
    this.btn25 = btn25;
    this.btn30 = btn30;
    this.btn50 = btn50;
    this.btn500 = btn500;
    this.btnAccept = btnAccept;
    this.btnFixed = btnFixed;
    this.cashCollected = cashCollected;
    this.changeDue = changeDue;
    this.layLine = layLine;
    this.layOverAll = layOverAll;
    this.laycollected = laycollected;
    this.layshowall = layshowall;
    this.layshowcollected = layshowcollected;
    this.total = total;
  }

  @NonNull
  public static ActivityCashLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_cash_layout, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityCashLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityCashLayoutBinding>inflateInternal(inflater, R.layout.activity_cash_layout, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityCashLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_cash_layout, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityCashLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityCashLayoutBinding>inflateInternal(inflater, R.layout.activity_cash_layout, null, false, component);
  }

  public static ActivityCashLayoutBinding bind(@NonNull View view) {
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
  public static ActivityCashLayoutBinding bind(@NonNull View view, @Nullable Object component) {
    return (ActivityCashLayoutBinding)bind(component, view, R.layout.activity_cash_layout);
  }
}