package com.dcs.myretailer.app.Printer;


import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Gravity;

import com.dcs.myretailer.app.Activity.RemarkMainActivity;
import com.dcs.myretailer.app.Cashier.MainActivity;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Model.BillPaymentValue;
import com.dcs.myretailer.app.Model.Cancellation;
import com.dcs.myretailer.app.Model.CategorySales;
import com.dcs.myretailer.app.Model.Discount;
import com.dcs.myretailer.app.Model.JeripayReceiptData;
import com.dcs.myretailer.app.Model.MercatusReceiptData;
import com.dcs.myretailer.app.Model.OrderDetails;
import com.dcs.myretailer.app.Model.Payment;
import com.dcs.myretailer.app.Model.ProductSales;
import com.dcs.myretailer.app.Model.ReceiptData;
import com.dcs.myretailer.app.Model.ReceiptZCloseData;
import com.dcs.myretailer.app.Model.Refund;
import com.dcs.myretailer.app.Model.TotalSales;
import com.dcs.myretailer.app.PrinterUtil;
import com.pax.commonlib.utils.FontCache;
import com.pax.glwrapper.imgprocessing.IImgProcessing;
import com.pax.glwrapper.impl.GL;
import com.pax.glwrapper.page.IPage;

import java.util.ArrayList;
import java.util.List;

public class PrintReceiptGenerator {
    String TAG = "TxnReceiptGenerator";

    int FONT_BIG = 30;
    int FONT_NORMAL = 32;
    //int FONT_NORMAL = 24;
    int FONT_SMALL = 20;

