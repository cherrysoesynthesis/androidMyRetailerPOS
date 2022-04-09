// Generated by data binding compiler. Do not edit!
package com.dcs.myretailer.app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.dcs.myretailer.app.FlatButton;
import com.dcs.myretailer.app.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivityMapSetupBinding extends ViewDataBinding {
  @NonNull
  public final Button btnBack;

  @NonNull
  public final Button btnBack1;

  @NonNull
  public final FlatButton btnColor;

  @NonNull
  public final FlatButton btnColor1;

  @NonNull
  public final Button btnDel;

  @NonNull
  public final Button btnDel1;

  @NonNull
  public final Button btnEdit;

  @NonNull
  public final Button btnEdit1;

  @NonNull
  public final FlatButton btnImg;

  @NonNull
  public final Button btnSave;

  @NonNull
  public final Button btnSave1;

  @NonNull
  public final LinearLayout chooseColor;

  @NonNull
  public final EditText editTextProductName1;

  @NonNull
  public final EditText etName;

  @NonNull
  public final EditText etName1;

  @NonNull
  public final LinearLayout layOverAll;

  @NonNull
  public final TextView lbl1;

  @NonNull
  public final TextView lbl2;

  @NonNull
  public final TextView lbl3;

  @NonNull
  public final Spinner spnList;

  protected ActivityMapSetupBinding(Object _bindingComponent, View _root, int _localFieldCount,
      Button btnBack, Button btnBack1, FlatButton btnColor, FlatButton btnColor1, Button btnDel,
      Button btnDel1, Button btnEdit, Button btnEdit1, FlatButton btnImg, Button btnSave,
      Button btnSave1, LinearLayout chooseColor, EditText editTextProductName1, EditText etName,
      EditText etName1, LinearLayout layOverAll, TextView lbl1, TextView lbl2, TextView lbl3,
      Spinner spnList) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnBack = btnBack;
    this.btnBack1 = btnBack1;
    this.btnColor = btnColor;
    this.btnColor1 = btnColor1;
    this.btnDel = btnDel;
    this.btnDel1 = btnDel1;
    this.btnEdit = btnEdit;
    this.btnEdit1 = btnEdit1;
    this.btnImg = btnImg;
    this.btnSave = btnSave;
    this.btnSave1 = btnSave1;
    this.chooseColor = chooseColor;
    this.editTextProductName1 = editTextProductName1;
    this.etName = etName;
    this.etName1 = etName1;
    this.layOverAll = layOverAll;
    this.lbl1 = lbl1;
    this.lbl2 = lbl2;
    this.lbl3 = lbl3;
    this.spnList = spnList;
  }

  @NonNull
  public static ActivityMapSetupBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_map_setup, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityMapSetupBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityMapSetupBinding>inflateInternal(inflater, R.layout.activity_map_setup, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityMapSetupBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_map_setup, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityMapSetupBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityMapSetupBinding>inflateInternal(inflater, R.layout.activity_map_setup, null, false, component);
  }

  public static ActivityMapSetupBinding bind(@NonNull View view) {
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
  public static ActivityMapSetupBinding bind(@NonNull View view, @Nullable Object component) {
    return (ActivityMapSetupBinding)bind(component, view, R.layout.activity_map_setup);
  }
}
