package com.dcs.myretailer.app;

import android.database.Cursor;
import android.graphics.Bitmap;

import androidx.appcompat.app.AlertDialog;

import com.dcs.myretailer.app.Activity.RemarkMainActivity;
import com.dcs.myretailer.app.Activity.SyncActivity;
import com.dcs.myretailer.app.Activity.TransactionDetailsActivity;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class RefundBill {
    public RefundBill(TransactionDetailsActivity context,
                      Integer sales_id, String BillNo, String STATUS
            , String dateFormat3, String checkBillnodate) {

        DBFunc.DBUserLog(Allocator.cashierName, Allocator.cashierID, Allocator.cashierAuth,
                System.currentTimeMillis(), DBFunc.PurifyString("TransactionDetailsActivity-OnClick " +"Refund Bill-"+BillNo));

        if (dateFormat3.equals(checkBillnodate)) {
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
            //                if (checkBillnodate.equals())
            //accessable = SyncActivity.volleyCheckPermission(this, Constraints.REFUNDTRANS,Constraints.Accessable);
            String accessablecancel = "0";
            if (RemarkMainActivity.userid.equals("1111")) {
                accessablecancel = "1";
            } else {
                accessablecancel = Query.SearchUserAccess(Constraints.F0025, RemarkMainActivity.userid, RemarkMainActivity.userpassword);
            }
            if (accessablecancel.equals("1")) {

                if (sales_id > 0) {
                    final SweetAlertDialog pRefundDialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                            //.setTitleText("Cancelled Bill")
                            .setContentText("Are you sure you want to refund?")
                            .setConfirmText(Constraints.YES)
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    RefundSales(context, BillNo, sales_id);

//                                    Toast.makeText(getApplicationContext(), "Refund" + " Successfully.", Toast.LENGTH_SHORT).show();
//                                    Intent intent = new Intent(getApplicationContext(), TransactionDetailsActivity.class);
//                                    intent.putExtra("BillNo", BillNo);
//                                    startActivity(intent);
//                                    finish();
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
            if (STATUS.equals("VOID")) {
                new AlertDialog.Builder(context, R.style.AlertDialogStyle)
                        .setMessage("Bill Already Cancel!")
                        .setCancelable(false)
                        .setNegativeButton(Constraints.OK, null)
                        .show();
            } else {
                if (STATUS.equals("Refund")) {
                    new AlertDialog.Builder(context, R.style.AlertDialogStyle)
                            .setMessage("Already Refund Bill!")
                            .setCancelable(false)
                            .setNegativeButton(Constraints.OK, null)
                            .show();
                }
            }
        }
    }

    private void RefundSales(TransactionDetailsActivity context, String BillNo, Integer sales_id) {

        Query.RefundBill(sales_id,"AA",BillNo);
//
//        //Refund
        String bno = BillNo;
        Integer sid = 0;
        String sql_sale_ = "SELECT BillNo,ID From Sales WHERE ReferenceBillNo = '"+BillNo+"'";
        Cursor csql_sale = DBFunc.Query(sql_sale_,false);
        if (csql_sale != null) {
            if (csql_sale.getCount() == 0){
                bno = BillNo;
                sid = 0;
            }
            if (csql_sale.moveToNext()) {
                bno = csql_sale.getString(0);
                sid = csql_sale.getInt(1);
            }
            csql_sale.close();
        }

        Bitmap bitmap__ = Query.GetLogPrint();

        Query.CheckEmulatorOrNot(context, sid, bno, "Refund","Refund",bitmap__,null);

        Query.UpdatStockAgentByStatus(BillNo);


        SyncActivity.ResyncOrNormal(context,bno,"","Normal","");


    }
}
