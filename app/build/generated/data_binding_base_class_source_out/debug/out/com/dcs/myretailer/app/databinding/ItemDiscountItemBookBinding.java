// Generated by data binding compiler. Do not edit!
package com.dcs.myretailer.app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.dcs.myretailer.app.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ItemDiscountItemBookBinding extends ViewDataBinding {
  @NonNull
  public final CardView cardviewItemDiscountPercentageId;

  @NonNull
  public final TextView itemDiscountPercentage;

  @NonNull
  public final TextView itemDiscountPercentageName;

  protected ItemDiscountItemBookBinding(Object _bindingComponent, View _root, int _localFieldCount,
      CardView cardviewItemDiscountPercentageId, TextView itemDiscountPercentage,
      TextView itemDiscountPercentageName) {
    super(_bindingComponent, _root, _localFieldCount);
    this.cardviewItemDiscountPercentageId = cardviewItemDiscountPercentageId;
    this.itemDiscountPercentage = itemDiscountPercentage;
    this.itemDiscountPercentageName = itemDiscountPercentageName;
  }

  @NonNull
  public static ItemDiscountItemBookBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.item_discount_item_book, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ItemDiscountItemBookBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ItemDiscountItemBookBinding>inflateInternal(inflater, R.layout.item_discount_item_book, root, attachToRoot, component);
  }

  @NonNull
  public static ItemDiscountItemBookBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.item_discount_item_book, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ItemDiscountItemBookBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ItemDiscountItemBookBinding>inflateInternal(inflater, R.layout.item_discount_item_book, null, false, component);
  }

  public static ItemDiscountItemBookBinding bind(@NonNull View view) {
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
  public static ItemDiscountItemBookBinding bind(@NonNull View view, @Nullable Object component) {
    return (ItemDiscountItemBookBinding)bind(component, view, R.layout.item_discount_item_book);
  }
}