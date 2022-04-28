package com.dcs.myretailer.app;

import android.database.Cursor;

import com.dcs.myretailer.app.Activity.SyncActivity;
import com.dcs.myretailer.app.Activity.TransactionDetailsActivity;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ResyncBill {
    public ResyncBill(TransactionDetailsActivity context, String BillNo) {
        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                System.currentTimeMillis(), DBFunc.PurifyString("TransactionDetailsActivity-OnClick " +"Resync Bill-"+BillNo));

        Cursor config_values_pro_item = null;
        String url = "";
        config_values_pro_item = DBFunc.Query("SELECT retails_id,company_code,company_url,option FROM POSSys", true);
        if (config_values_pro_item != null) {
            while (config_values_pro_item.moveToNext()) {
                url = config_values_pro_item.getString(2);
            }
        }
        if (url != null) {
            final SweetAlertDialog syncDialog =  new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText(Constraints.ResyncBillNoQuestion)
                    .setConfirmText(Constraints.YES)
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();


//                                    Cursor config_values_pro_item = null;
//                                    String url = "";
//                                    config_values_pro_item = DBFunc.Query("SELECT retails_id,company_code,company_url,option FROM POSSys", true);
//                                    if (config_values_pro_item != null) {
//                                        while (config_values_pro_item.moveToNext()) {
//                                            url = config_values_pro_item.getString(2);
//                                        }
//                                    }

                            String syncUrl = "";
                            Cursor Cursor_Possys = Query.GetURLAndCodeFromPossys();
                            if (Cursor_Possys != null) {
                                while (Cursor_Possys.moveToNext()) {
                                    syncUrl = Cursor_Possys.getString(2);
                                }
                                Cursor_Possys.close();
                            }

                            if (syncUrl != null) {

                                SyncActivity.ResyncOrNormal(context,BillNo,"","Normal","");
                            }

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

        }else {
            new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Resync Sales")
                    .setConfirmText("URL Empty!")
                    .show();

        }
    }
}
