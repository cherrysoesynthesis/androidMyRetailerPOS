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

import com.dcs.myretailer.app.Activity.CheckOutActivity;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.Setting.DiscountClass;
import com.dcs.myretailer.app.databinding.PercentageRecyclerViewBinding;

import java.util.ArrayList;
import java.util.List;

public class TabFragmentPercentage extends Fragment implements View.OnClickListener {
    public static String disVal = "";
    public static String St = "0";
    static List<DiscountClass> discounts = new ArrayList<DiscountClass>();
    public static String ChekingCheckoutOrEditProductFragment = "0";
    public static PercentageRecyclerViewBinding binding = null;
    public TabFragmentPercentage() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(
                inflater, R.layout.percentage_recycler_view, container, false);
        View view = binding.getRoot();

        binding.btnApply.setOnClickListener(this);

        showDisPercentageFun();

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
        showDisPercentageFun();
        super.onResume();
    }

    private void showDisPercentageFun() {
        discounts = Query.getDiscountAll("DiscountPercentage");

        //RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(getContext(),discounts,"product");
        RecyclerViewItemDiscountAdapter myAdapter = new RecyclerViewItemDiscountAdapter(getContext(),discounts,
                ChekingCheckoutOrEditProductFragment);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        binding.percentageRecyclerviewId.setLayoutManager(gridLayoutManager);
        binding.percentageRecyclerviewId.setAdapter(myAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_apply:
                //EditProductSheetFragment
                    Log.i("btn_apply__","btn_apply____1"+ItemDiscountActivity.name);
                    //CheckoutItem
                    if (ItemDiscountActivity.name.equals("Checkout")) {
                        Log.i("btn_apply__","btn_apply____2");
                        CheckOutActivity.str_percentage = binding.editTextDiscountAmount.getText().toString();
                        CheckOutActivity.str_percentage_value = binding.editTextDiscountAmount.getText().toString().replace(" % ","");

                        Intent intent = new Intent(getContext(), CheckOutActivity.class);
                        intent.putExtra("BillNo",CheckOutActivity.BillNo);
                        intent.putExtra("Status","TabFragmentPercentage");
                        intent.putExtra("StatusSALES","");
                        startActivity(intent);
                        ((Activity)getContext()).finish();
                    }else if (ItemDiscountActivity.name.equals("CheckoutItem")) {
                        Log.i("btn_apply__","btn_apply____2");
                        CheckOutActivity.str_percentage = binding.editTextDiscountAmount.getText().toString();
                        CheckOutActivity.str_percentage_value = binding.editTextDiscountAmount.getText().toString().replace(" % ","");

                        Intent intent = new Intent(getContext(), CheckOutActivity.class);
                        intent.putExtra("BillNo",CheckOutActivity.BillNo);
                        intent.putExtra("Status","TabFragmentPercentage_CheckoutForItem");
                        intent.putExtra("StatusSALES","");
                        startActivity(intent);
                        ((Activity)getContext()).finish();
                    }else{
                        Log.i("btn_apply__","btn_apply____3");
                        EditProductSheetFragment.str_percentage = binding.editTextDiscountAmount.getText().toString();
                        EditProductSheetFragment.str_percentage_value = binding.editTextDiscountAmount.getText().toString().replace(" % ","");

                        Intent intent = new Intent(getContext(), MainActivity.class);
                        intent.putExtra("name", "TabFragmentPercentage");
                        startActivity(intent);
                        ((Activity)getContext()).finish();

//                        FragmentManager manager = ((AppCompatActivity) getContext()).getSupportFragmentManager();
//                        EditProductSheetFragment editProductSheetFragment = new EditProductSheetFragment();
//                        editProductSheetFragment.show(manager, editProductSheetFragment.getTag());

                    }
                    //getActivity().finish();
                break;
        }
    }
}