// Generated by data binding compiler. Do not edit!
package com.dcs.myretailer.app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.dcs.myretailer.app.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class BilllistMainpageListBinding extends ViewDataBinding {
  @NonNull
  public final LinearLayout Lay1;

  @NonNull
  public final ScrollView ScrollView01;

  @NonNull
  public final CardView cardviewId;

  @NonNull
  public final FloatingActionButton floatingActionDeleteButton;

  @NonNull
  public final RecyclerView recyclerView;

  @NonNull
  public final TextView txtemptyMsg;

  protected BilllistMainpageListBinding(Object _bindingComponent, View _root, int _localFieldCount,
      LinearLayout Lay1, ScrollView ScrollView01, CardView cardviewId,
      FloatingActionButton floatingActionDeleteButton, RecyclerView recyclerView,
      TextView txtemptyMsg) {
    super(_bindingComponent, _root, _localFieldCount);
    this.Lay1 = Lay1;
    this.ScrollView01 = ScrollView01;
    this.cardviewId = cardviewId;
    this.floatingActionDeleteButton = floatingActionDeleteButton;
    this.recyclerView = recyclerView;
    this.txtemptyMsg = txtemptyMsg;
  }

  @NonNull
  public static BilllistMainpageListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.billlist_mainpage_list, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static BilllistMainpageListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<BilllistMainpageListBinding>inflateInternal(inflater, R.layout.billlist_mainpage_list, root, attachToRoot, component);
  }

  @NonNull
  public static BilllistMainpageListBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.billlist_mainpage_list, null, false, component)
   */
  @NonNull
  @Deprecated
  public static BilllistMainpageListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<BilllistMainpageListBinding>inflateInternal(inflater, R.layout.billlist_mainpage_list, null, false, component);
  }

  public static BilllistMainpageListBinding bind(@NonNull View view) {
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
  public static BilllistMainpageListBinding bind(@NonNull View view, @Nullable Object component) {
    return (BilllistMainpageListBinding)bind(component, view, R.layout.billlist_mainpage_list);
  }
}
