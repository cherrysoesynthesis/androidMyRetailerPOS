package com.dcs.myretailer.app.Cashier;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.dcs.myretailer.app.CheckOutActivity;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.Setting.DiscountClass;
import com.dcs.myretailer.app.databinding.PercentageRecyclerViewBinding;

import java.util.ArrayList;
import java.util.List;

public class TabFragmentAmount extends Fragment implements View.OnClickListener{
    public static String St = "0";
    public static String disVal = "0";
    public static String ChekingCheckoutOrEditProductFragment = "0";
    static List<DiscountClass> discounts = new ArrayList<DiscountClass>();
    public static PercentageRecyclerViewBinding binding = null;
    public TabFragmentAmount() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.percentage_recycler_view, container, false);
        View view = binding.getRoot();
        binding.btnApply.setOnClickListener(this);

        showDisAmountFun();

        String device = Query.GetDeviceData(Constraints.DEVICE);
        if (device.equals("M2-Max")) {
            LinearLayout.LayoutParams paramslayCardView = new LinearLayout.LayoutParams(700,
                    90);
            paramslayCardView.leftMargin = 50;
            paramslayCardView.topMargin = 20;
            binding.LayAmount.setLayoutParams(paramslayCardView);
            binding.btnApply.setLayoutParams(paramslayCardView);
        }

        return view;
    }

    @Override
    public void onResume() {
        showDisAmountFun();
        super.onResume();
    }

    private void showDisAmountFun() {
        discounts = Query.getDiscountAll("DiscountAmount");

        RecyclerViewItemAmountAdapter myAdapter = new RecyclerViewItemAmountAdapter(getContext(),discounts,ChekingCheckoutOrEditProductFragment);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        binding.percentageRecyclerviewId.setLayoutManager(gridLayoutManager);
        binding.percentageRecyclerviewId.setAdapter(myAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_apply:
//                EditProductSheetFragment.str_percentage = edit_text_discount_amount.getText().toString();
//                EditProductSheetFragment.str_percentage_value = edit_text_discount_amount.getText().toString().replace("$ ","");
               //                getActivity().finish();
//                Intent intent = new Intent(getContext(), MainActivity.class);
//                intent.putExtra("name", "TabFragmentAmount");
//                startActivity(intent);

                //getActivity().finish();
                if (ItemDiscountActivity.name.equals("Checkout")) {
                    CheckOutActivity.str_value = binding.editTextDiscountAmount.getText().toString().replace("$ ","");

                    Intent intent = new Intent(getContext(), CheckOutActivity.class);
                    intent.putExtra("BillNo",CheckOutActivity.BillNo);
                    intent.putExtra("Status","TabFragmentAmount");
                    intent.putExtra("StatusSALES","");
                    startActivity(intent);
                    ((Activity)getContext()).finish();
                }else if (ItemDiscountActivity.name.equals("CheckoutItem")) {
                    CheckOutActivity.str_value = binding.editTextDiscountAmount.getText().toString().replace("$ ","");

                    Intent intent = new Intent(getContext(), CheckOutActivity.class);
                    intent.putExtra("BillNo",CheckOutActivity.BillNo);
                    intent.putExtra("Status","TabFragmentAmount_CheckoutForItem");
                    intent.putExtra("StatusSALES","");
                    startActivity(intent);
                    ((Activity)getContext()).finish();
                }else{
                    EditProductSheetFragment.str_value = binding.editTextDiscountAmount.getText().toString().replace("$ ","");

                    Intent intent = new Intent(getContext(), MainActivity.class);
                    intent.putExtra("name", "TabFragmentAmount");
                    startActivity(intent);
                    ((Activity)getContext()).finish();
                }
                Log.i("___","CheckOutActivity_str_value___"+CheckOutActivity.str_value);
                Log.i("___","EditProductSheetFragment_str_value___"+EditProductSheetFragment.str_value);
                break;
        }
    }
}