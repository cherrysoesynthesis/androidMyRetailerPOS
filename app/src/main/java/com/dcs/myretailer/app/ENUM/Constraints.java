package com.dcs.myretailer.app.ENUM;

import android.annotation.SuppressLint;

import com.usdk.apiservice.aidl.constants.RFDeviceName;
import com.usdk.apiservice.aidl.pinpad.DeviceName;

public class Constraints {
    public static String PINNO = "PIN NO";
    public static String DEFAULTNAME = "Mariano";
    public static String ADMIN = "ADMIN";
    public static String WrongPassword = "Wrong Password!";
    public static String DonothaveUserAccess = "Don't have User Accessable Permision!";
    public static String BillNo = "BillNo";
    public static String Status = "Status";
    public static String StatusSALES = "StatusSALES";
    public static String ChooseProduct = "Please choose one product!";
    public static String SelectProduct = "Please select product!";
    public static String EmptyProduct = "Product Name Empty";
    public static String EmptyPrice = "Product Price Empty";
    public static String EmptyName = "Name Empty";
    public static String OK = "OK";
    public static String No = "No";
    public static String YES = "YES";
    public static String Products = "Products";
    public static String Categories = "Categories";
    public static String Bill = "Bill";
    public static String OnlineOrder = "Online Order";
    public static String OnlineOrderBill = "Online Closed Bill";
    public static String GroupBy = "GROUP BY";
    public static String NoGroupBy = "";
    public static String CANCEL = "CANCEL";
    public static String SALES = "SALES";
    public static String REFUND = "REFUND";
    public static String Reprint = "Reprint";
    public static String VOID = "VOID";
    public static String ISNOTNUMBER = "IS NOT NUMBER!";
    public static String ZReport = "ZReport";
    public static String XReport = "X Reports";
    public static String LINE = "================================";
    public static String LINEM2MAX = "======================================";
    public static String LINEX = "========================================";
    public static String LINEXM2MAX = "==================================================";
    public static String DOTLINE = "--------------------------------";
    public static String DOTLINEX = "-------------------------------------";
    public static String DOTLINEM2MAX = "------------------------------------------------";
    public static String DOTLINEXM2MAX = "--------------------------------------------------";
    public static String HEADER = "header";
    public static String LINEIMIN= "=============================";
    public static String DOTLINEIMIN = "------------------------------------------------";
    public static String CARDLABEL = "Card Label";
    public static String CARDNUMBER = "Card Number";
    public static String INVNUMBER = "Invoice Number";
    public static String PaymentType = "Payment Type";
    public static String PaymentAmount = "Payment Amount";
    public static String VoucherNumber = "Voucher Number";
    public static String VoucherAmount = "Voucher Amount";
    public static String MallLoyaltyStatus = "Mall Loyalty Status";
    public static String ZClose = "ZClose";
    public static String NotZClose = "NotZClose";
    public static String AMTNett = "AMT Nett";
    public static String Amount = "Amount";
    public static String AMTGross = "AMT Gross";
    public static String TaxTotal = "Tax Total";
    public static String AMTDiscount = "AMT Discount";
    public static String AMTSurcharge = "AMT Surcharge";
    public static String RoundAdj = "Round Adj";
    public static String AMTCollected = "AMT Collected";
    public static String QtyCancel = "Qty Cancel";
    public static String QtyRefund = "Qty Refund";
    public static String AMTCancel = "AMT Cancel";
    public static String ItemDiscountQty = "Item Discount Qty";
    public static String ItemDiscountAmount = "Item Discount Amount";
    public static String BillDiscountQty = "Bill Discount Qty";
    public static String BillDiscountAmount = "Bill Discount Amount";
    public static String QtySold = "Qty Sold";
    public static String Count = "Count";
    public static String BillCancelled = "Bill Cancelled";
    public static String BillCancel = "Bill Cancel";
    public static String BillRefund = "Bill Refund";
    public static String Quantity = "Quantity";
    public static String AMTNettCancel = "AMT Nett Cancel";
    public static String TaxCancel = "Tax Cancel";
    public static String BillPaid = "Bill Paid";
    public static String PasswordLength = "Password must be 4 digits.";
    public static String Deny = "Sorry! Please choose other Staff!";
//    public static Integer billFontSize = 16;
    public static String Zeroes = "00000000";
    public static String encrypt_password = "test123";
    public static String Downaloding = "Downloading..";
    public static String SyncPassword = "dcsSyn";
    public static String deleteLogPassword = "dcsSynDL";
    public static String Sync = "Data Sync..";
    public static String Progressing = "Progressing..";
    public static String ConfigPaymentHost = "Config_Payment_Host";
    public static String ConfigPaymentType = "Config_Payment_Type";
    public static String PromoType = "Promotype";
    public static String ProductModifier = "Product_Modifier";
    public static String NotEnoughAmount = "Not enought Amount! You want to pay with other payment?";
    public static String Exit = "Are you sure you want to exit?";
    public static String Print = "Are you sure you want to print?";
    public static String Delete = "Are you sure you want to delete?";
    public static String ResyncBillNoQuestion = "Are you sure you want to resync sale?";
    public static String KitchenPrinterPrintQuestion = "Are you sure you want to print kitchen printer?";
    public static String ReprintQuestion = "Are you sure you want to reprint?";
    public static String DeleteQuestion = "Are you sure you want to delete?";
    public static String NamePriceValueEmpty = "Name or Price value is empty!";
    public static String PriceValueZero = "Price value is less than zero!";
    public static String FillStockIn = "Please fill StockIn First.";
    public static String AdjQtyEmpty = "Adjustment Qty Empty!";
    public static String AdjDateEmpty = "Adjustment Datetime Empty!";
    public static String SavedSuccess = "Saved Successfully!";
    public static String QtyLessThanZero = "Input Qty is less than zero!";
    public static String InputQtyZero = "Input Qty is zero!";
    public static String InputQtyEmpty = "Input Qty is empty!";
    public static String AddProduct = "Add Product";
    public static String EditProduct = "Edit Product";
    public static String Add = "Add";
    public static String Update = "Update";
    public static String MercatusMember = "Mercatus Member?";
    public static String BarCodeNotFound = "BarCode Not Found!";
    public static String BarCodeNeedToAdd = "Please add BarCode Value!";
    public static String StockAdjSymbmol = "-";
    public static String PAX = "PAX";
    public static String INGENICO = "INGENICO";
    public static String INGENICO_MODEL_APOS_A8OVS = "APOS A8OVS";
    public static String INGENICO_MODEL_DX8000 = "DX8000";
    public static String PAX_E600M = "PAX_E600M";

