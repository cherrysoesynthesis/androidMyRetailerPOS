package com.dcs.myretailer.app.Cashier;

public class DeclarationConf {
        // LIST OF HOST TYPES (TAG 56)
        public static String HOST_TYPES_GLOBAL_PAYMENT = "Global Payment";
        public static String HOST_TYPES_OCBC = "OCBC";
        public static String HOST_TYPES_DBS = "DBS";
        public static String HOST_TYPES_BOC = "BOC";
        public static String HOST_TYPES_JERIPAY = "JERIPAY";
        public static String HOST_TYPES_MECATUS = "MERCATUS";
        public static String HOST_TYPES_AMERICAN_EXPRESS = "AMERICAN EXPRESS";
        public static String HOST_TYPES_UPI = "UPI"; // UNIONPAY
        public static String HOST_TYPES_JCB = "JCB"; //JAPANESE CREDIT BUREAU
        public static String HOST_TYPES_IPP = "IPP"; //INSTALMENT PAYMENT PLAN
        public static String HOST_TYPES_DINERS = "DINERS"; //DINERS
        public static String HOST_TYPES_ASCAN = "ASCAN"; //ASCAN
        // LIST OF CARD TYPES (TAG 56)
        public static String CARD_TYPE_CREDIT = "CREDITCARD";
        public static String CARD_TYPE_ALIPAY = "ALIPAY";
        public static String CARD_TYPE_APPLEPAY = "APPLEPAY";
        public static String CARD_TYPE_WECHAT_PAY = "WECHATPAY";
        public static String CARD_TYPE_GRAB_PAY = "GRABPAY";
        public static String CARD_TYPE_PAYNOW = "PAYNOW";
        public static String CARD_TYPE_EZLINK = "EZLINK";
        public static String CARD_TYPE_OTHERS = "OTHERS";
        public static String CARD_TYPE_NETS = "NETS";
        public static String CARD_TYPE_DEBIT_CARD = "DEBITCARD";
        public static String CARD_TYPE_GIFT_CARD = "GIFTCARD";

//        public static String PACKAGE_NAME_OCBC = "sg.com.mobileeftpos.paymentapplication";
//        //public static String CLASS_NAME_OCBC = "sg.com.mobileeftpos.paymentapplication.activities.ECRActivity";
//        public static String CLASS_NAME_OCBC = "sg.com.mobileeftpos.paymentapplication.ecr.EcrGatewayActivity";

        public static String PACKAGE_NAME_OCBC = "sg.com.eftpos.mobilepos.ocbc";
        public static String CLASS_NAME_OCBC = "sg.com.mobileeftpos.paymentapplication.ecr.EcrGatewayActivity";

        public static String PACKAGE_NAME_BOC = "sg.com.eftpos.mobilepos.boc";
        public static String CLASS_NAME_BOC = "sg.com.mobileeftpos.paymentapplication.ecr.EcrGatewayActivity";

        public static String PACKAGE_NAME_JERIPAY = "com.jeripay.payment";
        public static String CLASS_NAME_JERIPAY = "com.jeripay.payment.MainActivity";

        public static String PACKAGE_NAME_MERCATUS = "com.jeripay.mercatus";
        public static String CLASS_NAME_MERCATUS = "com.jeripay.mercatus.MainActivity";

        public static String PACKAGE_NAME_DBS = "sg.com.eftpos.mobilepos.dbs";
        public static String CLASS_NAME_DBS = "sg.com.mobileeftpos.paymentapplication.ecr.EcrGatewayActivity";

        public static String PACKAGE_NAME_ASCAN = "sg.com.eftpos.mobilepos.ascan";
        public static String CLASS_NAME_ASCAN = "sg.com.mobileeftpos.paymentapplication.ecr.EcrGatewayActivity";

        public static String PACKAGE_NAME_GLOBALPAYMENT = "com.pax.globalpay";
        public static String PACKAGE_NAME_GLOBALPAYMENT_APP_ID = "com.pax.globalpay";

