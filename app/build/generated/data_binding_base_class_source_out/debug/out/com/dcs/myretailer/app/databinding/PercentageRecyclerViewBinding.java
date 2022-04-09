// Generated by data binding compiler. Do not edit!
package com.dcs.myretailer.app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.dcs.myretailer.app.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class PercentageRecyclerViewBinding extends ViewDataBinding {
  @NonNull
  public final LinearLayout LayAmount;

  @NonNull
  public final ScrollView ScrollView01;

  @NonNull
  public final Button btnApply;

  @NonNull
  public final EditText editTextDiscountAmount;

  @NonNull
  public final RecyclerView percentageRecyclerviewId;

  protected PercentageRecyclerViewBinding(Object _bindingComponent, View _root,
      int _localFieldCount, LinearLayout LayAmount, ScrollView ScrollView01, Button btnApply,
      EditText editTextDiscountAmount, RecyclerView percentageRecyclerviewId) {
    super(_bindingComponent, _root, _localFieldCount);
    this.LayAmount = LayAmount;
    this.ScrollView01 = ScrollView01;
    this.btnApply = btnApply;
    this.editTextDiscountAmount = editTextDiscountAmount;
    this.percentageRecyclerviewId = percentageRecyclerviewId;
  }

  @NonNull
  public static PercentageRecyclerViewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.percentage_recycler_view, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static PercentageRecyclerViewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<PercentageRecyclerViewBinding>inflateInternal(inflater, R.layout.percentage_recycler_view, root, attachToRoot, component);
  }

  @NonNull
  public static PercentageRecyclerViewBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.percentage_recycler_view, null, false, component)
   */
  @NonNull
  @Deprecated
  public static PercentageRecyclerViewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<PercentageRecyclerViewBinding>inflateInternal(inflater, R.layout.percentage_recycler_view, null, false, component);
  }

  public static PercentageRecyclerViewBinding bind(@NonNull View view) {
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
  public static PercentageRecyclerViewBinding bind(@NonNull View view, @Nullable Object component) {
    return (PercentageRecyclerViewBinding)bind(component, view, R.layout.percentage_recycler_view);
  }
}