package com.dcs.myretailer.app.Cashier;

import android.os.Bundle;
//import android.support.design.widget.BottomSheetDialogFragment;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dcs.myretailer.app.R;

public class ClearBillSheetFragment extends BottomSheetDialogFragment {
    public ClearBillSheetFragment() {
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
        return inflater.inflate(R.layout.fragment_clear_bill_sheet_dialog, container, false);
    }
}
