// Generated by data binding compiler. Do not edit!
package com.dcs.myretailer.app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.dcs.myretailer.app.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivityAddNewConfigurationBinding extends ViewDataBinding {
  @NonNull
  public final LinearLayout LayActivate;

  @NonNull
  public final LinearLayout LayBankName;

  @NonNull
  public final LinearLayout LayBtnSave;

  @NonNull
  public final Button btnAdd;

  @NonNull
  public final Button btnDelete;

  @NonNull
  public final AppCompatCheckBox chkActivate;

  @NonNull
  public final EditText editTextName;

  protected ActivityAddNewConfigurationBinding(Object _bindingComponent, View _root,
      int _localFieldCount, LinearLayout LayActivate, LinearLayout LayBankName,
      LinearLayout LayBtnSave, Button btnAdd, Button btnDelete, AppCompatCheckBox chkActivate,
      EditText editTextName) {
    super(_bindingComponent, _root, _localFieldCount);
    this.LayActivate = LayActivate;
    this.LayBankName = LayBankName;
    this.LayBtnSave = LayBtnSave;
    this.btnAdd = btnAdd;
    this.btnDelete = btnDelete;
    this.chkActivate = chkActivate;
    this.editTextName = editTextName;
  }

  @NonNull
  public static ActivityAddNewConfigurationBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_add_new_configuration, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityAddNewConfigurationBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityAddNewConfigurationBinding>inflateInternal(inflater, R.layout.activity_add_new_configuration, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityAddNewConfigurationBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_add_new_configuration, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityAddNewConfigurationBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityAddNewConfigurationBinding>inflateInternal(inflater, R.layout.activity_add_new_configuration, null, false, component);
  }

  public static ActivityAddNewConfigurationBinding bind(@NonNull View view) {
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
  public static ActivityAddNewConfigurationBinding bind(@NonNull View view,
      @Nullable Object component) {
    return (ActivityAddNewConfigurationBinding)bind(component, view, R.layout.activity_add_new_configuration);
  }
}