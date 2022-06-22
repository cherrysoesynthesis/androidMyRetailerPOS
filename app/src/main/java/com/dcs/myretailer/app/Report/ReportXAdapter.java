package com.dcs.myretailer.app.Report;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dcs.myretailer.app.ENUM.Constraints;
import com.dcs.myretailer.app.Query.Query;
import com.dcs.myretailer.app.R;

import java.util.ArrayList;

public class ReportXAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> reportProductNameArr;
    ArrayList<String> reportProductPriceArr;
    ArrayList<String> reportProductHeaderArr;
    int flags[];
    LayoutInflater inflter;

    public ReportXAdapter(Context applicationContext, ArrayList<String> reportProductNameArr, ArrayList<String> reportProductPriceArr, ArrayList<String> reportProductHeaderArr) {
        this.context = applicationContext;
        this.reportProductNameArr = reportProductNameArr;
        this.reportProductPriceArr = reportProductPriceArr;
        this.reportProductHeaderArr = reportProductHeaderArr;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return reportProductNameArr.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.activity_report_x_listview, null);
        TextView report_product_name = (TextView) view.findViewById(R.id.report_x_name);
        TextView report_x_price = (TextView) view.findViewById(R.id.report_x_price);
        TextView report_x_header = (TextView) view.findViewById(R.id.report_x_header);

        String terminalTypeVal = Query.GetDeviceData(Constraints.TERMINAL_TYPE);
         if (terminalTypeVal.toUpperCase().equals(Constraints.IMIN)) {
            String device = Query.GetDeviceData(Constraints.DEVICE);
            if (device.equals("M2-Max")) {
                LinearLayout.LayoutParams paramsReporData = new LinearLayout.LayoutParams(380,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                //paramsReporData.leftMargin = 20;
                report_product_name.setLayoutParams(paramsReporData);
            }
        }

        report_x_header.setText(reportProductHeaderArr.get(i));
        report_product_name.setText(reportProductNameArr.get(i));
        report_x_price.setText(reportProductPriceArr.get(i));
        return view;
    }

//    @Override
//    public void notifyDataSetChanged() {
//        Log.i("XFrag__","_____"+"notifyDataSetChanged");
////        ReportXFragment.updateReportFragmentXFun();
//
////        inflter = (LayoutInflater.from(context));
//        super.notifyDataSetChanged();
//    }
//
//    @Override
//    public void notifyDataSetInvalidated() {
//        Log.i("XFrag__1","_____"+"notifyDataSetInvalidated");
//        super.notifyDataSetInvalidated();
//    }
}