        public static String EVENT_SALE = "C200";
        public static String EVENT_PREAUTH = "C201";
        public static String EVENT_OFFLINE = "C202"; // == PRE-AUTH CAPTURE
        public static String EVENT_REFUND = "C203";
        public static String EVENT_CASH_ADVANCE = "C204";
        public static String EVENT_CARD_NUMBER = "C209";
        public static String EVENT_VOID = "C300";
        public static String EVENT_INQUIRY = "C400";
        public static String EVENT_ECHO = "C902";
        public static String EVENT_ADJUST = "C500";
        public static String EVENT_SALE_ECRPE = "C600";
        public static String EVENT_PREAUTH_ECRPE = "C601";
        public static String EVENT_OFFLINE_ECRPE = "C602";
        public static String EVENT_REFUND_ECRPE = "C603";
        public static String EVENT_SETTLEMENT = "C700";
        public static String EVENT_EZLINK_SALE = "C610";
//        public static String EVENT_EZLINK_SALE = "C640";
        public static String EVENT_CEPAS_SALE = "C610";
        public static String EVENT_CEPAS_TOPUP = "C613";
        public static String EVENT_CEPAS_BALANCE = "C618";
        public static String EVENT_CONTACTLESS_SALE = "C630";
        public static String EVENT_WAIT = "";
        public static String EVENT_CALLBACK = "C922";
        public static String EVENT_MESSAGE_CALLBACK = "";
        public static String EVENT_BATCH_TOTAL_CALLBACK = "";
        public static String EVENT_MIFARE_READ_CARD_UID = "M209";

        public static String TMS_DOWNLOAD = "C800";
        public static String EVENT_BARCODESALE = "C701";
        public static String EVENT_BARCODEREFUND = "C702";
        public static String EVENT_AUTH = "C601";
        public static String EVENT_PRINT = "C905";
        public static String EVENT_DETAIL_REPORT = "C903";
        public static String EVENT_QRSCAN = "C603";
        public static String EVENT_BARCODE_ENTRY_MODE = "S";

        //MESSAGE (TAG 34)
        public static String MESSAGE_INSERT_CARD = "01";
        public static String MESSAGE_SWIFTLY_REMOVE_CARD = "02";
        public static String MESSAGE_REMOVE_CARD = "03";
        public static String MESSAGE_INSERT_CARD_SWIFTLY_REMOVE_CARD = "04";
        public static String MESSAGE_INSERT_OR_TAP_CARD = "05";
        public static String MESSAGE_TAP_CARD = "06";
        public static String MESSAGE_PIN_OK = "07";
        public static String MESSAGE_LAST_PIN_TRY = "08";
        public static String MESSAGE_INCORRECT_PIN = "09";
        public static String MESSAGE_ = "10";
        public static String MESSAGE_ENTER_PIN = "11";
        public static String MESSAGE_CONTACT_INTEFACE = "12";
        public static String MESSAGE_RETAP_CARD = "13";
        public static String MESSAGE_PHONE_INSTRUCTIONS = "96";
        public static String MESSAGE_WAINT_DO_NOT_REMOVE_CARD = "97";
        public static String MESSAGE_PROCESSING = "98";
        public static String MESSAGE_WAIT = "99";

        //Additional Printing Flag (TAG 96)  // RFU = 11 -99 // RFU = Reserved for future use
        public static String ADDITONAL_PRINTING_FLAG_SIGNATURE_LINE = "Signature line";
        public static String ADDITONAL_PRINTING_FLAG_DISCLAIMER_1 = "Disclaimer 1";
        public static String ADDITONAL_PRINTING_FLAG_DISCLAIMER_2 = "Disclaimer 2";
        public static String ADDITONAL_PRINTING_FLAG_DISCLAIMER_3 = "Disclaimer 3";
        public static String ADDITONAL_PRINTING_FLAG_DISCLAIMER_4 = "Disclaimer 4";
        public static String ADDITONAL_PRINTING_FLAG_DISCLAIMER_5 = "Disclaimer 5";
        public static String ADDITONAL_PRINTING_FLAG_DISCLAIMER_6 = "Disclaimer 6";
        public static String ADDITONAL_PRINTING_FLAG_DISCLAIMER_7 = "Disclaimer 7";
        public static String ADDITONAL_PRINTING_FLAG_PAN_MASK_FIRST_DIGIT = "PAN Mask First digit";
        public static String ADDITONAL_PRINTING_FLAG_PAN_MASK_SECOND_DIGIT = "PAN Mask Second digit";

        //TRANSACTION INFO (TAG 63)

