package com.dcs.myretailer.app.Report;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dcs.myretailer.app.R;

import java.util.ArrayList;

public class ReportOverallTransactionAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> reportOverallTransactionBillNoList;
    ArrayList<String> reportOverallTransactionPriceList;
    ArrayList<String> reportOverallTransactionDateTimeList;
    ArrayList<String> reportOverallTransactionCashierNameList;
    int flags[];
    LayoutInflater inflter;

    public ReportOverallTransactionAdapter(Context applicationContext, ArrayList<String> reportOverallTransactionBillNoList,
                                           ArrayList<String> reportOverallTransactionPriceList, ArrayList<String> reportOverallTransactionDateTimeList,
                                           ArrayList<String>  reportOverallTransactionCashierNameList) {
        this.context = context;
        this.reportOverallTransactionBillNoList = reportOverallTransactionBillNoList;
        this.reportOverallTransactionPriceList = reportOverallTransactionPriceList;
        this.reportOverallTransactionDateTimeList = reportOverallTransactionDateTimeList;
        this.reportOverallTransactionCashierNameList = reportOverallTransactionCashierNameList;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return reportOverallTransactionBillNoList.size();
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
        view = inflter.inflate(R.layout.activity_report_overall_transaction_listview, null);
        TextView report_overall_billno = (TextView) view.findViewById(R.id.report_overall_billno);
        TextView report_overall_price = (TextView) view.findViewById(R.id.report_overall_price);
        TextView report_overall_datetime = (TextView) view.findViewById(R.id.report_overall_datetime);
        TextView report_overall_cashiername = (TextView) view.findViewById(R.id.report_overall_cashiername);
        report_overall_billno.setText("#"+reportOverallTransactionBillNoList.get(i));
        report_overall_price.setText("$"+String.format("%.2f", Double.valueOf(String.valueOf(reportOverallTransactionPriceList.get(i)))));
        report_overall_datetime.setText(reportOverallTransactionDateTimeList.get(i));
        report_overall_cashiername.setText(reportOverallTransactionCashierNameList.get(i));


        return view;
    }
}

