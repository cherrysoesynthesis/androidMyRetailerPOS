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
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.dcs.myretailer.app.ModifierData;
import com.dcs.myretailer.app.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivityAddModifierBinding extends ViewDataBinding {
  @NonNull
  public final LinearLayout LayBtn;

  @NonNull
  public final LinearLayout LayModiName;

  @NonNull
  public final LinearLayout LayModiPrice;

  @NonNull
  public final ScrollView ScrollView01;

  @NonNull
  public final Button btnAddModifier;

  @NonNull
  public final Button btnDeleteModifier;

  @NonNull
  public final EditText editTextPermissionModifierName;

  @NonNull
  public final EditText editTextPermissionModifierPrice;

  @NonNull
  public final LinearLayout layOverAll;

  @Bindable
  protected ModifierData mModifierData;

  protected ActivityAddModifierBinding(Object _bindingComponent, View _root, int _localFieldCount,
      LinearLayout LayBtn, LinearLayout LayModiName, LinearLayout LayModiPrice,
      ScrollView ScrollView01, Button btnAddModifier, Button btnDeleteModifier,
      EditText editTextPermissionModifierName, EditText editTextPermissionModifierPrice,
      LinearLayout layOverAll) {
    super(_bindingComponent, _root, _localFieldCount);
    this.LayBtn = LayBtn;
    this.LayModiName = LayModiName;
    this.LayModiPrice = LayModiPrice;
    this.ScrollView01 = ScrollView01;
    this.btnAddModifier = btnAddModifier;
    this.btnDeleteModifier = btnDeleteModifier;
    this.editTextPermissionModifierName = editTextPermissionModifierName;
    this.editTextPermissionModifierPrice = editTextPermissionModifierPrice;
    this.layOverAll = layOverAll;
  }

  public abstract void setModifierData(@Nullable ModifierData ModifierData);

  @Nullable
  public ModifierData getModifierData() {
    return mModifierData;
  }

  @NonNull
  public static ActivityAddModifierBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_add_modifier, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityAddModifierBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityAddModifierBinding>inflateInternal(inflater, R.layout.activity_add_modifier, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityAddModifierBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_add_modifier, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityAddModifierBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityAddModifierBinding>inflateInternal(inflater, R.layout.activity_add_modifier, null, false, component);
  }

  public static ActivityAddModifierBinding bind(@NonNull View view) {
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
  public static ActivityAddModifierBinding bind(@NonNull View view, @Nullable Object component) {
    return (ActivityAddModifierBinding)bind(component, view, R.layout.activity_add_modifier);
  }
}
