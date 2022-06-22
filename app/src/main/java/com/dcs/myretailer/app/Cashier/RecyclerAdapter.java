package com.dcs.myretailer.app.Cashier;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.dcs.myretailer.app.Model.BillListModel;
import com.dcs.myretailer.app.Activity.CashLayoutActivity;
import com.dcs.myretailer.app.Activity.CheckOutActivity;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.Activity.RemarkMainActivity;
import com.dcs.myretailer.app.Activity.TransactionDetailsActivity;
import com.dcs.myretailer.app.databinding.RowListBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class RecyclerAdapter extends RecyclerView.Adapter {
    //private List<BillDetails> lstBillDetails;

    private LayoutInflater mInflater;
    private Context mContext;
    private List<BillListModel> mData;
    private final ViewBinderHelper binderHelper = new ViewBinderHelper();
    public static String selectedID = "0";
    private static Integer sales_id = 0;
    private static String recBillNo = "0";
    private static String recStatus = "";
    private static String recQueueNo = "";
    private static String recTableNo = "";
    private static String recTotalItems = "";
    private static String recTotalAmount = "";
    RowListBinding binding = null;
    public static String checkBillnodate = "";
    public static String dateFormat3 = "";
    public RecyclerAdapter(Context context, List<BillListModel> mData) {
        mContext = context;
        this.mData = mData;
        mInflater = LayoutInflater.from(context);

        // uncomment if you want to open only one row at a time
        // binderHelper.setOpenOnlyOne(true);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View view = mInflater.inflate(R.layout.row_list, parent, false);
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.row_list, parent, false);
        //View view = binding.getRoot();
        String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
        Log.i("terminalTypeVal__","terminalTypeVal_"+terminalTypeVal);
        if (terminalTypeVal.equals(Constraints.PAX_E600M)){
            FrameLayout.LayoutParams linearOverAllParams = new FrameLayout.LayoutParams(540,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
//            LinearLayout.LayoutParams linearOverAllParams = new LinearLayout.LayoutParams(540,
//                    ViewGroup.LayoutParams.WRAP_CONTENT);
//            linearOverAllParams.leftMargin = 10;
            binding.linearLayout.setLayoutParams(linearOverAllParams);

//            LinearLayout.LayoutParams linearOverAllParams1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT);
//            linearOverAllParams1.leftMargin = 2;
//            linearOverAllParams.rightMargin = 2;
//            binding.cardviewId.setLayoutParams(linearOverAllParams1);
        } else if (terminalTypeVal.equals(Constraints.IMIN)){
            String device = Query.GetDeviceData(Constraints.DEVICE);
            Log.i("terminalTypeVal__","terminalTypeVal_"+device);
            if (device.equals("M2-Max")) {
                    FrameLayout.LayoutParams linearOverAllParams1 = new FrameLayout.LayoutParams(800,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    binding.frontLayout.setLayoutParams(linearOverAllParams1);

                    FrameLayout.LayoutParams linearOverAllParams = new FrameLayout.LayoutParams(700,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    linearOverAllParams.gravity = Gravity.CENTER;
                    binding.linearLayout.setLayoutParams(linearOverAllParams);
            }
        }
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder h, int position) {
        final ViewHolder holder = (ViewHolder) h;


        recBillNo = mData.get(position).getBillNo();
        recStatus = mData.get(position).getSTATUS();
        recQueueNo = mData.get(position).getQueueNo();
        recTableNo = mData.get(position).getTableNo();
        recTotalItems = mData.get(position).getTotalItems();
        recTotalAmount = mData.get(position).getTotalAmount();

        if (mData != null && 0 <= position && position < mData.size()) {
            String text = "<font color='#a9aaad'>BILL#</font>"; //set Black color of name
            String str = text + recBillNo;
            if (recStatus.length() > 0) {
                if (!recStatus.toUpperCase().equals("0")) {
                    String status_text = "<br /><font color='#a9aaad'>STATUS#</font>";
                    str += status_text + recStatus;
                } else {

                    final String cancel_status = Query.getCancelByBillNo(recBillNo);

                    if (cancel_status.toUpperCase().equals("CANCEL")) {
                        String status_text = "<br /><font color='#a9aaad'>STATUS#</font>";
                        str += status_text + cancel_status.toUpperCase();
                    }
                }
            }

            if (recStatus.equals("HOLD")){
                if (recQueueNo != null) {
                    if (!recQueueNo.equals("0")) {
                        text = "<br /><font color='#a9aaad'>QueueNo#</font>"; //set Black color of name
                        str += text + recQueueNo;
                    }
                    if (!recTableNo.equals("0")) {
                        text = "<br /><font color='#a9aaad'>TableNo#</font>"; //set Black color of name
                        str += text + recTableNo;
                    }
                }
            }
            ((ViewHolder) h).binding.billNo.setText(Html.fromHtml(str));
            ((ViewHolder) h).binding.totalItems.setText("Total items:"+recTotalItems);

            ((ViewHolder) h).binding.datetime.setText(mData.get(position).getDate());

            Double ttl_amt = Double.valueOf(String.valueOf(recTotalAmount));

            if (ttl_amt >= 0.0){
                ((ViewHolder) h).binding.totalAmount.setText("$"+String.format("%.2f", ttl_amt));
            }else {
                ttl_amt = (-1) * ttl_amt;
                ((ViewHolder) h).binding.totalAmount.setText("-"+"$"+String.format("%.2f", ttl_amt));
            }
            //final String data = lstBillDetails.get(position);
            //final String data = mData.get(position).getID().toString();
            final String billNo = recBillNo;
            final String status = recStatus;

            // Use ViewBindHelper to restore and save the open/close state of the SwipeRevealView
            // put an unique string id as value, can be any string which uniquely define the data
            binderHelper.bind(holder.binding.swipeLayout, billNo);

            // Bind your data here
            holder.bind(billNo,status);


        }
    }

    @Override
    public int getItemCount() {
        if (mData == null)
            return 0;
        return mData.size();
    }

    /**
     * Only if you need to restore open/close state when the orientation is changed.
     * Call this method in {@link android.app.Activity#
//     * onSaveInstanceState(Bundle)}
     */
    public void saveStates(Bundle outState) {
        binderHelper.saveStates(outState);
    }

    /**
     * Only if you need to restore open/close state when the orientation is changed.
     * Call this method in {@link android.app.Activity#
     * onRestoreInstanceState(Bundle)}
     */
    public void restoreStates(Bundle inState) {
        binderHelper.restoreStates(inState);
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        RowListBinding binding;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = (RowListBinding) binding;
        }

        public void bind(final String billNo,final String status) {
            binding.deleteLayout.setVisibility(View.VISIBLE);
            binding.deleteLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String checktodaydate = "SELECT strftime('"+Constraints.sqldateformat+"', DateTime / 1000 + (3600*8), 'unixepoch') " +
                            "FROM Sales WHERE BillNo = '"+billNo+"'";
                    Cursor cursorchecktodaydate = DBFunc.Query(checktodaydate,false);

                    if (cursorchecktodaydate !=null ) {
                        if (cursorchecktodaydate.moveToNext()) {
                            checkBillnodate = cursorchecktodaydate.getString(0);
                        }
                        cursorchecktodaydate.close();
                    }
//                %m/%d/%Y

                    Date date1 = new Date();
                    dateFormat3 = new SimpleDateFormat("MM/dd/yyyy").format(date1).toString();

//                    if (!(dateFormat3.equals(checkBillnodate))) {
//                        final SweetAlertDialog pRefundDialog =   new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE)
//                                //.setTitleText("Cancelled Bill")
//                                .setContentText("Can't allow to refund for today's bill.")
//                                .setConfirmText(Constraints.OK)
//                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                    @Override
//                                    public void onClick(SweetAlertDialog sDialog) {
//                                        sDialog.dismissWithAnimation();
//                                    }
//                                }) ;
//                        pRefundDialog.show();
//                        pRefundDialog.setCancelable(false);
//                    } else {
//                 String accessablevoid = SyncActivity.volleyCheckPermission(this, Constraints.VOIDTRANS,"0");
//                 Log.i("__","Accessable_VOIDTRANS_"+accessablevoid);
                        String accessablecancel = "0";
                        if (RemarkMainActivity.userid.equals("1111")){
                            accessablecancel = "1";
                        }else {
                            accessablecancel = Query.SearchUserAccess(Constraints.F0019, RemarkMainActivity.userid,RemarkMainActivity.userpassword);
                        }

                        if (accessablecancel.equals("1")) {
                            new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("Are you sure you want to clear bill?")
                                    .setConfirmText(Constraints.YES)
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            //sDialog.dismissWithAnimation();

                                            sDialog.dismissWithAnimation();

                                            new AsyncTaskSync(mContext, billNo).execute();

                                        }
                                    })
                                    .setCancelButton(Constraints.No, new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            sDialog.dismissWithAnimation();
                                        }
                                    })
                                    .show();
                        }

