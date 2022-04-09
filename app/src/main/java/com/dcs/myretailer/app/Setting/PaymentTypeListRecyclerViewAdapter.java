package com.dcs.myretailer.app.Setting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.databinding.RecyclerviewPaymentypeRowBinding;

import java.util.ArrayList;

public class PaymentTypeListRecyclerViewAdapter extends RecyclerView.Adapter<PaymentTypeListRecyclerViewAdapter.ViewHolder> {
    Context context;
    ArrayList<String> paymentTypeName;
    ArrayList<Integer> paymentTypeOnOff;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    RecyclerviewPaymentypeRowBinding binding = null;
    // data is passed into the constructor
    public PaymentTypeListRecyclerViewAdapter(Context context, ArrayList<String> paymentTypeName, ArrayList<Integer> paymentTypeOnOff) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.paymentTypeName = paymentTypeName;
        this.paymentTypeOnOff = paymentTypeOnOff;
    }


    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(mInflater,R.layout.recyclerview_paymentype_row, parent, false);
        return new ViewHolder(binding);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String animal = paymentTypeName.get(position);
        holder.binding.textView.setText(animal);
        String device = Query.GetDeviceData(Constraints.DEVICE);
        if (device.equals("M2-Max")) {
            LinearLayout.LayoutParams paramslayCardView = new LinearLayout.LayoutParams(500,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            holder.binding.textView.setLayoutParams(paramslayCardView);
        }

        if (paymentTypeOnOff.get(position) == 1){
            holder.binding.paymenttypeOnoff.setChecked(true);
        }else{
            holder.binding.paymenttypeOnoff.setChecked(false);
        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return paymentTypeName.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        RecyclerviewPaymentypeRowBinding binding;
        public ViewHolder(RecyclerviewPaymentypeRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return paymentTypeName.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}