    public static String K21_DRIVER_NAME = "com.newland.me.K21Driver";
    public static String PINPAD_DEVICE_NAME = DeviceName.IPP;
    public static String RF_DEVICE_NAME = RFDeviceName.INNER;


    public static String ZReadNoFormat = "Z";


//    public enum INGENICO {
//        INGENICO,
//        INGENICO_MODEL_APOS_A8OVS,
//        INGENICO_MODEL_DX8000
//    }

    //INGENICO
    public static Integer checkOutScrollViewWidthIngenico = 370;

    //public static String dateYMD = "yyyy-MM-dd";
    public static String dateYMD_Sync = "yyyy-MM-dd";
    public static String dateYMD_YMDHMS = "yyyy-MM-dd HH:mm:ss";
    public static String dateY = "YY";
    //public static String dateYMD = "MM/dd/YYYY";
    public static String dateYMD = "MM/dd/yyyy";
//    public static String sqldateformat = "%Y-%m-%d";
    public static String sqldateformat = "%m/%d/%Y";
    public static String sqldateformat_sync = "%Y-%m-%d";
    public static String sqldateformatYMDHMS_sync = "%Y-%m-%d %H:%M:%S";
//    public static String dateYMD_format2 = "yyyy-MM-dd";
    @SuppressLint("SimpleDateFormat")
    public static String from_date = "0";
    @SuppressLint("SimpleDateFormat")
    public static String to_date = "0";