//                    }

                }
            });
            String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
            if (terminalTypeVal.equals(Constraints.PAX_E600M)){
                binding.frontLayout.setLayoutParams(new LinearLayout.LayoutParams(630, ViewGroup.LayoutParams.WRAP_CONTENT));

//                front_layout
//                binding.pager.setLayoutParams(new LinearLayout.LayoutParams(750, 580));

//                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams (500, FrameLayout.LayoutParams.WRAP_CONTENT);
//                //B.setLayoutParams ( params );
//                binding.frontLayout.setLayoutParams(params);
//                binding.linearLayout.setLayoutParams(new LinearLayout.LayoutParams(400, ViewGroup.LayoutParams.WRAP_CONTENT));
            }

            binding.frontLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    String checktodaydate = "SELECT strftime('" + Constraints.sqldateformat + "', DateTime / 1000 + (3600*8), 'unixepoch') " +
//                            "FROM Sales WHERE BillNo = '" + recBillNo + "'";
//                    Cursor cursorchecktodaydate = DBFunc.Query(checktodaydate, false);
//
//                    if (cursorchecktodaydate != null) {
//                        if (cursorchecktodaydate.moveToNext()) {
//                            checkBillnodate = cursorchecktodaydate.getString(0);
//                        }
//                        cursorchecktodaydate.close();
//                    }
////                %m/%d/%Y
//
//                    Date date1 = new Date();
//                    dateFormat3 = new SimpleDateFormat("MM/dd/yyyy").format(date1).toString();
//
//                    if (!(dateFormat3.equals(checkBillnodate))) {
//                        final SweetAlertDialog pRefundDialog = new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE)
//                                //.setTitleText("Cancelled Bill")
//                                .setContentText("Can't allow to refund for today's bill.")
//                                .setConfirmText(Constraints.OK)
//                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                    @Override
//                                    public void onClick(SweetAlertDialog sDialog) {
//                                        sDialog.dismissWithAnimation();
//                                    }
//                                });
//                        pRefundDialog.show();
//                        pRefundDialog.setCancelable(false);
//                    } else {
////                 String accessablevoid = SyncActivity.volleyCheckPermission(this, Constraints.VOIDTRANS,"0");
////                 Log.i("__","Accessable_VOIDTRANS_"+accessablevoid);
//                        String accessablecancel = Query.SearchUserAccess(Constraints.F0019, RemarkMainActivity.userid, RemarkMainActivity.userpassword);
//                        if (accessablecancel.equals("1")) {



                            String displayText = "" + billNo + " clicked";

                            selectedID = billNo;
                            sales_id = getSalesIDByBillNo(selectedID);
                            final String status = getStatusIDByBillNo(selectedID);

                            if (sales_id > 0) {
                                Intent intent = new Intent(mContext, TransactionDetailsActivity.class);
                                intent.putExtra("BillNo", String.valueOf(selectedID));
                                mContext.startActivity(intent);
                                ((Activity) mContext).finish();
                            } else {
                                String STATUS = "";
                                String sqlstatus = "Select STATUS from BillList " +
                                        "where BillNo = '" + billNo + "'";
                                Cursor ccsqlstatus = DBFunc.Query(sqlstatus, false);
                                if (ccsqlstatus != null) {
                                    if (ccsqlstatus.moveToNext()) {
                                        STATUS = ccsqlstatus.getString(0);
                                    }
                                    ccsqlstatus.close();
                                }
                                if (STATUS.equals("PENDING")) {
                                    Intent intent = new Intent(mContext, CheckOutActivity.class);
                                    intent.putExtra("BillNo", selectedID);
                                    intent.putExtra("Status", "Bill");
                                    intent.putExtra("StatusSALES", status);
                                    mContext.startActivity(intent);
                                    ((Activity) mContext).finish();
                                } else {
                                    Intent intent = new Intent(mContext, TransactionDetailsActivity.class);
                                    intent.putExtra("BillNo", String.valueOf(selectedID));
                                    mContext.startActivity(intent);
                                    ((Activity) mContext).finish();
                                }
                            }