    Typeface TYPE_FACE = FontCache.get("OpenSans-ExtraBold.ttf", RemarkMainActivity.context);
    public Bitmap generate(ReceiptData receiptDataJsonObj) {

        GL.init(RemarkMainActivity.context);
        IPage page = GL.getGL().getImgProcessing().createPage();
        page.adjustLineSpace(-5);

        //page.setTypeFace(FontCache.get("OpenSans-ExtraBold.ttf", RemarkMainActivity.context));
        page.setTypeFace(FontCache.get("verdana-bold.ttf", RemarkMainActivity.context));
//        page.setTypeFace(FontCache.get("verdana.ttf", RemarkMainActivity.context));

//        Typeface font = Typeface.createFromAsset(
//                activity.getAssets(),
//                "fonts/androidnation.ttf");


        String temp;
        String temp1;

        int shortLineNumber = 36;
        String dividerLine = "";
        for (int i = 0; i < shortLineNumber; i++) {
            dividerLine += "-";
        }

        // merchant ID
//        try {
            //JSONObject transaction = (JSONObject) details.get("transaction");
        showHeader(page,receiptDataJsonObj.getReceiptHeader(),"line",dividerLine);
        showHeader(page,receiptDataJsonObj.statusVoidBill,"line",dividerLine);
        showHeader(page,receiptDataJsonObj.statusCancelBill,"line",dividerLine);
        showHeader(page,receiptDataJsonObj.statusReprintBill,"line",dividerLine);
            showHeader(page,receiptDataJsonObj.getQueueNo(),"line",dividerLine);
            showHeader(page,receiptDataJsonObj.getOrderStatus(),"line",dividerLine);

            showHeader(page,receiptDataJsonObj.getReceiptNoDateTime(),"",dividerLine);
            showHeader(page,receiptDataJsonObj.getReceiptNo(),"line",dividerLine);
            showHeader(page,receiptDataJsonObj.getInvoiceNo(),"",dividerLine);
            showHeader(page,receiptDataJsonObj.getDeliveryText(),"",dividerLine);
            showHeader(page,"DESCRIPTION    QTY    PRICE","line",dividerLine);
            showHeader(page,receiptDataJsonObj.getDeliveryText(),"",dividerLine);

            ArrayList<OrderDetails> orderDetailsObj = receiptDataJsonObj.getOrderDetails();

            for (int iorderdetails = 0; iorderdetails < orderDetailsObj.size(); iorderdetails++) {
                OrderDetails orderDetailsModel = orderDetailsObj.get(iorderdetails);
                String qty = orderDetailsModel.getOderDetailsQty();
                String description = orderDetailsModel.getOderDetailDisName();
                String price = orderDetailsModel.getOderDetailPrice();
                page.addLine()
                    .addUnit(page.createUnit()
                            .setText(description)
                            .setFontSize(32)
                            .setGravity(Gravity.LEFT)
                            .setWeight(40.0f))
                    // .setWeight(2.0f))
                    .addUnit(page.createUnit()
                            .setText(qty)
                            .setFontSize(32)
                            .setGravity(Gravity.RIGHT)
                            .setWeight(40.0f))
                           // .setWeight(2.0f))
                    .addUnit(page.createUnit()
                            .setText(price)
                            .setFontSize(32)
                            .setGravity(Gravity.END)
                            .setWeight(40.0f));
                           // .setWeight(2.0f));


            }        page.addLine().addUnit(page.createUnit().setText(dividerLine)
                .setFontSize(FONT_NORMAL)
                .setGravity(Gravity.CENTER));


        showHeader(page,receiptDataJsonObj.getTotalItems(),"line",dividerLine);

        //SUB-TOTAL
        showNameAndValues(page,"SUB-TOTAL",receiptDataJsonObj.getSubTotal());

        //BillDiscount
        showNameAndValues(page,"BillDiscount",receiptDataJsonObj.getBillDiscount());
        showNameAndValues(page,receiptDataJsonObj.getBillDiscountNameValue(),"");

        //ServiceCharges
        showNameAndValues(page,receiptDataJsonObj.getServiceChargesName(),receiptDataJsonObj.getServiceChargesValue());

        //TAX
        showHeader(page,receiptDataJsonObj.getTaxInclusive(),"",dividerLine);
        showNameAndValues(page,receiptDataJsonObj.getTaxExclusiveName(),receiptDataJsonObj.getTaxExclusiveValue());

        //RoundAdj
        showNameAndValues(page,"Round Adj".toUpperCase(),receiptDataJsonObj.getRoundAdj());

        //TOTAL
        showNameAndValues(page,"TOTAL",receiptDataJsonObj.getBillTotal());


        ArrayList<BillPaymentValue> paymentBDetailsObj = receiptDataJsonObj.getBillPaymentDetailsValue();

        showHeader(page,"PAYMENT TYPE","",dividerLine);

        Log.i("orderDetailsModel__","orpaymentBDetailsObj.size()_"+paymentBDetailsObj.size());
        for (int i = 0; i < paymentBDetailsObj.size(); i++) {
            BillPaymentValue orderDetailsModel = paymentBDetailsObj.get(i);

            String name = orderDetailsModel.getBillPaymentValueDetailsName();
            String amount = orderDetailsModel.getBillPaymentValueDetailsAmount();
            String remarks = orderDetailsModel.getBillPaymentValueDetailsRemarks();


//
            if (name!= null && !(name.isEmpty())) {
                showNameAndValues(page,name,amount);
            }

            if (remarks!= null && !(remarks.isEmpty())) {
                showNameAndValues(page, "", remarks);
            }

        }

        //CHANGE
        showNameAndValues(page,"CHANGE",receiptDataJsonObj.getBillChangeAmount());

        //Mercatus
        MercatusReceiptDataFormat(page,receiptDataJsonObj);

        //Jeripay
        JeripayReceiptDataFormat(page,receiptDataJsonObj);

        page.addLine().addUnit(page.createUnit().setText(dividerLine)
                .setFontSize(FONT_NORMAL)
                .setGravity(Gravity.CENTER));


        showHeader(page,receiptDataJsonObj.getClosedSales(),"",dividerLine);
        showHeader(page,receiptDataJsonObj.getReceiptFooter(),"",dividerLine);
        showHeader(page,"\n","",dividerLine);

        IImgProcessing imgProcessing = GL.getGL().getImgProcessing();
        return imgProcessing.pageToBitmap(page, 550);
    }

