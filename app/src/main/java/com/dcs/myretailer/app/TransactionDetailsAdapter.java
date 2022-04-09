package com.dcs.myretailer.app;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TransactionDetailsAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> reportProductNameList;
    ArrayList<String>  reportProductPriceList;
    ArrayList<String>  reportProductQuantityList;
    int flags[];
    LayoutInflater inflter;

    public TransactionDetailsAdapter(Context context, ArrayList<String> reportProductQuantityList, ArrayList<String> reportProductNameList, ArrayList<String> reportProductPriceList) {
        this.context = context;
        this.reportProductNameList = reportProductNameList;
        this.reportProductPriceList = reportProductPriceList;
        this.reportProductQuantityList = reportProductQuantityList;
        inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return reportProductQuantityList.size();
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
        view = inflter.inflate(R.layout.activity_transaction_details_listview, null);
        TextView qty = (TextView) view.findViewById(R.id.qty);
        TextView product_name = (TextView) view.findViewById(R.id.product_name);
        TextView price = (TextView) view.findViewById(R.id.price);
        qty.setText(Html.fromHtml("Qty : <b>" + reportProductQuantityList.get(i) + "</b>"));
        String name = reportProductNameList.get(i);
//        if (name.length() < 1) {
//            name = "TEST";
//        }

        product_name.setText(Html.fromHtml("Product Name : <b>" + name + "</b>"));
        price.setText(Html.fromHtml("Price : <b>$"+String.format("%.2f", Double.valueOf(String.valueOf(reportProductPriceList.get(i)))) + "</b>"));
        //report_product_price.setText(reportProductPriceList.get(i));
        return view;
    }
}
