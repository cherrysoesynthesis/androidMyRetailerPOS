package com.dcs.myretailer.app;

import com.dcs.myretailer.app.Activity.TransactionDetailsActivity;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class RefreshBill {
    public RefreshBill(TransactionDetailsActivity context, String BillNo) {
        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                System.currentTimeMillis(), DBFunc.PurifyString("TransactionDetailsActivity-OnClick " +"Refresh Bill-"+BillNo));

        Query.UpdateBillListForZClose(BillNo);

        final SweetAlertDialog refreshDialog =  new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Refresh Bill Success")
                .setConfirmText(Constraints.OK)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                });
        refreshDialog.show();
        refreshDialog.setCancelable(false);

    }
}
