package com.dcs.myretailer.app.Setting;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dcs.myretailer.app.Activity.AddNewProductActivity;
import com.dcs.myretailer.app.ProductData;
import com.dcs.myretailer.app.Cashier.ProductMainPageFragment;
import com.dcs.myretailer.app.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecyclerNoImageViewProductManagementAdapter extends RecyclerView.Adapter<RecyclerNoImageViewProductManagementAdapter.MyViewHolder> {

    private Context mContext ;
    private List<ProductData> mData ;
    ArrayList<String> arr = new ArrayList<String>();
    HashMap<String,String> hashValues = new HashMap<String, String>();
    HashMap<String,String> hashValuesCount = new HashMap<String, String>();
    ArrayList selectedItems = new ArrayList();
    private String str ;
    private ProductMainPageFragment productMainPageFragment;
    int[] counter;
    public static String edit_fragment_status  = "0";
    public static Integer totalItems  = 0;
    public static Integer count_totalItems  = 0;
    public static Integer count_item_selected  = 0;
    public static Integer count_selected_total  = 0;
    public static Double totalAmount = 0.0;
    public static Double ItemDiscountAmount = 0.0;
    Handler mHandler;
    public RecyclerNoImageViewProductManagementAdapter(Context mContext, List<ProductData> mData, String str) {
        this.mContext = mContext;
        this.mData = mData;
        this.str = str;
    }

    @Override
    public RecyclerNoImageViewProductManagementAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardveiwnoimage_item_book,parent,false);
        counter = new int[getItemCount()];
        return new RecyclerNoImageViewProductManagementAdapter.MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final RecyclerNoImageViewProductManagementAdapter.MyViewHolder holder, final int position) {

        holder.tv_book_title.setText(mData.get(position).getTitle());

        // holder.book_price_id.setText("$"+String.format("%.2f", Double.valueOf(mData.get(position).getPrice())));

        holder.book_price_id.setText("$"+String.format("%.2f", Double.valueOf(mData.get(position).getPrice())));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                String selectedID = mData.get(position).getProductID();
                String selectedCatID = mData.get(position).getProductCategoryID();
                String selectedCatName = mData.get(position).getProductCategoryName();

                //Toast.makeText(v.getContext(), "selectedID " + selectedID, Toast.LENGTH_SHORT).show();
                //mContext.startActivity(new Intent(mContext, AddNewProductActivity.class));
                Intent intent = new Intent(mContext, AddNewProductActivity.class);
                intent.putExtra("ID", String.valueOf(selectedID));
                mContext.startActivity(intent);
            }
        });

//            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    int position = holder.getAdapterPosition();
//                    count_selected ++;
//                    selectedItems.add(count_selected);
//                    Log.i("dsfdsf_selectedItems", String.valueOf(selectedItems));
//                    //Toast.makeText(v.getContext(), "Position is " + position, Toast.LENGTH_SHORT).show();
//                    holder.itemView.setBackgroundColor(Color.parseColor("#567845"));
//                    holder.product_linear_layout_id.setBackgroundColor(Color.parseColor("#337ab7"));
//                    return false;
//                }
//            });
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }
    public int getSelectedItemCount() {
        if (selectedItems.size() > 0){

            return selectedItems.size();
        }else {

            return 0;
        }
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout product_linear_layout_id;
        public TextView tv_book_title,book_price_id,txt_item_ccount,tstid;
        CardView cardView ;
        public MyViewHolder(View itemView) {
            super(itemView);
            product_linear_layout_id = (LinearLayout) itemView.findViewById(R.id.product_linear_layout_id) ;
            tv_book_title = (TextView) itemView.findViewById(R.id.book_title_id) ;
            book_price_id = (TextView) itemView.findViewById(R.id.book_price_id) ;
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
            txt_item_ccount = (TextView) itemView.findViewById(R.id.txt_item_ccount);
            tstid = (TextView) itemView.findViewById(R.id.tstid);

        }
    }


}
