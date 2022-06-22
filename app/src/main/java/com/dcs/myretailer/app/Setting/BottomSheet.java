package com.dcs.myretailer.app.Setting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.dcs.myretailer.app.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheet extends BottomSheetDialogFragment implements View.OnClickListener {
    public BottomSheet() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_sheet_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayout linear_close = (LinearLayout) view.findViewById(R.id.linear_close);
        linear_close.setOnClickListener(this);

        LinearLayout addnewbill_layout = (LinearLayout) view.findViewById(R.id.addnewbill_layout);
        addnewbill_layout.setOnClickListener(this);

        LinearLayout addproductmanagement_layout = (LinearLayout) view.findViewById(R.id.addproductmanagement_layout);
        addproductmanagement_layout.setOnClickListener(this);

        LinearLayout myLayt = (LinearLayout) view.findViewById(R.id.addproduct_layout);
        myLayt.setOnClickListener(this);
        LinearLayout myaddcategory_layout = (LinearLayout) view.findViewById(R.id.addcategory_layout);
        myaddcategory_layout.setOnClickListener(this);
//        myLayt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i("hi","Click1");
//                AddProductSheetFragment addProductSheetFragment = new AddProductSheetFragment();
//                addProductSheetFragment.show(getActivity().getSupportFragmentManager(), addProductSheetFragment.getTag());
//            }
//        });
        LinearLayout myaddquickproduct_layout = (LinearLayout) view.findViewById(R.id.addquickproduct_layout);
        myaddquickproduct_layout.setOnClickListener(this);
//        myaddquickproduct_layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i("hi","Click1");
//                AddProductSheetFragment addProductSheetFragment = new AddProductSheetFragment();
//                addProductSheetFragment.show(getActivity().getSupportFragmentManager(), addProductSheetFragment.getTag());
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.addproductmanagement_layout:
//                Intent intent_product_management = new Intent(getContext(), ProductManagementActivity.class);
//                //intent_product_management.putExtra("name", "AddSheetFragment");
//                startActivity(intent_product_management);
//                getActivity().finish();
//                break;
//            case R.id.addnewbill_layout:
//
////                new AddSheetFragment.AsyncAddNewBill().execute();
//
//                Toast.makeText(getContext(), "New Bill Created Successfully!", Toast.LENGTH_LONG).show();
//
//                Intent intent = new Intent(getContext(), MainActivity.class);
//                intent.putExtra("name", "AddSheetFragment");
//                startActivity(intent);
//                getActivity().finish();
//                break;
//            case R.id.addproduct_layout:
//                dismiss();
//                AddProductSheetFragment addProductSheetFragment = new AddProductSheetFragment();
//                addProductSheetFragment.show(getActivity().getSupportFragmentManager(), addProductSheetFragment.getTag());
//                break;
//            case R.id.addcategory_layout:
//                dismiss();
//                AddCategorySheetFragment addCategorySheetFragment = new AddCategorySheetFragment();
//                addCategorySheetFragment.show(getActivity().getSupportFragmentManager(), addCategorySheetFragment.getTag());
//                break;
//            case R.id.addquickproduct_layout:
//                dismiss();
//                AddQuickProductSheetFragment addQuickProductSheetFragment = new AddQuickProductSheetFragment();
//                addQuickProductSheetFragment.show(getActivity().getSupportFragmentManager(), addQuickProductSheetFragment.getTag());
//                break;
            case R.id.linear_close:
                dismiss();
                break;
        }
    }


//    private class AsyncAddNewBill extends AsyncTask<Object, ImageView, Void> {
//
//        @Override
//        protected Void doInBackground(Object... params) {
//
//            Query.CreateNewBillAndDetailsBillProduct();
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//
//        }
//
//        @Override
//        protected void onPreExecute() {
//
//        }
//
//
//    }

}
