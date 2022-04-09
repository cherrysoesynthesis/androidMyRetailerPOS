package com.dcs.myretailer.app.Setting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dcs.myretailer.app.R;

import java.util.List;

public class PrinterAdapter extends BaseAdapter {
    Context context;
    List<PrinterList> taxes;
    int flags[];
    LayoutInflater inflter;

    public PrinterAdapter(Context context, List<PrinterList> taxes) {
        this.context = context;
        this.taxes = taxes;
        inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return taxes.size();
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
        view = inflter.inflate(R.layout.activity_tax_listview, null);
        TextView tax_name = (TextView) view.findViewById(R.id.tax_name);
        //tax_name.setText(taxes.get(i).getName()+"("+String.format("%.2f", Double.valueOf(String.valueOf(taxes.get(i).getRate())))+"%)");
        tax_name.setText(taxes.get(i).getName());
        return view;
    }
}
