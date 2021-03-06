// Generated by data binding compiler. Do not edit!
package com.dcs.myretailer.app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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

public abstract class ActivityStockAgentListviewBinding extends ViewDataBinding {
  @NonNull
  public final ImageView btnSubmitStockTake;

  @NonNull
  public final CardView cardviewId;

  @NonNull
  public final LinearLayout layall;

  @NonNull
  public final TextView stockAgentPluActualQty;

  @NonNull
  public final TextView stockAgentPluAdjustmentQty;

  @NonNull
  public final EditText stockAgentPluCountQty;

  @NonNull
  public final TextView stockAgentPluName;

  protected ActivityStockAgentListviewBinding(Object _bindingComponent, View _root,
      int _localFieldCount, ImageView btnSubmitStockTake, CardView cardviewId, LinearLayout layall,
      TextView stockAgentPluActualQty, TextView stockAgentPluAdjustmentQty,
      EditText stockAgentPluCountQty, TextView stockAgentPluName) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnSubmitStockTake = btnSubmitStockTake;
    this.cardviewId = cardviewId;
    this.layall = layall;
    this.stockAgentPluActualQty = stockAgentPluActualQty;
    this.stockAgentPluAdjustmentQty = stockAgentPluAdjustmentQty;
    this.stockAgentPluCountQty = stockAgentPluCountQty;
    this.stockAgentPluName = stockAgentPluName;
  }

  @NonNull
  public static ActivityStockAgentListviewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_stock_agent_listview, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityStockAgentListviewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityStockAgentListviewBinding>inflateInternal(inflater, R.layout.activity_stock_agent_listview, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityStockAgentListviewBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_stock_agent_listview, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityStockAgentListviewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityStockAgentListviewBinding>inflateInternal(inflater, R.layout.activity_stock_agent_listview, null, false, component);
  }

  public static ActivityStockAgentListviewBinding bind(@NonNull View view) {
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
  public static ActivityStockAgentListviewBinding bind(@NonNull View view,
      @Nullable Object component) {
    return (ActivityStockAgentListviewBinding)bind(component, view, R.layout.activity_stock_agent_listview);
  }
}
