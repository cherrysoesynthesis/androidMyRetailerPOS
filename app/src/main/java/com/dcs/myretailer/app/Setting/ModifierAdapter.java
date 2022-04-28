package com.dcs.myretailer.app.Setting;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dcs.myretailer.app.R;

import java.util.ArrayList;
import java.util.List;

public class ModifierAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> modiName;
    ArrayList<String> modiPrice;
    ArrayList<String> modiID;
    int flags[];
    LayoutInflater inflter;

    public ModifierAdapter(Context context, ArrayList<String> modiName, ArrayList<String> modiPrice, ArrayList<String> modiID) {
        this.context = context;
        this.modiName = modiName;
        this.modiPrice = modiPrice;
        this.modiID = modiID;
        inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return modiID.size();
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
        view = inflter.inflate(R.layout.activity_modifier_name_listview, null);
        TextView modifier_name = (TextView) view.findViewById(R.id.modifier_name);
        TextView pmodifier_price = (TextView) view.findViewById(R.id.pmodifier_price);
        modifier_name.setText(modiName.get(i));
        pmodifier_price.setText(modiPrice.get(i));
        return view;
    }
}
