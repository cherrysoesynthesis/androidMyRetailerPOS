package com.dcs.myretailer.app.Report;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dcs.myretailer.app.R;

class ReportOtherAdapter extends BaseAdapter {
    Context context;
    String reportProductNameList[];
    String reportProductPriceList[];
    int flags[];
    LayoutInflater inflter;

    public ReportOtherAdapter(Context applicationContext, String[] reportProductNameList, String[] reportProductPriceList) {
        this.context = context;
        this.reportProductNameList = reportProductNameList;
        this.reportProductPriceList = reportProductPriceList;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return reportProductNameList.length;
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
        view = inflter.inflate(R.layout.activity_report_other_listview, null);
        TextView report_product_name = (TextView) view.findViewById(R.id.report_product_name);
        TextView report_product_price = (TextView) view.findViewById(R.id.report_product_price);
        report_product_name.setText(reportProductNameList[i]);
        report_product_price.setText(reportProductPriceList[i]);
        return view;
    }
}