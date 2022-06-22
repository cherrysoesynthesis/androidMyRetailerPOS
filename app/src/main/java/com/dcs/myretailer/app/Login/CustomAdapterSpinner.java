package com.dcs.myretailer.app.Login;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dcs.myretailer.app.R;

import java.util.ArrayList;

public class CustomAdapterSpinner extends BaseAdapter {
    Context context;
    ArrayList<Integer> pin_id;
    ArrayList<Integer> pin_name;
    ArrayList<Integer> pin_no;
    LayoutInflater inflter;

    public CustomAdapterSpinner(Context context,ArrayList<Integer> pin_id, ArrayList<Integer> pin_name, ArrayList<Integer> pin_no) {
        this.context = context;
        this.pin_id = pin_id;
        this.pin_name = pin_name;
        this.pin_no = pin_no;
        inflter = (LayoutInflater.from(context));
    }
    @Override
    public int getCount() {
        return pin_id.size();
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
        view = inflter.inflate(R.layout.custom_spinner_items, null);
        TextView names = (TextView) view.findViewById(R.id.textView);
        Log.i("fkehrte__",pin_name.get(i).toString());
        names.setText(pin_name.get(i).toString());
        return view;
    }
}