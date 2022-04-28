package com.dcs.myretailer.app.Cashier;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dcs.myretailer.app.R;

import java.util.ArrayList;

class RecyclerViewBillHorizonalAdapter extends RecyclerView.Adapter<RecyclerViewBillHorizonalAdapter.MyViewHolder> {

    //private ArrayList<String> data;
    private Context mContext ;
    private ArrayList<String> mapname ;
    private ArrayList<String> billnamehorizontal ;
    public RecyclerViewBillHorizonalAdapter(Context mContext, ArrayList<String> mapname, ArrayList<String> billnamehorizontal) {
        this.mContext = mContext;
        this.mapname = mapname;
        this.billnamehorizontal = billnamehorizontal;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitle;
        private TextView mTitle1;
        RelativeLayout relativeLayout;

        public MyViewHolder(View itemView) {
            super(itemView);

            mTitle = itemView.findViewById(R.id.txtTitle);
            mTitle1 = itemView.findViewById(R.id.txtTitle1);
        }
    }

//    public RecyclerViewBillAdapter(ArrayList<String> data) {
//        this.data = data;
//    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_horizontal_row, parent, false);
        return new RecyclerViewBillHorizonalAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mTitle.setText(mapname.get(position));
        holder.mTitle1.setText(billnamehorizontal.get(position));
    }

    @Override
    public int getItemCount() {
        return mapname.size();
    }


    public void removeItem(int position) {
        mapname.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(String item, int position) {
        //mData.add(position, item);
        notifyItemInserted(position);
    }

    //public ArrayList<String> getData() {
    //return mData.
    //}
}
