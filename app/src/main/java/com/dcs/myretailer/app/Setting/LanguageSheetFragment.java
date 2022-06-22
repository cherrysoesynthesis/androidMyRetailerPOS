package com.dcs.myretailer.app.Setting;

import android.os.Bundle;
import androidx.annotation.Nullable;
//import android.support.design.widget.BottomSheetDialogFragment;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.dcs.myretailer.app.R;

public class LanguageSheetFragment extends BottomSheetDialogFragment implements View.OnClickListener {
    public LanguageSheetFragment() {
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
        return inflater.inflate(R.layout.fragment_language_sheet_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout linear_close = (LinearLayout) view.findViewById(R.id.linear_close);
        linear_close.setOnClickListener(this);
        ListView simpleList;
        String countryList[] = {"EN - English", "CN - Chinese", "BM - Bahasa", "TA - Tamil", "TH - Thai", "KM - Khmer", "BUR - Burmese"};
//        LinearLayout myLayt = (LinearLayout) view.findViewById(R.id.item_split_bill_layout);
//        myLayt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i("hi","Click1");
//                ItemBillSplitFragment itemBillSplitFragment = new ItemBillSplitFragment();
//                itemBillSplitFragment.show(getActivity().getSupportFragmentManager(), itemBillSplitFragment.getTag());
//            }
//        });

        simpleList = (ListView) view.findViewById(R.id.simpleListViewLanguageSheet);
        //LanguageArrayAdapter<String> arrayAdapter = new LanguageArrayAdapter<String>(this, R.layout.activity_language_listview, R.id.textView, countryList);
        LanguageArrayAdapter arrayAdapter = new LanguageArrayAdapter(getActivity(), countryList);
        simpleList.setAdapter(arrayAdapter);

//        LinearLayout myaddquickproduct_layout = (LinearLayout) view.findViewById(R.id.addquickproduct_layout);
//        myaddquickproduct_layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i("hi","Click1");
//                AddQuickProductSheetFragment addQuickProductSheetFragment = new AddQuickProductSheetFragment();
//                addQuickProductSheetFragment.show(getActivity().getSupportFragmentManager(), addQuickProductSheetFragment.getTag());
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_close:
                dismiss();
                break;
        }
    }
}


