//package com.dcs.myretailer.app.Setting;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.LinearLayout;
//import android.widget.Switch;
//import android.widget.TextView;
//
//import com.dcs.myretailer.app.ENUM.Constraints;
//import com.dcs.myretailer.app.Query.Query;
//import com.dcs.myretailer.app.R;
//
//import java.util.ArrayList;
//
//public class PaymentTypesAdapter extends BaseAdapter {
//    Context context;
//    ArrayList<String> paymentTypeName;
//    ArrayList<Integer> paymentTypeOnOff;
//    LayoutInflater inflter;
//
//    public PaymentTypesAdapter(Context context, ArrayList<String> paymentTypeName, ArrayList<Integer> paymentTypeOnOff) {
//        this.context = context;
//        this.paymentTypeName = paymentTypeName;
//        this.paymentTypeOnOff = paymentTypeOnOff;
//        inflter = (LayoutInflater.from(context));
//    }
//
//    @Override
//    public int getCount() {
//        return paymentTypeName.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        view = inflter.inflate(R.layout.activity_listview_payment_types, null);
//        TextView country = (TextView) view.findViewById(R.id.textView);
//        Switch paymenttype_onoff = view.findViewById(R.id.paymenttype_onoff);
////        ImageView icon = (ImageView) view.findViewById(R.id.icon);
//        String device = Query.GetDeviceData(Constraints.DEVICE);
//        if (device.equals("M2-Max")) {
//            LinearLayout.LayoutParams paramslayCardView = new LinearLayout.LayoutParams(700,
//                    ViewGroup.LayoutParams.WRAP_CONTENT);
//            country.setLayoutParams(paramslayCardView);
//            LinearLayout.LayoutParams paramspaymenttype_onoff = new LinearLayout.LayoutParams(200,
//                    ViewGroup.LayoutParams.WRAP_CONTENT);
//            paymenttype_onoff.setLayoutParams(paramspaymenttype_onoff);
//        }
//        country.setText(paymentTypeName.get(i));
//        //icon.setImageResource(flags[i]);
//       // android.support.v7.widget.SwitchCompat paymenttype_onoff = view.findViewById(R.id.paymenttype_onoff);
//        if (paymentTypeOnOff.get(i) == 1){
//            paymenttype_onoff.setChecked(true);
//        }else{
//            paymenttype_onoff.setChecked(false);
//        }
//        return view;
//    }
//}
//
//
////        extends BaseAdapter {
////    private final Context context;
////    private final ArrayList<String> paymentTypeName;
////    private final ArrayList<Integer> paymentTypeOnOff;
////    private final ArrayList<Integer> paymentTypeID;
////
////    public PaymentTypesAdapter(Context paymentListActivity, ArrayList<String> paymentTypeName,
////                               ArrayList<Integer> paymentTypeOnOff, ArrayList<Integer> paymentTypeID) {
////        //super(paymentListActivity, -1, paymentTypeName);
////        this.context = paymentListActivity;
////        this.paymentTypeName = paymentTypeName;
////        this.paymentTypeOnOff = paymentTypeOnOff;
////        this.paymentTypeID = paymentTypeID;
////    }
////
////    @Override
////    public int getCount() {
////        return paymentTypeName.size();
////    }
////
////    @Override
////    public Object getItem(int position) {
////        //return null;
////        return paymentTypeName.get(position);
////    }
////
////    @Override
////    public long getItemId(int position) {
////        return 0;
////    }
////
////    @Override
////    public View getView(int position, View convertView, ViewGroup parent) {
////        LayoutInflater inflater = (LayoutInflater) context
////                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
////        View view = inflater.inflate(R.layout.activity_product_types_listview, parent, false);
////        TextView paymenttype_name = view.findViewById(R.id.paymenttype_name);
////        Switch paymenttype_onoff = view.findViewById(R.id.paymenttype_onoff);
////        paymenttype_name.setText(paymentTypeName.get(position));
////        if (paymentTypeOnOff.get(position) == 1){
////            paymenttype_onoff.setChecked(true);
////        }else{
////            paymenttype_onoff.setChecked(false);
////        }
////        return view;
////    }
////}
////
////
//////        extends BaseAdapter {
//////    Context context;
//////    ArrayList<String> paymentTypeName;
//////    ArrayList<Integer> paymentTypeOnOff;
//////    ArrayList<Integer> paymentTypeID;
//////    LayoutInflater inflter;
//////
//////    public PaymentTypesAdapter(Context context, ArrayList<String> paymentTypeName, ArrayList<Integer> paymentTypeOnOff, ArrayList<Integer> paymentTypeID) {
//////        this.context = context;
//////        this.paymentTypeName = paymentTypeName;
//////        this.paymentTypeOnOff = paymentTypeOnOff;
//////        this.paymentTypeID = paymentTypeID;
//////        inflter = (LayoutInflater.from(context));
//////    }
//////
//////    @Override
//////    public int getCount() {
//////        return paymentTypeID.size();
//////    }
//////
//////    @Override
//////    public Object getItem(int i) {
//////        return paymentTypeID.get(i);
//////    }
//////
//////    @Override
//////    public long getItemId(int i) {
//////        return 0;
//////    }
//////
//////    @Override
//////    public View getView(int i, View view, ViewGroup viewGroup) {
//////        view = inflter.inflate(R.layout.activity_product_types_listview, null);
//////        TextView paymenttype_name = view.findViewById(R.id.paymenttype_name);
//////        Switch paymenttype_onoff = view.findViewById(R.id.paymenttype_onoff);
//////        paymenttype_name.setText(paymentTypeName.get(i));
//////        if (paymentTypeOnOff.get(i) == 1){
//////            paymenttype_onoff.setChecked(true);
//////        }else{
//////            paymenttype_onoff.setChecked(false);
//////        }
//////        return view;
//////    }
//////}