    public Bitmap generateZClose(ReceiptZCloseData receiptDataJsonObj) {

        GL.init(RemarkMainActivity.context);
        IPage page = GL.getGL().getImgProcessing().createPage();
        page.adjustLineSpace(-5);

        page.setTypeFace(FontCache.get("verdana-bold.ttf", RemarkMainActivity.context));
//        page.setTypeFace(FontCache.get("verdana.ttf", RemarkMainActivity.context));

//        Typeface font = Typeface.createFromAsset(
//                activity.getAssets(),
//                "fonts/androidnation.ttf");


        String temp;
        String temp1;

        int shortLineNumber = 36;
        String dividerLine = "";
        for (int i = 0; i < shortLineNumber; i++) {
            dividerLine += "-";
        }

        // merchant ID
//        try {
            //JSONObject transaction = (JSONObject) details.get("transaction");

        TotalSales totalSales = receiptDataJsonObj.getTotalSales();
        ProductSales productSales = receiptDataJsonObj.getProductSales();
        CategorySales categorySales = receiptDataJsonObj.getCategorySales();
        List<Payment> payment = receiptDataJsonObj.getPayment();
        Discount discount = receiptDataJsonObj.getDiscount();
        Refund refund = receiptDataJsonObj.getRefund();
        Cancellation cancellation = receiptDataJsonObj.getCancellation();
        String ZCloseStatus = receiptDataJsonObj.getZCloseStatus();
        String ZClosedt = receiptDataJsonObj.getZClosedt();



        TotalSalesFormat(page,totalSales,dividerLine);
        showline(page,dividerLine);
        ProductSalesFormat(page,productSales,dividerLine);
        showline(page,dividerLine);
        CategorySalesFormat(page,categorySales,dividerLine);
        showline(page,dividerLine);
        PaymentFormat(page,payment,dividerLine);
        showline(page,dividerLine);
        DiscountFormat(page,discount,dividerLine);
        showline(page,dividerLine);
        RefundFormat(page,refund,dividerLine);
        showline(page,dividerLine);
        CancellationFormat(page,cancellation,dividerLine);
        showline(page,dividerLine);
        showHeader(page,ZCloseStatus,"line",dividerLine);
        showHeader(page,ZClosedt,"line",dividerLine);
        showHeader(page,"\n","",dividerLine);

        IImgProcessing imgProcessing = GL.getGL().getImgProcessing();
        return imgProcessing.pageToBitmap(page, 550);
    }

    private void CancellationFormat(IPage page, Cancellation cancellation, String dividerLine) {
        showHeader(page,"*Cancellation*","line",dividerLine);
        showNameAndValues(page,Constraints.Quantity,cancellation.getQuantity());
        showNameAndValues(page, Constraints.AMTNett,cancellation.getAMTNett());
        showNameAndValues(page,Constraints.TaxTotal,cancellation.getTaxTotal());
        showNameAndValues(page,Constraints.AMTSurcharge,cancellation.getAMTSurcharge());
    }

    private void RefundFormat(IPage page, Refund refund, String dividerLine) {
        showHeader(page,"*Refund*","line",dividerLine);
        showNameAndValues(page,Constraints.BillRefund,refund.getBillRefund());
        showNameAndValues(page,Constraints.Quantity,refund.getQuantity());
        showNameAndValues(page,Constraints.AMTNett,refund.getAMTNett());
        showNameAndValues(page,Constraints.AMTSurcharge,refund.getAMTSurcharge());
    }

    private void DiscountFormat(IPage page, Discount discount, String dividerLine) {
        showHeader(page,"*Discount*","line",dividerLine);
        showNameAndValues(page,"Total Item Dis",discount.getTotalItemDis());
        showNameAndValues(page,"Total Bill Dis",discount.getTotalBillDis());
    }

