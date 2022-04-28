package com.dcs.myretailer.app;

import android.content.Context;
import android.content.res.Resources;

import com.dcs.myretailer.app.Activity.TransactionDetailsActivity;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Printer.KitchenPrinter;
import com.dcs.myretailer.app.Query.Query;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ReprintKitchenPrinter {
    public ReprintKitchenPrinter(TransactionDetailsActivity context, Resources resourceVal,String BillNo){
        String chkKitchenPrinter = Query.GetOptions(28);
        if (chkKitchenPrinter.equals("1")) {

            Context c = context;
            final SweetAlertDialog syncDialog =  new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText(Constraints.KitchenPrinterPrintQuestion)
                    .setConfirmText(Constraints.YES)
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();

                            new KitchenPrinter(c, resourceVal, BillNo);
                        }
                    })
                    .setCancelButton(Constraints.No, new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                        }
                    });
            syncDialog.show();
            syncDialog.setCancelable(false);

        }
    }
}
