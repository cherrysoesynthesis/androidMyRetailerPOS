// Generated by data binding compiler. Do not edit!
package com.dcs.myretailer.app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.dcs.myretailer.app.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivitySampleBinding extends ViewDataBinding {
  @NonNull
  public final Button btnLogin;

  @NonNull
  public final EditText editLoginName;

  @NonNull
  public final LinearLayout first;

  @NonNull
  public final ImageView imgLoginName;

  @NonNull
  public final LinearLayout layBtnLogin;

  @NonNull
  public final LinearLayout layOverAll;

  @NonNull
  public final LinearLayout layOverall;

  @NonNull
  public final TextView pinNo;

  @NonNull
  public final ImageView profileImage;

  @NonNull
  public final TextView profileName;

  @NonNull
  public final Spinner spinner;

  protected ActivitySampleBinding(Object _bindingComponent, View _root, int _localFieldCount,
      Button btnLogin, EditText editLoginName, LinearLayout first, ImageView imgLoginName,
      LinearLayout layBtnLogin, LinearLayout layOverAll, LinearLayout layOverall, TextView pinNo,
      ImageView profileImage, TextView profileName, Spinner spinner) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnLogin = btnLogin;
    this.editLoginName = editLoginName;
    this.first = first;
    this.imgLoginName = imgLoginName;
    this.layBtnLogin = layBtnLogin;
    this.layOverAll = layOverAll;
    this.layOverall = layOverall;
    this.pinNo = pinNo;
    this.profileImage = profileImage;
    this.profileName = profileName;
    this.spinner = spinner;
  }

  @NonNull
  public static ActivitySampleBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_sample, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivitySampleBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivitySampleBinding>inflateInternal(inflater, R.layout.activity_sample, root, attachToRoot, component);
  }

  @NonNull
  public static ActivitySampleBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_sample, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivitySampleBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivitySampleBinding>inflateInternal(inflater, R.layout.activity_sample, null, false, component);
  }

  public static ActivitySampleBinding bind(@NonNull View view) {
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
  public static ActivitySampleBinding bind(@NonNull View view, @Nullable Object component) {
    return (ActivitySampleBinding)bind(component, view, R.layout.activity_sample);
  }
}
