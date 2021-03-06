// Generated by data binding compiler. Do not edit!
package com.dcs.myretailer.app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.dcs.myretailer.app.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivityAddNewCategoryBinding extends ViewDataBinding {
  @NonNull
  public final ScrollView ScrollView01;

  @NonNull
  public final Button btnAddCategory;

  @NonNull
  public final Button btnDeleteCategory;

  @NonNull
  public final LinearLayout choosePhotoId;

  @NonNull
  public final EditText editTextCategoryName;

  @NonNull
  public final ImageView imgChooseFolder;

  @NonNull
  public final LinearLayout layCatename;

  @NonNull
  public final LinearLayout layOverAll;

  @NonNull
  public final LinearLayout laycategoryId;

  @NonNull
  public final ImageView takeCameraId;

  protected ActivityAddNewCategoryBinding(Object _bindingComponent, View _root,
      int _localFieldCount, ScrollView ScrollView01, Button btnAddCategory,
      Button btnDeleteCategory, LinearLayout choosePhotoId, EditText editTextCategoryName,
      ImageView imgChooseFolder, LinearLayout layCatename, LinearLayout layOverAll,
      LinearLayout laycategoryId, ImageView takeCameraId) {
    super(_bindingComponent, _root, _localFieldCount);
    this.ScrollView01 = ScrollView01;
    this.btnAddCategory = btnAddCategory;
    this.btnDeleteCategory = btnDeleteCategory;
    this.choosePhotoId = choosePhotoId;
    this.editTextCategoryName = editTextCategoryName;
    this.imgChooseFolder = imgChooseFolder;
    this.layCatename = layCatename;
    this.layOverAll = layOverAll;
    this.laycategoryId = laycategoryId;
    this.takeCameraId = takeCameraId;
  }

  @NonNull
  public static ActivityAddNewCategoryBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_add_new_category, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityAddNewCategoryBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityAddNewCategoryBinding>inflateInternal(inflater, R.layout.activity_add_new_category, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityAddNewCategoryBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_add_new_category, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityAddNewCategoryBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityAddNewCategoryBinding>inflateInternal(inflater, R.layout.activity_add_new_category, null, false, component);
  }

  public static ActivityAddNewCategoryBinding bind(@NonNull View view) {
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
  public static ActivityAddNewCategoryBinding bind(@NonNull View view, @Nullable Object component) {
    return (ActivityAddNewCategoryBinding)bind(component, view, R.layout.activity_add_new_category);
  }
}
