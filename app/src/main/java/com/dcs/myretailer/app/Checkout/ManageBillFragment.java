package com.dcs.myretailer.app.Checkout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;

import com.dcs.myretailer.app.Activity.CashLayoutActivity;
import com.dcs.myretailer.app.Cashier.MainActivity;
import com.dcs.myretailer.app.Activity.CheckOutActivity;
import com.dcs.myretailer.app.Database.DBFunc;
import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;
import com.dcs.myretailer.app.Activity.TransactionDetailsActivity;
import com.dcs.myretailer.app.databinding.FragmentManageBillDialogBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

//import android.support.design.widget.BottomSheetDialogFragment;

public class ManageBillFragment extends BottomSheetDialogFragment {
    FragmentManageBillDialogBinding binding = null;
    public ManageBillFragment() {
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
//        return inflater.inflate(R.layout.fragment_manage_bill_dialog, container, false);
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_manage_bill_dialog, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.linearClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
         binding.clearBillLayout.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   new AlertDialog.Builder(getContext(), R.style.AlertDialogStyle)
                           .setMessage(Constraints.DeleteQuestion)
                           .setCancelable(false)
                           .setPositiveButton(Constraints.YES, new DialogInterface.OnClickListener() {
                               public void onClick(DialogInterface dialog, int id) {
                                   deleteBillByBillNo(CheckOutActivity.BillNo);
                               }
                           })
                           .setNegativeButton(Constraints.No, null)
                           .show();
               }
           });
        binding.editBillLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.putExtra("name", "ManageBillFragment");
                getActivity().finish();
                //MainActivity.St = "1";
                startActivity(intent);
            }
        });
        binding.cancelBillLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CashLayoutActivity.cancelBill(CheckOutActivity.BillNo,getContext(),0);

                Intent intent = new Intent(getContext(), TransactionDetailsActivity.class);
                intent.putExtra("BillNo", CheckOutActivity.BillNo);
                startActivity(intent);
                (getActivity()).finish();
            }
        });
    }
    private void deleteBillByBillNo(String billNo) {
        Integer billID = Query.findLatestID("BillNo","Bill",false);

        String BNo = Query.findBillNoByBillID(billID);


        if (BNo.equals(billNo)) {
            Query.CreateNewBillAndDetailsBillProduct();
        }

        DBFunc.ExecQuery("DELETE FROM BillDetails WHERE BillNo = '"+ billNo + "'", false);
        DBFunc.ExecQuery("DELETE FROM Details_BillProduct WHERE BillNo = '"+ billNo + "'", false);
        DBFunc.ExecQuery("DELETE FROM DetailsBillProduct WHERE BillNo = '"+ billNo + "'", false);
        DBFunc.ExecQuery("DELETE FROM BillPLU WHERE BillNo = '"+ billNo + "'", false);
        DBFunc.ExecQuery("DELETE FROM BillList WHERE BillNo = '"+ billNo + "'", false);

        dismiss();

    }
}

