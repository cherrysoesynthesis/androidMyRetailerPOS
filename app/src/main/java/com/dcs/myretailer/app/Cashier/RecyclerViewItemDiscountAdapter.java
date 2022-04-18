package com.dcs.myretailer.app.Cashier;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.dcs.myretailer.app.Activity.CheckOutActivity;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.Setting.DiscountClass;
import com.dcs.myretailer.app.databinding.ItemDiscountItemBookBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecyclerViewItemDiscountAdapter extends RecyclerView.Adapter<RecyclerViewItemDiscountAdapter.MyViewHolder> {

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

    ItemDiscountItemBookBinding binding = null;
    public RecyclerViewItemDiscountAdapter(Context mContext, List<DiscountClass> mData, String str) {
        this.mContext = mContext;
        this.mData = mData;
        this.str = str;
    }

    @Override
    public RecyclerViewItemDiscountAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        binding = DataBindingUtil.inflate(mInflater,R.layout.item_discount_item_book,parent,false);
        counter = new int[getItemCount()];
        return new MyViewHolder(binding);
    }
    @Override
    public void onBindViewHolder(final RecyclerViewItemDiscountAdapter.MyViewHolder holder, final int position) {
        holder.binding.itemDiscountPercentageName.setText(mData.get(position).getDiscount_Name());
        holder.binding.itemDiscountPercentage.setText(mData.get(position).getDiscount_Value()+" % ");
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
                        TabFragmentPercentage.binding.editTextDiscountAmount.setText(mData.get(position).getDiscount_Value() + " % ");
                    } else {
                        TabFragmentPercentage.binding.editTextDiscountAmount.setText("0");
                    }
                }
            }
        });
    }

    public static void NormalDisFun(MyViewHolder holder, Context mContext, List<DiscountClass> mData, Integer position, String str) {

        String selectedID = mData.get(position).getDiscount_ID().toString();
        TabFragmentPercentage.binding.editTextDiscountAmount.setText(mData.get(position).getDiscount_Value() + " % ");
        TabFragmentPercentage.St = "1";
        Log.i("DF_NormalDisFun___",str+"____"+String.valueOf(mData.get(position).getDiscount_ID()));
        if (str.equals("EditProductSheetFragment")) {
            EditProductSheetFragment.disID = String.valueOf(mData.get(position).getDiscount_ID());
            EditProductSheetFragment.disName = mData.get(position).getDiscount_Name();
            EditProductSheetFragment.disOptions = mData.get(position).getDiscount_Options();
            EditProductSheetFragment.disTypeName = mData.get(position).getDiscountType();
            EditProductSheetFragment.disType = mData.get(position).getType();
            EditProductSheetFragment.disValue = mData.get(position).getDiscount_Value();
        }else if (str.equals("Checkout")) {
            CheckOutActivity.billDisIDAmt = "";
            CheckOutActivity.billDisNameAmt = "";
            CheckOutActivity.billDisOptionsAmt = "";
            CheckOutActivity.billDisTypeNameAmt = "";
            CheckOutActivity.billDisTypeAmt = "";
            CheckOutActivity.billDisValueAmt = "";

            CheckOutActivity.billDisID = String.valueOf(mData.get(position).getDiscount_ID());
            CheckOutActivity.billDisName = mData.get(position).getDiscount_Name();
            CheckOutActivity.billDisOptions = mData.get(position).getDiscount_Options();
            CheckOutActivity.billDisTypeName = mData.get(position).getDiscountType();
            CheckOutActivity.billDisType = mData.get(position).getType();
            CheckOutActivity.billDisValue = mData.get(position).getDiscount_Value();
        }else {
            CheckOutActivity.disID = String.valueOf(mData.get(position).getDiscount_ID());
            CheckOutActivity.disName = mData.get(position).getDiscount_Name();
            CheckOutActivity.disOptions = mData.get(position).getDiscount_Options();
            CheckOutActivity.disTypeName = mData.get(position).getDiscountType();
            CheckOutActivity.disType = mData.get(position).getType();
            CheckOutActivity.disValue = mData.get(position).getDiscount_Value();
        }
        TabFragmentPercentage.disVal = mData.get(position).getDiscount_Value();
        holder.binding.cardviewItemDiscountPercentageId.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
//                holder.tv_book_title.setTextColor(ContextCompat.getColor(mContext, R.color.white));
//                holder.book_price_id.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        Log.i("selectedID",selectedID);
//                Toast.makeText(v.getContext(), "selectedID " + selectedID, Toast.LENGTH_SHORT).show();
//                //mContext.startActivity(new Intent(mContext, AddNewProductActivity.class));
//                Intent intent = new Intent(mContext, MainActivity.class);
//                intent.putExtra("name", "DiscountPercentage");
//                mContext.startActivity(intent);


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
//        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        input.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        input.setRawInputType(Configuration.KEYBOARD_12KEY);
//        alertDialog.setView(input, 10, 0, 10, 0); // 10 spacing, left and right
//        input.setSingleLine(true);
//        layout.setPadding(10, 0, 10, 0);
        input.setTextColor(ContextCompat.getColor(context, R.color.mine_shaft));
        input.setHint("Amount");
        input.setText("0");
//        input.setPadding(10, 0, 10, 0);

        LinearLayout.LayoutParams paramslayCardView = new LinearLayout.LayoutParams(50, ViewGroup.LayoutParams.WRAP_CONTENT);
        //FrameLayout.LayoutParams paramslayCardView = new FrameLayout.LayoutParams(580, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramslayCardView.leftMargin = 10;
        input.setLayoutParams(paramslayCardView);

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
                            TabFragmentPercentage.binding.editTextDiscountAmount.setText(inputValue + " % ");

                            holder.binding.itemDiscountPercentage.setText(inputValue+ " % ");

                            if (str.equals("EditProductSheetFragment")) {
                                EditProductSheetFragment.disValue = inputValue;
                            }else if (str.equals("Checkout")) {
                                CheckOutActivity.billDisValueAmt = "";
                                CheckOutActivity.billDisValue = inputValue;
                            }else {
                                CheckOutActivity.disValue = inputValue;
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

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ItemDiscountItemBookBinding binding;
        public MyViewHolder(ItemDiscountItemBookBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
