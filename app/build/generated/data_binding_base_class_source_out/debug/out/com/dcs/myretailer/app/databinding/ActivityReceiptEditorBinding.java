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
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.dcs.myretailer.app.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivityReceiptEditorBinding extends ViewDataBinding {
  @NonNull
  public final LinearLayout Laybtn;

  @NonNull
  public final LinearLayout Layprintlogo;

  @NonNull
  public final ScrollView ScrollView01;

  @NonNull
  public final Button btnAdd;

  @NonNull
  public final Button btnAddFooter;

  @NonNull
  public final Button btnAddReceiptEditor;

  @NonNull
  public final Button btnDelete;

  @NonNull
  public final Button btnDeleteFooter;

  @NonNull
  public final AppCompatCheckBox chkPrintLogo;

  @NonNull
  public final LinearLayout choosePhotoId;

  @NonNull
  public final EditText editSelectFontAssets;

  @NonNull
  public final EditText editTextReceiptEditorFooter;

  @NonNull
  public final EditText editTextReceiptEditorHeader;

  @NonNull
  public final LinearLayout generalSettingLayout;

  @NonNull
  public final ImageView imgChooseFolder;

  @NonNull
  public final LinearLayout imgZclose;

  @NonNull
  public final LinearLayout lay1;

  @NonNull
  public final LinearLayout lay2;

  @NonNull
  public final LinearLayout layFontAssets;

  @NonNull
  public final LinearLayout layOverAll;

  @NonNull
  public final LinearLayout linearFooter;

  @NonNull
  public final LinearLayout linearLayoutAddHeader;

  @NonNull
  public final ImageView takeCameraId;

  protected ActivityReceiptEditorBinding(Object _bindingComponent, View _root, int _localFieldCount,
      LinearLayout Laybtn, LinearLayout Layprintlogo, ScrollView ScrollView01, Button btnAdd,
      Button btnAddFooter, Button btnAddReceiptEditor, Button btnDelete, Button btnDeleteFooter,
      AppCompatCheckBox chkPrintLogo, LinearLayout choosePhotoId, EditText editSelectFontAssets,
      EditText editTextReceiptEditorFooter, EditText editTextReceiptEditorHeader,
      LinearLayout generalSettingLayout, ImageView imgChooseFolder, LinearLayout imgZclose,
      LinearLayout lay1, LinearLayout lay2, LinearLayout layFontAssets, LinearLayout layOverAll,
      LinearLayout linearFooter, LinearLayout linearLayoutAddHeader, ImageView takeCameraId) {
    super(_bindingComponent, _root, _localFieldCount);
    this.Laybtn = Laybtn;
    this.Layprintlogo = Layprintlogo;
    this.ScrollView01 = ScrollView01;
    this.btnAdd = btnAdd;
    this.btnAddFooter = btnAddFooter;
    this.btnAddReceiptEditor = btnAddReceiptEditor;
    this.btnDelete = btnDelete;
    this.btnDeleteFooter = btnDeleteFooter;
    this.chkPrintLogo = chkPrintLogo;
    this.choosePhotoId = choosePhotoId;
    this.editSelectFontAssets = editSelectFontAssets;
    this.editTextReceiptEditorFooter = editTextReceiptEditorFooter;
    this.editTextReceiptEditorHeader = editTextReceiptEditorHeader;
    this.generalSettingLayout = generalSettingLayout;
    this.imgChooseFolder = imgChooseFolder;
    this.imgZclose = imgZclose;
    this.lay1 = lay1;
    this.lay2 = lay2;
    this.layFontAssets = layFontAssets;
    this.layOverAll = layOverAll;
    this.linearFooter = linearFooter;
    this.linearLayoutAddHeader = linearLayoutAddHeader;
    this.takeCameraId = takeCameraId;
  }

  @NonNull
  public static ActivityReceiptEditorBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_receipt_editor, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityReceiptEditorBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityReceiptEditorBinding>inflateInternal(inflater, R.layout.activity_receipt_editor, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityReceiptEditorBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_receipt_editor, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityReceiptEditorBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityReceiptEditorBinding>inflateInternal(inflater, R.layout.activity_receipt_editor, null, false, component);
  }

  public static ActivityReceiptEditorBinding bind(@NonNull View view) {
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
  public static ActivityReceiptEditorBinding bind(@NonNull View view, @Nullable Object component) {
    return (ActivityReceiptEditorBinding)bind(component, view, R.layout.activity_receipt_editor);
  }
}
