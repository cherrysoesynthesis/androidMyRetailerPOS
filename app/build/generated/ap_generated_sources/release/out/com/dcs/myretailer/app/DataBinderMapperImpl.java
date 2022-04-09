package com.dcs.myretailer.app;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.dcs.myretailer.app.databinding.ActivityAddModifierBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityAddNewCategoryBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityAddNewConfigurationBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityAddNewProductBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityAddNewVouchersAndDiscountBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityAddPaymentTypeBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityAddTaxConfigurationsBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityButtonListviewBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityCashLayoutBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityCategoryManagementBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityCheckOutBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityCheckOutListviewBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityConfigurationHostBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityDatabaseImportExportBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityFloorMapBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityGeneralSettingBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityHardwareBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityItemDiscountBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityLicenseKeyBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityMainBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityMapSetupBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityMapbuttonSetupBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityModifierBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityPaymentCashSuccesBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityPaymentListBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityPermissionGroupBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityPosConfigurationBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityPosConfigurationIminBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityPrinterListBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityProductManagementBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityReceiptEditorBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityRemarkMainBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityReportBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityReportSettingBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityReportXListviewBindingImpl;
import com.dcs.myretailer.app.databinding.ActivitySampleBindingImpl;
import com.dcs.myretailer.app.databinding.ActivitySettingBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityStaffManagementBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityStockAdjustmentBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityStockAgentListviewBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityStockInBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityStockManagementBindingImpl;
import com.dcs.myretailer.app.databinding.ActivitySupportBindingImpl;
import com.dcs.myretailer.app.databinding.ActivitySyncBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityTaxConfigurationBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityTransactionDetailsBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityTransactionDetailsListviewBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityVouchersAndDiscountsBindingImpl;
import com.dcs.myretailer.app.databinding.ActivityZCloseResyncBindingImpl;
import com.dcs.myretailer.app.databinding.BilllistMainpageListBindingImpl;
import com.dcs.myretailer.app.databinding.CardveiwItemBookBindingImpl;
import com.dcs.myretailer.app.databinding.CardveiwnoimageItemBookBindingImpl;
import com.dcs.myretailer.app.databinding.CategoryMainPageListBindingImpl;
import com.dcs.myretailer.app.databinding.CheckoutBillInformationOrdersummaryBindingImpl;
import com.dcs.myretailer.app.databinding.CheckoutBillInformationShowBindingImpl;
import com.dcs.myretailer.app.databinding.CheckoutPaymentListBindingImpl;
import com.dcs.myretailer.app.databinding.FragmentAddProductSheetDialogBindingImpl;
import com.dcs.myretailer.app.databinding.FragmentAddQuickProductSheetDialogBindingImpl;
import com.dcs.myretailer.app.databinding.FragmentEditProductSheetDialogBindingImpl;
import com.dcs.myretailer.app.databinding.FragmentFirstBindingImpl;
import com.dcs.myretailer.app.databinding.FragmentManageBillDialogBindingImpl;
import com.dcs.myretailer.app.databinding.FragmentReportCategoryBindingImpl;
import com.dcs.myretailer.app.databinding.FragmentReportDateSheetDialogBindingImpl;
import com.dcs.myretailer.app.databinding.FragmentReportOverallBindingImpl;
import com.dcs.myretailer.app.databinding.FragmentReportProductBindingImpl;
import com.dcs.myretailer.app.databinding.FragmentReportxProductBindingImpl;
import com.dcs.myretailer.app.databinding.ItemDiscountItemAmountBookBindingImpl;
import com.dcs.myretailer.app.databinding.ItemDiscountItemBookBindingImpl;
import com.dcs.myretailer.app.databinding.ItemPaymentCheckoutItemBookBindingImpl;
import com.dcs.myretailer.app.databinding.ItemZcloserResyncItemBookBindingImpl;
import com.dcs.myretailer.app.databinding.OnhandqtyLayoutBindingImpl;
import com.dcs.myretailer.app.databinding.PercentageRecyclerViewBindingImpl;
import com.dcs.myretailer.app.databinding.ProductMainPageListBindingImpl;
import com.dcs.myretailer.app.databinding.RecyclerviewItemBindingImpl;
import com.dcs.myretailer.app.databinding.RecyclerviewPaymentypeRowBindingImpl;
import com.dcs.myretailer.app.databinding.RowListBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYADDMODIFIER = 1;

  private static final int LAYOUT_ACTIVITYADDNEWCATEGORY = 2;

  private static final int LAYOUT_ACTIVITYADDNEWCONFIGURATION = 3;

  private static final int LAYOUT_ACTIVITYADDNEWPRODUCT = 4;

  private static final int LAYOUT_ACTIVITYADDNEWVOUCHERSANDDISCOUNT = 5;

  private static final int LAYOUT_ACTIVITYADDPAYMENTTYPE = 6;

  private static final int LAYOUT_ACTIVITYADDTAXCONFIGURATIONS = 7;

  private static final int LAYOUT_ACTIVITYBUTTONLISTVIEW = 8;

  private static final int LAYOUT_ACTIVITYCASHLAYOUT = 9;

  private static final int LAYOUT_ACTIVITYCATEGORYMANAGEMENT = 10;

  private static final int LAYOUT_ACTIVITYCHECKOUT = 11;

  private static final int LAYOUT_ACTIVITYCHECKOUTLISTVIEW = 12;

  private static final int LAYOUT_ACTIVITYCONFIGURATIONHOST = 13;

  private static final int LAYOUT_ACTIVITYDATABASEIMPORTEXPORT = 14;

  private static final int LAYOUT_ACTIVITYFLOORMAP = 15;

  private static final int LAYOUT_ACTIVITYGENERALSETTING = 16;

  private static final int LAYOUT_ACTIVITYHARDWARE = 17;

  private static final int LAYOUT_ACTIVITYITEMDISCOUNT = 18;

  private static final int LAYOUT_ACTIVITYLICENSEKEY = 19;

  private static final int LAYOUT_ACTIVITYMAIN = 20;

  private static final int LAYOUT_ACTIVITYMAPSETUP = 21;

  private static final int LAYOUT_ACTIVITYMAPBUTTONSETUP = 22;

  private static final int LAYOUT_ACTIVITYMODIFIER = 23;

  private static final int LAYOUT_ACTIVITYPAYMENTCASHSUCCES = 24;

  private static final int LAYOUT_ACTIVITYPAYMENTLIST = 25;

  private static final int LAYOUT_ACTIVITYPERMISSIONGROUP = 26;

  private static final int LAYOUT_ACTIVITYPOSCONFIGURATION = 27;

  private static final int LAYOUT_ACTIVITYPOSCONFIGURATIONIMIN = 28;

  private static final int LAYOUT_ACTIVITYPRINTERLIST = 29;

  private static final int LAYOUT_ACTIVITYPRODUCTMANAGEMENT = 30;

  private static final int LAYOUT_ACTIVITYRECEIPTEDITOR = 31;

  private static final int LAYOUT_ACTIVITYREMARKMAIN = 32;

  private static final int LAYOUT_ACTIVITYREPORT = 33;

  private static final int LAYOUT_ACTIVITYREPORTSETTING = 34;

  private static final int LAYOUT_ACTIVITYREPORTXLISTVIEW = 35;

  private static final int LAYOUT_ACTIVITYSAMPLE = 36;

  private static final int LAYOUT_ACTIVITYSETTING = 37;

  private static final int LAYOUT_ACTIVITYSTAFFMANAGEMENT = 38;

  private static final int LAYOUT_ACTIVITYSTOCKADJUSTMENT = 39;

  private static final int LAYOUT_ACTIVITYSTOCKAGENTLISTVIEW = 40;

  private static final int LAYOUT_ACTIVITYSTOCKIN = 41;

  private static final int LAYOUT_ACTIVITYSTOCKMANAGEMENT = 42;

  private static final int LAYOUT_ACTIVITYSUPPORT = 43;

  private static final int LAYOUT_ACTIVITYSYNC = 44;

  private static final int LAYOUT_ACTIVITYTAXCONFIGURATION = 45;

  private static final int LAYOUT_ACTIVITYTRANSACTIONDETAILS = 46;

  private static final int LAYOUT_ACTIVITYTRANSACTIONDETAILSLISTVIEW = 47;

  private static final int LAYOUT_ACTIVITYVOUCHERSANDDISCOUNTS = 48;

  private static final int LAYOUT_ACTIVITYZCLOSERESYNC = 49;

  private static final int LAYOUT_BILLLISTMAINPAGELIST = 50;

  private static final int LAYOUT_CARDVEIWITEMBOOK = 51;

  private static final int LAYOUT_CARDVEIWNOIMAGEITEMBOOK = 52;

  private static final int LAYOUT_CATEGORYMAINPAGELIST = 53;

  private static final int LAYOUT_CHECKOUTBILLINFORMATIONORDERSUMMARY = 54;

  private static final int LAYOUT_CHECKOUTBILLINFORMATIONSHOW = 55;

  private static final int LAYOUT_CHECKOUTPAYMENTLIST = 56;

  private static final int LAYOUT_FRAGMENTADDPRODUCTSHEETDIALOG = 57;

  private static final int LAYOUT_FRAGMENTADDQUICKPRODUCTSHEETDIALOG = 58;

  private static final int LAYOUT_FRAGMENTEDITPRODUCTSHEETDIALOG = 59;

  private static final int LAYOUT_FRAGMENTFIRST = 60;

  private static final int LAYOUT_FRAGMENTMANAGEBILLDIALOG = 61;

  private static final int LAYOUT_FRAGMENTREPORTCATEGORY = 62;

  private static final int LAYOUT_FRAGMENTREPORTDATESHEETDIALOG = 63;

  private static final int LAYOUT_FRAGMENTREPORTOVERALL = 64;

  private static final int LAYOUT_FRAGMENTREPORTPRODUCT = 65;

  private static final int LAYOUT_FRAGMENTREPORTXPRODUCT = 66;

  private static final int LAYOUT_ITEMDISCOUNTITEMAMOUNTBOOK = 67;

  private static final int LAYOUT_ITEMDISCOUNTITEMBOOK = 68;

  private static final int LAYOUT_ITEMPAYMENTCHECKOUTITEMBOOK = 69;

  private static final int LAYOUT_ITEMZCLOSERRESYNCITEMBOOK = 70;

  private static final int LAYOUT_ONHANDQTYLAYOUT = 71;

  private static final int LAYOUT_PERCENTAGERECYCLERVIEW = 72;

  private static final int LAYOUT_PRODUCTMAINPAGELIST = 73;

  private static final int LAYOUT_RECYCLERVIEWITEM = 74;

  private static final int LAYOUT_RECYCLERVIEWPAYMENTYPEROW = 75;

  private static final int LAYOUT_ROWLIST = 76;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(76);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_add_modifier, LAYOUT_ACTIVITYADDMODIFIER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_add_new_category, LAYOUT_ACTIVITYADDNEWCATEGORY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_add_new_configuration, LAYOUT_ACTIVITYADDNEWCONFIGURATION);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_add_new_product, LAYOUT_ACTIVITYADDNEWPRODUCT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_add_new_vouchers_and_discount, LAYOUT_ACTIVITYADDNEWVOUCHERSANDDISCOUNT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_add_payment_type, LAYOUT_ACTIVITYADDPAYMENTTYPE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_add_tax_configurations, LAYOUT_ACTIVITYADDTAXCONFIGURATIONS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_button_listview, LAYOUT_ACTIVITYBUTTONLISTVIEW);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_cash_layout, LAYOUT_ACTIVITYCASHLAYOUT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_category_management, LAYOUT_ACTIVITYCATEGORYMANAGEMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_check_out, LAYOUT_ACTIVITYCHECKOUT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_check_out_listview, LAYOUT_ACTIVITYCHECKOUTLISTVIEW);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_configuration_host, LAYOUT_ACTIVITYCONFIGURATIONHOST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_database_import_export, LAYOUT_ACTIVITYDATABASEIMPORTEXPORT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_floor_map, LAYOUT_ACTIVITYFLOORMAP);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_general_setting, LAYOUT_ACTIVITYGENERALSETTING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_hardware, LAYOUT_ACTIVITYHARDWARE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_item_discount, LAYOUT_ACTIVITYITEMDISCOUNT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_license_key, LAYOUT_ACTIVITYLICENSEKEY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_main, LAYOUT_ACTIVITYMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_map_setup, LAYOUT_ACTIVITYMAPSETUP);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_mapbutton_setup, LAYOUT_ACTIVITYMAPBUTTONSETUP);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_modifier, LAYOUT_ACTIVITYMODIFIER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_payment_cash_succes, LAYOUT_ACTIVITYPAYMENTCASHSUCCES);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_payment_list, LAYOUT_ACTIVITYPAYMENTLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_permission_group, LAYOUT_ACTIVITYPERMISSIONGROUP);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_pos_configuration, LAYOUT_ACTIVITYPOSCONFIGURATION);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_pos_configuration_imin, LAYOUT_ACTIVITYPOSCONFIGURATIONIMIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_printer_list, LAYOUT_ACTIVITYPRINTERLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_product_management, LAYOUT_ACTIVITYPRODUCTMANAGEMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_receipt_editor, LAYOUT_ACTIVITYRECEIPTEDITOR);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_remark_main, LAYOUT_ACTIVITYREMARKMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_report, LAYOUT_ACTIVITYREPORT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_report_setting, LAYOUT_ACTIVITYREPORTSETTING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_report_x_listview, LAYOUT_ACTIVITYREPORTXLISTVIEW);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_sample, LAYOUT_ACTIVITYSAMPLE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_setting, LAYOUT_ACTIVITYSETTING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_staff_management, LAYOUT_ACTIVITYSTAFFMANAGEMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_stock_adjustment, LAYOUT_ACTIVITYSTOCKADJUSTMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_stock_agent_listview, LAYOUT_ACTIVITYSTOCKAGENTLISTVIEW);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_stock_in, LAYOUT_ACTIVITYSTOCKIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_stock_management, LAYOUT_ACTIVITYSTOCKMANAGEMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_support, LAYOUT_ACTIVITYSUPPORT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_sync, LAYOUT_ACTIVITYSYNC);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_tax_configuration, LAYOUT_ACTIVITYTAXCONFIGURATION);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_transaction_details, LAYOUT_ACTIVITYTRANSACTIONDETAILS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_transaction_details_listview, LAYOUT_ACTIVITYTRANSACTIONDETAILSLISTVIEW);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_vouchers_and_discounts, LAYOUT_ACTIVITYVOUCHERSANDDISCOUNTS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.activity_z_close_resync, LAYOUT_ACTIVITYZCLOSERESYNC);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.billlist_mainpage_list, LAYOUT_BILLLISTMAINPAGELIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.cardveiw_item_book, LAYOUT_CARDVEIWITEMBOOK);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.cardveiwnoimage_item_book, LAYOUT_CARDVEIWNOIMAGEITEMBOOK);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.category_main_page_list, LAYOUT_CATEGORYMAINPAGELIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.checkout_bill_information_ordersummary, LAYOUT_CHECKOUTBILLINFORMATIONORDERSUMMARY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.checkout_bill_information_show, LAYOUT_CHECKOUTBILLINFORMATIONSHOW);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.checkout_payment_list, LAYOUT_CHECKOUTPAYMENTLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.fragment_add_product_sheet_dialog, LAYOUT_FRAGMENTADDPRODUCTSHEETDIALOG);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.fragment_add_quick_product_sheet_dialog, LAYOUT_FRAGMENTADDQUICKPRODUCTSHEETDIALOG);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.fragment_edit_product_sheet_dialog, LAYOUT_FRAGMENTEDITPRODUCTSHEETDIALOG);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.fragment_first, LAYOUT_FRAGMENTFIRST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.fragment_manage_bill_dialog, LAYOUT_FRAGMENTMANAGEBILLDIALOG);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.fragment_report_category, LAYOUT_FRAGMENTREPORTCATEGORY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.fragment_report_date_sheet_dialog, LAYOUT_FRAGMENTREPORTDATESHEETDIALOG);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.fragment_report_overall, LAYOUT_FRAGMENTREPORTOVERALL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.fragment_report_product, LAYOUT_FRAGMENTREPORTPRODUCT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.fragment_reportx_product, LAYOUT_FRAGMENTREPORTXPRODUCT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.item_discount_item_amount_book, LAYOUT_ITEMDISCOUNTITEMAMOUNTBOOK);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.item_discount_item_book, LAYOUT_ITEMDISCOUNTITEMBOOK);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.item_payment_checkout_item_book, LAYOUT_ITEMPAYMENTCHECKOUTITEMBOOK);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.item_zcloser_resync_item_book, LAYOUT_ITEMZCLOSERRESYNCITEMBOOK);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.onhandqty_layout, LAYOUT_ONHANDQTYLAYOUT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.percentage_recycler_view, LAYOUT_PERCENTAGERECYCLERVIEW);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.product_main_page_list, LAYOUT_PRODUCTMAINPAGELIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.recyclerview_item, LAYOUT_RECYCLERVIEWITEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.recyclerview_paymentype_row, LAYOUT_RECYCLERVIEWPAYMENTYPEROW);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.dcs.myretailer.app.R.layout.row_list, LAYOUT_ROWLIST);
  }

  private final ViewDataBinding internalGetViewDataBinding0(DataBindingComponent component,
      View view, int internalId, Object tag) {
    switch(internalId) {
      case  LAYOUT_ACTIVITYADDMODIFIER: {
        if ("layout/activity_add_modifier_0".equals(tag)) {
          return new ActivityAddModifierBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_add_modifier is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYADDNEWCATEGORY: {
        if ("layout/activity_add_new_category_0".equals(tag)) {
          return new ActivityAddNewCategoryBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_add_new_category is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYADDNEWCONFIGURATION: {
        if ("layout/activity_add_new_configuration_0".equals(tag)) {
          return new ActivityAddNewConfigurationBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_add_new_configuration is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYADDNEWPRODUCT: {
        if ("layout/activity_add_new_product_0".equals(tag)) {
          return new ActivityAddNewProductBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_add_new_product is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYADDNEWVOUCHERSANDDISCOUNT: {
        if ("layout/activity_add_new_vouchers_and_discount_0".equals(tag)) {
          return new ActivityAddNewVouchersAndDiscountBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_add_new_vouchers_and_discount is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYADDPAYMENTTYPE: {
        if ("layout/activity_add_payment_type_0".equals(tag)) {
          return new ActivityAddPaymentTypeBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_add_payment_type is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYADDTAXCONFIGURATIONS: {
        if ("layout/activity_add_tax_configurations_0".equals(tag)) {
          return new ActivityAddTaxConfigurationsBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_add_tax_configurations is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYBUTTONLISTVIEW: {
        if ("layout/activity_button_listview_0".equals(tag)) {
          return new ActivityButtonListviewBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_button_listview is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYCASHLAYOUT: {
        if ("layout/activity_cash_layout_0".equals(tag)) {
          return new ActivityCashLayoutBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_cash_layout is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYCATEGORYMANAGEMENT: {
        if ("layout/activity_category_management_0".equals(tag)) {
          return new ActivityCategoryManagementBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_category_management is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYCHECKOUT: {
        if ("layout/activity_check_out_0".equals(tag)) {
          return new ActivityCheckOutBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_check_out is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYCHECKOUTLISTVIEW: {
        if ("layout/activity_check_out_listview_0".equals(tag)) {
          return new ActivityCheckOutListviewBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_check_out_listview is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYCONFIGURATIONHOST: {
        if ("layout/activity_configuration_host_0".equals(tag)) {
          return new ActivityConfigurationHostBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_configuration_host is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYDATABASEIMPORTEXPORT: {
        if ("layout/activity_database_import_export_0".equals(tag)) {
          return new ActivityDatabaseImportExportBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_database_import_export is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYFLOORMAP: {
        if ("layout/activity_floor_map_0".equals(tag)) {
          return new ActivityFloorMapBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_floor_map is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYGENERALSETTING: {
        if ("layout/activity_general_setting_0".equals(tag)) {
          return new ActivityGeneralSettingBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_general_setting is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYHARDWARE: {
        if ("layout/activity_hardware_0".equals(tag)) {
          return new ActivityHardwareBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_hardware is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYITEMDISCOUNT: {
        if ("layout/activity_item_discount_0".equals(tag)) {
          return new ActivityItemDiscountBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_item_discount is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYLICENSEKEY: {
        if ("layout/activity_license_key_0".equals(tag)) {
          return new ActivityLicenseKeyBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_license_key is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYMAIN: {
        if ("layout/activity_main_0".equals(tag)) {
          return new ActivityMainBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_main is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYMAPSETUP: {
        if ("layout/activity_map_setup_0".equals(tag)) {
          return new ActivityMapSetupBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_map_setup is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYMAPBUTTONSETUP: {
        if ("layout/activity_mapbutton_setup_0".equals(tag)) {
          return new ActivityMapbuttonSetupBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_mapbutton_setup is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYMODIFIER: {
        if ("layout/activity_modifier_0".equals(tag)) {
          return new ActivityModifierBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_modifier is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYPAYMENTCASHSUCCES: {
        if ("layout/activity_payment_cash_succes_0".equals(tag)) {
          return new ActivityPaymentCashSuccesBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_payment_cash_succes is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYPAYMENTLIST: {
        if ("layout/activity_payment_list_0".equals(tag)) {
          return new ActivityPaymentListBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_payment_list is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYPERMISSIONGROUP: {
        if ("layout/activity_permission_group_0".equals(tag)) {
          return new ActivityPermissionGroupBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_permission_group is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYPOSCONFIGURATION: {
        if ("layout/activity_pos_configuration_0".equals(tag)) {
          return new ActivityPosConfigurationBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_pos_configuration is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYPOSCONFIGURATIONIMIN: {
        if ("layout/activity_pos_configuration_imin_0".equals(tag)) {
          return new ActivityPosConfigurationIminBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_pos_configuration_imin is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYPRINTERLIST: {
        if ("layout/activity_printer_list_0".equals(tag)) {
          return new ActivityPrinterListBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_printer_list is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYPRODUCTMANAGEMENT: {
        if ("layout/activity_product_management_0".equals(tag)) {
          return new ActivityProductManagementBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_product_management is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYRECEIPTEDITOR: {
        if ("layout/activity_receipt_editor_0".equals(tag)) {
          return new ActivityReceiptEditorBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_receipt_editor is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYREMARKMAIN: {
        if ("layout/activity_remark_main_0".equals(tag)) {
          return new ActivityRemarkMainBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_remark_main is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYREPORT: {
        if ("layout/activity_report_0".equals(tag)) {
          return new ActivityReportBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_report is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYREPORTSETTING: {
        if ("layout/activity_report_setting_0".equals(tag)) {
          return new ActivityReportSettingBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_report_setting is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYREPORTXLISTVIEW: {
        if ("layout/activity_report_x_listview_0".equals(tag)) {
          return new ActivityReportXListviewBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_report_x_listview is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYSAMPLE: {
        if ("layout/activity_sample_0".equals(tag)) {
          return new ActivitySampleBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_sample is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYSETTING: {
        if ("layout/activity_setting_0".equals(tag)) {
          return new ActivitySettingBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_setting is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYSTAFFMANAGEMENT: {
        if ("layout/activity_staff_management_0".equals(tag)) {
          return new ActivityStaffManagementBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_staff_management is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYSTOCKADJUSTMENT: {
        if ("layout/activity_stock_adjustment_0".equals(tag)) {
          return new ActivityStockAdjustmentBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_stock_adjustment is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYSTOCKAGENTLISTVIEW: {
        if ("layout/activity_stock_agent_listview_0".equals(tag)) {
          return new ActivityStockAgentListviewBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_stock_agent_listview is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYSTOCKIN: {
        if ("layout/activity_stock_in_0".equals(tag)) {
          return new ActivityStockInBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_stock_in is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYSTOCKMANAGEMENT: {
        if ("layout/activity_stock_management_0".equals(tag)) {
          return new ActivityStockManagementBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_stock_management is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYSUPPORT: {
        if ("layout/activity_support_0".equals(tag)) {
          return new ActivitySupportBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_support is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYSYNC: {
        if ("layout/activity_sync_0".equals(tag)) {
          return new ActivitySyncBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_sync is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYTAXCONFIGURATION: {
        if ("layout/activity_tax_configuration_0".equals(tag)) {
          return new ActivityTaxConfigurationBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_tax_configuration is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYTRANSACTIONDETAILS: {
        if ("layout/activity_transaction_details_0".equals(tag)) {
          return new ActivityTransactionDetailsBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_transaction_details is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYTRANSACTIONDETAILSLISTVIEW: {
        if ("layout/activity_transaction_details_listview_0".equals(tag)) {
          return new ActivityTransactionDetailsListviewBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_transaction_details_listview is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYVOUCHERSANDDISCOUNTS: {
        if ("layout/activity_vouchers_and_discounts_0".equals(tag)) {
          return new ActivityVouchersAndDiscountsBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_vouchers_and_discounts is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYZCLOSERESYNC: {
        if ("layout/activity_z_close_resync_0".equals(tag)) {
          return new ActivityZCloseResyncBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_z_close_resync is invalid. Received: " + tag);
      }
      case  LAYOUT_BILLLISTMAINPAGELIST: {
        if ("layout/billlist_mainpage_list_0".equals(tag)) {
          return new BilllistMainpageListBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for billlist_mainpage_list is invalid. Received: " + tag);
      }
    }
    return null;
  }

  private final ViewDataBinding internalGetViewDataBinding1(DataBindingComponent component,
      View view, int internalId, Object tag) {
    switch(internalId) {
      case  LAYOUT_CARDVEIWITEMBOOK: {
        if ("layout/cardveiw_item_book_0".equals(tag)) {
          return new CardveiwItemBookBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for cardveiw_item_book is invalid. Received: " + tag);
      }
      case  LAYOUT_CARDVEIWNOIMAGEITEMBOOK: {
        if ("layout/cardveiwnoimage_item_book_0".equals(tag)) {
          return new CardveiwnoimageItemBookBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for cardveiwnoimage_item_book is invalid. Received: " + tag);
      }
      case  LAYOUT_CATEGORYMAINPAGELIST: {
        if ("layout/category_main_page_list_0".equals(tag)) {
          return new CategoryMainPageListBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for category_main_page_list is invalid. Received: " + tag);
      }
      case  LAYOUT_CHECKOUTBILLINFORMATIONORDERSUMMARY: {
        if ("layout/checkout_bill_information_ordersummary_0".equals(tag)) {
          return new CheckoutBillInformationOrdersummaryBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for checkout_bill_information_ordersummary is invalid. Received: " + tag);
      }
      case  LAYOUT_CHECKOUTBILLINFORMATIONSHOW: {
        if ("layout/checkout_bill_information_show_0".equals(tag)) {
          return new CheckoutBillInformationShowBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for checkout_bill_information_show is invalid. Received: " + tag);
      }
      case  LAYOUT_CHECKOUTPAYMENTLIST: {
        if ("layout/checkout_payment_list_0".equals(tag)) {
          return new CheckoutPaymentListBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for checkout_payment_list is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTADDPRODUCTSHEETDIALOG: {
        if ("layout/fragment_add_product_sheet_dialog_0".equals(tag)) {
          return new FragmentAddProductSheetDialogBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_add_product_sheet_dialog is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTADDQUICKPRODUCTSHEETDIALOG: {
        if ("layout/fragment_add_quick_product_sheet_dialog_0".equals(tag)) {
          return new FragmentAddQuickProductSheetDialogBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_add_quick_product_sheet_dialog is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTEDITPRODUCTSHEETDIALOG: {
        if ("layout/fragment_edit_product_sheet_dialog_0".equals(tag)) {
          return new FragmentEditProductSheetDialogBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_edit_product_sheet_dialog is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTFIRST: {
        if ("layout/fragment_first_0".equals(tag)) {
          return new FragmentFirstBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_first is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTMANAGEBILLDIALOG: {
        if ("layout/fragment_manage_bill_dialog_0".equals(tag)) {
          return new FragmentManageBillDialogBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_manage_bill_dialog is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTREPORTCATEGORY: {
        if ("layout/fragment_report_category_0".equals(tag)) {
          return new FragmentReportCategoryBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_report_category is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTREPORTDATESHEETDIALOG: {
        if ("layout/fragment_report_date_sheet_dialog_0".equals(tag)) {
          return new FragmentReportDateSheetDialogBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_report_date_sheet_dialog is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTREPORTOVERALL: {
        if ("layout/fragment_report_overall_0".equals(tag)) {
          return new FragmentReportOverallBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_report_overall is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTREPORTPRODUCT: {
        if ("layout/fragment_report_product_0".equals(tag)) {
          return new FragmentReportProductBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_report_product is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTREPORTXPRODUCT: {
        if ("layout/fragment_reportx_product_0".equals(tag)) {
          return new FragmentReportxProductBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_reportx_product is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMDISCOUNTITEMAMOUNTBOOK: {
        if ("layout/item_discount_item_amount_book_0".equals(tag)) {
          return new ItemDiscountItemAmountBookBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_discount_item_amount_book is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMDISCOUNTITEMBOOK: {
        if ("layout/item_discount_item_book_0".equals(tag)) {
          return new ItemDiscountItemBookBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_discount_item_book is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMPAYMENTCHECKOUTITEMBOOK: {
        if ("layout/item_payment_checkout_item_book_0".equals(tag)) {
          return new ItemPaymentCheckoutItemBookBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_payment_checkout_item_book is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMZCLOSERRESYNCITEMBOOK: {
        if ("layout/item_zcloser_resync_item_book_0".equals(tag)) {
          return new ItemZcloserResyncItemBookBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_zcloser_resync_item_book is invalid. Received: " + tag);
      }
      case  LAYOUT_ONHANDQTYLAYOUT: {
        if ("layout/onhandqty_layout_0".equals(tag)) {
          return new OnhandqtyLayoutBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for onhandqty_layout is invalid. Received: " + tag);
      }
      case  LAYOUT_PERCENTAGERECYCLERVIEW: {
        if ("layout/percentage_recycler_view_0".equals(tag)) {
          return new PercentageRecyclerViewBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for percentage_recycler_view is invalid. Received: " + tag);
      }
      case  LAYOUT_PRODUCTMAINPAGELIST: {
        if ("layout/product_main_page_list_0".equals(tag)) {
          return new ProductMainPageListBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for product_main_page_list is invalid. Received: " + tag);
      }
      case  LAYOUT_RECYCLERVIEWITEM: {
        if ("layout/recyclerview_item_0".equals(tag)) {
          return new RecyclerviewItemBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for recyclerview_item is invalid. Received: " + tag);
      }
      case  LAYOUT_RECYCLERVIEWPAYMENTYPEROW: {
        if ("layout/recyclerview_paymentype_row_0".equals(tag)) {
          return new RecyclerviewPaymentypeRowBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for recyclerview_paymentype_row is invalid. Received: " + tag);
      }
      case  LAYOUT_ROWLIST: {
        if ("layout/row_list_0".equals(tag)) {
          return new RowListBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for row_list is invalid. Received: " + tag);
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      // find which method will have it. -1 is necessary becausefirst id starts with 1;
      int methodIndex = (localizedLayoutId - 1) / 50;
      switch(methodIndex) {
        case 0: {
          return internalGetViewDataBinding0(component, view, localizedLayoutId, tag);
        }
        case 1: {
          return internalGetViewDataBinding1(component, view, localizedLayoutId, tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(49);

    static {
      sKeys.put(1, "ModifierData");
      sKeys.put(0, "_all");
      sKeys.put(2, "allowNameQuickEdit");
      sKeys.put(3, "allowOpenPrice");
      sKeys.put(4, "allowPriceQuickEdit");
      sKeys.put(5, "base64imgValue");
      sKeys.put(6, "category");
      sKeys.put(7, "code");
      sKeys.put(8, "condi_Seq");
      sKeys.put(9, "dateTime");
      sKeys.put(10, "deptID");
      sKeys.put(11, "description");
      sKeys.put(12, "dt");
      sKeys.put(13, "dtime");
      sKeys.put(14, "iD");
      sKeys.put(15, "image");
      sKeys.put(16, "imageFileName");
      sKeys.put(17, "imageItemID");
      sKeys.put(18, "imageType");
      sKeys.put(19, "imageUrl");
      sKeys.put(20, "name");
      sKeys.put(21, "name2");
      sKeys.put(22, "onHandQty");
      sKeys.put(23, "openPrice");
      sKeys.put(24, "option");
      sKeys.put(25, "pLUID");
      sKeys.put(26, "price");
      sKeys.put(27, "productCategoryID");
      sKeys.put(28, "productCategoryName");
      sKeys.put(29, "productData");
      sKeys.put(30, "productID");
      sKeys.put(31, "productModifiers");
      sKeys.put(32, "productTaxID");
      sKeys.put(33, "productTaxName");
      sKeys.put(34, "productVariant");
      sKeys.put(35, "qty");
      sKeys.put(36, "qtyActual");
      sKeys.put(37, "qtyAdjustment");
      sKeys.put(38, "qtyBalance");
      sKeys.put(39, "qtyReturn");
      sKeys.put(40, "qtySold");
      sKeys.put(41, "remarks");
      sKeys.put(42, "systemQty");
      sKeys.put(43, "thumbnail");
      sKeys.put(44, "title");
      sKeys.put(45, "transNo");
      sKeys.put(46, "uUID");
      sKeys.put(47, "varianceQty");
      sKeys.put(48, "viewmodel");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(76);

    static {
      sKeys.put("layout/activity_add_modifier_0", com.dcs.myretailer.app.R.layout.activity_add_modifier);
      sKeys.put("layout/activity_add_new_category_0", com.dcs.myretailer.app.R.layout.activity_add_new_category);
      sKeys.put("layout/activity_add_new_configuration_0", com.dcs.myretailer.app.R.layout.activity_add_new_configuration);
      sKeys.put("layout/activity_add_new_product_0", com.dcs.myretailer.app.R.layout.activity_add_new_product);
      sKeys.put("layout/activity_add_new_vouchers_and_discount_0", com.dcs.myretailer.app.R.layout.activity_add_new_vouchers_and_discount);
      sKeys.put("layout/activity_add_payment_type_0", com.dcs.myretailer.app.R.layout.activity_add_payment_type);
      sKeys.put("layout/activity_add_tax_configurations_0", com.dcs.myretailer.app.R.layout.activity_add_tax_configurations);
      sKeys.put("layout/activity_button_listview_0", com.dcs.myretailer.app.R.layout.activity_button_listview);
      sKeys.put("layout/activity_cash_layout_0", com.dcs.myretailer.app.R.layout.activity_cash_layout);
      sKeys.put("layout/activity_category_management_0", com.dcs.myretailer.app.R.layout.activity_category_management);
      sKeys.put("layout/activity_check_out_0", com.dcs.myretailer.app.R.layout.activity_check_out);
      sKeys.put("layout/activity_check_out_listview_0", com.dcs.myretailer.app.R.layout.activity_check_out_listview);
      sKeys.put("layout/activity_configuration_host_0", com.dcs.myretailer.app.R.layout.activity_configuration_host);
      sKeys.put("layout/activity_database_import_export_0", com.dcs.myretailer.app.R.layout.activity_database_import_export);
      sKeys.put("layout/activity_floor_map_0", com.dcs.myretailer.app.R.layout.activity_floor_map);
      sKeys.put("layout/activity_general_setting_0", com.dcs.myretailer.app.R.layout.activity_general_setting);
      sKeys.put("layout/activity_hardware_0", com.dcs.myretailer.app.R.layout.activity_hardware);
      sKeys.put("layout/activity_item_discount_0", com.dcs.myretailer.app.R.layout.activity_item_discount);
      sKeys.put("layout/activity_license_key_0", com.dcs.myretailer.app.R.layout.activity_license_key);
      sKeys.put("layout/activity_main_0", com.dcs.myretailer.app.R.layout.activity_main);
      sKeys.put("layout/activity_map_setup_0", com.dcs.myretailer.app.R.layout.activity_map_setup);
      sKeys.put("layout/activity_mapbutton_setup_0", com.dcs.myretailer.app.R.layout.activity_mapbutton_setup);
      sKeys.put("layout/activity_modifier_0", com.dcs.myretailer.app.R.layout.activity_modifier);
      sKeys.put("layout/activity_payment_cash_succes_0", com.dcs.myretailer.app.R.layout.activity_payment_cash_succes);
      sKeys.put("layout/activity_payment_list_0", com.dcs.myretailer.app.R.layout.activity_payment_list);
      sKeys.put("layout/activity_permission_group_0", com.dcs.myretailer.app.R.layout.activity_permission_group);
      sKeys.put("layout/activity_pos_configuration_0", com.dcs.myretailer.app.R.layout.activity_pos_configuration);
      sKeys.put("layout/activity_pos_configuration_imin_0", com.dcs.myretailer.app.R.layout.activity_pos_configuration_imin);
      sKeys.put("layout/activity_printer_list_0", com.dcs.myretailer.app.R.layout.activity_printer_list);
      sKeys.put("layout/activity_product_management_0", com.dcs.myretailer.app.R.layout.activity_product_management);
      sKeys.put("layout/activity_receipt_editor_0", com.dcs.myretailer.app.R.layout.activity_receipt_editor);
      sKeys.put("layout/activity_remark_main_0", com.dcs.myretailer.app.R.layout.activity_remark_main);
      sKeys.put("layout/activity_report_0", com.dcs.myretailer.app.R.layout.activity_report);
      sKeys.put("layout/activity_report_setting_0", com.dcs.myretailer.app.R.layout.activity_report_setting);
      sKeys.put("layout/activity_report_x_listview_0", com.dcs.myretailer.app.R.layout.activity_report_x_listview);
      sKeys.put("layout/activity_sample_0", com.dcs.myretailer.app.R.layout.activity_sample);
      sKeys.put("layout/activity_setting_0", com.dcs.myretailer.app.R.layout.activity_setting);
      sKeys.put("layout/activity_staff_management_0", com.dcs.myretailer.app.R.layout.activity_staff_management);
      sKeys.put("layout/activity_stock_adjustment_0", com.dcs.myretailer.app.R.layout.activity_stock_adjustment);
      sKeys.put("layout/activity_stock_agent_listview_0", com.dcs.myretailer.app.R.layout.activity_stock_agent_listview);
      sKeys.put("layout/activity_stock_in_0", com.dcs.myretailer.app.R.layout.activity_stock_in);
      sKeys.put("layout/activity_stock_management_0", com.dcs.myretailer.app.R.layout.activity_stock_management);
      sKeys.put("layout/activity_support_0", com.dcs.myretailer.app.R.layout.activity_support);
      sKeys.put("layout/activity_sync_0", com.dcs.myretailer.app.R.layout.activity_sync);
      sKeys.put("layout/activity_tax_configuration_0", com.dcs.myretailer.app.R.layout.activity_tax_configuration);
      sKeys.put("layout/activity_transaction_details_0", com.dcs.myretailer.app.R.layout.activity_transaction_details);
      sKeys.put("layout/activity_transaction_details_listview_0", com.dcs.myretailer.app.R.layout.activity_transaction_details_listview);
      sKeys.put("layout/activity_vouchers_and_discounts_0", com.dcs.myretailer.app.R.layout.activity_vouchers_and_discounts);
      sKeys.put("layout/activity_z_close_resync_0", com.dcs.myretailer.app.R.layout.activity_z_close_resync);
      sKeys.put("layout/billlist_mainpage_list_0", com.dcs.myretailer.app.R.layout.billlist_mainpage_list);
      sKeys.put("layout/cardveiw_item_book_0", com.dcs.myretailer.app.R.layout.cardveiw_item_book);
      sKeys.put("layout/cardveiwnoimage_item_book_0", com.dcs.myretailer.app.R.layout.cardveiwnoimage_item_book);
      sKeys.put("layout/category_main_page_list_0", com.dcs.myretailer.app.R.layout.category_main_page_list);
      sKeys.put("layout/checkout_bill_information_ordersummary_0", com.dcs.myretailer.app.R.layout.checkout_bill_information_ordersummary);
      sKeys.put("layout/checkout_bill_information_show_0", com.dcs.myretailer.app.R.layout.checkout_bill_information_show);
      sKeys.put("layout/checkout_payment_list_0", com.dcs.myretailer.app.R.layout.checkout_payment_list);
      sKeys.put("layout/fragment_add_product_sheet_dialog_0", com.dcs.myretailer.app.R.layout.fragment_add_product_sheet_dialog);
      sKeys.put("layout/fragment_add_quick_product_sheet_dialog_0", com.dcs.myretailer.app.R.layout.fragment_add_quick_product_sheet_dialog);
      sKeys.put("layout/fragment_edit_product_sheet_dialog_0", com.dcs.myretailer.app.R.layout.fragment_edit_product_sheet_dialog);
      sKeys.put("layout/fragment_first_0", com.dcs.myretailer.app.R.layout.fragment_first);
      sKeys.put("layout/fragment_manage_bill_dialog_0", com.dcs.myretailer.app.R.layout.fragment_manage_bill_dialog);
      sKeys.put("layout/fragment_report_category_0", com.dcs.myretailer.app.R.layout.fragment_report_category);
      sKeys.put("layout/fragment_report_date_sheet_dialog_0", com.dcs.myretailer.app.R.layout.fragment_report_date_sheet_dialog);
      sKeys.put("layout/fragment_report_overall_0", com.dcs.myretailer.app.R.layout.fragment_report_overall);
      sKeys.put("layout/fragment_report_product_0", com.dcs.myretailer.app.R.layout.fragment_report_product);
      sKeys.put("layout/fragment_reportx_product_0", com.dcs.myretailer.app.R.layout.fragment_reportx_product);
      sKeys.put("layout/item_discount_item_amount_book_0", com.dcs.myretailer.app.R.layout.item_discount_item_amount_book);
      sKeys.put("layout/item_discount_item_book_0", com.dcs.myretailer.app.R.layout.item_discount_item_book);
      sKeys.put("layout/item_payment_checkout_item_book_0", com.dcs.myretailer.app.R.layout.item_payment_checkout_item_book);
      sKeys.put("layout/item_zcloser_resync_item_book_0", com.dcs.myretailer.app.R.layout.item_zcloser_resync_item_book);
      sKeys.put("layout/onhandqty_layout_0", com.dcs.myretailer.app.R.layout.onhandqty_layout);
      sKeys.put("layout/percentage_recycler_view_0", com.dcs.myretailer.app.R.layout.percentage_recycler_view);
      sKeys.put("layout/product_main_page_list_0", com.dcs.myretailer.app.R.layout.product_main_page_list);
      sKeys.put("layout/recyclerview_item_0", com.dcs.myretailer.app.R.layout.recyclerview_item);
      sKeys.put("layout/recyclerview_paymentype_row_0", com.dcs.myretailer.app.R.layout.recyclerview_paymentype_row);
      sKeys.put("layout/row_list_0", com.dcs.myretailer.app.R.layout.row_list);
    }
  }
}