//                        } else {
//                            Query.DonothaveUserAccess(mContext);
//                        }
//                    }
                }
            });
        }
    }

    private static class AsyncTaskSync extends AsyncTask<Object, Void, Void> {
        Context c;
        String bno;
        public AsyncTaskSync(Context context,String billNo) {
            c = context;
            bno = billNo;
        }

        protected Void doInBackground(Object... params) {

            //String displayText = "" + data + " clicked";
            String displayText = "" + bno + " clicked";

            selectedID = bno;
            //selectedID = recBillNo;
            sales_id = getSalesIDByBillNo(selectedID);
            final String status = getStatusIDByBillNo(selectedID);

            CashLayoutActivity.cancelBill(bno,c,sales_id);

            MainActivity.binding.pager.setCurrentItem(2);
            Intent intent = new Intent(c, MainActivity.class);
            c.startActivity(intent);
            ((Activity)c).finish();
            MainActivity.binding.pager.setCurrentItem(2);
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
        }

        @Override
        protected void onPreExecute() {
        }
    }
    public static Integer getSalesIDByBillNo(String billNo){
        Integer sales_id = 0;
        String sql = "SELECT ID FROM SALES Where BillNo = '"+ billNo +"' ";
        //Log.i("_sql__",sql);
        Cursor c = DBFunc.Query(sql, false);
        if (c != null) {
            sales_id = 0;
            if (c.moveToNext()) {
                if (!c.isNull(0)) {
                    Log.i("DSF_Integer_", String.valueOf(c.getInt(0)));
                    sales_id = c.getInt(0);
                }
            }
            c.close();
        }
        return sales_id;
    }

    public static String getStatusIDByBillNo(String billNo){
        String status = "0";
        //String sql = "SELECT OnlineOrderBill FROM Bill Where BillNo = '"+ billNo +"' ";
        String sql = "SELECT OnlineOrderBill FROM DetailsBillProduct Where BillNo = '"+ billNo +"' ";

        Cursor c = DBFunc.Query(sql, false);
        if (c != null) {
            status = "0";
            if (c.moveToNext()) {
                if (!c.isNull(0)) {
                    status = c.getString(0);
                }
            }
            c.close();
        }
        return status;
    }