    private void PaymentFormat(IPage page, List<Payment> payment, String dividerLine) {
        showHeader(page,"*Payment*","line",dividerLine);
        for (int i = 0; i < payment.size(); i++) {
            Payment paymentdetails = payment.get(i);

            showNameAndValues(page,paymentdetails.PaymentTypeName,paymentdetails.getPaymentTypeAmount());
        }
    }

    private void CategorySalesFormat(IPage page, CategorySales categorySales, String dividerLine) {
        showHeader(page,"*Category*","line",dividerLine);
        showNameAndValues(page,Constraints.QtySold,categorySales.getQtySold());
        showNameAndValues(page,Constraints.AMTNett,categorySales.getAMTNett());
    }

    private void ProductSalesFormat(IPage page, ProductSales productSales, String dividerLine) {

        showHeader(page,"*Product SALES*","line",dividerLine);
        showNameAndValues(page,Constraints.QtySold,productSales.getQtySold());
        showNameAndValues(page,Constraints.Amount,productSales.getAmount());
    }

    private void TotalSalesFormat(IPage page, TotalSales totalSales, String dividerLine) {
        showHeader(page,"*TOTAL SALES*","line",dividerLine);
        showNameAndValues(page,Constraints.BillPaid,totalSales.getBillPaid());
        showNameAndValues(page,Constraints.BillCancel,totalSales.getBillCancel());
        showNameAndValues(page,Constraints.BillRefund,totalSales.getBillRefund());
        showNameAndValues(page,Constraints.QtySold,totalSales.getQtySold());
        showNameAndValues(page,Constraints.AMTNett,totalSales.getAMTNett());
        showNameAndValues(page,Constraints.TaxTotal,totalSales.getTaxTotal());
        showNameAndValues(page,Constraints.AMTDiscount,totalSales.getAMTDiscount());
        showNameAndValues(page,Constraints.AMTSurcharge,totalSales.getAMTSurcharge());
        showNameAndValues(page,Constraints.RoundAdj,totalSales.getRoundAdj());
        showNameAndValues(page,Constraints.QtyCancel,totalSales.getQtyCancel());
        showNameAndValues(page,Constraints.QtyRefund,totalSales.getQtyRefund());
    }

    private void JeripayReceiptDataFormat(IPage page, ReceiptData receiptDataJsonObj) {
        JeripayReceiptData jprd = receiptDataJsonObj.getJeripayInfo();
       // showHeader(page,"Card Information","","");
        showNameAndValues(page, "Card Information","");
        showNameAndValues(page, "Status",jprd.getStatus());
        showNameAndValues(page, "Acquirer",jprd.getAcquirer());
        showNameAndValues(page, "Acquirer PaymentID",jprd.getAcquirer_PaymentID());
        showNameAndValues(page, "Amount",jprd.getAmount());
        showNameAndValues(page, Constraints.CARDLABEL,jprd.getCard_Label());
        showNameAndValues(page, Constraints.CARDNUMBER,jprd.getCard_Number());
        showNameAndValues(page, Constraints.INVNUMBER,jprd.getInvoice_Number());
    }

    private void MercatusReceiptDataFormat(IPage page, ReceiptData receiptDataJsonObj) {
        MercatusReceiptData mrd = receiptDataJsonObj.getMercatusInfo();
        showHeader(page,mrd.getMemberStatus(),"","");
        showNameAndValues(page, Constraints.PaymentType,mrd.getPayment_type());
        showNameAndValues(page, Constraints.PaymentAmount,mrd.getPayment_amount());
        showNameAndValues(page, Constraints.CARDLABEL,mrd.getCard_Label());
        showNameAndValues(page, Constraints.CARDNUMBER,mrd.getCard_Number());
        showNameAndValues(page, Constraints.INVNUMBER,mrd.getInvoice_Number());
        showNameAndValues(page, Constraints.VoucherNumber,mrd.getVoucher_number());
        showNameAndValues(page, Constraints.VoucherAmount,mrd.getVoucher_amount());
    }

