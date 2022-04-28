package com.dcs.myretailer.app.Checkout;

import android.os.Bundle;
//import android.support.design.widget.BottomSheetDialogFragment;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dcs.myretailer.app.R;
class VoucherScanFragment extends BottomSheetDialogFragment {
    public VoucherScanFragment() {
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
        return inflater.inflate(R.layout.fragment_voucher_scan_dialog, container, false);
//        return inflater.inflate(R.layout.fragment_add_sheet_dialog, container, false);
    }

//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        LinearLayout myLayt = (LinearLayout) view.findViewById(R.id.voucher_layout);
//        myLayt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {

//                VoucherScanFragment voucherScanFragment = new VoucherScanFragment();
//                voucherScanFragment.show(getActivity().getSupportFragmentManager(), voucherScanFragment.getTag());
//            }
//        });
////        LinearLayout myaddquickproduct_layout = (LinearLayout) view.findViewById(R.id.addquickproduct_layout);
////        myaddquickproduct_layout.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {

////                AddQuickProductSheetFragment addQuickProductSheetFragment = new AddQuickProductSheetFragment();
////                addQuickProductSheetFragment.show(getActivity().getSupportFragmentManager(), addQuickProductSheetFragment.getTag());
////            }
////        });
//    }
}

