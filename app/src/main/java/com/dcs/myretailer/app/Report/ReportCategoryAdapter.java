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

public class ReportCategoryAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> reportCategoryNameArr;
    ArrayList<String> reportCategoryPriceArr;
    ArrayList<String> reportCategoryQtyArr;
    ArrayList<String> reportCategoryBillNoArrVal;
    int flags[];
    LayoutInflater inflter;

    public ReportCategoryAdapter(Context applicationContext, ArrayList<String> reportCategoryNameArr,
                                 ArrayList<String> reportCategoryPriceArr, ArrayList<String> reportCategoryQtyArr,
                                 ArrayList<String> reportCategoryBillNoArrVal) {
        this.context = applicationContext;
        this.reportCategoryNameArr = reportCategoryNameArr;
        this.reportCategoryPriceArr = reportCategoryPriceArr;
        this.reportCategoryQtyArr = reportCategoryQtyArr;
        this.reportCategoryBillNoArrVal = reportCategoryBillNoArrVal;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return reportCategoryNameArr.size();
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
        view = inflter.inflate(R.layout.activity_report_category_listview, null);
        TextView report_product_name = (TextView) view.findViewById(R.id.report_product_name);
        TextView report_product_price = (TextView) view.findViewById(R.id.report_product_price);
        TextView report_product_quantity = (TextView) view.findViewById(R.id.report_product_quantity);
        try {
            if (reportCategoryNameArr.get(i) != null && reportCategoryNameArr.get(i).equals("0")){
                report_product_name.setText("");
            }else {
                report_product_name.setText(reportCategoryNameArr.get(i));
            }

        } catch (IndexOutOfBoundsException e){

        }
        try {
            report_product_quantity.setText(reportCategoryBillNoArrVal.get(i));
        } catch (Exception e) {
            report_product_quantity.setText("");
        }
        try {
            report_product_price.setText("$"+String.format("%.2f", Double.valueOf(String.valueOf(reportCategoryPriceArr.get(i)))));
        } catch (IndexOutOfBoundsException e){

        }
        String device = Query.GetDeviceData(Constraints.DEVICE);
        if (device.equals("M2-Max")) {
            LinearLayout.LayoutParams paramscheckOutScrollView = new LinearLayout.LayoutParams(400,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            report_product_name.setLayoutParams(paramscheckOutScrollView);
            LinearLayout.LayoutParams paramsreport_product_name = new LinearLayout.LayoutParams(350,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            report_product_price.setLayoutParams(paramsreport_product_name);
        }
        return view;
    }
}
