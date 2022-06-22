//package com.dcs.myretailer.app;
//
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.database.Cursor;
//import android.os.Bundle;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.widget.RecyclerView;
//import android.text.Html;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.dcs.myretailer.app.Cashier.BillDetails;
//import com.dcs.myretailer.app.Cashier.ButtonAdapter;
//import com.dcs.myretailer.app.Cashier.MainActivity;
//import com.dcs.myretailer.app.Cashier.RecyclerAdapter;
//import com.dcs.myretailer.app.Cashier.RecyclerViewAdapter;
//import com.dcs.myretailer.app.Database.DBFunc;
//import com.dcs.myretailer.app.Query.Query;
//import com.chauthai.swipereveallayout.SwipeRevealLayout;
//import com.chauthai.swipereveallayout.ViewBinderHelper;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//public class PromotionRecyclerAdapter extends RecyclerView.Adapter {
//    //private List<BillDetails> lstBillDetails;
//
//    private LayoutInflater mInflater;
//    private Context mContext;
//    private List<BillDetails> mData;
//    private final ViewBinderHelper binderHelper = new ViewBinderHelper();
//    public static String selectedID = "0";
//    private static Integer sales_id = 0;
//    private static String recBillNo = "0";
//    public PromotionRecyclerAdapter(Context context, List<BillDetails> mData) {
//        mContext = context;
//        this.mData = mData;
//        mInflater = LayoutInflater.from(context);
//
//        // uncomment if you want to open only one row at a time
//        // binderHelper.setOpenOnlyOne(true);
//    }
//
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = mInflater.inflate(R.layout.row_list1, parent, false);
////        View view = mInflater.inflate(R.layout.row_list, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder h, int position) {
//        final ViewHolder holder = (ViewHolder) h;
//
//    }
//
//    @Override
//    public int getItemCount() {
//        if (mData == null)
//            return 0;
//        return mData.size();
//    }
//
//    /**
//     * Only if you need to restore open/close state when the orientation is changed.
//     * Call this method in {@link android.app.Activity#
//    //     * onSaveInstanceState(Bundle)}
//     */
//    public void saveStates(Bundle outState) {
//        binderHelper.saveStates(outState);
//    }
//
//    /**
//     * Only if you need to restore open/close state when the orientation is changed.
//     * Call this method in {@link android.app.Activity#
//     * onRestoreInstanceState(Bundle)}
//     */
//    public void restoreStates(Bundle inState) {
//        binderHelper.restoreStates(inState);
//    }
//
//    private class ViewHolder extends RecyclerView.ViewHolder {
//        TextView txt_promo_name;
//        public ViewHolder(View itemView) {
//            super(itemView);
//            txt_promo_name = (TextView) itemView.findViewById(R.id.txt_promo_name);
//            txt_promo_name.setText();
////            text = (TextView) itemView.findViewById(R.id.text);
//        }
//
//    }
//}