        //EMV DATA (TAG 53)
        public static String EMV_DATA_LABEL_TVR = "TVR"; // Terminal Verification Results
        public static String EMV_DATA_LABEL_TSI = "TSI"; // Transaction Status Information
        public static String EMV_DATA_LABEL_AID = "AID"; // Chip Application ID (Right padded with spaces)
        public static String EMV_DATA_LABEL_APP_LABEL = "APP LABEL"; // Chip Application ID (Right padded with spaces)
        public static String EMV_DATA_LABEL_TC = "TC"; // Transacton Cryptogram

        public static String SOURCE_PATH = "com.eftpos.paymentapp.ocbc";
        public static String DESC_PATH = "sg.com.mobileeftpos.paymentapplication";

        public static String PACKAGE_NAME = "sg.com.mobileeftpos.paymentapplication";
        public static String CLASS_NAME = "sg.com.mobileeftpos.paymentapplication.activities.ECRActivity";

        public static String TITLE_SGD = "SGD $";
        public static String ENTER_AMOUNT = "Enter Amount(CENTS)";
        public static String TITLE_INVOICE_NUMBER = "Invoice No";
        public static String ENTER_INVOICE_AMOUNT = "Enter Invoice No";

        public static String submitSalesTransNo = "TransNo";
        public static String submitSalesRetailID = "RetailID";
        public static String submitSalesSalesDate = "SalesDate";
        public static String submitSalesSalesStatus = "SalesStatus";
        public static String submitSalesMemberID = "MemberID";
        public static String submitSalesSalesTaxTtl = "SalesTaxTtl";
        public static String submitSalesTaxType = "SalesTaxType";
        public static String submitSalesTaxRate = "SalesTaxRate";
        public static String submitSalesSalesRounding = "SalesRounding";
        public static String submitSalesItemRemarks = "ItemRemarks";
        public static String submitSalesItemID = "ItemID";
        public static String submitSalesItemQty = "ItemQty";
        public static String submitSalesItemUOMDesc = "ItemUOMDesc";
        public static String submitSalesItemPrice = "ItemPrice";
        public static String submitSalesItemDisc = "ItemDisc";
        public static String submitSalesItemTax = "ItemTax";
        public static String submitSalesItemPromoID = "PromoID";
        public static String submitSalesItemPromoType = "PromoType";
        public static String submitSalesItemPromoDisc = "PromoDisc";
        public static String submitSalesItemPromoTypeCode = "PromoTypeCode";
        public static String submitSalesItemTotal = "ItemTotal";
        public static String submitSalesItemSales = "ItemSales";
        public static String getInventoryResult = "";
        public static String getItemDepartmentResponse = "";
        public static String getItemDepartmentResult = "";
        public static String getInventoryUOMResult = "";
        public static String getInventoryMultipleUOMResult = "";
        public static String getItemPromotionResult = "";
        public static String getMemberResult = "";
        public static String find_1 = "SIEH CHENG";
        public static String find_2 = "";
        public static String getItemPromotiondateTime = "";
        public static String getItemPromotionItemSku = "";
        public static String getItemPromotionRetailId = "";
        public static String getItemPromotionQty = "";
        public static String getItemPromotionUOM = "";
        public static String getInventoryRetailID = "1";
        public static String getInventoryUOMItemSku = "";
        public static String getInventoryMultipleUOMRetailId = "";
        public static String ItemID = "";
        public static String ItemSKU = "";
        public static String ItemDescp = "";
        public static String SupBarCode = "SupBarCode";
        public static String UOM = "";
        public static String Price = "";
        public static String OnHandQty = "";
        public static String ID = "";
        public static String Value = "";
        public static String Nick = "";
        public static Integer countting = 0;

        public static String PAYMENT_TYPE_CASH = "cash";
        public static String PAYMENT_TYPE_MASTER = "master";
        public static String PAYMENT_TYPE_VISA = "visa";
        public static String PAYMENT_TYPE_NET = "NET";

        public static String WHITE_BOX_MAC_ADDRESS = "34:81:F4:1D:1E:3C";
        public static int QR_REQUEST = 111;
        public static int QR_VOUCHER_REQUEST = 222;

