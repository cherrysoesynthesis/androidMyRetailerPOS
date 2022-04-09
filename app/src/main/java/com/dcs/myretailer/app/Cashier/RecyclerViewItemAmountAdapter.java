package com.dcs.myretailer.app.Cashier;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.dcs.myretailer.app.CheckOutActivity;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.Setting.DiscountClass;
import com.dcs.myretailer.app.databinding.ItemDiscountItemAmountBookBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecyclerViewItemAmountAdapter extends RecyclerView.Adapter<RecyclerViewItemAmountAdapter.MyViewHolder> {

    private Context mContext ;
    private List<DiscountClass> mData ;
    ArrayList<String> arr = new ArrayList<String>();
    HashMap<String,String> hashValues = new HashMap<String, String>();
    HashMap<String,String> hashValuesCount = new HashMap<String, String>();
    ArrayList selectedItems = new ArrayList();
    private String str ;
    private ProductMainPageFragment productMainPageFragment;
    int[] counter;
    public static Integer totalItems  = 0;
    public static Integer count_totalItems  = 0;
    public static Integer count_item_selected  = 0;
    public static Double totalAmount = 0.0;
    //public static String disVal = "";
    ArrayList<String> disamt = new ArrayList<String>();
    ItemDiscountItemAmountBookBinding binding = null;
    public RecyclerViewItemAmountAdapter(Context mContext, List<DiscountClass> mData, String str) {
        this.mContext = mContext;
        this.mData = mData;
        this.str = str;
    }

    @Override
    public RecyclerViewItemAmountAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        binding = DataBindingUtil.inflate(mInflater,R.layout.item_discount_item_amount_book,parent,false);
        counter = new int[getItemCount()];
//        return new RecyclerViewItemAmountAdapter.MyViewHolder(binding.getRoot());
        return new MyViewHolder(binding);
    }
    @Override
    public void onBindViewHolder(final RecyclerViewItemAmountAdapter.MyViewHolder holder, final int position) {

        holder.binding.itemDiscountPercentageName.setText(mData.get(position).getDiscount_Name());
        try {
            holder.binding.itemDiscountPercentage.setText("$"+String.format("%.2f", Double.valueOf(String.valueOf(mData.get(position).getDiscount_Value()))));

        }  catch (NumberFormatException e){
            holder.binding.itemDiscountPercentage.setText("$0.00");
        }
         holder.binding.cardviewItemDiscountPercentageId.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.pinlocktextcolor));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();

                String chkOpenDiscount = mData.get(position).getOpenDiscountStatus();


                NormalDisFun(holder,mContext,mData,position,str);

                if (chkOpenDiscount != null && chkOpenDiscount.equals("1")){
                    showOpenDiscountDialog(holder,mContext,str);
                }else {

                    if (mData.get(position).getDiscount_Value().length() > 0) {
                        TabFragmentAmount.binding.editTextDiscountAmount.setText("$ " + String.format("%.2f", Double.valueOf(mData.get(position).getDiscount_Value())));
                    } else {
                        TabFragmentAmount.binding.editTextDiscountAmount.setText("0.00");
                    }
                }


            }
        });
    }

    public static void showOpenDiscountDialog(MyViewHolder holder, Context context, String str) {
        // Creating alert Dialog with one Button
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context, R.style.AlertDialogStyle);

        //AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();

        // Setting Dialog Title
        alertDialog.setTitle("Open Discount");

        // Setting Dialog Message
//        alertDialog.setMessage("Enter Discount Value");
        final EditText input = new EditText(context);
        //input.setInputType(InputType.TYPE_CLASS_NUMBER);
        input.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        input.setRawInputType(Configuration.KEYBOARD_12KEY);
//        alertDialog.setView(input, 10, 0, 10, 0); // 10 spacing, left and right
//        input.setSingleLine(true);
//        layout.setPadding(10, 0, 10, 0);
        input.setTextColor(ContextCompat.getColor(context, R.color.mine_shaft));
        input.setHint("Amount");
        input.setText("0");
        alertDialog.setView(input);

        // Setting Icon to Dialog
//        alertDialog.setIcon(R.drawable.key);

        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        // Write your code here to execute after dialog
//                        Toast.makeText(context,"Password Matched", Toast.LENGTH_SHORT).show();
//                        Intent myIntent1 = new Intent(view.getContext(), Show.class);
//                        startActivityForResult(myIntent1, 0);