    public static String BOARD = "BOARD";
    public static String MODEL = "MODEL";
    public static String BRAND = "BRAND";
    public static String DEVICE = "DEVICE";
    public static String DISPLAY = "DISPLAY";
    public static String PRODUCT = "PRODUCT";
    public static String TERMINAL_TYPE = "TERMINAL_TYPE";
    public static String TERMINAL_VERSION = "VERSION";
    public static String VERSION_NAME = "3.3.23";
    public static String NewLand = "NewLand";
    public static String A930 = "A930";
    public static String E600M = "E600M";
    public static String LANDI = "LANDI";
    public static String ALPS = "alps";
    public static String IMIN = "IMIN";
    public static String iMin = "iMin";
    public static String Verifone = "Verifone";

    public static String Add_Modifier = "Add Modifier";
    public static String EDIT_Modifier = "Edit Modifier";
    public static String ADD = "Add";
    public static String UPDATE = "Update";

    public static String ProductManagement = "Product Management";
    public static String ZCloseTitle = "ZClose";


    public static String ONLINESALESENQUIRY = "ONLINESALESENQUIRY";
    public static String FNB_Function = "FNB Function";
    public static String Service_Function = "Service Function";
    public static String Stock_Transfer_Function = "Stock Transfer Function";
    public static String Resync_Stock_Transfer = "Resync Stock Transfer";
    public static String STKTRANSFER = "STKTRANSFER";
    public static String New_Transfer = "New Transfer";
    public static String Received_Transfer = "Received Transfer";
    public static String STKTRANSENQUIRY = "STKTRANSENQUIRY";
    public static String STKTRANSREPRINT = "STKTRANSREPRINT";
    public static String Deposit_Function = "Deposit Function";
    public static String DEPOSIT = "DEPOSIT";
    public static String Cancel_Deposit = "Cancel Deposit";
    public static String Finalize_Deposit = "Finalize Deposit";
    public static String DEPOSITENQUIRY = "DEPOSITENQUIRY";
    public static String Membership_Function = "Membership Function";
    public static String CLEARCUST = "CLEARCUST";
    public static String CUSTENQUIRY = "CUSTENQUIRY";
    public static String View_Member_History = "View Member History";
    public static String ADDNEWCUST = "ADDNEWCUST";
    public static String Hourly_Sales_Report = "Hourly Sales Report";
    public static String PREVIEWHOURLY = "PREVIEWHOURLY";
    public static String Print_Hourly_Report = "Print Hourly Report";
    public static String Email_Hourly_Report = "Email Hourly Report";
    public static String X_Z_Reading = "X/Z Reading";
    public static String XZRPT = "XZRPT";
    public static String X_Reports = "X-Reports";
    public static String X_Report_By_Cashier = "X-Report By Cashier";
    public static String X_Report_By_Department = "X-Report By Department";
    public static String Z_Reports = "Z-Reports";
    public static String Z_Closing = "Z-Closing";
    public static String Print_Z_Report = "Print Z-Report";
    public static String XZPRINTOPTIONS = "XZPRINTOPTIONS";
    public static String REPRINTZRPT = "REPRINTZRPT";
    public static String LOGINSYSTEM = "LOGINSYSTEM";
    public static String ORDERINGONLINE = "ORDERINGONLINE";
    public static String Customize_Function = "Customize Function";
    public static String ONOFFPRINTER = "ONOFFPRINTER";
    public static String ORDERING = "ORDERING";
    public static String EDITQTY = "EDITQTY";
    public static String ITEMRECOGN = "ITEMRECOGN";
    public static String CANCELTRANS = "CANCELTRANS";
    public static String REPRINTBILL = "REPRINTBILL";
    public static String ITEMREFUND = "ITEMREFUND";
    public static String HOLDTRANS = "HOLDTRANS";
    public static String LINEDISC = "LINEDISC";
    public static String GROUPDISC = "GROUPDISC";
    public static String LINEVOID = "LINEVOID";
    public static String RECALLTRANS = "RECALLTRANS";
    public static String CLRGROUPDISC = "CLRGROUPDISC";
    public static String ITEMENQUIRY = "ITEMENQUIRY";
    public static String VOIDTRANS = "VOIDTRANS";
    public static String REFUNDTRANS = "REFUNDTRANS";
    public static String POENTRY = "POENTRY";
    public static String FLOATENTRY = "FLOATENTRY";
    public static String RAENTRY = "RAENTRY";
    public static String NOSALES = "NOSALES";
    public static String CHANGEUSER = "CHANGEUSER";
    public static String PRICELEVEL = "PRICELEVEL";
    public static String SALESPERSON = "SALESPERSON";
    public static String OPENPRICE = "OPENPRICE";
    public static String TIMEIN = "TIMEIN";
    public static String TIMEOUT = "TIMEOUT";
    public static String STAFFDISC = "STAFFDISC";
    public static String GLM = "GLM";
    public static String TLM = "TLM";
    public static String Machine_Setup = "Machine Setup";
    public static String TRAININGMODE = "TRAININGMODE";
    public static String Accessable = "0";
    public static String PASSFirstDigits = "XXXXX";


