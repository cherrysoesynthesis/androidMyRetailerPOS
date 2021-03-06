// Generated by data binding compiler. Do not edit!
package com.dcs.myretailer.app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.dcs.myretailer.app.R;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivityStockAdjustmentBinding extends ViewDataBinding {
  @NonNull
  public final LinearLayout LayStockInDate;

  @NonNull
  public final Button btnAddAdjStock;

  @NonNull
  public final TextInputLayout editTextInputLayProductStockAdjDate;

  @NonNull
  public final EditText editTextProductStockAdjDate;

  @NonNull
  public final EditText editTextProductStockAdjQty;

  @NonNull
  public final ImageView imgTockAdjust;

  @NonNull
  public final LinearLayout layStockAdjBtnAdd;

  @NonNull
  public final LinearLayout layStockInOverAll;

  @NonNull
  public final LinearLayout layStockQty;

  @NonNull
  public final LinearLayout layStockadjust;

  @NonNull
  public final LinearLayout layadj;

  protected ActivityStockAdjustmentBinding(Object _bindingComponent, View _root,
      int _localFieldCount, LinearLayout LayStockInDate, Button btnAddAdjStock,
      TextInputLayout editTextInputLayProductStockAdjDate, EditText editTextProductStockAdjDate,
      EditText editTextProductStockAdjQty, ImageView imgTockAdjust, LinearLayout layStockAdjBtnAdd,
      LinearLayout layStockInOverAll, LinearLayout layStockQty, LinearLayout layStockadjust,
      LinearLayout layadj) {
    super(_bindingComponent, _root, _localFieldCount);
    this.LayStockInDate = LayStockInDate;
    this.btnAddAdjStock = btnAddAdjStock;
    this.editTextInputLayProductStockAdjDate = editTextInputLayProductStockAdjDate;
    this.editTextProductStockAdjDate = editTextProductStockAdjDate;
    this.editTextProductStockAdjQty = editTextProductStockAdjQty;
    this.imgTockAdjust = imgTockAdjust;
    this.layStockAdjBtnAdd = layStockAdjBtnAdd;
    this.layStockInOverAll = layStockInOverAll;
    this.layStockQty = layStockQty;
    this.layStockadjust = layStockadjust;
    this.layadj = layadj;
  }

  @NonNull
  public static ActivityStockAdjustmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_stock_adjustment, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityStockAdjustmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityStockAdjustmentBinding>inflateInternal(inflater, R.layout.activity_stock_adjustment, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityStockAdjustmentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_stock_adjustment, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityStockAdjustmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityStockAdjustmentBinding>inflateInternal(inflater, R.layout.activity_stock_adjustment, null, false, component);
  }

  public static ActivityStockAdjustmentBinding bind(@NonNull View view) {
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
  public static ActivityStockAdjustmentBinding bind(@NonNull View view,
      @Nullable Object component) {
    return (ActivityStockAdjustmentBinding)bind(component, view, R.layout.activity_stock_adjustment);
  }
}
