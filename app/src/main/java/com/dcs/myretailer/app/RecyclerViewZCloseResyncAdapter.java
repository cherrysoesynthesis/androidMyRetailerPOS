package com.dcs.myretailer.app;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.dcs.myretailer.app.databinding.ItemZcloserResyncItemBookBinding;

import java.util.List;

public class RecyclerViewZCloseResyncAdapter  extends RecyclerView.Adapter<RecyclerViewZCloseResyncAdapter.MyViewHolder> {

    private Context mContext ;
    private List<String> mData ;
    private List<String> mDataValues ;
    private String str ;
    int[] counter;
    public static Integer totalItems  = 0;
    public static Integer count_totalItems  = 0;
    public static Integer count_item_selected  = 0;
    public static Double totalAmount = 0.0;

    ItemZcloserResyncItemBookBinding binding;

    public RecyclerViewZCloseResyncAdapter(Context mContext, List<String> mData, List<String> mDataValues) {
        this.mContext = mContext;
        this.mData = mData;
        this.mDataValues = mDataValues;
        this.str = str;
    }

    @Override
    public RecyclerViewZCloseResyncAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

//        View view ;
//        LayoutInflater mInflater = LayoutInflater.from(mContext);
//        binding = DataBindingUtil.inflate(mInflater,R.layout.item_zcloser_resync_item_book,parent,false);
//        counter = new int[getItemCount()];
//        return new RecyclerViewZCloseResyncAdapter.MyViewHolder(binding);

        LayoutInflater mInflater = LayoutInflater.from(mContext);
        binding = DataBindingUtil.inflate(mInflater,R.layout.item_zcloser_resync_item_book,parent,false);
        return new RecyclerViewZCloseResyncAdapter.MyViewHolder(binding);
    }
    @Override
    public void onBindViewHolder(final RecyclerViewZCloseResyncAdapter.MyViewHolder holder, final int position) {
//        holder.binding.itemDiscountPercentageName.setText(mData.get(position).getDiscount_Name());
//        holder.binding.itemDiscountPercentage.setText(mData.get(position).getDiscount_Value()+" % ");
//        holder.binding.cardviewItemDiscountPercentageId.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.pinlocktextcolor));

//        holder.binding.editTextTransFrom.setHint(mData.get(position));
//        holder.binding.editTextTransFrom.setText(mDataValues.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
//                String chkOpenDiscount = mData.get(position).getOpenDiscountStatus();
//
//                NormalDisFun(holder,mContext,mData,position,str);
//
//                if (chkOpenDiscount != null && chkOpenDiscount.equals("1")){
//                    showOpenDiscountDialog(holder,mContext,str);
//                }else {
//
//                    if (mData.get(position).getDiscount_Value().length() > 0) {
//                        TabFragmentPercentage.binding.editTextDiscountAmount.setText(mData.get(position).getDiscount_Value() + " % ");
//                    } else {
//                        TabFragmentPercentage.binding.editTextDiscountAmount.setText("0");
//                    }
//                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ItemZcloserResyncItemBookBinding binding;

        public MyViewHolder(ItemZcloserResyncItemBookBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
