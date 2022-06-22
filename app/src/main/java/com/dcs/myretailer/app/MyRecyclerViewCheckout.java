package com.dcs.myretailer.app;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewCheckout extends RecyclerView.Adapter<MyRecyclerViewCheckout.MyViewHolder>  implements View.OnClickListener  {
    String data[];
    TypedArray images;
    //MyRecyclerView mv;
    Activity act_context;
    RecyclerViewItemClickInterface listener;
    List<ViewModel> model;

    Context context;
    ArrayList<String> reportProductNameList;
    ArrayList<String>  reportProductPriceList;
    ArrayList<String>  reportProductQuantityList;
    ArrayList<String>  reportProductIDList;
    ArrayList<String>  reportDiscountList;
    ArrayList<String>  sldDiscountName;
    ArrayList<String>  sldDiscountType;
    ArrayList<String>  sldDiscountValue;
    LayoutInflater inflter;
//    public MyRecyclerViewCheckout(List<ViewModel> model,Activity act_context) {
    public MyRecyclerViewCheckout(Context context, ArrayList<String> reportProductQuantityList, ArrayList<String> reportProductNameList, ArrayList<String> reportProductPriceList, ArrayList<String> reportProductIDList, ArrayList<String> reportDiscountList,
                                  ArrayList<String> sldDiscountName, ArrayList<String> sldDiscountType, ArrayList<String> sldDiscountValue) {
        // TODO Auto-generated constructor stub
        this.model=model;
        this.act_context=act_context;
        this.context = context;
        this.reportProductNameList = reportProductNameList;
        this.reportProductPriceList = reportProductPriceList;
        this.reportProductQuantityList = reportProductQuantityList;
        this.reportProductIDList = reportProductIDList;
        this.reportDiscountList = reportDiscountList;
        this.sldDiscountName = sldDiscountName;
        this.sldDiscountType = sldDiscountType;
        this.sldDiscountValue = sldDiscountValue;
        inflter = (LayoutInflater.from(context));
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView imageView1;
        private TextView textView1;
        //private IMyViewHolderClicks listener;

        public MyViewHolder(View inflatedView) {
            super(inflatedView);
            // TODO Auto-generated constructor stub
            textView1=(TextView) inflatedView.findViewById(R.id.textView1Checkout);
            imageView1=(TextView) inflatedView.findViewById(R.id.imageView1Checkout);
        }
    }

    @Override
    public int getItemViewType(int position) {
        // TODO Auto-generated method stub
        Log.i("getIemViewTypePosition", ""+position);
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        // TODO Auto-generated method stub
        return model.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // TODO Auto-generated method stub
        holder.itemView.setTag(model.get(position));
        //holder.imageView1.setImageResource(model.get(position).getImage());
        holder.imageView1.setText(model.get(position).getName());
        holder.textView1.setText(model.get(position).getPrice());
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        // TODO Auto-generated method stub
        View v= LayoutInflater.from(act_context).inflate(R.layout.row_layout, parent, false);
        v.setOnClickListener(this);
        MyRecyclerViewCheckout.MyViewHolder vh = new MyViewHolder(v);
        Log.i("onCreateViewHolder", "ViewHolder created");
        return vh;
    }

//    public void removeItem(int position) {
//        mDataSet.remove(position);
//        notifyItemRemoved(position);
//        // Add whatever you want to do when removing an Item
//    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (listener != null) {
            ViewModel model = (ViewModel) v.getTag();
            listener.onItemclick(v,model);}
    }

    void setOnItemClickListener(RecyclerViewItemClickInterface listener){

        this.listener=listener;
    }

    public void remove(ViewModel item) {
        int position = model.indexOf(item);
        model.remove(position);
        notifyItemRemoved(position);
    }

}