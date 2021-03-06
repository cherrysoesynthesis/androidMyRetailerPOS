// Generated by data binding compiler. Do not edit!
package com.dcs.myretailer.app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.dcs.myretailer.app.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class FragmentAddQuickProductSheetDialogBinding extends ViewDataBinding {
  @NonNull
  public final Button btnAdd;

  @NonNull
  public final ImageView closeCashierFloatAmt;

  @NonNull
  public final EditText editTextQuickEditName;

  @NonNull
  public final EditText editTextQuickEditPrice;

  @NonNull
  public final RelativeLayout erase;

  @NonNull
  public final LinearLayout linearClose;

  @NonNull
  public final RelativeLayout rel;

  @NonNull
  public final TextView txtHeader;

  @NonNull
  public final TextView txtOldValue;

  protected FragmentAddQuickProductSheetDialogBinding(Object _bindingComponent, View _root,
      int _localFieldCount, Button btnAdd, ImageView closeCashierFloatAmt,
      EditText editTextQuickEditName, EditText editTextQuickEditPrice, RelativeLayout erase,
      LinearLayout linearClose, RelativeLayout rel, TextView txtHeader, TextView txtOldValue) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnAdd = btnAdd;
    this.closeCashierFloatAmt = closeCashierFloatAmt;
    this.editTextQuickEditName = editTextQuickEditName;
    this.editTextQuickEditPrice = editTextQuickEditPrice;
    this.erase = erase;
    this.linearClose = linearClose;
    this.rel = rel;
    this.txtHeader = txtHeader;
    this.txtOldValue = txtOldValue;
  }

  @NonNull
  public static FragmentAddQuickProductSheetDialogBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_add_quick_product_sheet_dialog, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentAddQuickProductSheetDialogBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentAddQuickProductSheetDialogBinding>inflateInternal(inflater, R.layout.fragment_add_quick_product_sheet_dialog, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentAddQuickProductSheetDialogBinding inflate(
      @NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_add_quick_product_sheet_dialog, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentAddQuickProductSheetDialogBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentAddQuickProductSheetDialogBinding>inflateInternal(inflater, R.layout.fragment_add_quick_product_sheet_dialog, null, false, component);
  }

  public static FragmentAddQuickProductSheetDialogBinding bind(@NonNull View view) {
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
  public static FragmentAddQuickProductSheetDialogBinding bind(@NonNull View view,
      @Nullable Object component) {
    return (FragmentAddQuickProductSheetDialogBinding)bind(component, view, R.layout.fragment_add_quick_product_sheet_dialog);
  }
}
