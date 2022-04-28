package com.dcs.myretailer.app.Setting.StockManagement;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.dcs.myretailer.app.Activity.StockAgentDetatilsActivity;
import com.dcs.myretailer.app.Activity.StockManagementActivity;
import com.dcs.myretailer.app.Cashier.MainActivity;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.Model.StockAgent;
import com.dcs.myretailer.app.databinding.ActivityStockAgentListviewBinding;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class StockManagementAdapter extends RecyclerView.Adapter{
    private LayoutInflater mInflater;
    private Context mContext;
    private List<StockAgent> mData;
    private final ViewBinderHelper binderHelper = new ViewBinderHelper();
    public static String selectedID = "0";
    private static Integer sales_id = 0;
    private static String recBillNo = "0";
    ActivityStockAgentListviewBinding binding = null;
    public StockManagementAdapter(Context context, List<StockAgent> mData) {
        mContext = context;
        this.mData = mData;
        mInflater = LayoutInflater.from(context);

        // uncomment if you want to open only one row at a time
        // binderHelper.setOpenOnlyOne(true);
    }

    @Override
    public StockManagementAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(mInflater,R.layout.activity_stock_agent_listview, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder h, @SuppressLint("RecyclerView") final int position) {
        final StockManagementAdapter.ViewHolder holder = (StockManagementAdapter.ViewHolder) h;
//        if (mData != null && 0 <= position && position < mData.size()) {


            final String plu_ID = mData.get(position).getPLUID().toString();
            final String actualQty = mData.get(position).getQtyActual().toString();
            final String adjustmentQty = mData.get(position).getQtyAdjustment().toString();
            final String countQty = mData.get(position).getQtyBalance().toString();


            String sql_plu = "SELECT Name,Price,Code,Image,ID,ProductCategoryID,ProductCategoryName,UUID,AllowOpenPrice " +
                    "FROM PLU WHERE ID = " + plu_ID ;
            Cursor c_plu = DBFunc.Query(sql_plu,true);
            if (c_plu != null){
                if (c_plu.moveToNext()){

                    String name = c_plu.getString(0);
                    String price = c_plu.getString(1);


                    SpannableString content = new SpannableString("Content");
                    content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
                    ((ViewHolder) h).binding.stockAgentPluName.setText(content);
                    ((StockManagementAdapter.ViewHolder) h).binding.stockAgentPluName.setText(name);
                    ((ViewHolder) h).binding.stockAgentPluActualQty.setText(actualQty);
                    ((ViewHolder) h).binding.stockAgentPluAdjustmentQty.setText(adjustmentQty);
                    ((StockManagementAdapter.ViewHolder) h).binding.stockAgentPluCountQty.setText(countQty);
                    ((ViewHolder) h).binding.stockAgentPluCountQty.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_CLASS_PHONE );
                }
                c_plu.close();
            }

        String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
        String device = Query.GetDeviceData(Constraints.DEVICE);
          if (terminalTypeVal.equals(Constraints.IMIN)) {

            if (device.equals("M2-Max")) {
                LinearLayout.LayoutParams linearOverAllParamsCard = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                //linearOverAllParams.leftMargin = 30;
                binding.cardviewId.setLayoutParams(linearOverAllParamsCard);
                FrameLayout.LayoutParams linearOverAllParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                //linearOverAllParams.leftMargin = 30;
                binding.layall.setLayoutParams(linearOverAllParams);

                LinearLayout.LayoutParams LayproductheaderParams = new LinearLayout.LayoutParams(400,
                        60);
                //LayproductheaderParams.leftMargin = 30;
                binding.stockAgentPluName.setLayoutParams(LayproductheaderParams);
            }

        }

            ((ViewHolder) h).binding.btnSubmitStockTake.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String countQty =  ((ViewHolder) h).binding.stockAgentPluCountQty.getText().toString();

                    Integer adjustQty = 0;
                    if (Integer.parseInt(countQty) != 0){
                        adjustQty = Integer.parseInt(countQty)  - Integer.parseInt(actualQty);

                        Integer QtySold = Integer.parseInt(actualQty);
                        Integer QtyAdjustment = adjustQty;
                        Integer QtyReturn = 0;
                        Integer QtyBalance = Integer.parseInt(countQty);
                        Integer QtyActual = Integer.parseInt(actualQty);
                        Integer PLUID = Integer.parseInt(plu_ID);

//                        String DateTime = Query.GetDateFormart55();
//
//                        StockAgent stockAgent = new StockAgent(QtySold,QtyAdjustment,QtyReturn,QtyBalance,QtyActual,PLUID,DateTime);
//                        Query.SaveStockAgent(stockAgent);

                        String searchPLUStockAgent = "SELECT PLUID FROM StockAgent WHERE PLUID = "+PLUID;
                        Cursor csearchPLUStockAgent = DBFunc.Query(searchPLUStockAgent,true);
                        if (csearchPLUStockAgent != null) {
                            if (csearchPLUStockAgent.getCount() == 0){
                                String DateTime = Query.GetDateFormart55();

                                StockAgent stockAgent = new StockAgent(QtySold,QtyAdjustment,QtyReturn,QtyBalance,QtyActual,PLUID,DateTime);
                                Query.SaveStockAgent(stockAgent);

                            } else {


                                String query = "UPDATE StockAgent SET ";
                                query += " QtyAdjustment = "+ QtyAdjustment +" ,";
                                query += " QtyReturn = "+ QtyReturn +" ,";
                                query += " QtyBalance = "+ QtyBalance +" ,";
                                query += " QtyActual = "+ QtyActual +" ,";
                                query += " DateTime = '" + System.currentTimeMillis() +"' ";
                                query += " WHERE PLUID = "+plu_ID+"";
                                DBFunc.ExecQuery(query, true);
                                Log.i("sdd__sales_br_id", String.valueOf(query));
                            }
                            csearchPLUStockAgent.close();
                        }


                        Query.UpdateOnHandQtyByPLUID(PLUID,Integer.parseInt(countQty));

                        DialogFun(PLUID,"Successful!");
                    }

                }
            });

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View v) {
                        String pluID = mData.get(position).getPLUID().toString();
                        Intent intent = new Intent(mContext, StockAgentDetatilsActivity.class);
                        MainActivity.category_status = "1";
                        MainActivity.str_tab_fragment_2 = "0";
                        intent.putExtra("StockAgent_pluID",pluID);
                        mContext.startActivity(intent);
                        //((Activity)mContext).finish();
                }
            });





//        }
    }

    private void DialogFun(final Integer plu_id , String str) {
        new SweetAlertDialog(mContext, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText(str)
                //.setContentText("Won't be able to recover this file!")
                //.setConfirmText("Yes,delete it!")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        //sDialog.dismissWithAnimation();
                        Intent i = new Intent(mContext, StockManagementActivity.class);
                        mContext.startActivity(i);
                        ((Activity) mContext).finish();

                    }
                })
//                        .setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
//                            @Override
//                            public void onClick(SweetAlertDialog sDialog) {
//                                sDialog.dismissWithAnimation();
//                            }
//                        })
                .show();
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
         ActivityStockAgentListviewBinding binding;

        public ViewHolder(ActivityStockAgentListviewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}