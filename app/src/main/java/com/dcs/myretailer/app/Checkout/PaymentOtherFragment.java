//package com.dcs.myretailer.app.Checkout;
//
//import android.database.Cursor;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.LinearLayout;
//
//import androidx.annotation.Nullable;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.dcs.myretailer.app.Query.Query;
//import com.dcs.myretailer.app.R;
//import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
//
//import java.util.ArrayList;
//
////import android.support.design.widget.BottomSheetDialogFragment;
//
//public class PaymentOtherFragment extends BottomSheetDialogFragment implements View.OnClickListener {
//    ArrayList<PaymentTypes> paymentTypesClass = new ArrayList<PaymentTypes>();
//    public PaymentOtherFragment() {
//        // Required empty public constructor
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_payment_other_dialog, container, false);
////        return inflater.inflate(R.layout.fragment_add_sheet_dialog, container, false);
//    }
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        LinearLayout linear_close = (LinearLayout) view.findViewById(R.id.linear_close);
//        linear_close.setOnClickListener(this);
//        getPaymentsAll();
//        RecyclerView myrv = (RecyclerView) view.findViewById(R.id.payment_type_recyclerview_id);
//        PaymentTypesCheckoutAdapter myAdapter = new PaymentTypesCheckoutAdapter(getContext(),paymentTypesClass,"product",PaymentTypesCheckoutAdapter.bill_type);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4);
//        myrv.setLayoutManager(gridLayoutManager);
//        myrv.setAdapter(myAdapter);
//    }
//
//    private void getPaymentsAll() {
//        Cursor c = Query.GetPaymentInfoAll();
//        if(c!=null){
//            paymentTypesClass.clear();
//            while(c.moveToNext()){
//                paymentTypesClass.add(new PaymentTypes(c.getString(0),c.getString(1),
//                        c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c
//                        .getString(7),c.getString(8)));
//            }
//        }
//        c.close();
//    }
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.linear_close:
//                dismiss();
//                break;
//        }
//    }
//}
//