    public static String FL001 = "FL001"; //	LOGINSYSTEM
    public static String M0020 = "M0020"; //	X/Z Reading
    public static String F0086 = "F0086"; //	X-Report By Sales Person
    public static String F0088 = "F0088"; //	BOM Kit Report
    public static String F0089 = "F0089"; //	BOM Dekit Report
    public static String F0090 = "F0090"; //	X-Report By Department Monthly
    public static String F0091 = "F0091"; //	Commision Report By Sales Person
    public static String F0092 = "F0092"; //	X-Report By Sales Person(Member Type)
    public static String F0093 = "F0093"; //	Staff Meal Report
    public static String F0094 = "F0094"; //	Wastage Report
    public static String F0095 = "F0095"; //	Requisition Order Report
    public static String M0022 = "M0022"; //	Z-Reports
    public static String F0107 = "F0107"; //	XZPRINTOPTIONS
    public static String F0060 = "F0060"; //	REPRINTZRPT
    public static String F0001 = "F0001"; //	CANCELTRANS
    public static String F0003 = "F0003"; //	ITEMREFUND
    public static String F0005 = "F0005"; //	HOLDTRANS
    public static String F0019 = "F0019"; //	VOIDTRANS
    public static String F0025 = "F0025"; //	REFUNDTRANS
    public static String F0020 = "F0020"; //	XZRPT
    public static String F0030 = "F0030"; //	X-Reports
           // F0057
    public static String F0084 = "F0084"; //	X-Report By Cashier
    public static String F0085 = "F0085"; //	X-Report By Department
    public static String F0031 = "F0031"; //	Z-Closing
    public static String F0096 = "F0096"; //	Print Z-Report


//    public static String SupportActivity = "SupportActivity";
//    public static String ActivityMapSetup = "ActivityMapSetup";
//    public static String ModifierActivity = "ModifierActivity";
//    public static String StockManagementActivity = "StockManagementActivity";
//    public static String ReportSettingActivity = "ReportSettingActivity";
//    public static String ConfigurationHostActivity = "ConfigurationHostActivity";
//    public static String DatabaseImportExportActivity = "DatabaseImportExportActivity";
//    public static String StaffManagementActivity = "StaffManagementActivity";
//    public static String PermissionGroupActivity = "PermissionGroupActivity";
//    public static String GeneralSettingActivity = "GeneralSettingActivity";
//    public static String ProductManagementActivity = "ProductManagementActivity";
//    public static String CategoryManagementActivity = "CategoryManagementActivity";
//    public static String PrinterListActivity = "PrinterListActivity";
//    public static String PaymentListActivity = "PaymentListActivity";
//    public static String VouchersAndDiscountsActivity = "VouchersAndDiscountsActivity";
//    public static String TaxConfigurationActivity = "TaxConfigurationActivity";
//    public static String PosConfigurationActivity = "PosConfigurationActivity";
//    public static String ReceiptEditorActivity = "ReceiptEditorActivity";


    public static String sqldateformat_kitchenprinter_dmy = "%d-%m-%Y";
    public static String sqldateformat_kitchenprinter_h = "%H";
    public static String sqldateformat_kitchenprinter_m = "%M";
    public static String sqldateformat_kitchenprinter_s = "%S";
}