//                        NormalDisFun(holder,mContext,mData,position,str);

                        String inputValue = input.getText().toString();
                        if (inputValue != null && inputValue.length() > 0) {

                            TabFragmentAmount.binding.editTextDiscountAmount.setText("$ " + String.format("%.2f", Double.valueOf(inputValue)));

                            holder.binding.itemDiscountPercentage.setText("$ " + String.format("%.2f", Double.valueOf(inputValue)));

                            if (str.equals("EditProductSheetFragment")) {
                                EditProductSheetFragment.disValueAmt = inputValue;
                            }else if (str.equals("Checkout")) {
                                CheckOutActivity.billDisValue = "";

                                CheckOutActivity.billDisValueAmt = inputValue;
                            }else {
                                CheckOutActivity.disValueAmt = inputValue;
                            }
                        }
                    }
                });
        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog
                        dialog.cancel();
                    }
                });

        // closed

        // Showing Alert Message
        alertDialog.show();
    }

    private void NormalDisFun(MyViewHolder holder, Context mContext, List<DiscountClass> mData, int position, String str) {
        String selectedID = mData.get(position).getDiscount_ID().toString();
        TabFragmentAmount.binding.editTextDiscountAmount.setText("$ " + String.format("%.2f", Double.valueOf(mData.get(position).getDiscount_Value())));
        TabFragmentAmount.St = "1";
//                disVal = mData.get(position).getDiscount_Value().toString();
        TabFragmentAmount.St = "1";
        if (str.equals("EditProductSheetFragment")) {
            EditProductSheetFragment.disIDAmt = String.valueOf(mData.get(position).getDiscount_ID());
            EditProductSheetFragment.disNameAmt = mData.get(position).getDiscount_Name();
            EditProductSheetFragment.disOptionsAmt = mData.get(position).getDiscount_Options();
            EditProductSheetFragment.disTypeNameAmt = mData.get(position).getDiscountType();
            EditProductSheetFragment.disTypeAmt = mData.get(position).getType();
            EditProductSheetFragment.disValueAmt = mData.get(position).getDiscount_Value();
        }else if (str.equals("Checkout")) {
            CheckOutActivity.billDisID = "";
            CheckOutActivity.billDisName = "";
            CheckOutActivity.billDisOptions = "";
            CheckOutActivity.billDisTypeName = "";
            CheckOutActivity.billDisType = "";
            CheckOutActivity.billDisValue = "";

            CheckOutActivity.billDisIDAmt = String.valueOf(mData.get(position).getDiscount_ID());
            CheckOutActivity.billDisNameAmt = mData.get(position).getDiscount_Name();
            CheckOutActivity.billDisOptionsAmt = mData.get(position).getDiscount_Options();
            CheckOutActivity.billDisTypeNameAmt = mData.get(position).getDiscountType();
            CheckOutActivity.billDisTypeAmt = mData.get(position).getType();
            CheckOutActivity.billDisValueAmt = mData.get(position).getDiscount_Value();
        }else {
            CheckOutActivity.disIDAmt = String.valueOf(mData.get(position).getDiscount_ID());
            CheckOutActivity.disNameAmt = mData.get(position).getDiscount_Name();
            CheckOutActivity.disOptionsAmt = mData.get(position).getDiscount_Options();
            CheckOutActivity.disTypeNameAmt = mData.get(position).getDiscountType();
            CheckOutActivity.disTypeAmt = mData.get(position).getType();
            CheckOutActivity.disValueAmt = mData.get(position).getDiscount_Value();
        }
        TabFragmentAmount.disVal = mData.get(position).getDiscount_Value();
        holder.binding.cardviewItemDiscountPercentageId.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.pinlocktextcolor));
//                if (disamt.contains(selectedID)){
//                   // holder.item_discount_percentage.setText("$0.00");
//                    //disVal = "0.0";
//                    holder.cardview_item_discount_percentage_id.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.pinlocktextcolor));
//                    disamt.remove(selectedID);
//                }else {
//                    //disVal = mData.get(position).getDiscount_Value().toString();
//                    //holder.item_discount_percentage.setText(String.format("%.2f", Double.valueOf(String.valueOf(mData.get(position).getDiscount_Value()))));
//                    holder.cardview_item_discount_percentage_id.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
//                    disamt.add(selectedID);
//                }
//                holder.tv_book_title.setTextColor(ContextCompat.getColor(mContext, R.color.white));
//                holder.book_price_id.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        //Log.i("selectedID",selectedID);
//                Toast.makeText(v.getContext(), "selectedID " + selectedID, Toast.LENGTH_SHORT).show();
//                //mContext.startActivity(new Intent(mContext, AddNewProductActivity.class));
//                Intent intent = new Intent(mContext, MainActivity.class);
//                intent.putExtra("name", "DiscountAmount");
//                mContext.startActivity(intent);


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ItemDiscountItemAmountBookBinding binding;
        public MyViewHolder(ItemDiscountItemAmountBookBinding binding) {
            super(binding.getRoot());
            this.binding = (ItemDiscountItemAmountBookBinding) binding;
        }
    }
}
