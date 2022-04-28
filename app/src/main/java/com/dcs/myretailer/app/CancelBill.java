package com.dcs.myretailer.app;

import androidx.appcompat.app.AlertDialog;

import com.dcs.myretailer.app.Activity.CashLayoutActivity;
import com.dcs.myretailer.app.Activity.RemarkMainActivity;
import com.dcs.myretailer.app.Activity.TransactionDetailsActivity;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class CancelBill {
    public CancelBill(TransactionDetailsActivity context,
                      Integer sales_id, String BillNo, String STATUS
            , String dateFormat3, String checkBillnodate) {
        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                System.currentTimeMillis(), DBFunc.PurifyString("TransactionDetailsActivity-OnClick " +"Cancel Bill-"+BillNo));

        if (!(dateFormat3.equals(checkBillnodate))) {
            final SweetAlertDialog pRefundDialog =   new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                    //.setTitleText("Cancelled Bill")
                    .setContentText("Can't allow to refund for today's bill.")
                    .setConfirmText(Constraints.OK)
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                        }
                    }) ;
            pRefundDialog.show();
            pRefundDialog.setCancelable(false);
        } else {
//                 String accessablevoid = SyncActivity.volleyCheckPermission(this, Constraints.VOIDTRANS,"0");
//                 Log.i("__","Accessable_VOIDTRANS_"+accessablevoid);
            String accessablecancel = "0";
            if (RemarkMainActivity.userid.equals("1111")){
                accessablecancel = "1";
            }else {
                accessablecancel = Query.SearchUserAccess(Constraints.F0019, RemarkMainActivity.userid,RemarkMainActivity.userpassword);
            }
            if (accessablecancel.equals("1")){
                if (STATUS.equals("VOID")){

                    new AlertDialog.Builder(context, R.style.AlertDialogStyle)
                            .setMessage("Already Cancel Bill!")
                            .setCancelable(false)
                            .setNegativeButton(Constraints.OK, null)
                            .show();

                }else {
                    final SweetAlertDialog pRefundDialog =   new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                            //.setTitleText("Cancelled Bill")
                            .setContentText("Are you sure you want to cancel?")
                            .setConfirmText(Constraints.YES)
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();


                                    CashLayoutActivity.cancelBill(BillNo,context,sales_id);

                                }
                            })
                            .setCancelButton(Constraints.No, new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();
                                }
                            });
                    pRefundDialog.show();
                    pRefundDialog.setCancelable(false);
                }
            } else {
                Query.DonothaveUserAccess(context);
            }
        }
    }
}