//    private void deleteBillByBillNo(String billNo) {
//        Integer billID = Query.findLatestID("BillNo","Bill",false);
//        Log.i("billID___","billID___"+billID);
//        String BNo = Query.findBillNoByBillID(billID);
//        Log.i("billID___","billIDbillNo___"+billNo);
//        Log.i("billID___","billIDBNo___"+BNo);
//        if (BNo.equals(billNo)) {
//            Query.CreateNewBillAndDetailsBillProduct();
//        }
//
//        DBFunc.ExecQuery("DELETE FROM BillDetails WHERE BillNo = '"+ billNo + "'", false);
//        DBFunc.ExecQuery("DELETE FROM Details_BillProduct WHERE BillNo = '"+ billNo + "'", false);
//        DBFunc.ExecQuery("DELETE FROM DetailsBillProduct WHERE BillNo = '"+ billNo + "'", false);
//        DBFunc.ExecQuery("DELETE FROM BillPLU WHERE BillNo = '"+ billNo + "'", false);
//        DBFunc.ExecQuery("DELETE FROM BillList WHERE BillNo = '"+ billNo + "'", false);
//        DBFunc.ExecQuery("DELETE FROM Bill WHERE TransNo = '"+ billNo + "'", false);
//
//        new AlertDialog.Builder(mContext, R.style.AlertDialogStyle)
//                .setMessage("BILL#"+billNo+" Deleted Successfully!")
//                .setCancelable(false)
//                .setPositiveButton(Constraints.OK, new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
////                            DBFunc.ExecQuery("INSERT INTO Bill (OpenDateTime, Cashier, CashierID, BalNo, OnlineOrderBill) VALUES " +
////                                    "(" + System.currentTimeMillis() + ",'" + DBFunc.PurifyString(Allocator.cashierName) + "', " +
////                                    Allocator.cashierID + ", '" + MainActivity.BillID + 1 + "', 'OFF')", false);
//                            Query.CreateNewBill("OFF","");
//
//
//                            //c = DBFunc.Query("SELECT BillNo FROM Bill where CloseDateTime IS NULL order by CloseDateTime DESC ", false);
//                            //c = DBFunc.Query("SELECT BillNo FROM Bill where CloseDateTime IS NULL order by BillNo DESC ", false);
//                            CheckOutActivity.sldIDArr.clear();
//                            String strbillNo= "";
//
//                            //strbillNo = MainActivity.billNo(MainActivity.BillID + 1);
//                            strbillNo = Query.findBillNoByBillID(MainActivity.BillID + 1);
//
//                            Integer totalItems = RecyclerViewAdapter.totalItems;
//                            ArrayList<String> sldIDArr = RecyclerViewAdapter.sldIDArr;
//                            ArrayList<Integer> sldQtyArr = RecyclerViewAdapter.sldQtyArr;
//                            ArrayList<String> sldNameArr = RecyclerViewAdapter.sldNameArr;
//                            ArrayList<String> sltPriceTotalArr = RecyclerViewAdapter.sltPriceTotalArr;
//                            ArrayList<String> sltBillDisArr = RecyclerViewAdapter.sltBillDisArr;
//                            ArrayList<String> sltCategoryIDArr = RecyclerViewAdapter.sltCategoryIDArr;
//                            ArrayList<String> sltCategoryNameArr = RecyclerViewAdapter.sltCategoryNameArr;
//                            Integer countSelectedArr = RecyclerViewAdapter.countSelectedArr;
//                            String BillNo = String.valueOf(MainActivity.BillID);
//                            Integer qty = 1;
//                            String dateFormat3 = CheckOutActivity.dateFormat55.format(new Date()).toString();
//                            String paymenttype = "Cash";
//                            String status = Constraints.SALES;
//                            String Itemstatus = Constraints.SALES;
//                            Double sub_total = 0.0;
//                            Double amount = 0.0;
//                            ArrayList<Integer> Modifier_ID =  ButtonAdapter.ID;
//                            ArrayList<String> vchQueueNo = RecyclerViewAdapter.vchQueueNo;
//                            ArrayList<String> intTableNo = RecyclerViewAdapter.intTableNo;
//                            Integer billIDDD = Query.getBillID(strbillNo);
//                            CheckOutActivity.SaveBill(billIDDD,strbillNo,sldNameArr,sldQtyArr,sltPriceTotalArr,sltBillDisArr,sltCategoryNameArr,
//                                    qty,totalItems,sldIDArr,sltCategoryIDArr,countSelectedArr,dateFormat3,status,
//                                    sub_total,amount,paymenttype,Modifier_ID,vchQueueNo,intTableNo,Itemstatus,RecyclerViewAdapter.slddisID,RecyclerViewAdapter.slddisName,
//                                    RecyclerViewAdapter.slddisTypeName,RecyclerViewAdapter.slddisType,RecyclerViewAdapter.slddisValue);
//                            //CheckOutActivity.SaveBill(strbillNo);
//
//                            Intent intent = new Intent(mContext, MainActivity.class);
//                            intent.putExtra("name", "RecyclerAdapter");
//                            mContext.startActivity(intent);
//                            ((Activity)mContext).finish();
////                            MainActivity.this.finish();
//
//                        }
//                    })
//                //.setNegativeButton("OK", null)
//                .show();
//    }
}