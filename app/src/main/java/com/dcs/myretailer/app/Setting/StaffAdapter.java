package com.dcs.myretailer.app.Setting;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dcs.myretailer.app.R;

import java.util.List;

class StaffAdapter extends BaseAdapter {
    Context context;
    List<StaffClass> staff;
    int flags[];
    LayoutInflater inflter;

    public StaffAdapter(Context context, List<StaffClass> staff) {
        this.context = context;
        this.staff = staff;
        inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return staff.size();
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
        view = inflter.inflate(R.layout.activity_staff_listview, null);
        TextView tax_name = (TextView) view.findViewById(R.id.staff_name);
        //tax_name.setText(taxes.get(i).getName()+"("+String.format("%.2f", Double.valueOf(String.valueOf(taxes.get(i).getRate())))+"%)");
        Log.i("Sdfdsstaff_",staff.get(i).getName());
        tax_name.setText(staff.get(i).getName());
        return view;
    }
}
