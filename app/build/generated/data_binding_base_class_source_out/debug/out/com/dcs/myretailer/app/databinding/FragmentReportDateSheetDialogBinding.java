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
import java.lang.Deprecated;
import java.lang.Object;

public abstract class FragmentReportDateSheetDialogBinding extends ViewDataBinding {
  @NonNull
  public final LinearLayout Lay1;

  @NonNull
  public final LinearLayout LayEnding;

  @NonNull
  public final LinearLayout LayPrevious;

  @NonNull
  public final LinearLayout LayStarting;

  @NonNull
  public final Button btnApplyDateRange;

  @NonNull
  public final ImageView btnType;

  @NonNull
  public final ImageView closeCashierFloatAmt;

  @NonNull
  public final EditText editTextDatecount;

  @NonNull
  public final EditText editTextEnding;

  @NonNull
  public final EditText editTextStarting;

  @NonNull
  public final ImageView imgTextEnding;

  @NonNull
  public final ImageView imgTextStarting;

  @NonNull
  public final LinearLayout linearClose;

  @NonNull
  public final EditText typeName;

  protected FragmentReportDateSheetDialogBinding(Object _bindingComponent, View _root,
      int _localFieldCount, LinearLayout Lay1, LinearLayout LayEnding, LinearLayout LayPrevious,
      LinearLayout LayStarting, Button btnApplyDateRange, ImageView btnType,
      ImageView closeCashierFloatAmt, EditText editTextDatecount, EditText editTextEnding,
      EditText editTextStarting, ImageView imgTextEnding, ImageView imgTextStarting,
      LinearLayout linearClose, EditText typeName) {
    super(_bindingComponent, _root, _localFieldCount);
    this.Lay1 = Lay1;
    this.LayEnding = LayEnding;
    this.LayPrevious = LayPrevious;
    this.LayStarting = LayStarting;
    this.btnApplyDateRange = btnApplyDateRange;
    this.btnType = btnType;
    this.closeCashierFloatAmt = closeCashierFloatAmt;
    this.editTextDatecount = editTextDatecount;
    this.editTextEnding = editTextEnding;
    this.editTextStarting = editTextStarting;
    this.imgTextEnding = imgTextEnding;
    this.imgTextStarting = imgTextStarting;
    this.linearClose = linearClose;
    this.typeName = typeName;
  }

  @NonNull
  public static FragmentReportDateSheetDialogBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_report_date_sheet_dialog, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentReportDateSheetDialogBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentReportDateSheetDialogBinding>inflateInternal(inflater, R.layout.fragment_report_date_sheet_dialog, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentReportDateSheetDialogBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_report_date_sheet_dialog, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentReportDateSheetDialogBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentReportDateSheetDialogBinding>inflateInternal(inflater, R.layout.fragment_report_date_sheet_dialog, null, false, component);
  }

  public static FragmentReportDateSheetDialogBinding bind(@NonNull View view) {
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
  public static FragmentReportDateSheetDialogBinding bind(@NonNull View view,
      @Nullable Object component) {
    return (FragmentReportDateSheetDialogBinding)bind(component, view, R.layout.fragment_report_date_sheet_dialog);
  }
}
