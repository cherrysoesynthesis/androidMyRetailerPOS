// Generated by data binding compiler. Do not edit!
package com.dcs.myretailer.app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.dcs.myretailer.app.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivitySyncBinding extends ViewDataBinding {
  @NonNull
  public final ScrollView ScrollView01;

  @NonNull
  public final CardView cardviewId;

  @NonNull
  public final LinearLayout container;

  @NonNull
  public final Button deleteLog;

  @NonNull
  public final Button downloadDepartment;

  @NonNull
  public final Button downloadInventory;

  @NonNull
  public final Button downloadPromotion;

  @NonNull
  public final Button downloadUserAccess;

  @NonNull
  public final Button getDiscountlist;

  @NonNull
  public final Button getEunoiaMenu;

  @NonNull
  public final Button getEunoiaProducts;

  @NonNull
  public final LinearLayout layOverAll;

  @NonNull
  public final LinearLayout productLinearLayoutId;

  @NonNull
  public final Button resendSale;

  @NonNull
  public final Button syncDiscount;

  @NonNull
  public final Button syncId;

  @NonNull
  public final ListView syncList;

  @NonNull
  public final Button syncPaymentType;

  @NonNull
  public final Button syncStockadjustment;

  protected ActivitySyncBinding(Object _bindingComponent, View _root, int _localFieldCount,
      ScrollView ScrollView01, CardView cardviewId, LinearLayout container, Button deleteLog,
      Button downloadDepartment, Button downloadInventory, Button downloadPromotion,
      Button downloadUserAccess, Button getDiscountlist, Button getEunoiaMenu,
      Button getEunoiaProducts, LinearLayout layOverAll, LinearLayout productLinearLayoutId,
      Button resendSale, Button syncDiscount, Button syncId, ListView syncList,
      Button syncPaymentType, Button syncStockadjustment) {
    super(_bindingComponent, _root, _localFieldCount);
    this.ScrollView01 = ScrollView01;
    this.cardviewId = cardviewId;
    this.container = container;
    this.deleteLog = deleteLog;
    this.downloadDepartment = downloadDepartment;
    this.downloadInventory = downloadInventory;
    this.downloadPromotion = downloadPromotion;
    this.downloadUserAccess = downloadUserAccess;
    this.getDiscountlist = getDiscountlist;
    this.getEunoiaMenu = getEunoiaMenu;
    this.getEunoiaProducts = getEunoiaProducts;
    this.layOverAll = layOverAll;
    this.productLinearLayoutId = productLinearLayoutId;
    this.resendSale = resendSale;
    this.syncDiscount = syncDiscount;
    this.syncId = syncId;
    this.syncList = syncList;
    this.syncPaymentType = syncPaymentType;
    this.syncStockadjustment = syncStockadjustment;
  }

  @NonNull
  public static ActivitySyncBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_sync, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivitySyncBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivitySyncBinding>inflateInternal(inflater, R.layout.activity_sync, root, attachToRoot, component);
  }

  @NonNull
  public static ActivitySyncBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_sync, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivitySyncBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivitySyncBinding>inflateInternal(inflater, R.layout.activity_sync, null, false, component);
  }

  public static ActivitySyncBinding bind(@NonNull View view) {
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
  public static ActivitySyncBinding bind(@NonNull View view, @Nullable Object component) {
    return (ActivitySyncBinding)bind(component, view, R.layout.activity_sync);
  }
}
