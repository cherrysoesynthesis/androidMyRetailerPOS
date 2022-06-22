package com.dcs.myretailer.app.Cashier;

import android.os.Bundle;
import androidx.annotation.Nullable;
//import android.support.design.widget.BottomSheetDialogFragment;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.R;

public class CashierFloatAmountSheetFragment extends BottomSheetDialogFragment implements View.OnClickListener {
    EditText cashier_float_amount;
    public CashierFloatAmountSheetFragment() {
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
        View mView = inflater.inflate(R.layout.fragment_casher_float_amt_sheet_dialog, container, false);
        //initChargeLayoutViews();
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        ImageView close_cashier_float_amt = (ImageView) view.findViewById(R.id.close_cashier_float_amt);
        LinearLayout linear_close = (LinearLayout) view.findViewById(R.id.linear_close);
        linear_close.setOnClickListener(this);
        cashier_float_amount = (EditText) view.findViewById(R.id.edit_text_cashier_float_amount);
        cashier_float_amount.setText(MainActivity.CashierFloatAmount);
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
        Button btn_cashier_float_amount = (Button) view.findViewById(R.id.btn_cashier_float_amount);
        btn_cashier_float_amount.setOnClickListener(this);
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
        //LinearLayout myLayt = (LinearLayout) view.findViewById(R.id.addproduct_layout);
//        myLayt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i("hi","Click1");
//                AddProductSheetFragment addProductSheetFragment = new AddProductSheetFragment();
//                addProductSheetFragment.show(getActivity().getSupportFragmentManager(), addProductSheetFragment.getTag());
//            }
//        });
//        LinearLayout myaddquickproduct_layout = (LinearLayout) view.findViewById(R.id.addquickproduct_layout);
//        myaddquickproduct_layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i("hi","Click1");
////                AddQuickProductSheetFragment addQuickProductSheetFragment = new AddQuickProductSheetFragment();
////                addQuickProductSheetFragment.show(getActivity().getSupportFragmentManager(), addQuickProductSheetFragment.getTag());
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_cashier_float_amount:
                saveCashierFloatAmount();
//                Intent intent = new Intent(getContext(), MainActivity.class);
//                intent.putExtra("name", "CashierFloatAmountSheetFragment");
//                getActivity().finish();
//                startActivity(intent);
                dismiss();
            case R.id.linear_close:
                dismiss();
            break;
        }
    }

    private void saveCashierFloatAmount() {
        String sql = "UPDATE Bill SET CashierFloatAmount = '" + cashier_float_amount.getText().toString() + "' WHERE BillNo = " + MainActivity.BillID;
        DBFunc.ExecQuery(sql, false);
    }
}

