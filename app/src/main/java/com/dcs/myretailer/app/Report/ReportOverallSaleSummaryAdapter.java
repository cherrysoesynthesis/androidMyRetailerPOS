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

class ReportOverallSaleSummaryAdapter extends BaseAdapter {
    Context context;
    String reportOverallSalesSummaryNameList[];
    ArrayList<String> mylist;
    int flags[];
    LayoutInflater inflter;

    public ReportOverallSaleSummaryAdapter(Context context, String[] reportOverallSalesSummaryNameList, ArrayList<String> mylist) {
        this.context = context;
        this.reportOverallSalesSummaryNameList = reportOverallSalesSummaryNameList;
        this.mylist = mylist;
        inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return reportOverallSalesSummaryNameList.length;
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
        view = inflter.inflate(R.layout.activity_report_overall_sales_summary_listview, null);
        TextView report_product_name = (TextView) view.findViewById(R.id.report_product_name);
        TextView report_product_price = (TextView) view.findViewById(R.id.report_product_price);
        report_product_name.setText(reportOverallSalesSummaryNameList[i]);
        if (mylist.size() > 0) {
            try {
                if (mylist.get(i).toString().length() > 0) {

                    report_product_price.setText("$" + String.format("%.2f", Double.valueOf(String.valueOf(mylist.get(i)))));
                }
            }catch (Exception e){
                report_product_price.setText("$0.00");
            }
        }else{
            report_product_price.setText("$0.00");
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