        public static String QTY_SOLD = "Qty Sold";
        public static String AMT_NETT = "AMT Nett";
        public static String AMT_GROSS = "AMT Gross";
        public static String AMOUNT = "Amount";
        public static String BILL_PAID = "Bill Paid";
        public static String BILL_CANCEL = "Bill Cancel";
        public static String TAX_TOTAL = "Tax Total";
        public static String AMT_DISCOUNT = "AMT Discount";
        public static String AMT_SURCHARGE = "AMT Surcharge";
        public static String ROUND_ADJ = "Round Adj";
        public static String AMT_COLLECTED = "AMT Collected";
        public static String QTY_CANCEL = "Qty Cancel";
        public static String AMT_CANCEL = "AMT Cancel";
        public static String AMT_NETT_CANCEL = "AMT Nett Cancel";
        public static String TAX = "Tax";
        public static String TAX_CANCEL = "Tax Cancel";
        public static String CANCEL_COUNT = "Cancel Count";
        public static String CANCEL_TAX = "Cancel Tax";
        public static String COUNT = "Count";
        public static String CANCEL_AMOUNT = "Cancel Amount";
        public static String SURCHARGE_COUNT = "Surcharge Count";
        public static String CANCEL_AMT = "Cancel AMT";
        public static String BILL_CANCELLED = "Bill Cancelled";
        public static String QUANTITY = "Quantity";
        public static String DISCOUNT_COUNT = "Discount Count";
        public static String GRAND_TOTAL_DISCOUNT = "Grand Total Discount";
        public static String REFER_INTO_SALES = "*Refer Info Sales*";
        public static String GRAND_TOTAL_REFER_SALES = "Grand Total Refer Sales";
        public static String DEPARTMENT_SALES = "*Department Sales*";
        public static String TOTAL_SALES = "*TOTAL SALES*";
        public static String PLU_SALES = "*PLU SALES*";
        public static String GRAND_TOTAL_PLU_SALES = "Grand Total PLU Sales";
        public static String DISCOUNT_AMT = "Disc Amt";
        public static String SURCHARGE = "Surcharge";
        public static String DISCOUNT_SURCHARGE = "*Discount/Surcharge*";
        public static String PAYMENT_COUNT = "*Payment Count*";
        public static String GRAND_TOTAL_PAYMENT = "Grand Total Payment";

        public static String TYPE_SALES = "sales";
        public static String TYPE_DEPT = "dept";
        public static String TYPE_PLU = "plu";
        public static String TYPE_DISC = "disc";
        public static String TYPE_PAY = "pay";
        public static String TYPE_REFER = "refer";

        public static String TYPE_VOID = "void";
        public static String TYPE_CANCELLATION = "*Cancellation*";
        public static String PRE_CANCELLATION = "Pre-Cancellation";
        public static String POST_CANCELLATION = "Post-Cancellation";
        public static String REPORT_CLEAR = "*REPORT CLEAR!*";
        public static String REPRINT_REPORT = "*REPRINT REPORT*";
        public static String BILL_UNPAID = "*BILL UNPAID*";
        public static String BILL_CANCELL = "*BILL CANCEL*";


        public static String Sync_billNo = "";
        public static String Sync_TransNo = "";
        public static String Sync_RetailID = "";
        public static String Sync_SalesDate = "";
        public static String Sync_SalesStatus = "";
        public static String Sync_MemberID = "";
        public static String Sync_SalesRounding = "";
        public static String Sync_SalesTaxTtl = "";
        public static String Sync_SalesTotalAmount = "";
        public static String Sync_ItemSalesID = "";
        public static String Sync_SalesPaymentsID = "";
        public static String SyncStatus = "";
        public static String sync_submitSalesItemID = "";
        public static String sync_SupBarCode = "";
        public static String sync_submitSalesItemQty = "";
        public static String sync_submitSalesItemUOMDesc = "";
        public static String sync_submitSalesItemPrice = "";
        public static String sync_submitSalesItemDisc = "";
        public static String sync_submitSalesItemTax = "";
        public static String sync_submitSalesItemPromoID = "";
        public static String sync_submitSalesItemPromoType = "";
        public static String sync_submitSalesItemPromoDisc = "";
        public static String sync_submitSalesItemPromoTypeCode = "";
        public static String sync_submitSalesItemTotal = "";
        public static String sync_submitSalesItemSales = "";

        public static String sync_PaymentID = "";
        public static String sync_strPayment = "";
        public static String sync_SalesPayTtl = "";
        public static String sync_SalesBalTtl = "";
        public static String sync_ChangeAmount = "";

}
