package com.dcs.myretailer.app.Setting;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dcs.myretailer.app.R;

public class SyncSheetFragment extends BottomSheetDialogFragment {
    public SyncSheetFragment() {
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
        //return inflater.inflate(R.layout.fragment_casher_float_amt_sheet_dialog, container, false);
        View mView = inflater.inflate(R.layout.fragment_sync_sheet_dialog, container, false);
        //initChargeLayoutViews();
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        ImageView close_cashier_float_amt = (ImageView) view.findViewById(R.id.close_cashier_float_amt);
//        close_cashier_float_amt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getContext(), MainActivity.class);
//                intent.putExtra("name", "CashierFloatAmountSheetFragment");
//                getActivity().finish();
//                startActivity(intent);
//                //getDialog().setCanceledOnTouchOutside(true);
//                //Log.i("DASFDS", String.valueOf(view));
//                //getActivity().getFragmentManager().popBackStack();
//                //getActivity().getFragmentManager().popBackStack();
//                //getDialog().setCanceledOnTouchOutside(true);
//                //getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//            }
//        });
//        Button btn_cashier_float_amount = (Button) view.findViewById(R.id.btn_cashier_float_amount);
//        btn_cashier_float_amount.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getContext(), MainActivity.class);
//                intent.putExtra("name", "CashierFloatAmountSheetFragment");
//                getActivity().finish();
//                startActivity(intent);
//                //getDialog().setCanceledOnTouchOutside(true);
//                //Log.i("DASFDS", String.valueOf(view));
//                //getActivity().getFragmentManager().popBackStack();
//                //getActivity().getFragmentManager().popBackStack();
//                //getDialog().setCanceledOnTouchOutside(true);
//                //getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//            }
//        });
//        //LinearLayout myLayt = (LinearLayout) view.findViewById(R.id.addproduct_layout);
////        myLayt.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Log.i("hi","Click1");
////                AddProductSheetFragment addProductSheetFragment = new AddProductSheetFragment();
////                addProductSheetFragment.show(getActivity().getSupportFragmentManager(), addProductSheetFragment.getTag());
////            }
////        });
////        LinearLayout myaddquickproduct_layout = (LinearLayout) view.findViewById(R.id.addquickproduct_layout);
////        myaddquickproduct_layout.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Log.i("hi","Click1");
//////                AddQuickProductSheetFragment addQuickProductSheetFragment = new AddQuickProductSheetFragment();
//////                addQuickProductSheetFragment.show(getActivity().getSupportFragmentManager(), addQuickProductSheetFragment.getTag());
////            }
////        });
    }
}


