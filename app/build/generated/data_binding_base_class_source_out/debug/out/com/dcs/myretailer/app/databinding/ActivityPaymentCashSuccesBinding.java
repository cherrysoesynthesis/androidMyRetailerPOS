// Generated by data binding compiler. Do not edit!
package com.dcs.myretailer.app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.dcs.myretailer.app.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivityPaymentCashSuccesBinding extends ViewDataBinding {
  @NonNull
  public final Button btnPaymentCash;

  @NonNull
  public final TextView btnPaymentTypeName;

  @NonNull
  public final TextView btnPaymentTypeSuccessFail;

  @NonNull
  public final TextView btnPaymentTypeValue;

  @NonNull
  public final LinearLayout changeAmtLinearlayout;

  @NonNull
  public final ImageView img;

  @NonNull
  public final LinearLayout layOverAll;

  @NonNull
  public final TextView paymentSuccessChangeAmount;

  @NonNull
  public final TextView paymentSuccessPaymentAmount;

  @NonNull
  public final TextView paymentSuccessTotalAmount;

  protected ActivityPaymentCashSuccesBinding(Object _bindingComponent, View _root,
      int _localFieldCount, Button btnPaymentCash, TextView btnPaymentTypeName,
      TextView btnPaymentTypeSuccessFail, TextView btnPaymentTypeValue,
      LinearLayout changeAmtLinearlayout, ImageView img, LinearLayout layOverAll,
      TextView paymentSuccessChangeAmount, TextView paymentSuccessPaymentAmount,
      TextView paymentSuccessTotalAmount) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnPaymentCash = btnPaymentCash;
    this.btnPaymentTypeName = btnPaymentTypeName;
    this.btnPaymentTypeSuccessFail = btnPaymentTypeSuccessFail;
    this.btnPaymentTypeValue = btnPaymentTypeValue;
    this.changeAmtLinearlayout = changeAmtLinearlayout;
    this.img = img;
    this.layOverAll = layOverAll;
    this.paymentSuccessChangeAmount = paymentSuccessChangeAmount;
    this.paymentSuccessPaymentAmount = paymentSuccessPaymentAmount;
    this.paymentSuccessTotalAmount = paymentSuccessTotalAmount;
  }

  @NonNull
  public static ActivityPaymentCashSuccesBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_payment_cash_succes, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityPaymentCashSuccesBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityPaymentCashSuccesBinding>inflateInternal(inflater, R.layout.activity_payment_cash_succes, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityPaymentCashSuccesBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_payment_cash_succes, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityPaymentCashSuccesBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityPaymentCashSuccesBinding>inflateInternal(inflater, R.layout.activity_payment_cash_succes, null, false, component);
  }

  public static ActivityPaymentCashSuccesBinding bind(@NonNull View view) {
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
  public static ActivityPaymentCashSuccesBinding bind(@NonNull View view,
      @Nullable Object component) {
    return (ActivityPaymentCashSuccesBinding)bind(component, view, R.layout.activity_payment_cash_succes);
  }
}
