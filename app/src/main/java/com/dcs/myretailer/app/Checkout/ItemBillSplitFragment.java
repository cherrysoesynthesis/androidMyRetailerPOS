package com.dcs.myretailer.app.Checkout;

import android.os.Bundle;
import androidx.annotation.Nullable;
//import android.support.design.widget.BottomSheetDialogFragment;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dcs.myretailer.app.R;

class ItemBillSplitFragment extends BottomSheetDialogFragment {
    public ItemBillSplitFragment() {
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
        return inflater.inflate(R.layout.fragment_item_bill_split_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        LinearLayout myLayt = (LinearLayout) view.findViewById(R.id.item_split_bill_layout);
//        myLayt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {

//                ItemBillSplitFragment itemBillSplitFragment = new ItemBillSplitFragment();
//                itemBillSplitFragment.show(getActivity().getSupportFragmentManager(), itemBillSplitFragment.getTag());
//            }
//        });
//        LinearLayout myaddquickproduct_layout = (LinearLayout) view.findViewById(R.id.addquickproduct_layout);
//        myaddquickproduct_layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {

//                AddQuickProductSheetFragment addQuickProductSheetFragment = new AddQuickProductSheetFragment();
//                addQuickProductSheetFragment.show(getActivity().getSupportFragmentManager(), addQuickProductSheetFragment.getTag());
//            }
//        });
    }
}