    private void showline(IPage page, String dividerLine) {
        page.addLine().addUnit(page.createUnit().setText(dividerLine)
                .setFontSize(FONT_NORMAL)
                .setGravity(Gravity.CENTER));
    }

    private void showNameAndValues(IPage page, String leftName, String rightValue) {

        try {

            if (rightValue != null && !rightValue.isEmpty() && !leftName.equals("0")) {
                page.addLine()
                        .addUnit(page.createUnit()
                                .setText(leftName)
                                .setFontSize(32)
                                .setGravity(Gravity.LEFT)
                                .setWeight(40.0f))
                        // .setWeight(2.0f))
                        .addUnit(page.createUnit()
                                .setText(rightValue)
                                .setFontSize(32)
                                .setGravity(Gravity.RIGHT)
                                .setWeight(40.0f));
            }
        } catch (Exception e) {
            Log.i("exception___","err_"+e.getMessage());
        }
    }

    private void showHeader(IPage page,String name,String status,String dividerLine) {
        if(name!=null && !name.isEmpty()) {
            page.addLine()
                    .addUnit(page.createUnit()
                            .setText(name)
                            .setFontSize(32)
                            .setGravity(Gravity.LEFT)
                            .setWeight(30.0f));

            if (status.equals("line")) {
                page.addLine().addUnit(page.createUnit().setText(dividerLine)
                        .setFontSize(FONT_NORMAL)
                        .setGravity(Gravity.CENTER));
            }
        }
    }
//
//    private Bitmap getImageFromAssetsFile(String fileName) {
//        Bitmap image = null;
//        AssetManager am = FinancialApplication.getApp().getResources().getAssets();
//        try {
//            InputStream is = am.open(fileName);
//            image = BitmapFactory.decodeStream(is);
//            is.close();
//        } catch (IOException e) {
//            com.pax.commonlib.utils.LogUtils.e(TAG, "", e);
//        }
//
//        return image;
//
//    }

//    public static String getPaddedNumber(long num, int digit) {
//        NumberFormat nf = NumberFormat.getInstance(Locale.US);
//        nf.setGroupingUsed(false);
//        nf.setMaximumIntegerDigits(digit);
//        nf.setMinimumIntegerDigits(digit);
//        return nf.format(num);
//    }

    private Bitmap loadSignature(byte[] data) {
        if (data == null) {
            return null;
        }
        return GL.getGL().getImgProcessing().jbigToBitmap(data);
    }

    public static void printReceipt(ReceiptData object){
        PrintReceiptGenerator txnReceipt = new PrintReceiptGenerator();
//        JSONObject js = new JSONObject();
//        js.put("transaction",)
        Bitmap receipImage = txnReceipt.generate(object);
        PrinterUtil.getInstance().print(receipImage);

        Bitmap barCode = object.getReceiptNoBarCode();
        Bitmap bitmap_qr_shoptima = object.getShoptima();
        Bitmap bitmap__ = object.getScaledBitmap();

        try {
            if (bitmap__ != null || !bitmap__.equals("0") || !bitmap__.equals("null")) {
                PrinterUtil.getInstance().printBitmap(bitmap__, false);
            }
        } catch (Exception e){

        }
        try {
            if (MainActivity.chk_pos_qr_code_.equals(true)) {
                if (bitmap_qr_shoptima != null) {
                    PrinterUtil.getInstance().printBitmap(bitmap_qr_shoptima,false);
                }
            } } catch (Exception e){

        }
        try {
            if (barCode != null || !barCode.equals("0") || !barCode.equals("null")) {
                PrinterUtil.getInstance().printBitmap(barCode,true);
            }
        } catch (Exception e){

        }

    }
    public static void printZCloseReceipt(ReceiptZCloseData object){
        PrintReceiptGenerator txnReceipt = new PrintReceiptGenerator();
//        JSONObject js = new JSONObject();
//        js.put("transaction",)
        Bitmap receipImage = txnReceipt.generateZClose(object);
        PrinterUtil.getInstance().print(receipImage);
    }
}